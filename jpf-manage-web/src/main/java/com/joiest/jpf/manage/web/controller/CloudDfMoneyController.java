package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudCompanyMoney;
import com.joiest.jpf.common.po.PayCloudDfMoney;
import com.joiest.jpf.common.po.PayCloudDfMoneyExample;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.entity.CloudCompanyMoneyInfo;
import com.joiest.jpf.entity.CloudInterfaceStreamInfo;
import com.joiest.jpf.facade.CloudCompanyMoneyServiceFacade;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import com.joiest.jpf.facade.CloudInterfaceStreamServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/cloudDfMoney")
public class CloudDfMoneyController {

    @Autowired
    private CloudDfMoneyServiceFacade cloudDfMoneyServiceFacade;

    @Autowired
    private CloudCompanyMoneyServiceFacade cloudCompanyMoneyServiceFacade;

    @Autowired
    private CloudInterfaceStreamServiceFacade cloudInterfaceStreamServiceFacade;

    /**
     * 代付开始打款
     * dfIds  代付明细主键ID串 例： 1,2
     * fid 订单号
     */
    @RequestMapping(value = "/batchMoney", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JpfResponseDto batchMoney(HttpServletRequest request){

        String companyMoneyId = request.getParameter("companyMoneyId");//订单表ID
        String dfIds = request.getParameter("dfIds");//代付ID字符串
        if(StringUtils.isBlank(dfIds) || dfIds == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "没有可代付明细");
        }

        //更新订单表 为打款中
        CloudCompanyMoneyInfo companyMoneyInfo = cloudCompanyMoneyServiceFacade.getRecById(companyMoneyId);
        if( companyMoneyInfo.getMontype() == 3 ){//已发起打款
            //throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单已发起打款，请勿重复点击");
        }
        if( companyMoneyInfo.getMontype() != 1 && companyMoneyInfo.getMontype() != 3 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单不能发起打款请求，请联系管理员");
        }

        String[] ids_str = dfIds.split(",");
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < ids_str.length; i++) {
            ids.add(Long.parseLong(ids_str[i]));
        }
        if( ids.isEmpty() || ids.size() <=0 ){//未选择代付明细ID
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "请选择代付信息");
        }

        PayCloudCompanyMoney comMoneyData = new PayCloudCompanyMoney();
        comMoneyData.setMontype((byte)3);
        JpfResponseDto jpfcomMoneyDto = cloudCompanyMoneyServiceFacade.updateRecById(comMoneyData,companyMoneyId);

        //调用代付接口
        Date date = new Date();
        String dateTime = date.toString();
        Map<String,Object> map = new HashMap<>();
        map.put("batchid",companyMoneyId);
        map.put("dfid",ids);
        String cloudWaitpayKeycode = ManageConstants.ClOUD_WAITPAY_KEYCODE; //校验码keycode
        String requestUrl = ManageConstants.ClOUD_WAITPAY_URl; //校验码keycode

        //排序转换
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);
        String respos = ToolUtils.mapToUrl(treeMap);
        map.put("token",cloudWaitpayKeycode);

        String requestParam = ToolUtils.mapToUrl(map);//请求参数

        String response = OkHttpUtils.postForm(requestUrl,map);

        //json---转换代码---
        Map<String,String> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>() {});
        if( responseMap.isEmpty() ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "代付接口异常");
        }
        String code=responseMap.get("code");
        if( code =="10000" ){ //代付成功
            //记录pay_cloud_interface_stream表操作记录
            CloudInterfaceStreamInfo cloudInterfaceStreamInfo = new CloudInterfaceStreamInfo();
            //存取短信接口调用记录
            cloudInterfaceStreamInfo.setRequestUrl(requestUrl);
            cloudInterfaceStreamInfo.setRequestContent(requestParam);
            cloudInterfaceStreamInfo.setType((byte)2);
            cloudInterfaceStreamInfo.setResponseContent(response);
            cloudInterfaceStreamInfo.setCompanyMoneyId(companyMoneyId);
            cloudInterfaceStreamInfo.setTaskId("0");
            cloudInterfaceStreamInfo.setStaffId("0");
            cloudInterfaceStreamInfo.setAddtime(date);
            cloudInterfaceStreamServiceFacade.insRecord(cloudInterfaceStreamInfo);
        }

        //更新订单下对应的代付明细状态为：打款中
        PayCloudDfMoney recordData = new PayCloudDfMoney();
        recordData.setMontype(4); //更新为打款中

        JpfResponseDto jpfResponseDto = cloudDfMoneyServiceFacade.updateDfRecordsByids(recordData,ids);

        return jpfResponseDto;
    }

}
