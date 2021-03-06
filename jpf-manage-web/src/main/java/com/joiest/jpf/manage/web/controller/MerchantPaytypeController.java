package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.dto.AddMerPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeResponse;
import com.joiest.jpf.dto.ModifyMerPayTypeRequest;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.entity.MerchantPayTypeInfo;
import com.joiest.jpf.entity.MerchantTypeInfo;
import com.joiest.jpf.facade.MerPayTypeServiceFacade;
import com.joiest.jpf.facade.MerTypeServiceFacade;
import com.joiest.jpf.facade.MerchantServiceFacade;
import com.joiest.jpf.facade.impl.MerTypeServiceFacadeImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/merchant/paytype")
public class MerchantPaytypeController {

    private static final Logger logger = LogManager.getLogger(MerchantPaytypeController.class);

    @Autowired
    private MerPayTypeServiceFacade merPayTypeServiceFacade;

    @Autowired
    private MerchantServiceFacade merchantServiceFacade;

    @Autowired
    private MerTypeServiceFacade merTypeServiceFacade;
    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("merchant/merchantPaytypeList");
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetMerchPayTypeRequest request) {
        GetMerchPayTypeResponse response = merPayTypeServiceFacade.getMerPayTypes(request);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", response.getCount());
        map.put("rows", response.getPayTypeInfos());
        return map;
    }

    /**
     * 配置支付类型---页面
     * @param id 商户ID
     * @return
     */
    @RequestMapping("/add/page")
    public ModelAndView addPage(String id,ModelMap modelMap){
        //商户信息
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(id));
        //当前已有支付类型
        GetMerchPayTypeResponse payTypes = merPayTypeServiceFacade.getOneMerPayTypes(Long.valueOf(id));
        modelMap.put("mtsid",id);
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("payTypeList", payTypes.getPayTypeInfos());
        return new ModelAndView("merchant/merchantPaytypeAdd",modelMap);
    }

    /**
     * 配置支付类型---实际添加页面
     */
    @RequestMapping("add/realpage")
    public ModelAndView addRealPage(String catid, String id, ModelMap modelMap){
        //获取单个支付类型信息
        MerchantTypeInfo merchantTypeInfo = merTypeServiceFacade.getOneTypeByCatid(catid);
        //商户信息
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(id));
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("merchantTypeInfo", merchantTypeInfo);
        return new ModelAndView("merchant/merchantPaytypeRealAdd", modelMap);
    }

    /**
     * 添加支付类型
     */
    @RequestMapping("/add/action")
    @ResponseBody
    public JpfResponseDto addAction(AddMerPayTypeRequest request){
        return merPayTypeServiceFacade.addMerPayType(request);

    }

    /**
     * 添加支付类型&参数---action
     */
    @RequestMapping("/add/addMerPayTypeOne")
    @ResponseBody
    public JpfResponseDto addMerPayTypeOne(@RequestBody ModifyMerPayTypeRequest request){
        return merPayTypeServiceFacade.addMerPayTypeOne(request);

    }

    /**
     * 支付类型实际编辑页面
     * @param  id pay_merchants_paytype.id
     * @param tpid  pay_merchant_paytype.tpid 支付类型ID
     */
    @RequestMapping("modify/realpage")
    public ModelAndView modifyRealPage(String mtsid, String id, String tpid, ModelMap modelMap){
        //获取某个商户的单个支付类型
        MerchantPayTypeInfo merpayTypeInfoOne = merPayTypeServiceFacade.getMerOnePayTypes(Long.valueOf(id));
        //获取单个支付类型信息
        MerchantTypeInfo TypeInfoOne = merTypeServiceFacade.getOneTypeByCatid(tpid);
        //商户信息
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(mtsid));
        modelMap.addAttribute("merpayTypeInfoOne", merpayTypeInfoOne);
        // 银联分期配置信息如果不为空，则把json串解析出来
        if (StringUtils.isNotBlank(merpayTypeInfoOne.getParam())){
            Map<String,String> paramMap = JsonUtils.toCollection(merpayTypeInfoOne.getParam(), new TypeReference<HashMap<String, String>>(){});
            //银联分期支付参数
            modelMap.addAttribute("CP_Acctid", paramMap.get("CP_Acctid"));
            modelMap.addAttribute("CP_MerchaNo", paramMap.get("CP_MerchaNo"));
            modelMap.addAttribute("CP_Code", paramMap.get("CP_Code"));
            modelMap.addAttribute("CP_Salt", paramMap.get("CP_Salt"));

            //微信全额支付参数
            modelMap.addAttribute("merSubMchid", paramMap.get("merSubMchid"));
            modelMap.addAttribute("payLimit", paramMap.get("payLimit"));
        }

        //参数2
        if ( StringUtils.isNotBlank(merpayTypeInfoOne.getParamSec()) )
        {
            Map<String,String> paramSecMap = JsonUtils.toCollection(merpayTypeInfoOne.getParamSec(), new TypeReference<Map<String, String>>(){});

            //银联支付---商户费率
            modelMap.addAttribute("merRate", paramSecMap.get("merRate"));
        }
        modelMap.addAttribute("TypeInfoOne", TypeInfoOne);
        modelMap.addAttribute("merchantInfo", merchantInfo);
        return new ModelAndView("merchant/merchantPaytypeRealModify", modelMap);
    }

    /**
     * 修改支付类型&参数---action
     */
    @RequestMapping("/modify/modifyMerPayTypeOne")
    @ResponseBody
    public JpfResponseDto modifyMerPayTypeOne(@RequestBody ModifyMerPayTypeRequest request){
        return merPayTypeServiceFacade.modifyMerPayTypeOne(request);

    }

    @RequestMapping("/delete/action")
    @ResponseBody
    public JpfResponseDto deleteAction(@RequestParam("id[]") List<Long> id){
        return merPayTypeServiceFacade.deleteMerPayType(id);
    }

    /**
     * 商户分期类型---页面
     * @param id 商户支付类型id
     * @param mtsid 商户ID
     */
    @RequestMapping("/stage/page")
    public ModelAndView stagePage(String mtsid, String id, ModelMap modelMap){
        //商户信息
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(mtsid));
        //获取某个商户的单个支付类型
        MerchantPayTypeInfo merpayTypeInfoOne = merPayTypeServiceFacade.getMerOnePayTypes(Long.valueOf(id));
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("merpayTypeInfoOne", merpayTypeInfoOne);
        return new ModelAndView("merchant/stageModify", modelMap);
    }

    /**
     * 商户分期类型配置
     */
    @RequestMapping("/stage/action")
    @ResponseBody
    public JpfResponseDto modifyMerBankcatid(@RequestBody ModifyMerPayTypeRequest request){
        return merPayTypeServiceFacade.modifyMerBankcatid(request);
    }

    /**
     * 配置商户密钥
     */
    @RequestMapping("/pk")
    @ResponseBody
    public JpfResponseDto modifyMerPrivateKey(String id,String pkey)
    {
        return merPayTypeServiceFacade.modifyMerPKey(id,pkey);
    }

}
