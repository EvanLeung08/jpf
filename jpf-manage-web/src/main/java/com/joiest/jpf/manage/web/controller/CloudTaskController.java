package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import com.joiest.jpf.manage.web.util.CheckBanksUtils;
import com.joiest.jpf.manage.web.util.SmsUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/cloudTask")
public class CloudTaskController {

    @Autowired
    private CloudTaskServiceFacade cloudTaskServiceFacade;

    @Autowired
    private CloudCompanyServiceFacade cloudCompanyServiceFacade;

    @Autowired
    private CloudCompanyMoneyServiceFacade CloudCompanyMoneyServiceFacade;

    @Autowired
    private CloudCompanyStaffServiceFacade cloudCompanyStaffServiceFacade;

    @Autowired
    private CloudStaffBanksServiceFacade cloudStaffBanksServiceFacade;

    @Autowired
    private BankServiceFacade bankServiceFacade;

    @Autowired
    private CloudDfMoneyServiceFacade cloudDfMoneyServiceFacade;

    @Autowired
    private CloudInterfaceStreamServiceFacade cloudInterfaceStreamServiceFacade;

    @Autowired
    private CloudCompanyAgentServiceFacade cloudCompanyAgentServiceFacade;

    @Autowired
    private CloudCompanySalesServiceFacade cloudCompanySalesServiceFacade;

    @Autowired
    private BankCardServiceFacade bankCardServiceFacade;

    @Autowired
    private CloudCompactServiceFacade cloudCompactServiceFacade;

    @Autowired
    private CloudRechargeServiceFacade cloudRechargeServiceFacade;

    @Autowired
    private MerTypeServiceFacade merTypeServiceFacade;

    @Autowired
    private CloudCompactStaffServiceFacade cloudCompactStaffServiceFacade;

    private static final Logger logger = LogManager.getLogger(CloudTaskController.class);

    /**
     * 批量打款页
     */
    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("cloudTask/index");
    }

    /**
     * 任务列表页
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(CloudTaskRequest request){
        CloudTaskResponse response = cloudTaskServiceFacade.getTasks(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return map;
    }

    /**
     * 新建任务列表页
     */
    @RequestMapping("/addTask")
    @ResponseBody
    public ModelAndView addTask(){
        return new ModelAndView("cloudTask/addTask");
    }

    /**
     * 新任务提交操作
     * pay_cloud_company_staff 企业的自由职业者信息表
     * pay_cloud_staff_banks 自由职业者的银行卡信息表
     * pay_cloud_df_money 云账户充值记录表
     */
    @RequestMapping(value = "/submitTask", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String submitTask(String company_id, String company_name, @RequestParam("uploadfile") MultipartFile uploadfile, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception{
        CloudCompanyInfo companyInfo = cloudCompanyServiceFacade.getRecById(company_id);

        // 判断excel数据正确完整性
        // 获取当前的文件名
        String fileNameAll = uploadfile.getOriginalFilename();
        String fileName=fileNameAll.substring(0,fileNameAll.lastIndexOf("."));

        // 解析xml
        ExcelDealUtils excelDealUtils = new ExcelDealUtils();

        // 循环判断每一条数据
        Map<Object,Object> map = excelDealUtils.getImportExcel(uploadfile.getInputStream(), uploadfile.getOriginalFilename());
        List<Object> list = new ArrayList<>(map.values());

        // 组装自由职业者信息数组
        List<CloudRemitExcelInfo> staffInfosSuccess = new ArrayList<>();
        List<CloudRemitExcelInfo> staffInfosFailed = new ArrayList<>();
        // 检查每条自由职业者信息的完整性
        UUID uuid = UUID.randomUUID();
        String Batchno = "";
        String companyMoney_Str;
        double companyMoney = 0;
        double companyMoneySame = 0;
        int count=0;
        int samecount=0;
        String contractNo = "";
        Map<String,Object> responseMap = new HashMap<>();
        // 最大支持N条数据
        int maxDfAmount = Integer.parseInt(ConfigUtil.getValue("MAX_DF_AMOUNT"));
        maxDfAmount = maxDfAmount + 4;  // 加上表头的四行
        if ( list.size() > maxDfAmount){
            // 已超过最大打款数量限制
            return "-5";
        }
        if ( list.size() < 5 ){
            responseMap.put("code","10004");
            responseMap.put("info","批次号与文件名不一致请修改后上传！");
            responseMap.put("data",staffInfosFailed);
        }
        String failReason = "";
        for ( int i=0; i<list.size(); i++){
            // 外循环为一行excel记录
            if(i >= 4){
                CloudRemitExcelInfo staffInfo = new CloudRemitExcelInfo();
                Map<Integer,String> singlePerson = (Map<Integer,String>)list.get(i);
                // 内循环为该记录的列值
                byte flag = 1;
                // 检查列数据的正确性
                for ( int j=0; j<singlePerson.size(); j++){
                    // 第一列类型必须是0或者1
                    if (  singlePerson.get(0) != null && !singlePerson.get(0).equals("0") && !singlePerson.get(0).equals("1") ){
                        flag = 0;
                        failReason = "第一列类型填写错误";
                    }else if ( j == 8 ){
                        // 检查打款金额是否超过了限额
                        Double monthMoneyLimit = Double.parseDouble(ConfigUtil.getValue("DFAPI_MAX"));
                        Double singlePersonDfMoney = Double.parseDouble(singlePerson.get(j));
                        if ( singlePersonDfMoney > monthMoneyLimit ){
                            flag = 0;
                            failReason = "打款金额超过了每月限额";
                        }
                    }else{
                        // 前9列数据必填
                        if ( StringUtils.isBlank(singlePerson.get(j)) ){
                            flag = 0;
                            failReason = "请确保打款信息完整";
                        }
                    }
                }
                // 如果行数据全为空
                if ( singlePerson.size() == 0 ){
                    continue;
                }else{
                    if ( StringUtils.isNotBlank(singlePerson.get(0)) ){
                        staffInfo.setType(Byte.parseByte(singlePerson.get(0)));
                    }
                    if ( StringUtils.isNotBlank(singlePerson.get(1)) ){
                        staffInfo.setBankName(singlePerson.get(1));
                    }
                    if ( StringUtils.isNotBlank(singlePerson.get(2)) ){
                        staffInfo.setProvince(singlePerson.get(2));
                    }
                    if ( StringUtils.isNotBlank(singlePerson.get(3)) ){
                        staffInfo.setCity(singlePerson.get(3));
                    }
                    if ( StringUtils.isNotBlank(singlePerson.get(4)) ){
                        staffInfo.setBankNo(StringUtils.trim(singlePerson.get(4)));
                    }
                    if ( StringUtils.isNotBlank(singlePerson.get(5)) ){
                        staffInfo.setName(singlePerson.get(5));
                    }
                    if ( StringUtils.isNotBlank(singlePerson.get(6)) ){
                        staffInfo.setIDNo(StringUtils.trim(singlePerson.get(6)));
                    }
                    if ( StringUtils.isNotBlank(singlePerson.get(7)) ){
                        staffInfo.setPhone(StringUtils.trim(singlePerson.get(7)));
                    }
                    if ( StringUtils.isNotBlank(singlePerson.get(8)) ){
                        staffInfo.setMoney(new BigDecimal(singlePerson.get(8)));
                    }
                    staffInfo.setMemo(singlePerson.get(9));
                }
                if ( flag == 0 ){
                    staffInfosFailed.add(staffInfo);
                }else{
                    staffInfosSuccess.add(staffInfo);
                }
                //总数
                count++;
                //总钱数==获取decimal方式
                if ( StringUtils.isNotBlank(singlePerson.get(8)) ){
                    companyMoney_Str = new DecimalFormat("#.00").format(Double.parseDouble(singlePerson.get(8))) ;
                    companyMoney = BigDecimalCalculateUtils.add(companyMoney,Double.parseDouble(companyMoney_Str));
                }
            }else{
                Map<Integer,String> singlePerson = (Map<Integer,String>)list.get(i);
                for (int j=0; j<singlePerson.size()-1; j++){
                    if(i==2){
                        Batchno=singlePerson.get(0);
                        samecount=Integer.parseInt(singlePerson.get(1));
                        companyMoney_Str = new DecimalFormat("#.00").format(Double.parseDouble(singlePerson.get(2))) ;
                        companyMoneySame=Double.parseDouble(companyMoney_Str);
                        contractNo = singlePerson.get(3);

                        // 判断合同编号的合法性
                        if ( j==3 ){
                            // 判断企业充值表中存不存在此合同编号
                            if ( StringUtils.isBlank(contractNo) ){
                                // 合同编号为空，请检查
                                return "-1";
                            }
                            CloudRechargeInfo cloudRechargeInfo = cloudRechargeServiceFacade.getRecByPactno(contractNo,(byte)0);
                            if ( cloudRechargeInfo.getMerchNo() == null ){
                                return "-4";
                            }
                            if ( !cloudRechargeInfo.getMerchNo().equals(companyInfo.getMerchNo()) ){
                                // 充值表中不存在此合同编号
                                return "-2";
                            }
                        }
                   }
                }
            }
        }
        // 把excel表的金额统计保留两位小数
        BigDecimal companyMoneyBigDecimal = new BigDecimal(companyMoney);
        companyMoneyBigDecimal = companyMoneyBigDecimal.setScale(2,RoundingMode.HALF_UP);
        if(!fileName.equals(Batchno)){
            responseMap.put("code","10004");
            responseMap.put("info","批次号与文件名不一致请修改后上传！");
            responseMap.put("data",staffInfosFailed);
            LogsCustomUtils2.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else if(count !=samecount){
            responseMap.put("code","10004");
            responseMap.put("info","总笔数与实际笔数不符请修改后上传！");
            responseMap.put("data",staffInfosFailed);
            LogsCustomUtils2.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else if(companyMoney!=companyMoneySame){
            responseMap.put("code","10004");
            responseMap.put("info","总金额与实际金额不符请修改后上传！");
            responseMap.put("data",staffInfosFailed);
            LogsCustomUtils2.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else if ( staffInfosFailed.size() > 0 ){
            responseMap.put("code","10004");
            responseMap.put("info","表格存在以下错误数据，原因："+failReason);
            responseMap.put("data",staffInfosFailed);
            LogsCustomUtils2.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }
        /* 上传任务时取消金额验证，改为打款前验证
        else if ( companyInfo.getCloudmoney().compareTo(companyMoneyBigDecimal) == -1 ){
            // "该企业账户余额不足，剩余："+companyInfo.getCloudmoney()
            return "-3";
        }
        */
        else{
            // OSS上传excel文件
            String savePre = ConfigUtil.getValue("EXCEL_PATH");
            String path = PhotoUtil.saveFile(uploadfile, savePre);
            Map<String,Object> requestMap = new HashMap<>();
            requestMap.put("path",path);
            String url = ConfigUtil.getValue("CLOUD_API_URL")+"/oss/upload";
            String response = OkHttpUtils.postForm(url,requestMap);

            // 增加==OSS接口流水==
            CloudInterfaceStreamInfo cloudInterfaceStreamInfo = new CloudInterfaceStreamInfo();
            cloudInterfaceStreamInfo.setType((byte)0);
            cloudInterfaceStreamInfo.setRequestUrl(url);
            cloudInterfaceStreamInfo.setRequestContent(path);
            cloudInterfaceStreamInfo.setResponseContent(response);
            cloudInterfaceStreamInfo.setAddtime(new Date());
            cloudInterfaceStreamInfo.setBatchNo(Batchno);
            cloudInterfaceStreamServiceFacade.insRecord(cloudInterfaceStreamInfo);

            // 写入缓存文件
            responseMap.put("code","10000");
            responseMap.put("info","数据检测无误");
            responseMap.put("batchNo",Batchno);
            responseMap.put("money",""+companyMoney);
            responseMap.put("persons",""+count);
            responseMap.put("contractNo",contractNo);
            responseMap.put("data",staffInfosSuccess);
            responseMap.put("ossUrl",response);
            LogsCustomUtils2.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);

            return uuid.toString();
        }
    }

    /**
     * 查询公司页
     */
    @RequestMapping("/companys")
    public ModelAndView companys(){
        return new ModelAndView("cloudTask/companys");
    }

    /**
     * 下载模板
     */
    @RequestMapping(value="/download")
    public ResponseEntity<byte[]> download()throws Exception {
        //下载文件路径
        String filename=ConfigUtil.getValue("EXCEL_NAME");
        String path=ConfigUtil.getValue("EXCEL_PATH");
        String filename2 = null;
        try {
            //解决找到文件问题:
            // URLEonder把中文用UTF-8编码,然而,tomcat用iso-8859-1解码
            //,我们需要用iso-8859-1编码,再重新用utf-8解码才能匹配到硬盘文件的名字
            filename2 = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        File file = new File(path + filename2);
        HttpHeaders headers = new HttpHeaders();


        String filename3 = null;
        try {
            //解决提示下载框的中文问题:
            // 所有浏览器都会使用本地编码，即中文操作系统使用GBK
            // 浏览器收到这个文件名后，会使用iso-8859-1来解码
            //编码流程:把中文用GBK编码为字节数组,再用ISO-8859-1编码,浏览器先用ISO-8859-1解码为字节数组,在用GBK解码为中文
            filename3 = new String(filename2.getBytes("GBK"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        headers.setContentDispositionFormData("attachment", filename3);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 列出解析成功或失败的自由职业者信息
     */
    @RequestMapping("/persons")
    @ResponseBody
    public ModelAndView persons(String data,ModelMap modelMap){
        String up=data;
        up = StringUtils.strip(up,"\"");
        up = StringUtils.stripEnd(up,"\"");
        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("CACHE_PATH")+up+".txt","UTF-8");
        Map<String,String> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
        String code=jsonMap.get("code");
        String info=jsonMap.get("info");
        Map<String,List< LinkedHashMap<String,String> >> jsonMaps = JsonUtils.toObject(fileContent,HashMap.class);
        List< LinkedHashMap<String,String> > list = jsonMaps.get("data");
        modelMap.addAttribute("data",data);
        modelMap.addAttribute("code",code);
        modelMap.addAttribute("info",info);
        modelMap.addAttribute("total",list.size());
        return new ModelAndView("cloudTask/persons",modelMap);

    }

    /**
     * 列出解析成功或失败的自由职业者信息的具体读取数据操作
     */
    @RequestMapping(value = "/personsData", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String, Object> personsData(String data){
        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("CACHE_PATH")+data+".txt","UTF-8");
        Map<String,List< LinkedHashMap<String,String> >> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
        List< LinkedHashMap<String,String> > list = jsonMap.get("data");

        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("total",list.size());
        responseMap.put("rows",jsonMap.get("data"));

        return responseMap;
    }

    /**
     * 运营确认excel文件内容无误后点击确认的操作
     */
    @RequestMapping(value = "/confirmPersons", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public JpfResponseDto confirmPersons(String companyId, String data, HttpServletRequest httpRequest) throws Exception {

        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("CACHE_PATH")+data+".txt","UTF-8");
        Map<String,String> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
        String batchNo = jsonMap.get("batchNo");
        String persons = ""+jsonMap.get("persons");
        String money = jsonMap.get("money");
        String contractNo = jsonMap.get("contractNo");

        // 判断任务记录中批次号的唯一性
        CloudTaskInfo existTask = cloudTaskServiceFacade.getOneTaskByBatchNo(batchNo);
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        if ( existTask.getId() != null ){
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("该批次号已经存在，请不要重复上传");

            return jpfResponseDto;
        }

        // 查询商户信息
        CloudCompanyInfo companyInfo = cloudCompanyServiceFacade.getRecById(companyId);

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        // 新建任务记录
        String agentNo;
        /*if ( httpRequest.getRequestURI().indexOf("xinxiangfuwu.com") > 0 ){
            agentNo = ConfigUtil.getValue("XINXIANG_AGENT_NO");
        }else{
            agentNo = ConfigUtil.getValue("ZHENXIANG_AGENT_NO");
        }*/
        agentNo = ConfigUtil.getValue("XINXIANG_AGENT_NO");
        // 查找代理商户的商户名称
        CloudCompanyInfo cloudCompanyInfo = cloudCompanyServiceFacade.getRecByMerchNo(agentNo);
        CloudTaskInfo cloudTaskInfo = new CloudTaskInfo();
        cloudTaskInfo.setOpratorId(""+userInfo.getId());
        cloudTaskInfo.setOpratorName(userInfo.getUserName());
        cloudTaskInfo.setCompanyId(companyInfo.getId());
        cloudTaskInfo.setCompanyName(companyInfo.getName());
        cloudTaskInfo.setAgentNo(agentNo);
        cloudTaskInfo.setAgentName(cloudCompanyInfo.getName());
        cloudTaskInfo.setMerchNo(companyInfo.getMerchNo());
        cloudTaskInfo.setCompanyType(companyInfo.getType());
        cloudTaskInfo.setBatchno(batchNo);
        cloudTaskInfo.setPersons(Integer.parseInt(persons));    // 人数总计
        cloudTaskInfo.setMoney(new BigDecimal(money));
        cloudTaskInfo.setContractNo(contractNo);
        cloudTaskInfo.setFilePath(ConfigUtil.getValue("CACHE_PATH")+data+".txt");   // 待修改
        cloudTaskInfo.setStatus((byte)1);   // 任务状态 0=未处理 1=处理中 2=完成 3=失败
        cloudTaskInfo.setIsLock((byte)0);   // 任务锁定 0=未锁 1=锁定
        cloudTaskInfo.setCreated(new Date());
        String taskRes = cloudTaskServiceFacade.insTask(cloudTaskInfo);
        if ( Integer.parseInt(taskRes) <= 0 ){
            jpfResponseDto.setRetCode("10002");
            jpfResponseDto.setRetMsg("新建任务失败");

            return jpfResponseDto;
        }

        // 新建一个待锁定的代付款批次订单
        CloudCompanyMoneyInfo cloudCompanyMoneyInfo = new CloudCompanyMoneyInfo();
        String companyMoneyId;
        cloudCompanyMoneyInfo.setAgentNo(agentNo);
        cloudCompanyMoneyInfo.setMerchNo(companyInfo.getMerchNo());
        cloudCompanyMoneyInfo.setCommoney(new BigDecimal(jsonMap.get("money")));
        cloudCompanyMoneyInfo.setAddtime(new Date());
        cloudCompanyMoneyInfo.setUid(""+companyInfo.getId());
        cloudCompanyMoneyInfo.setFid(contractNo);   // 合同编号
        cloudCompanyMoneyInfo.setVid((byte)8);
        cloudCompanyMoneyInfo.setIntro("");
        cloudCompanyMoneyInfo.setMontype((byte)0);      // 发放状态 -1删除 0=待锁定 1=待付款 2=处理完成,3=处理完成(部分失败),4=处理失败
        cloudCompanyMoneyInfo.setBatchstatus((byte)0);  // 打款状态 0=未处理 1=部分失败 2=全部失败 3=全部成功
        cloudCompanyMoneyInfo.setBatchno(batchNo);                          // 用户批次号
        cloudCompanyMoneyInfo.setBatchitems(persons);                       // 批次总笔数
        cloudCompanyMoneyInfo.setBatchallmoney(new BigDecimal(money));      // 批次总金额
        cloudCompanyMoneyInfo.setBatchdealitems("0");                        // 批次成功笔数
        cloudCompanyMoneyInfo.setBatchdealmoney(new BigDecimal("0"));   // 批次成功总金额
        cloudCompanyMoneyInfo.setBatchfailitems("0");                        // 批次失败笔数
        cloudCompanyMoneyInfo.setBatchfailmoney(new BigDecimal("0"));   // 批次失败总金额
        // 查询代理商户和业务商户的费率，计算总费率
        CloudCompanyAgentInfo agentInfo = cloudCompanyAgentServiceFacade.getAgentByAgentNo(agentNo);
        CloudCompanySalesInfo salesInfo = cloudCompanySalesServiceFacade.getSalesBySalesNo(companyInfo.getMerchNo());
        Double totalRate = Double.parseDouble(agentInfo.getAgentRate().toString()) + Double.parseDouble(salesInfo.getSalesRate().toString());
        Double moneyDouble = new Double(money);
        Double feeMoney = totalRate * moneyDouble;
        cloudCompanyMoneyInfo.setFeeRate(new BigDecimal(totalRate));
        cloudCompanyMoneyInfo.setFeemoney(new BigDecimal(feeMoney).setScale(2,BigDecimal.ROUND_DOWN));   // 服务费金额：实发金额*服务费率
        // 增值税金额
        Double addedValueTax = new Double(ConfigUtil.getValue("ADDED_VALUE_TAX"));
        Double taxMoney = ( moneyDouble + feeMoney ) / ( 1 + addedValueTax ) * addedValueTax;
        cloudCompanyMoneyInfo.setTaxRate(new BigDecimal(addedValueTax));
        cloudCompanyMoneyInfo.setTaxmoney(new BigDecimal(taxMoney).setScale(2,BigDecimal.ROUND_DOWN));
        // 增值税附加金额
        Double addedValueTaxAddtion = new Double(ConfigUtil.getValue("ADDED_VALUE_TAX_ADDITION"));
        Double addedValueTaxAddtionMoney = taxMoney*addedValueTaxAddtion;
        cloudCompanyMoneyInfo.setTaxmoreTax(new BigDecimal(addedValueTaxAddtion));
        cloudCompanyMoneyInfo.setTaxmoremoney(new BigDecimal(addedValueTaxAddtionMoney).setScale(2,BigDecimal.ROUND_DOWN));
        // 毛利金额
        Double individualTax = new Double(ConfigUtil.getValue("INDIVIDUAL_TAX"));
        Double yinhuaTax = new Double(ConfigUtil.getValue("YINHUA_TAX"));
        BigDecimal shouldMoney = new BigDecimal(moneyDouble / ( 1-individualTax-yinhuaTax)).setScale(2,BigDecimal.ROUND_DOWN); // 应发金额
        Double profit = (moneyDouble + feeMoney) - (shouldMoney.doubleValue() + taxMoney + addedValueTaxAddtionMoney);
        cloudCompanyMoneyInfo.setProfitmoney(new BigDecimal(profit).setScale(2,BigDecimal.ROUND_DOWN));      // 毛利金额
        cloudCompanyMoneyInfo.setShouldMoney(shouldMoney);
        cloudCompanyMoneyInfo.setIndividualTax(new BigDecimal(individualTax));
        cloudCompanyMoneyInfo.setIndividualMoney(new BigDecimal(shouldMoney.doubleValue()*individualTax));
        cloudCompanyMoneyInfo.setYinhuaTax(new BigDecimal(yinhuaTax));
        cloudCompanyMoneyInfo.setYinhuaMoney(new BigDecimal(shouldMoney.doubleValue()*yinhuaTax).setScale(2,BigDecimal.ROUND_DOWN));
        // 判断有没有已经存在的合同编号
        /*CloudCompanyMoneyInfo existCompanyMoneyInfo = CloudCompanyMoneyServiceFacade.getRecByFid(contractNo);
        if ( existCompanyMoneyInfo.getId() != null ){
            jpfResponseDto.setRetCode("10003");
            jpfResponseDto.setRetMsg("该合同编号已经存在，请修改后上传");

            return jpfResponseDto;
        }*/
        int companyMoneyRes = CloudCompanyMoneyServiceFacade.addRec(cloudCompanyMoneyInfo);
        if ( companyMoneyRes <= 0 ){
            jpfResponseDto.setRetCode("10004");
            jpfResponseDto.setRetMsg("新建批次订单失败");

            return jpfResponseDto;
        }else{
            CloudCompanyMoneyInfo cloudCompanyMoneyInfo1 = CloudCompanyMoneyServiceFacade.getRecByBatchNo(batchNo);
            companyMoneyId = cloudCompanyMoneyInfo1.getId();
        }

        // 鉴权信息数据入库
        Map<String,List< LinkedHashMap<String,String> >> jsonMapData = JsonUtils.toObject(fileContent,HashMap.class);
        List< LinkedHashMap<String,String> > personsList = jsonMapData.get("data");
        CloudCompanyStaffInfo cloudCompanyStaffInfo = new CloudCompanyStaffInfo();

        for ( LinkedHashMap<String,String> singlePerson:personsList ){
            // 通过身份证号和手机号先判断企业有没有这个员工
            CloudCompanyStaffInfo info = new CloudCompanyStaffInfo();
            info.setNickname(singlePerson.get("name"));
            info.setIdcard(singlePerson.get("idno").toUpperCase()); //身份证号默认大写
            info.setMobile(singlePerson.get("phone"));
            info.setMerchNo(singlePerson.get("bankNo"));
            info.setStatus((byte)1);
            CloudCompanyStaffInfo existStaff = cloudCompanyStaffServiceFacade.getOneStaff(info);
            // CloudCompanyStaffInfo existStaff = cloudCompanyStaffServiceFacade.getStaffByInfo(info);
            int staffId;
            if ( existStaff.getId() == null ){
                // 插入员工信息
                cloudCompanyStaffInfo.setUid(""+userInfo.getId());
                cloudCompanyStaffInfo.setNickname(singlePerson.get("name"));
                cloudCompanyStaffInfo.setMobile(singlePerson.get("phone"));
                cloudCompanyStaffInfo.setMerchNo(companyInfo.getMerchNo());
                cloudCompanyStaffInfo.setStatus((byte)1);
                cloudCompanyStaffInfo.setIsActive((byte)0);
                cloudCompanyStaffInfo.setEmail("");
                cloudCompanyStaffInfo.setCode("");
                cloudCompanyStaffInfo.setIdcard(singlePerson.get("idno").toUpperCase()); //身份证号默认大写
                cloudCompanyStaffInfo.setUcardid(""+0);
                cloudCompanyStaffInfo.setCreated(new Date());
                cloudCompanyStaffInfo.setUpdated(new Date());
                staffId = Integer.parseInt(cloudCompanyStaffServiceFacade.addStaff(cloudCompanyStaffInfo));
                if ( staffId > 0 ){
                    logger.info(singlePerson.get("name")+"插入员工信息表成功");
                }
            }else{
                logger.info(singlePerson.get("name")+"员工已经存在，无需再插入");
                staffId = Integer.parseInt(existStaff.getId());
            }

            // 通过银行卡号、手机号和员工id先判断这个员工是否添加过这个银行卡
            CloudStaffBanksInfo searchBank = new CloudStaffBanksInfo();
            searchBank.setStaffid(Long.parseLong(""+staffId));
            searchBank.setBankno(singlePerson.get("bankNo"));
            searchBank.setBankphone(singlePerson.get("phone"));
            CloudStaffBanksInfo existBank = cloudStaffBanksServiceFacade.getStaffBankByInfo(searchBank);
            if ( existBank.getId() == null ){
                // 插入员工银行卡信息
                CloudStaffBanksInfo cloudStaffBanksInfo = new CloudStaffBanksInfo();
                cloudStaffBanksInfo.setStaffid(Long.parseLong(""+staffId));
                BankInfo bankInfo = bankServiceFacade.getBankByName(singlePerson.get("bankName"));
                cloudStaffBanksInfo.setBankid(bankInfo.getId());
                cloudStaffBanksInfo.setBankno(singlePerson.get("bankNo"));
                cloudStaffBanksInfo.setBanknickname(singlePerson.get("name"));
                cloudStaffBanksInfo.setBankphone(singlePerson.get("phone"));
                cloudStaffBanksInfo.setBankname(singlePerson.get("bankName"));
                cloudStaffBanksInfo.setBankprovince(singlePerson.get("province"));
                cloudStaffBanksInfo.setBankcity(singlePerson.get("city"));
                cloudStaffBanksInfo.setBankActive("0");
                cloudStaffBanksInfo.setBankacctattr(String.valueOf(singlePerson.get("type")));
                int staffBanksRes = cloudStaffBanksServiceFacade.addStaffBank(cloudStaffBanksInfo);
                if ( staffBanksRes > 0 ){
                    logger.info(singlePerson.get("name")+"插入员工银行表成功");
                }
            }else{
                logger.info(singlePerson.get("name")+"的银行卡信息已经存在，无需再插入");
            }

            // 根据卡号查询银行编码
            BankCardInfo bankCardInfo = bankCardServiceFacade.getBankCardByCardNO(singlePerson.get("bankNo"));
            if ( bankCardInfo.getCode() == null ){
                logger.info(singlePerson.get("name")+"的银行卡号是"+singlePerson.get("bankNo")+"，未查找到银行编码");
            }

            // 按人员生成代付记录
            CloudDfMoneyInfo cloudDfMoneyInfo = new CloudDfMoneyInfo();
            cloudDfMoneyInfo.setAgentNo(agentNo);
            cloudDfMoneyInfo.setMerchNo(companyInfo.getMerchNo());
            cloudDfMoneyInfo.setUid(Long.parseLong(""+userInfo.getId()));
            cloudDfMoneyInfo.setFid(companyInfo.getId());   // 企业id
            cloudDfMoneyInfo.setBusstaffid(Long.parseLong(""+staffId));
            cloudDfMoneyInfo.setUsername(singlePerson.get("phone"));
            // 7月31日新增 实发金额减去个人所得税 start
            BigDecimal preMoney = new BigDecimal(String.valueOf(singlePerson.get("money")));    // 预发放金额，即excel表上填的金额
//            Double preMoneyDouble = new Double(String.valueOf(singlePerson.get("money")));
            Double tax = new Double(ConfigUtil.getValue("INDIVIDUAL_TAX"));     // 个人所得税税点
//            Double commoneyDouble = preMoneyDouble * ( 1 - tax );       // 计算实发金额
//            BigDecimal commoney = new BigDecimal(ToolUtils.halfUpDouble(commoneyDouble, 2));    // 实发金额四舍五入
            // 7月31日新增 实发金额减去个人所得税 end
            cloudDfMoneyInfo.setPreMoney(preMoney);
            cloudDfMoneyInfo.setIncomeRate(BigDecimal.valueOf(tax));
            cloudDfMoneyInfo.setCommoney(preMoney);
            cloudDfMoneyInfo.setBankno(singlePerson.get("bankNo"));
            cloudDfMoneyInfo.setBanknickname(singlePerson.get("name"));
            cloudDfMoneyInfo.setIdno(singlePerson.get("idno").toUpperCase()); //身份证号默认大写
            cloudDfMoneyInfo.setBankphone(singlePerson.get("phone"));
            cloudDfMoneyInfo.setBankname(singlePerson.get("bankName"));     // 开户行
            cloudDfMoneyInfo.setBankprovince(singlePerson.get("province"));
            cloudDfMoneyInfo.setBankcity(singlePerson.get("city"));
            cloudDfMoneyInfo.setBanktype(singlePerson.get("bankName"));         // 卡类型 例如：建行 工商
            cloudDfMoneyInfo.setBankcode(bankCardInfo.getFindcode());
            cloudDfMoneyInfo.setBankacctattr(Integer.parseInt(String.valueOf(singlePerson.get("type"))));
            cloudDfMoneyInfo.setAddtime(new Date());
            cloudDfMoneyInfo.setRealname(singlePerson.get("name"));
            cloudDfMoneyInfo.setMontype(0); //未申请
            cloudDfMoneyInfo.setRemark(singlePerson.get("memo"));
            cloudDfMoneyInfo.setVid(1);      // 待修改
            cloudDfMoneyInfo.setIsActive(0);
            cloudDfMoneyInfo.setContent("");    // 待修改
            cloudDfMoneyInfo.setOperastate(0);  // 待修改
            cloudDfMoneyInfo.setTranno("");
            cloudDfMoneyInfo.setOrderid("");
            cloudDfMoneyInfo.setOrderids("");
            // 计算应发金额（税前）
            Double num1 = new Double(String.valueOf(singlePerson.get("money")));
            Double num2 = new Double("1")-tax;
            Double beforeTaxMoney = num1 / num2;
            cloudDfMoneyInfo.setPayablemoney(new BigDecimal(beforeTaxMoney));
            Double individualTaxMoney = beforeTaxMoney * tax;
            cloudDfMoneyInfo.setWithholdmoney(new BigDecimal(individualTaxMoney));
            cloudDfMoneyInfo.setInvostatus(1);
            cloudDfMoneyInfo.setCompanyMoneyId(companyMoneyId);
            cloudDfMoneyInfo.setPactno(contractNo);
            long dfMoneyRes = cloudDfMoneyServiceFacade.addDfMoney(cloudDfMoneyInfo);
            if ( dfMoneyRes > 0 ){
                logger.info(singlePerson.get("name")+"插入员工打款记录表成功");
            }else{
                logger.info(singlePerson.get("name")+"插入员工打款记录表失败");
            }

            // 新增个人合同记录
            CloudCompactStaffInfo cloudCompactStaffInfo = new CloudCompactStaffInfo();
            String compactNoPre;
            // 个人合同编号前缀
            if ( httpRequest.getRequestURI().indexOf("xinxiangfuwu.com") > 0 ){
                compactNoPre = "XX";
            }else{
                compactNoPre = "ZX";
            }
            // 企业号后6位
            String last6 = StringUtils.substring(companyInfo.getMerchNo(),companyInfo.getMerchNo().length()-6,-1);
            String compactNo = compactNoPre + last6 + System.currentTimeMillis() + ToolUtils.getRandomInt(100000000,999999999);
            cloudCompactStaffInfo.setCompactNo(compactNo);
            cloudCompactStaffInfo.setStaffid(Long.parseLong(""+staffId));
            cloudCompactStaffInfo.setDfid(dfMoneyRes);
            cloudCompactStaffInfo.setPactno(contractNo);
            // 查询合同内容
            CloudCompactInfo cloudCompactInfo = cloudCompactServiceFacade.getRecByType((byte)1);
            cloudCompactStaffInfo.setContent(cloudCompactInfo.getContent());
            cloudCompactStaffInfo.setCompactActive((byte)0);
            cloudCompactStaffInfo.setCompactid(Long.parseLong(cloudCompactInfo.getId()));
            // 获取充值信息中需求字段相关内容
            CloudRechargeRequest cloudRechargeRequest = new CloudRechargeRequest();
            cloudRechargeRequest.setPactno(contractNo);
            CloudRechargeResponse cloudRechargeResponse = cloudRechargeServiceFacade.getRecords(cloudRechargeRequest);
            CloudRechargeInfo cloudRechargeInfo = cloudRechargeResponse.getList().get(0);
            String needPathes[] = cloudRechargeInfo.getNeedcatpath().split(",");
            List<MerchantTypeInfo> typesList = merTypeServiceFacade.getTypesByCatpath(needPathes[0]);
            cloudCompactStaffInfo.setTicketid(typesList.get(1).getCatid());
            cloudCompactStaffInfo.setTicketcontent(typesList.get(1).getCat());
            cloudCompactStaffInfo.setEntryid(""+typesList.get(2).getCatid());
            cloudCompactStaffInfo.setEntryname(typesList.get(2).getCat());
            cloudCompactStaffInfo.setCreated(new Date());
            int compactStaffRes = cloudCompactStaffServiceFacade.insRecord(cloudCompactStaffInfo);
            if ( compactStaffRes > 0 ){
                logger.info(singlePerson.get("name")+"个人合同记录插入成功");
            }else{
                logger.info(singlePerson.get("name")+"个人合同记录插入失败");
            }

            // 鉴权功能 非线程
            /*CheckBanksRequest checkBanksRequest = new CheckBanksRequest();
            checkBanksRequest.setAccountNo(singlePerson.get("bankNo"));
            checkBanksRequest.setIdCard(singlePerson.get("idno").toUpperCase());
            checkBanksRequest.setMobile(singlePerson.get("phone"));
            checkBanksRequest.setName(singlePerson.get("name"));
            checkBanksRequest.setDateTime(new Date());
            checkBanks(checkBanksRequest);*/
        }

        // 鉴权功能 线程
        Thread thread = new Thread(new CheckBanksUtils(data,taskRes));
        thread.start();

        return new JpfResponseDto();
    }

    /**
     * 任务详情页
     */
    @RequestMapping("/taskDetail")
    public ModelAndView taskDetail(String taskId, ModelMap modelMap){
        modelMap.addAttribute("taskId",taskId);

        return new ModelAndView("cloudTask/detail",modelMap);
    }

    /**
     * 任务详情页中的鉴权数据
     */
    @RequestMapping("/banksData")
    @ResponseBody
    public Map<String,Object> banksData(String taskId){
        // 获取任务详情
        CloudTaskInfo cloudTaskInfo = cloudTaskServiceFacade.getOneTask(taskId);
        String filePath = cloudTaskInfo.getFilePath();
        String fileContent = ToolUtils.readFromFile(filePath,"UTF-8");
        Map<String,List< LinkedHashMap<String,String> >> jsonMapData = JsonUtils.toObject(fileContent,HashMap.class);
        List< LinkedHashMap<String,String> > personsList = jsonMapData.get("data");

        List<CloudStaffBanksInfo> list = new ArrayList<>();
        for ( LinkedHashMap<String,String> singlePerson:personsList ){
            CloudStaffBanksInfo searchInfo = new CloudStaffBanksInfo();
            searchInfo.setBankno(singlePerson.get("bankNo"));
            searchInfo.setBankphone(singlePerson.get("phone"));
            CloudStaffBanksInfo cloudStaffBanksInfo = cloudStaffBanksServiceFacade.getStaffBankByInfo(searchInfo);
            list.add(cloudStaffBanksInfo);
        }

        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("total",list.size());
        responseMap.put("rows",list);

        return responseMap;
    }

    /**
     * 任务处理完成后点击锁定
     */
    @RequestMapping("/lockOrder")
    @ResponseBody
    public JpfResponseDto lockOrder(String taskId){
        // 获取任务信息
        CloudTaskInfo cloudTaskInfo = cloudTaskServiceFacade.getOneTask(taskId);

        // 判断此任务是否已经锁定
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        if ( cloudTaskInfo.getIsLock() == 1 ){
            jpfResponseDto.setRetCode("0001");
            jpfResponseDto.setRetMsg("该任务已经锁定，请不要重复锁定");

            return jpfResponseDto;
        }

        // 任务鉴权处理完成才能锁定
        if ( cloudTaskInfo.getStatus() != 3 ){
            jpfResponseDto.setRetCode("0002");
            jpfResponseDto.setRetMsg("该任务所有的鉴权都通过后才能锁定");

            return jpfResponseDto;
        }

        // 获取批次订单信息
        CloudCompanyMoneyInfo cloudCompanyMoneyInfo = CloudCompanyMoneyServiceFacade.getRecByBatchNo(cloudTaskInfo.getBatchno());

        //查询用户是否实名签约
        String webName = "欣享科技";
        String hotLine = "010-67077608";
        String heTongUrl = ConfigUtil.getValue("WEIXIN_URL") + "/#/Identityno";  //实名签约地址
        String shiMingUrl = ConfigUtil.getValue("WEIXIN_URL") + "/#/Idtesttwo";//合同签约地址

        String company_money_id = cloudCompanyMoneyInfo.getId(); //关联批次订单表的id
        CloudDfMoneyRequest dfRequest = new CloudDfMoneyRequest();
        dfRequest.setCompanyMoneyId(company_money_id);
        List<CloudDfMoneyInfo> dfMoneyInfoList=  cloudDfMoneyServiceFacade.getAllBySective(dfRequest);

        //记录pay_cloud_interface_stream表操作记录
        CloudInterfaceStreamInfo cloudInterfaceStreamInfo = new CloudInterfaceStreamInfo();

        // 查询用户是否全部鉴权
        for (int i = 0; i < dfMoneyInfoList.size() ; i++){
            CloudStaffBanksInfo searchStaffBanksInfo = new CloudStaffBanksInfo();
            searchStaffBanksInfo.setBankno(dfMoneyInfoList.get(i).getBankno());
            searchStaffBanksInfo.setBankphone(dfMoneyInfoList.get(i).getBankphone());
            CloudStaffBanksInfo cloudStaffBanksInfo = cloudStaffBanksServiceFacade.getStaffBankByInfo(searchStaffBanksInfo);
            if ( !cloudStaffBanksInfo.getBankActive().equals("1") ){
                // 如果有人鉴权状态不是通过就返回错误
                jpfResponseDto.setRetCode("0001");
                jpfResponseDto.setRetMsg("该批次中存在鉴权失败的记录，请修改后重新上传excel");

                return jpfResponseDto;
            }
        }

        // 更新任务为锁定
        CloudTaskInfo upTaskInfo = new CloudTaskInfo();
        upTaskInfo.setId(taskId);
        upTaskInfo.setIsLock((byte)1);
        int taskRes = cloudTaskServiceFacade.updateColumn(upTaskInfo);

        // 更新批次订单为已锁定
        CloudCompanyMoneyInfo upCompanyMoneyInfo = new CloudCompanyMoneyInfo();
        upCompanyMoneyInfo.setId(cloudCompanyMoneyInfo.getId());
        upCompanyMoneyInfo.setMontype((byte)1);
        int companyMoneyRes = CloudCompanyMoneyServiceFacade.updateColumn(upCompanyMoneyInfo);

        // 更新代付状态为可支付
        cloudDfMoneyServiceFacade.updateDfMontype(cloudCompanyMoneyInfo.getId());

        Date date = new Date();
        String dateTime = date.toString();

        //循环发送短信
        for (int i = 0; i < dfMoneyInfoList.size() ; i++) {
            Long dfMoneyId = dfMoneyInfoList.get(i).getId();//代付表主键ID
            String banknickname = dfMoneyInfoList.get(i).getBanknickname();//收款人

            String mobile = dfMoneyInfoList.get(i).getBankphone(); //手机号
            Long busstaffid = dfMoneyInfoList.get(i).getBusstaffid(); //员工ID

            //短信发送
            String content = "";

            if ( !dfMoneyInfoList.get(i).getCompactStaffCompactActive().equals("1") ){ //未签合同
                /*content = "尊敬的"+banknickname+",委托"+webName+"为您进行结算服务,需要您在["+webName+"]平台";//短信内容
                content += "签约合同。点击： "+heTongUrl;//短信内容*/
                content = "尊敬的"+banknickname+"，您有一份新的合同需要签约，请前往欣享服务微信公众号进行签约。";//短信内容
            }
            if ( !dfMoneyInfoList.get(i).getCompanyStaffIsActice().equals("1") ){ //未实名签约
                /*content = "尊敬的"+banknickname+",委托"+webName+"为您进行结算服务,需要您在["+webName+"]平台";//短信内容
                content += "签约认证。点击： "+shiMingUrl;//短信内容*/
                content = "尊敬的"+banknickname+"，欢迎加入欣享服务共享经济服务平台，请在微信搜索公众号“欣享服务”进行认证签约";//短信内容
                content += "。(请使用您的身份证号进行登录，客服热线："+hotLine+")";//短信内容
            }
            /*if ( !dfMoneyInfoList.get(i).getCompactStaffCompactActive().equals("1") && !dfMoneyInfoList.get(i).getCompanyStaffIsActice().equals("1") ){ //未实名签约
                content = "尊敬的"+banknickname+",委托"+webName+"为您进行结算服务,需要您在["+webName+"]平台";//短信内容
                content += "签约认证并完成合同签约。点击： "+shiMingUrl;//短信内容
                content = "尊敬的"+banknickname+",欢迎加入欣享服务共享经济服务平台，请在微信搜索公众号“欣享服务”进行认证签约";//短信内容
                content += ".(请使用您的身份证号进行登录，客服热线："+hotLine+")";//短信内容
            }*/
            if( StringUtils.isNotBlank(content) ){
                //发送短信
                Map<String,String> retsult = SmsUtils.send(mobile,content,"xinxiang");
                if ( retsult == null ){
                    jpfResponseDto.setRetCode("0010");
                    jpfResponseDto.setRetMsg("触发短信接口失败，请检查");

                    return jpfResponseDto;
                }
                String requestUrl = retsult.get("requestUrl"); //请求Url
                String requestParam = retsult.get("requestParam");//请求参数
                String response =  retsult.get("response");//返回结果json字符串

                //存取短信接口调用记录
                cloudInterfaceStreamInfo.setRequestUrl(requestUrl);
                cloudInterfaceStreamInfo.setRequestContent(requestParam);
                cloudInterfaceStreamInfo.setType((byte)2);
                cloudInterfaceStreamInfo.setResponseContent(response);
                cloudInterfaceStreamInfo.setCompanyMoneyId(company_money_id);
                cloudInterfaceStreamInfo.setTaskId(taskId);
                cloudInterfaceStreamInfo.setStaffId(busstaffid.toString());
                cloudInterfaceStreamInfo.setAddtime(date);
                cloudInterfaceStreamServiceFacade.insRecord(cloudInterfaceStreamInfo);

                //json---转换代码---
                Map<String,String> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>() {});
                if( responseMap.containsKey("code") ){
                    String result=responseMap.get("code");
                    if( result.equals("10000 ")){//短信签约成功 更新为代付款状态
                        CloudDfMoneyRequest dfMoneyRequest = new CloudDfMoneyRequest();
                        int count = cloudDfMoneyServiceFacade.updateDfMoneyActiveById(dfMoneyRequest,dfMoneyId);
                    }
                }
            }
        }

        return jpfResponseDto;
    }

    /**
     * 鉴权操作
     * 返回 1=已经鉴权过 2=流水创建失败 3=鉴权失败 4=鉴权成功
     */
    @RequestMapping("/checkBanks")
    public int checkBanks(CheckBanksRequest checkBanksRequest){
        // 先查询这个银行卡号和手机号是否已鉴权过
        CloudStaffBanksInfo isCheckedInfo = new CloudStaffBanksInfo();
        isCheckedInfo.setBankno(checkBanksRequest.getAccountNo());
        isCheckedInfo.setBankphone(checkBanksRequest.getMobile());
        CloudStaffBanksInfo queryRecord = cloudStaffBanksServiceFacade.getStaffBankByInfo(isCheckedInfo);
        if ( queryRecord.getBankActive().equals("1") ){
            return 1;
        }

        // 拼接鉴权4要素参数并触发接口
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("accountNo",checkBanksRequest.getAccountNo());
        requestMap.put("idCard",checkBanksRequest.getIdCard());
        requestMap.put("mobile",checkBanksRequest.getMobile());
        requestMap.put("name",checkBanksRequest.getName());
        requestMap.put("dateTime",checkBanksRequest.getDateTime());

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(requestMap);

        String sign = Md5Encrypt.md5(ToolUtils.mapToUrl(treeMap) + ConfigUtil.getValue("API_SECRET")).toUpperCase();
        requestMap.put("sign",sign);
        String requestUrl = ConfigUtil.getValue("CLOUD_API_URL")+"/toolcate/bankFourCheck";
        String response = OkHttpUtils.postForm(requestUrl,requestMap);
        Map<String,String> responseMap = JsonUtils.toObject(response, HashMap.class);

        // 添加流水记录
        CloudInterfaceStreamInfo cloudInterfaceStreamInfo = new CloudInterfaceStreamInfo();
        cloudInterfaceStreamInfo.setType((byte)1);
        cloudInterfaceStreamInfo.setRequestUrl(requestUrl);
        cloudInterfaceStreamInfo.setRequestContent(ToolUtils.mapToUrl(requestMap));
        cloudInterfaceStreamInfo.setResponseContent(response);
        cloudInterfaceStreamInfo.setTaskId(checkBanksRequest.getTaskId());
        cloudInterfaceStreamInfo.setStaffId(checkBanksRequest.getStaffId());
        cloudInterfaceStreamInfo.setStaffBanksId(checkBanksRequest.getStaffBanksId());
        cloudInterfaceStreamInfo.setAddtime(new Date());
        int streamRes = cloudInterfaceStreamServiceFacade.insRecord(cloudInterfaceStreamInfo);
        if ( streamRes > 0 ){
            logger.info("员工id为 " + checkBanksRequest.getStaffId() + "，银行卡id为 " + checkBanksRequest.getStaffBanksId() + " 的流水记录创建成功");
        }else{
            logger.info("员工id为 " + checkBanksRequest.getStaffId() + "，银行卡id为 " + checkBanksRequest.getStaffBanksId() + " 的流水记录创建失败");
            return 2;
        }

        if ( responseMap.get("code").equals("10000") ){
            // 鉴权成功
            CloudStaffBanksInfo cloudStaffBanksInfo = new CloudStaffBanksInfo();
            cloudStaffBanksInfo.setBankno(checkBanksRequest.getAccountNo());
            cloudStaffBanksInfo.setBankphone(checkBanksRequest.getMobile());
            cloudStaffBanksInfo.setBankActive("1");
            int staffBanksRes = cloudStaffBanksServiceFacade.updateColumn(cloudStaffBanksInfo);
            if ( staffBanksRes > 0 ){
                logger.info("员工id为 " + checkBanksRequest.getStaffId() + "，银行卡id为 " + checkBanksRequest.getStaffBanksId() + " 的鉴权成功，已更新银行卡为已鉴权");
            }
        }else {
            // 鉴权失败
            logger.info("员工id为 " + checkBanksRequest.getStaffId() + "，银行卡id为 " + checkBanksRequest.getStaffBanksId() + " 的鉴权失败");
            return 3;
        }

        return 4;
    }

    /**
     * 测试鉴权接口
     */
    @RequestMapping(value = "/testCheckBanks")
    @ResponseBody
    public String testCheckBanks(){
        /*Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("accountNo","6212260200132547676");
        requestMap.put("idCard","410711198701161537");
        requestMap.put("mobile","15810063151");
        requestMap.put("name","李晓飞");
        requestMap.put("dateTime","2018-07-19 13:43:00");

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(requestMap);

        String sign = Md5Encrypt.md5(ToolUtils.mapToUrl(treeMap) + ConfigUtil.getValue("API_SECRET")).toUpperCase();
        requestMap.put("sign",sign);
        String requestUrl = ConfigUtil.getValue("CLOUD_API_URL")+"/toolcate/bankFourCheck";
        String response = OkHttpUtils.postForm(requestUrl,requestMap);
        Map<String,String> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>(){});

        return JsonUtils.toJson(responseMap);*/

        String response = "{\"code\":\"10000\",\"info\":\"验证通过\",\"data\":{\"idCard\":\"410711198701161537\",\"accountNo\":\"6212260200132547676\",\"bank\":\"中国工商银行\",\"cardName\":\"牡丹卡普卡\",\"cardType\":\"借记卡\",\"name\":\"李晓飞\",\"mobile\":\"15810063151\",\"sex\":\"男\",\"area\":\"河南省新乡市牧野区\",\"province\":\"河南省\",\"city\":\"新乡市\",\"prefecture\":\"牧野区\",\"birthday\":\"1987-01-16\",\"addrCode\":\"410711\",\"lastCode\":\"7\"}}";
        /*response = response.replaceAll("\\\\","");
        response = StringUtils.strip(response,"\"");
        response = StringUtils.stripEnd(response,"\"");*/
        Map<String,String> responseMap = JsonUtils.toObject(response, HashMap.class);

        return "";
    }

}
