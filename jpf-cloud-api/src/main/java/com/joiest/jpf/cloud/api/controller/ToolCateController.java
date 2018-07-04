package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.ToolCateRequest;
import com.joiest.jpf.dto.ToolCateResponse;
import com.joiest.jpf.entity.CloudIdcardInfo;
import com.joiest.jpf.facade.CloudIdcardServiceFacade;
import com.joiest.jpf.facade.ToolCateServiceFacade;
import com.joiest.jpf.facade.impl.RedisCustomServiceFacadeImpl;
import com.joiest.jpf.facade.impl.ToolCateServiceFacadeImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("toolcate")
public class ToolCateController {

    @Autowired
    private ToolCateServiceFacade toolCateServiceFacade;

    @Autowired
    private CloudIdcardServiceFacade cloudIdcardServiceFacade;

    @Autowired
    private RedisCustomServiceFacadeImpl redisCustomServiceFacade;

    /**
     * 身份证OCR识别
     * */
    @RequestMapping(value = "/idCardOcr", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String idCardOcr(HttpServletRequest request) throws IOException {

        String side = request.getParameter("side");
        String imgInfo = request.getParameter("img");

        if(side == null || imgInfo==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Error",null);
        }
        String ocrRes = toolCateServiceFacade.idCardOcr(request,side,imgInfo);
        String baseRe = Base64CustomUtils.base64Encoder(ocrRes);
        baseRe = baseRe.replaceAll("\r\n","");

        return baseRe;
    }
    /**
     * 身份证分析入库
     * */
    @RequestMapping(value = "/idCardAnaly", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String idCardAnaly(HttpServletRequest request) throws IOException {

        String face = request.getParameter("face");
        String back = request.getParameter("back");
        String type = request.getParameter("type");
        //参数是否为空
        if(face == null || face.isEmpty() || back == null || back.isEmpty()){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Error", null);
        }
        String faceBase = Base64CustomUtils.base64Decoder(face);
        String backBase = Base64CustomUtils.base64Decoder(back);
        type = Base64CustomUtils.base64Decoder(type);

        JSONObject faceResult = JSONObject.fromObject(faceBase);
        JSONObject backResult = JSONObject.fromObject(backBase);

        //实名验证
       JSONObject cardAuth = toolCateServiceFacade.idenAuth(faceResult.get("name").toString(),faceResult.get("num").toString());
       if(cardAuth.get("code").equals("10008")){

           return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), cardAuth.get("info").toString(),null);
       }
        CloudIdcardInfo cloudIdcardInfo=cloudIdcardServiceFacade.getCloudIdcardByCardNo(faceResult.get("num").toString());

        YjResponseDto yjResponseDto= new YjResponseDto();
        if(cloudIdcardInfo == null){

            int idCard= cloudIdcardServiceFacade.addCloudIdcard(faceResult,backResult,new Byte(type));
            if(idCard > 0){

                Map<String,Object> map = new HashMap<>();
                map.put("id",idCard);

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "身份证信息上传成功",map);

            }else{

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "身份证信息上传失败",null);
            }
        }else{

            Map<String,Object> map = new HashMap<>();
            map.put("id",cloudIdcardInfo.getId());

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "身份证信息上传成功",map);
        }
    }
    /*
    * 身份证号、姓名实名认证
    * */
    @RequestMapping(value = "/idenAuth", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String idenAuth(HttpServletRequest request) {

        String name = request.getParameter("name");
        String idCard = request.getParameter("idCard");

        JSONObject cardAuth = toolCateServiceFacade.idenAuth(name,idCard);

        String baseRe = Base64CustomUtils.base64Encoder(cardAuth.toString());
        baseRe = baseRe.replaceAll("\r\n","");

        return baseRe;
    }
    /**
     * 短信发送
     * */
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendSms(HttpServletRequest request) throws IOException {

        String phone = request.getParameter("mobile");
        phone = Base64CustomUtils.base64Decoder(phone);
        try{

            int verificateCode = toolCateServiceFacade.getRandomInt(100000,999999);//短信内容

            redisCustomServiceFacade.set(ConfigUtil.getValue("CLOUD_USER_AUTH")+phone,new Integer(verificateCode).toString(),60*10);
            String content = null;
            content = "尊敬的用户，您此次的手机验证码是："+verificateCode+",10十分钟内有效";

            int result = toolCateServiceFacade.sendSms(phone, content);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。

            if(result==0){//返回值为0，代表成功

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "短信发送成功",null);
            }else{//返回值为非0，代表失败

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "短信发送失败",null);
            }
        }catch (Exception e) {

            e.printStackTrace();//异常处理
        }
        return null;
    }
}