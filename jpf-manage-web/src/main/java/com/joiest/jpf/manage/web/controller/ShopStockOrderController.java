package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.ShopInterfaceStreamInfo;
import com.joiest.jpf.entity.ShopStockCardInfo;
import com.joiest.jpf.entity.ShopStockOrderInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ShopInterfaceStreamServiceFacade;
import com.joiest.jpf.facade.ShopProductServiceFacade;
import com.joiest.jpf.facade.ShopStockOrderProductServiceFacade;
import com.joiest.jpf.facade.ShopStockOrderServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/shopStockOrder")
public class ShopStockOrderController {

    @Autowired
    private ShopStockOrderServiceFacade shopStockOrderServiceFacade;

    @Autowired
    private ShopInterfaceStreamServiceFacade shopInterfaceStreamServiceFacade;

    @Autowired
    private ShopStockOrderProductServiceFacade shopStockOrderProductServiceFacade;

    @Autowired
    private ShopProductServiceFacade shopProductServiceFacade;
    /**
     * 运营采购订单页面
     */
    @RequestMapping("/index")
    public String index(){


        return "shopStockOrder/List";
    }
    /**
     * 运营采购订单列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopStockOrderRequest request) {
        //定义运营角色对应的状态值
        List<Byte> statusArr=new ArrayList<Byte>();
        statusArr.add((byte)0);  //取消
        statusArr.add((byte)1);  //新建
        statusArr.add((byte)2); //审批
        request.setStatusArr(statusArr);
        GetShopStockOrderResponse response= shopStockOrderServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }


    /**
     * 财务采购订单页面
     */
    @RequestMapping("/indexCaiwu")
    public String indexCaiwu(){
        return "shopStockOrder/ListCaiwu";
    }
    /**
     * 财务采购订单列表
     */
    @RequestMapping("/listCaiwu")
    @ResponseBody
    public Map<String, Object> listCaiwu(GetShopStockOrderRequest request) {
        //定义财务角色对应的状态值
        List<Byte> statusArr=new ArrayList<Byte>();
        statusArr.add((byte)3);  //审批
        statusArr.add((byte)4);  //付款
        statusArr.add((byte)0);  //取消
        request.setStatusArr(statusArr);
        GetShopStockOrderResponse response= shopStockOrderServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }
    /**
     * 采购商品列表
     */
    @RequestMapping("/productsList")
    @ResponseBody
    public Map<String, Object> productsList(String OrderNo) {

        GetShopStockOrderProductResponse response= shopStockOrderProductServiceFacade.getProduct(OrderNo);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }



    /**
     * 添加申请页面
     */
    @RequestMapping("/add/page")
    @ResponseBody
    public ModelAndView addPage(ModelMap modelMap){

        return new ModelAndView("shopStockOrder/add", modelMap);

    }

    @RequestMapping("/products")
    public ModelAndView products()
    {
        return new ModelAndView("shopStockOrder/products");
    }

    @RequestMapping("/plist")
    @ResponseBody
    public Map<String, Object> plist(GetShopProductRequest request)
    {
        Map<String, Object> map = new HashMap<>();
        GetShopProductResponse response = shopProductServiceFacade.getProductList(request);
        map.put("rows", response.getList());
        map.put("total", response.getCount());
        return map;
    }

    /**
     * 审核页
     */
    @RequestMapping("/audit/page")
    @ResponseBody
    public ModelAndView auditPage(String orderNo,int type,ModelMap modelMap){

        ShopStockOrderInfo shopStockOrderInfo= shopStockOrderServiceFacade.getOne(orderNo);
        modelMap.addAttribute("shopStockOrderInfo",shopStockOrderInfo);
        if(type==1){
            modelMap.addAttribute("type",1);
        }else if(type==2){
            modelMap.addAttribute("type",2);
        }
        return new ModelAndView("shopStockOrder/audit", modelMap);

    }

    /**
     * 详情页
     */
    @RequestMapping("/view/page")
    @ResponseBody
    public ModelAndView viewPage(String orderNo,ModelMap modelMap){

        ShopStockOrderInfo shopStockOrderInfo= shopStockOrderServiceFacade.getOne(orderNo);
        modelMap.addAttribute("shopStockOrderInfo",shopStockOrderInfo);
        return new ModelAndView("shopStockOrder/view", modelMap);

    }

    /**
     * 新增采购订单
     */
    @RequestMapping("/submitProducts")
    @ResponseBody
    public JpfResponseDto submitProducts( GetShopStockOrderRequest request,HttpServletRequest httpRequest)throws Exception{

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());
        // 存储数据
        shopStockOrderServiceFacade.addStocks(request);
        return new JpfResponseDto();
    }

    /**
     * 审核操作
     */
    @RequestMapping("/audit/action")
    @ResponseBody

    public JpfResponseDto AuditAction(GetShopStockOrderRequest request,HttpServletRequest httpRequest)throws Exception{

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());
        request.setCheckOperatorId(userInfo.getId().toString());
        request.setCheckOperatorName(userInfo.getUserName());
        return shopStockOrderServiceFacade.AuditOrder(request);

    }
    /**
     * 商品采购
     * @param
     */
    @RequestMapping("purchase")
    @ResponseBody
    public ModelAndView purchase(HttpServletRequest request,ModelMap modelMap){

        String id = request.getParameter("orderid");
        ShopStockOrderInfo shopStockOrderInfo = shopStockOrderServiceFacade.getStockOrderById(id);

        //if(shopStockOrderInfo.getis)
        modelMap.addAttribute("id",request.getParameter("id"));

        return new ModelAndView("shopStockOrder/Purchase",modelMap);
    }
    /**
     * 商品采购 操作卡密以及数据
     * @param
     */
    @RequestMapping("submitPurchase")
    @ResponseBody
    public JSONObject submitPurchase(HttpServletRequest request, @RequestParam(value = "file",required = true) MultipartFile file) throws Exception{

        String id = request.getParameter("orderid");

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonData = new JSONObject();
        jsonObject.put("code","10008");
        jsonObject.put("info","ERROR");

        ShopStockOrderInfo shopStockOrderInfo = shopStockOrderServiceFacade.getStockOrderById(id);
        if(shopStockOrderInfo == null){

            jsonObject.put("info","未获取到采购订单");
            return jsonObject;
        }
        InputStream in = file.getInputStream();
        Map<Object,Object> rowoOb = new ExcelDealUtils().getImportExcel(in, file.getOriginalFilename());

        Integer rowSize = rowoOb.size();
        Integer actualySize = rowSize-4;
        // 最大支持N条数据
        Integer maxDfAmount = Integer.parseInt(ConfigUtil.getValue("MAX_DF_AMOUNT"));
        maxDfAmount = maxDfAmount + 4;  // 加上表头的四行
        if ( rowSize > maxDfAmount){

            // 已超过最大采购数量限制
            jsonObject.put("info","已超过单次最大支持的采购数量，请修改后上传");
            return jsonObject;
        }
        Map<Object,Object> singlePerson = null;

        // 组装自由职业者信息数组
        JSONArray shopStockCardSuccess = new JSONArray();
        JSONArray shopStockCardFailed = new JSONArray();
        //循环行
        for (Map.Entry<Object, Object> hang : rowoOb.entrySet()) {

            //循环行中具体的列
            Map<Object,Object> cellOb = (Map<Object,Object>)hang.getValue();
            if(cellOb==null || cellOb.size() ==0){
                continue;
            }
            if ((Integer) hang.getKey()  > 4) {

                ShopStockCardInfo scinfo = new ShopStockCardInfo();
                boolean flag = true;
                for (Map.Entry<Object, Object> lie : cellOb.entrySet()) {

                    String col = lie.getKey().toString();

                    // 前5列数据必填
                    if ( StringUtils.isBlank(lie.getValue().toString()) ){

                        flag = false;
                    }
                    if(col.equals("0")){

                        scinfo.setType(Byte.valueOf(lie.getValue().toString()));
                    }
                    if(col.equals("1")){

                        scinfo.setCardNo(lie.getValue().toString());
                    }
                    if(col.equals("2")){

                        scinfo.setCardPass(lie.getValue().toString());
                    }
                    if(col.equals("3")){

                        scinfo.setExpireMonth(new Integer(lie.getValue().toString()));
                    }
                    if(col.equals("4")){

                        Date time = DateUtils.stringToDate(lie.getValue().toString());
                        scinfo.setExpireDate(time);
                    }
                    /*if(col.equals("5") && StringUtils.isNotBlank(lie.getValue().toString())){

                        // 把excel表的金额统计保留两位小数
                        BigDecimal pbid = new BigDecimal(lie.getValue().toString());
                        BigDecimal bid = pbid.setScale(2, RoundingMode.HALF_UP);
                        scinfo.setBid(bid);
                    }*/
                }
                if(flag){

                    shopStockCardSuccess.add(scinfo);
                }else{

                    shopStockCardFailed.add(scinfo);
                }
            }else{
                for (Map.Entry<Object, Object> lie : cellOb.entrySet()) {

                    if(lie.getKey().toString().equals("0") && hang.getKey().toString().equals("2")){

                        if(!lie.getValue().toString().equals(actualySize.toString())){

                            jsonObject.put("info","总笔数与实际笔数不符请修改后上传！");
                            return jsonObject;
                        }
                    }
                }
            }
        }
        UUID fileUUid = UUID.randomUUID();

        //组装文件数据
        JSONObject fileData = new JSONObject();

        //处理失败数据
        if(shopStockCardFailed.size()>0){

            fileData.put("faildData",shopStockCardFailed);
        }
        if(shopStockCardSuccess.size()>0){

            fileData.put("sucessData",shopStockCardSuccess);
        }
        LogsCustomUtils2.writeIntoFile(fileData.toString(),ConfigUtil.getValue("CACHE_PATH")+fileUUid.toString()+".txt",false);

        // OSS上传excel文件
        String savePre = ConfigUtil.getValue("EXCEL_PATH");
        String path = PhotoUtil.saveFile(file, savePre);
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("path",path);
        String url = ConfigUtil.getValue("CLOUD_API_URL")+"/oss/upload";
        String response = "https://yifuka.oss-cn-beijing.aliyuncs.com/clouds/1533717137872.zip";
        //String response = OkHttpUtils.postForm(url,requestMap);

        // 添加OSS流水
        ShopInterfaceStreamInfo shopInterfaceStreamInfo = new ShopInterfaceStreamInfo();
        shopInterfaceStreamInfo.setType((byte)0);
        shopInterfaceStreamInfo.setRequestUrl(url);
        shopInterfaceStreamInfo.setRequestContent(path);
        shopInterfaceStreamInfo.setResponseContent(response);
        shopInterfaceStreamInfo.setAddtime(new Date());
        shopInterfaceStreamServiceFacade.addStream(shopInterfaceStreamInfo);

        // 修改采购订单数据
        Map<String,String> stockOrder = new HashMap<>();
        stockOrder.put("oss_url",response);
        stockOrder.put("id",id);
        shopStockOrderServiceFacade.upStockOrderById(stockOrder);

        jsonData.put("fileUUid",fileUUid.toString());
        jsonData.put("id",id);
        jsonObject.put("code","10000");
        jsonObject.put("info","SUCCESS");
        jsonObject.put("data",jsonData);

        return jsonObject;
    }
    /**
     * 商品采购下载模板
     */
    @RequestMapping(value="/download")
    public ResponseEntity<byte[]> download()throws Exception {

        //下载文件路径
        String filename=ConfigUtil.getValue("EXCEL_PURCHASE_NAME");
        String path=ConfigUtil.getValue("EXCEL_PATH");
        String filename2 = null;
        String filename3 = null;

        //解决找到文件问题:
        // URLEonder把中文用UTF-8编码,然而,tomcat用iso-8859-1解码
        //,我们需要用iso-8859-1编码,再重新用utf-8解码才能匹配到硬盘文件的名字
        filename2 = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
        filename3 = new String(filename2.getBytes("GBK"), "ISO-8859-1");
        File file = new File(path + filename2);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentDispositionFormData("attachment", filename3);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }
    /**
     * 展示处理数据
     */
    @RequestMapping("/purchaseData")
    @ResponseBody
    public ModelAndView purchaseData(HttpServletRequest request,ModelMap modelMap){

        String fileUUid = request.getParameter("fileUUid");
        String id = request.getParameter("id");

        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("CACHE_PATH")+fileUUid+".txt","UTF-8");
        //Map<String,String> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
        JSONObject fileCon = JSONObject.fromObject(fileContent);

        int faildSize = 0;
        int successSize = 0;
        if(fileCon.containsKey("faildData")){

            faildSize = fileCon.getJSONArray("faildData").size();
        }
        if(fileCon.containsKey("sucessData")){

            successSize = fileCon.getJSONArray("sucessData").size();
        }
        modelMap.addAttribute("faildSize",faildSize);
        modelMap.addAttribute("successSize",successSize);
        modelMap.addAttribute("fileUUid",fileUUid);
        modelMap.addAttribute("id",id);

        return new ModelAndView("shopStockOrder/PurchaseShow",modelMap);
    }
    /**
     * 展示处理数据
     */
    @RequestMapping(value = "/purchaseShow", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String, Object> purchaseShow(HttpServletRequest request,ModelMap modelMap){

        String fileUUid = request.getParameter("fileUUid");
        String type = request.getParameter("type");

        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("CACHE_PATH")+fileUUid+".txt","UTF-8");
        //Map<String,String> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
        JSONObject fileCon = JSONObject.fromObject(fileContent);

        JSONArray rows;
        if(type.equals("success")){

            rows = fileCon.getJSONArray("sucessData");
            //Map<String,List< LinkedHashMap<String,String> >> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
            //List< LinkedHashMap<String,String> > list = jsonMap.get("data");
            //Map<String,Object> responseMap = new HashMap<>();
        }else{

            rows = fileCon.getJSONArray("faildData");
        }
        String data = rows.toString();
        List jsonMap = JSONArray.toList(rows, new ShopStockCardInfo(), new JsonConfig());

        Map<String ,Object> responseMap = new HashMap<>();
        responseMap.put("rows",jsonMap);
        responseMap.put("total",rows.size());

        return responseMap;
    }
}