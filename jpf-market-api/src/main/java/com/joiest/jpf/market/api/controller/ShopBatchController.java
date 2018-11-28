package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayShopBatch;
import com.joiest.jpf.common.po.PayShopCompanyCharge;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ShopBatchServiceFacade;
import com.joiest.jpf.facade.ShopCompanyChargeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: admin
 * @Date: 2018/11/28 14:58
 * @Description:
 */
@Controller
@RequestMapping("/market-manager/shopBatchController")
public class ShopBatchController {

    @Autowired
    private ShopCompanyChargeServiceFacade shopCompanyChargeServiceFacade;

    /**
     * 查询可用的订单号
     * @param request
     * @return
     */
    @RequestMapping(value = "/getUsableBatchNo",method = RequestMethod.POST)
    @ResponseBody
    public String getUsableBatchNo(HttpServletRequest request){

        String companyId = request.getParameter("companyId");
        if(StringUtils.isBlank(companyId)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(),null);
        }
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            List<PayShopCompanyCharge> payShopCompanyCharges = shopCompanyChargeServiceFacade.getListByCompanyId(Base64CustomUtils.base64Decoder(companyId));
            for (PayShopCompanyCharge payShopCompanyCharge:payShopCompanyCharges) {
                Map<String,Object> map =new ConcurrentHashMap<>();
                map.put("id",payShopCompanyCharge.getId());
                map.put("contractNo",payShopCompanyCharge.getContractNo());
                map.put("balance",payShopCompanyCharge.getBalance());
                map.put("transferRate",payShopCompanyCharge.getTransferRate());
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),JpfInterfaceErrorInfo.FAIL.getDesc(),null);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),list);
    }
}