package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.util.exportExcel;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.facade.ShopOrderServiceFacade;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/shopOrder")
public class ShopOrderController {

    private Logger logger = LoggerFactory.getLogger(ShopOrderController.class);
    
    @Autowired
    private ShopOrderServiceFacade shopOrderServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "shopOrder/List";
    }

    /**
     * 服务市场公司列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopOrderRequest request) {
        GetShopOrderResponse response= shopOrderServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    /**
     * 客户详情页面
     */
    @RequestMapping("/orderInfo")
    @ResponseBody
    public ModelAndView orderInfo(String orderNo,ModelMap modelMap){
      //根据订单号查出订单详细信息
        ShopOrderInfo  shopOrderInfo= shopOrderServiceFacade.getOne(orderNo);
        modelMap.addAttribute("shopOrderInfo", shopOrderInfo);
        return new ModelAndView("shopOrder/orderDetail", modelMap);

    }

    /**
     * 欣豆市场订单管理-Excel导出
     * @param request
     * @param response
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(GetShopOrderRequest request, HttpServletResponse response){
        long startProgramTime = System.currentTimeMillis();
        Map<String,String> requestOrderSourceMap = new HashMap<>(2);
        requestOrderSourceMap.put("0","自平台");
        requestOrderSourceMap.put("1","敬恒");
        
        Map<String,String> requestOrderStatusMap = new HashMap<>(4);
        requestOrderStatusMap.put("0","待支付");
        requestOrderStatusMap.put("1","已支付");
        requestOrderStatusMap.put("2","支付失败");
        requestOrderStatusMap.put("3","已取消");

        request.setSourceParam(requestOrderSourceMap);
        request.setOrderStatusParam(requestOrderStatusMap);
        long startQueryTime = System.currentTimeMillis();
        List<PayShopOrderCustom> excelList = shopOrderServiceFacade.getExcelList(request);
        logger.info("欣豆市场订单数据查询时间:{} 秒",(System.currentTimeMillis() - startQueryTime) / 1000);
        try {
            long startExportTime = System.currentTimeMillis();
            JSONObject jsonObject = exportExcelByInfoNew(response, excelList, 1, "");
            logger.info("欣豆市场订单数据Excel导出时间:{} 秒" ,(System.currentTimeMillis() - startExportTime) / 1000);
            logger.info("欣豆市场订单数据导出程序执行时间:{} 秒",(System.currentTimeMillis() - startProgramTime) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"数据导出异常");
        }
    }

    /**
     * 导出欣豆市场数据格式到Excel文件
     * @param response 响应头
     * @param data 数据
     * @param type 1 下载  2 生成文件
     * @param path
     * @return
     */
    private JSONObject exportExcelByInfoNew(HttpServletResponse response, List<PayShopOrderCustom> data, int type, String path) throws UnsupportedEncodingException {
        type = type < 1 ? 1 : type;
        JSONObject res = new JSONObject();
        res.put("code","10000");
        res.put("info","SUCCESS");
        SXSSFWorkbook xssfWorkbook = new SXSSFWorkbook();
        String status = "",interfaceType = "",source = "";
        SXSSFSheet sheet = xssfWorkbook.getSheet("sheet1");
        if (sheet == null) {
            sheet = xssfWorkbook.createSheet("sheet1");
        }
        exportExcel.genSheetHead(sheet, 0, generateTitle());
        if(!CollectionUtils.isEmpty(data)){
            for (int rownum = 1; rownum <= data.size(); rownum++) {
                SXSSFRow row = sheet.createRow(rownum);
                int k = -1;
                exportExcel.createCell(row, ++k, data.get(rownum-1).getOrderNo());
                exportExcel.createCell(row, ++k, data.get(rownum-1).getProductName());
                exportExcel.createCell(row, ++k, data.get(rownum-1).getTypeName());
                exportExcel.createCell(row, ++k, data.get(rownum-1).getAmount() == null ? "" : String.valueOf(data.get(rownum-1).getAmount()));
                exportExcel.createCell(row, ++k, data.get(rownum-1).getTotalMoney() == null ? "" : String.valueOf(data.get(rownum-1).getTotalMoney()));
                exportExcel.createCell(row, ++k, data.get(rownum-1).getTotalDou() == null ? "" :String.valueOf(data.get(rownum-1).getTotalDou()));
                exportExcel.createCell(row, ++k, URLDecoder.decode(StringUtils.isBlank(data.get(rownum-1).getCustomerName()) ? "" : data.get(rownum-1).getCustomerName(),"UTF-8"));
                exportExcel.createCell(row, ++k, data.get(rownum-1).getAddtime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.get(rownum-1).getAddtime()));
                exportExcel.createCell(row, ++k, data.get(rownum-1).getPaytime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.get(rownum-1).getPaytime()));
                if(data.get(rownum-1).getStatus() == null){
                    status = "";
                }else{
                    switch (data.get(rownum-1).getStatus()){
                        case 0: status = "待支付";break;
                        case 1: status = "已支付";break;
                        case 2: status = "支付失败";break;
                        case 3: status = "已取消";break;
                        case 4: status = "充值成功";break;
                        case 5: status = "充值失败";break;
                    }
                }
                exportExcel.createCell(row, ++k, status);
                if(data.get(rownum-1).getInterfaceType() == null){
                    interfaceType = "";
                }else{
                    switch (data.get(rownum-1).getInterfaceType()) {
                        case 0:interfaceType = "欧飞";break;
                        case 1:interfaceType = "威能";break;
                    }
                }
                exportExcel.createCell(row, ++k, interfaceType);
                if(data.get(rownum-1).getSource() == null){
                    source = "";
                }else if(data.get(rownum-1).getSource() == 0){
                    source = "自平台";
                }else if(data.get(rownum-1).getSource() == 1){
                    source = "敬恒";
                }
                exportExcel.createCell(row, ++k, source);
            }
        }
        String fileName = "欣豆市场订单列表-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
        return exportExcel.writeIntoExcel(fileName, response, xssfWorkbook, path, type, res);
    }

    /**
     * 定义标题
     * @return
     */
    private Map<Integer,Object> generateTitle(){
        Map<Integer, Object> firstTitles = new HashMap<>(12);
        firstTitles.put(0, "订单号");
        firstTitles.put(1, "产品名称");
        firstTitles.put(2, "产品类型");
        firstTitles.put(3, "数量");
        firstTitles.put(4, "总金额");
        firstTitles.put(5, "总欣豆");
        firstTitles.put(6, "微信昵称");
        firstTitles.put(7, "下单时间");
        firstTitles.put(8, "支付时间");
        firstTitles.put(9, "状态");
        firstTitles.put(10, "供应商");
        firstTitles.put(11, "订单来源");
        return firstTitles;
    }
}
