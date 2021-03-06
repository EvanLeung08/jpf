package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.ArithmeticUtils;
import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopCustomerCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.*;
import com.joiest.jpf.dto.ShopBatchCouponResponse;
import com.joiest.jpf.entity.PayShopBatchCouponResult;
import com.joiest.jpf.entity.PayShopBatchCouponResultInfo;
import com.joiest.jpf.entity.ShopBatchCouponInfo;
import com.joiest.jpf.entity.ShopCustomerInfo;
import com.joiest.jpf.facade.ShopBatchCouponServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShopBatchCouponServiceFacadeImpl implements ShopBatchCouponServiceFacade {

    @Autowired
    private PayShopBatchMapper payShopBatchMapper;

    @Autowired
    private PayShopBatchCouponMapper payShopBatchCouponMapper;

    @Autowired
    private PayShopCustomerMapper payShopCustomerMapper;

    @Autowired
    private PayShopCustomerCustomMapper payShopCustomerCustomMapper;

    @Autowired
    private PayShopCouponActiveMapper payShopCouponActiveMapper;

    @Autowired
    private PayShopCouponRemainMapper payShopCouponRemainMapper;

    @Autowired
    private PayCloudInterfaceStreamMapper payCloudInterfaceStreamMapper;

    @Autowired
    private PayShopConponExcelMapper payShopConponExcelMapper;

    @Autowired
    private PayShopCouponOrderMapper payShopCouponOrderMapper;
    @Autowired
    private PayShopCouponOrderInfoMapper payShopCouponOrderInfoMapper;
    /**
     * 根据id获取单个券详情
     */
    @Override
    public ShopBatchCouponInfo getCouponById(String id){
        PayShopBatchCoupon payShopBatchCoupon = payShopBatchCouponMapper.selectByPrimaryKey(id);
        ShopBatchCouponInfo shopBatchCouponInfo = new ShopBatchCouponInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopBatchCoupon.class, ShopBatchCouponInfo.class, false);
        beanCopier.copy(payShopBatchCoupon, shopBatchCouponInfo, null);

        return shopBatchCouponInfo;
    }

    /**
     * 根据批次号获取券
     */
    @Override
    public ShopBatchCouponResponse getCouponByBatchId(String batchId, int page, int rows){
        ShopBatchCouponResponse response = new ShopBatchCouponResponse();

        PayShopBatchCouponExample e = new PayShopBatchCouponExample();
        PayShopBatchCouponExample.Criteria c = e.createCriteria();
        c.andBatchIdEqualTo(batchId);
        int count = payShopBatchCouponMapper.countByExample(e);
        e.setPageNo(page);
        e.setPageSize(rows);

        List<PayShopBatchCoupon> list = payShopBatchCouponMapper.selectByExample(e);
        List<ShopBatchCouponInfo> infos = new ArrayList<>();
        if ( !list.isEmpty() ){
            for (PayShopBatchCoupon payShopBatchCoupon:list){
                ShopBatchCouponInfo shopBatchCouponInfo = new ShopBatchCouponInfo();
                BeanCopier beanCopier = BeanCopier.create(PayShopBatchCoupon.class, ShopBatchCouponInfo.class, false);
                beanCopier.copy(payShopBatchCoupon, shopBatchCouponInfo, null);

                infos.add(shopBatchCouponInfo);
            }
        }
        response.setList(infos);
        response.setCount(count);

        return response;
    }

    /**
     * 根据面值和批次号查找某企业的券数量
     */
    @Override
    public int getCouponNumByValue(String companyId, String value, String batchNo){
        PayShopBatchCouponExample e = new PayShopBatchCouponExample();
        PayShopBatchCouponExample.Criteria c = e.createCriteria();
        c.andCompanyIdEqualTo(companyId);
        c.andMoneyEqualTo(Integer.parseInt(value));
        if(StringUtils.isNotBlank(batchNo)){
            c.andBatchNoEqualTo(batchNo);
        }
        c.andIsActiveEqualTo((byte)0);
        c.andIsExpiredEqualTo((byte)0);

        return payShopBatchCouponMapper.countByExample(e);
    }

    /**
     * 批量发放欣券给个人
     */
    @Override
    @Transactional
    public List<ShopCustomerInfo> sendCouponsToPersons(List<LinkedHashMap<String,Object>> list, String companyId, String companyName, String batchNo, String excelLocalUrl){
        boolean allSuccess = true;
        List<ShopCustomerInfo> sendedList = new ArrayList<>();
        for ( int i=0; i<list.size(); i++){
            // 获取每条信息详情
            LinkedHashMap<String,Object> singlePerson = list.get(i);
            String name = singlePerson.get("name").toString();
            String phone = singlePerson.get("phone").toString();
            String value = singlePerson.get("dou").toString();
            String idno = singlePerson.get("idno") == null ? "" : singlePerson.get("idno").toString();
            ShopCustomerInfo sendedCustomerInfo = sendCouponToPersonAndActive(name,phone,idno,value,companyId,companyName,batchNo);
            sendedList.add(sendedCustomerInfo);
            if ( sendedCustomerInfo.getStatus() != 1 ){
                allSuccess = false;
            }
        }

        if ( allSuccess ){
            // OSS上传excel文件
            Map<String,Object> requestMap = new HashMap<>();
            requestMap.put("path",excelLocalUrl);
            String url = ConfigUtil.getValue("CLOUD_API_URL")+"/oss/upload";
            String response = OkHttpUtils.postForm(url,requestMap);

            // 增加==OSS接口流水==
            PayCloudInterfaceStream payCloudInterfaceStream = new PayCloudInterfaceStream();
            payCloudInterfaceStream.setType((byte)0);
            payCloudInterfaceStream.setRequestUrl(url);
            payCloudInterfaceStream.setRequestContent(ToolUtils.mapToUrl(requestMap));
            payCloudInterfaceStream.setResponseContent(response);
            payCloudInterfaceStream.setAddtime(new Date());
            payCloudInterfaceStreamMapper.insertSelective(payCloudInterfaceStream);

            // 更新批次表相关字段
            PayShopBatchExample batchExample = new PayShopBatchExample();
            PayShopBatchExample.Criteria batchCriteria = batchExample.createCriteria();
            batchCriteria.andBatchNoEqualTo(batchNo);
            List<PayShopBatch> batchList = payShopBatchMapper.selectByExample(batchExample);
            String batchId = batchList.get(0).getId();
            PayShopBatch payShopBatch = payShopBatchMapper.selectByPrimaryKey(batchId);

            String newExcelUrl;
            if ( StringUtils.isNotBlank(payShopBatch.getExcelUrl()) ){
                newExcelUrl = payShopBatch.getExcelUrl() +','+ response;
            }else{
                newExcelUrl = response;
            }

            PayShopBatch payShopBatchUpdate = new PayShopBatch();
            payShopBatchUpdate.setId(batchId);
            payShopBatchUpdate.setExcelUrl(newExcelUrl);
            payShopBatchUpdate.setSendTime(new Date());
            payShopBatchUpdate.setSendType((byte)1);
            payShopBatchUpdate.setUpdatetime(new Date());
            payShopBatchMapper.updateByPrimaryKeySelective(payShopBatchUpdate);
        }

        return sendedList;
    }

    /**
     * 将一个欣券发给某个人并激活
     */
    @Override
    @Transactional
    public ShopCustomerInfo sendCouponToPersonAndActive(String name, String phone, String idno, String dou, String companyId, String companyName, String batchNo){
        // 充值成功的用户集合
        ShopCustomerInfo chargedCustomer = new ShopCustomerInfo();

        // 判断数据库有没有这个顾客，没有就添加
        PayShopCustomerExample payShopCustomerExample = new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = payShopCustomerExample.createCriteria();
        c.andPhoneEqualTo(phone);
        List<PayShopCustomer> customerList = payShopCustomerMapper.selectByExample(payShopCustomerExample);
        PayShopCustomer payShopCustomer;
        if ( customerList.isEmpty() ){
            // 如果没有该手机号用户，添加一个
            PayShopCustomer customerInsert = new PayShopCustomer();
            customerInsert.setName(name);
            customerInsert.setCompanyId(Long.parseLong(companyId));
            customerInsert.setCompanyName(companyName);
            customerInsert.setPhone(phone);
            customerInsert.setIsVerify((byte)0);
            customerInsert.setStatus((byte)1);
            customerInsert.setDou(new BigDecimal(dou));
            customerInsert.setAddtime(new Date());
            payShopCustomerCustomMapper.insertSelective(customerInsert);
            // 添加完后更新校验码字段
            PayShopCustomerExample customerExample = new PayShopCustomerExample();
            PayShopCustomerExample.Criteria customerCriteria = customerExample.createCriteria();
            customerCriteria.andPhoneEqualTo(phone);

            String newCode = ToolUtils.CreateCode(dou,customerInsert.getId());      // dou校验码
            PayShopCustomer customerUpdate = new PayShopCustomer();
            customerUpdate.setCode(newCode);
            customerUpdate.setUpdatetime(new Date());
            payShopCustomerMapper.updateByExampleSelective(customerUpdate,customerExample);

            payShopCustomer = customerInsert;
            chargedCustomer.setPhone(phone);
        }else{
            // 充值豆
            payShopCustomer = customerList.get(0);
            BigDecimal newDou = new BigDecimal(ArithmeticUtils.add(payShopCustomer.getDou().toString(),dou,2));
            payShopCustomer.setDou(newDou);
            String newCode = ToolUtils.CreateCode(newDou.toString(),payShopCustomer.getId());     // dou校验码
            payShopCustomer.setCode(newCode);
            payShopCustomer.setUpdatetime(new Date());
            payShopCustomerMapper.updateByPrimaryKey(payShopCustomer);
            chargedCustomer.setPhone(payShopCustomer.getPhone());
        }

        // 查找充值面值的一张券
        PayShopBatchCouponExample couponExample = new PayShopBatchCouponExample();
        PayShopBatchCouponExample.Criteria couponCriteria = couponExample.createCriteria();
        couponCriteria.andCompanyIdEqualTo(companyId);
        couponCriteria.andDouEqualTo(new BigDecimal(dou));
        couponCriteria.andBatchNoEqualTo(batchNo);
        couponCriteria.andIsActiveEqualTo((byte)0);
        couponCriteria.andIsExpiredEqualTo((byte)0);
        List<PayShopBatchCoupon> list = payShopBatchCouponMapper.selectByExample(couponExample);
        if ( !list.isEmpty() ){
            // 券标记为已使用
            PayShopBatchCoupon couponUpdate = list.get(0);
            couponUpdate.setIsActive((byte)1);
            couponUpdate.setActiveTime(new Date());
            couponUpdate.setUpdatetime(new Date());
            // 根据有效期获取过期时间
            Date dNow = new Date();   //当前时间
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(dNow);//把当前时间赋给日历
            calendar.add(Calendar.MONTH, couponUpdate.getExpireMonth());  //设置为几个月
            Date dBefore = calendar.getTime();   //得到几个月后的时间
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd 00:00:00"); //设置时间格式
            String LastM= sdf.format(dBefore.getTime() + 24 * 3600 * 1000);    //格式化几个月后的时间
            Date dateAddMonth=null;
            try {
                dateAddMonth= sdf.parse(LastM);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            couponUpdate.setActiveCustomerId(payShopCustomer.getId());
            couponUpdate.setActivePhone(phone);
            couponUpdate.setActiveName(payShopCustomer.getName());
            couponUpdate.setExpireTime(dateAddMonth);
            couponUpdate.setSendTime(new Date());
            couponUpdate.setSendType((byte)1);
            payShopBatchCouponMapper.updateByPrimaryKey(list.get(0));

            // 批次余额表
            PayShopCouponRemain payShopCouponRemain = new PayShopCouponRemain();
            payShopCouponRemain.setCouponId(couponUpdate.getId());
            payShopCouponRemain.setCouponNo(couponUpdate.getCouponNo());
            payShopCouponRemain.setCouponActiveCode(couponUpdate.getActiveCode());
            payShopCouponRemain.setCustomerId(payShopCustomer.getId());
            payShopCouponRemain.setCouponDou(couponUpdate.getDou());
            payShopCouponRemain.setCouponDouLeft(couponUpdate.getDou());
            payShopCouponRemain.setStatus((byte)0);
            payShopCouponRemain.setExpireTime(couponUpdate.getExpireTime());
            payShopCouponRemain.setAddtime(new Date());
            payShopCouponRemain.setExpireTime(couponUpdate.getExpireTime());
            payShopCouponRemainMapper.insertSelective(payShopCouponRemain);

            // 激活日志
            PayShopCouponActive payShopCouponActive = new PayShopCouponActive();
            payShopCouponActive.setCustomerId(payShopCustomer.getId());
            payShopCouponActive.setCustomerName(payShopCustomer.getName());
            payShopCouponActive.setCompanyId(Integer.parseInt(companyId));
            payShopCouponActive.setCompanyName(companyName);
            payShopCouponActive.setBatchId(Integer.parseInt(couponUpdate.getBatchId()));
            payShopCouponActive.setBatchNo(couponUpdate.getBatchNo());
            payShopCouponActive.setCouponNo(couponUpdate.getCouponNo());
            payShopCouponActive.setActiveCode(couponUpdate.getActiveCode());
            payShopCouponActive.setMoney(new BigDecimal(dou));
            payShopCouponActive.setDou(new BigDecimal(dou));
            payShopCouponActive.setType("0");
            payShopCouponActive.setExpireTime(couponUpdate.getExpireTime());
            payShopCouponActive.setAddtime(new Date());
            payShopCouponActiveMapper.insertSelective(payShopCouponActive);
        }else{
            throw new JpfException("10001","未查到豆数量为"+dou+"的可用的券");
        }

        chargedCustomer.setStatus((byte)1);
        return chargedCustomer;
    }

    /**
     * 获取对应面值的若干欣券
     */
    @Override
    @Transactional
    public List<ShopBatchCouponInfo> getCoupons(List<LinkedHashMap<String,Object>> list, String companyId, String batchNo, String excelLocalUrl){
        // OSS上传excel文件
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("path",excelLocalUrl);
        String url = ConfigUtil.getValue("CLOUD_API_URL")+"/oss/upload";
        String response = OkHttpUtils.postForm(url,requestMap);

        // 增加==OSS接口流水==
        PayCloudInterfaceStream payCloudInterfaceStream = new PayCloudInterfaceStream();
        payCloudInterfaceStream.setType((byte)0);
        payCloudInterfaceStream.setRequestUrl(url);
        payCloudInterfaceStream.setRequestContent(ToolUtils.mapToUrl(requestMap));
        payCloudInterfaceStream.setResponseContent(response);
        payCloudInterfaceStream.setAddtime(new Date());
        payCloudInterfaceStreamMapper.insertSelective(payCloudInterfaceStream);

        // 更新批次表相关字段
        PayShopBatchExample batchExample = new PayShopBatchExample();
        PayShopBatchExample.Criteria batchCriteria = batchExample.createCriteria();
        batchCriteria.andBatchNoEqualTo(batchNo);
        List<PayShopBatch> batchList = payShopBatchMapper.selectByExample(batchExample);
        String batchId = batchList.get(0).getId();
        PayShopBatch payShopBatch = payShopBatchMapper.selectByPrimaryKey(batchId);

        String newExcelUrl;
        if ( StringUtils.isNotBlank(payShopBatch.getExcelUrl()) ){
            newExcelUrl = payShopBatch.getExcelUrl() +','+ response;
        }else{
            newExcelUrl = response;
        }

        PayShopBatch payShopBatchUpdate = new PayShopBatch();
        payShopBatchUpdate.setId(batchId);
        payShopBatchUpdate.setExcelUrl(newExcelUrl);
        payShopBatchUpdate.setSendTime(new Date());
        payShopBatchUpdate.setSendType((byte)1);
        payShopBatchUpdate.setUpdatetime(new Date());
        payShopBatchMapper.updateByPrimaryKeySelective(payShopBatchUpdate);

        // 获取待发券的个人
        List<ShopBatchCouponInfo> couponsList = new ArrayList<>();
        for ( int i=0; i<list.size(); i++){
            // 获取手机号
            LinkedHashMap<String,Object> singlePerson = list.get(i);
            ShopBatchCouponInfo shopBatchCouponInfo = new ShopBatchCouponInfo();
            shopBatchCouponInfo.setActivePhone(singlePerson.get("phone").toString());

            // 获取充值面值的一张券
            PayShopBatchCouponExample couponExample = new PayShopBatchCouponExample();
            PayShopBatchCouponExample.Criteria couponCriteria = couponExample.createCriteria();
            couponCriteria.andCompanyIdEqualTo(companyId);
            couponCriteria.andDouEqualTo(new BigDecimal(singlePerson.get("dou").toString()));
            couponCriteria.andBatchNoEqualTo(batchNo);
            couponCriteria.andIsActiveEqualTo((byte)0);
            couponCriteria.andIsExpiredEqualTo((byte)0);
            List<PayShopBatchCoupon> findCoupons = payShopBatchCouponMapper.selectByExample(couponExample);
            if ( !list.isEmpty() ){
                // 将找到的券分配给个人
                PayShopBatchCoupon findCoupon = findCoupons.get(0);
                shopBatchCouponInfo.setActiveCode(findCoupon.getActiveCode());
                couponsList.add(shopBatchCouponInfo);

                // 将个人的手机号更新到该券的信息中
                findCoupon.setActivePhone(singlePerson.get("phone").toString());
                findCoupon.setSendTime(new Date());
                findCoupon.setSendType((byte)3);
                findCoupon.setUpdatetime(new Date());
                payShopBatchCouponMapper.updateByPrimaryKeySelective(findCoupon);
            }else{
                throw new JpfException("10001","未查到豆数量为"+singlePerson.get("dou")+"的可用的券");
            }
        }

        return couponsList;
    }

    /**
     * 根据批次id批量更新券的发送方式
     */
    @Override
    @Transactional
    public int updateCouponSendTypeByBatchId(String batchId){
        PayShopBatchCouponExample e = new PayShopBatchCouponExample();
        PayShopBatchCouponExample.Criteria c = e.createCriteria();
        c.andBatchIdEqualTo(batchId);
        PayShopBatchCoupon payShopBatchCoupon = new PayShopBatchCoupon();
        payShopBatchCoupon.setSendTime(new Date());
        payShopBatchCoupon.setSendType((byte)0);
        payShopBatchCoupon.setUpdatetime(new Date());

        return payShopBatchCouponMapper.updateByExampleSelective(payShopBatchCoupon,e);
    }

    @Override
    public PayShopBatchCouponResultInfo getCouponsByOrderId(Map<String, Object> map) {
        PayShopBatchCouponExample example = new PayShopBatchCouponExample();
        PayShopBatchCouponExample.Criteria criteria = example.createCriteria();
        if(map.get("sendType")!=null){
            //1 未发放
            if(StringUtils.equals("1",map.get("sendType").toString())){
                criteria.andSendTypeIsNull();
            //2 未发放
            }else{
                criteria.andSendTypeIsNotNull();
            }
        }
        //isActive==1
        if(map.get("isActive")!=null){
            //1 未发放
            if(StringUtils.equals("1",map.get("isActive").toString())){
                criteria.andIsActiveEqualTo((byte)1);
                //2 未发放
            }else{
                 criteria.andIsActiveEqualTo((byte)0);
            }
        }
        if(map.get("orderNo")!=null){
            PayShopCouponOrderExample example1 = new PayShopCouponOrderExample();
            PayShopCouponOrderExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andOrderNoEqualTo(map.get("orderNo").toString());
            List<PayShopCouponOrder> payShopCouponOrders = payShopCouponOrderMapper.selectByExample(example1);
            if(payShopCouponOrders!=null&&payShopCouponOrders.size()!=0){
                criteria.andOrderIdEqualTo(payShopCouponOrders.get(0).getId());
            }else{
                criteria.andOrderIdEqualTo("99999999");
            }
        }else{
            criteria.andOrderIdEqualTo("99999999");
        }
        if (map.get("pageSize")==null||Long.valueOf(map.get("pageSize").toString())<= 0){
            example.setPageSize(10);
        }else{
            example.setPageSize(Long.valueOf(map.get("pageSize").toString()));
        }
        if(map.get("pageNo")==null||Long.valueOf(map.get("pageNo").toString())== 0){
            example.setPageNo(1);
        }else{
            example.setPageNo(Long.valueOf(map.get("pageNo").toString()));
        }
        List<PayShopBatchCoupon> payShopBatchCoupons = payShopBatchCouponMapper.selectByExample(example);
        int count = payShopBatchCouponMapper.countByExample(example);
        List<PayShopBatchCouponResult> payShopBatchCouponResult=new ArrayList<>();
        for (PayShopBatchCoupon payShopBatchCoupon: payShopBatchCoupons) {
            PayShopBatchCouponResult result = new PayShopBatchCouponResult();
            //根据订单查询excel信息
            String start = StringUtils.substring(payShopBatchCoupon.getActiveCode(),0,2);
            String end = StringUtils.substring(payShopBatchCoupon.getActiveCode(),-2,payShopBatchCoupon.getActiveCode().length());
            payShopBatchCoupon.setActiveCode(start+"****"+end);
            if(payShopBatchCoupon.getSendType()==null){
                payShopBatchCoupon.setSendType((byte)5);
            }
            String start1 = StringUtils.substring(payShopBatchCoupon.getCouponNo(),0,5);
            String end2 = StringUtils.substring(payShopBatchCoupon.getCouponNo(),-6,payShopBatchCoupon.getActiveCode().length());
            payShopBatchCoupon.setCouponNo(start1+"****"+end2);

            result.setActiveCode(payShopBatchCoupon.getActiveCode());
            result.setActiveCustomerId(payShopBatchCoupon.getActiveCustomerId());
            result.setActiveName(payShopBatchCoupon.getActiveName());
            result.setActivePhone(payShopBatchCoupon.getActivePhone());
            result.setActiveTime(payShopBatchCoupon.getActiveTime());
            result.setBatchId(payShopBatchCoupon.getBatchId());
            result.setBatchNo(payShopBatchCoupon.getBatchNo());
            result.setCompanyId(payShopBatchCoupon.getCompanyId());
            result.setCompanyName(payShopBatchCoupon.getCompanyName());
            result.setCouponNo(payShopBatchCoupon.getCouponNo());
            result.setDou(payShopBatchCoupon.getDou());
            result.setExpireMonth(payShopBatchCoupon.getExpireMonth());
            result.setExpireTime(payShopBatchCoupon.getExpireTime());
            result.setAddtime(payShopBatchCoupon.getAddtime());
            result.setId(payShopBatchCoupon.getId());
            result.setIsActive(payShopBatchCoupon.getIsActive());
            result.setIsExpired(payShopBatchCoupon.getIsExpired());
            result.setMoney(payShopBatchCoupon.getMoney());
            result.setOrderId(payShopBatchCoupon.getOrderId());
            result.setSendTime(payShopBatchCoupon.getSendTime());
            result.setSendType(payShopBatchCoupon.getSendType());
            result.setStatus(payShopBatchCoupon.getStatus());
            result.setUpdatetime(payShopBatchCoupon.getUpdatetime());
            //查询excel
            PayShopConponExcelExample excelExample = new PayShopConponExcelExample();
            PayShopConponExcelExample.Criteria criteria1 = excelExample.createCriteria();
            criteria1.andCouponIdEqualTo(payShopBatchCoupon.getId());
            List<PayShopConponExcel> payShopConponExcels = payShopConponExcelMapper.selectByExample(excelExample);
            if(payShopConponExcels!=null&&payShopConponExcels.size()!=0){
                result.setUseName(payShopConponExcels.get(0).getUserName());
                result.setPhone(payShopConponExcels.get(0).getUsePhone());
                result.setIdcard(payShopConponExcels.get(0).getIdcard());
            }
            payShopBatchCouponResult.add(result);
        }

        //查询订单的详情
        PayShopBatchCouponResultInfo payShopBatchCouponResultInfo = new PayShopBatchCouponResultInfo();
        if(payShopBatchCoupons!=null&&payShopBatchCoupons.size()!=0){
            PayShopCouponOrderInfoExample example1 = new PayShopCouponOrderInfoExample();
            PayShopCouponOrderInfoExample.Criteria criteria1 = example1.createCriteria();
            example1.setOrderByClause(" price asc");
            criteria1.andOrderIdEqualTo(payShopBatchCoupons.get(0).getOrderId());
            List<PayShopCouponOrderInfo> payShopCouponOrderInfos =payShopCouponOrderInfoMapper.selectByExample(example1);
            StringBuilder stringBuilder = new StringBuilder("商户发送请求共"+payShopCouponOrderInfos.size()+"条记录;-");
            for (PayShopCouponOrderInfo payShopCouponOrderInfo:payShopCouponOrderInfos) {
                stringBuilder.append("面值"+payShopCouponOrderInfo.getPrice()+","+payShopCouponOrderInfo.getNumber()+"笔-");
            }
            payShopBatchCouponResultInfo.setPayShopBatchCoupons(payShopBatchCouponResult);
            payShopBatchCouponResultInfo.setTotal(count);
            payShopBatchCouponResultInfo.setMsg(stringBuilder.toString());
        }
        return payShopBatchCouponResultInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ShopBatchCouponInfo> getCouponsWeb(List<LinkedHashMap<String,Object>> list, String companyId, String batchId, String excelLocalUrl) {
        // OSS上传excel文件
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("path",excelLocalUrl);
        String url = ConfigUtil.getValue("CLOUD_API_URL")+"/oss/upload";
        String response = OkHttpUtils.postForm(url,requestMap);
        // 增加==OSS接口流水==
        PayCloudInterfaceStream payCloudInterfaceStream = new PayCloudInterfaceStream();
        payCloudInterfaceStream.setType((byte)0);
        payCloudInterfaceStream.setRequestUrl(url);
        payCloudInterfaceStream.setRequestContent(ToolUtils.mapToUrl(requestMap));
        payCloudInterfaceStream.setResponseContent(response);
        payCloudInterfaceStream.setAddtime(new Date());
        payCloudInterfaceStreamMapper.insertSelective(payCloudInterfaceStream);
        // 更新批次表相关字段
        PayShopBatch payShopBatch = payShopBatchMapper.selectByPrimaryKey(batchId);
        String newExcelUrl;
        if (StringUtils.isNotBlank(payShopBatch.getExcelUrl()) ){
            newExcelUrl = payShopBatch.getExcelUrl() +','+ response;
        }else{
            newExcelUrl = response;
        }
        payShopBatch.setExcelUrl(newExcelUrl);
        payShopBatch.setSendTime(new Date());
        //payShopBatch.setSendType((byte)1);
        payShopBatch.setUpdatetime(new Date());
        payShopBatch.setStatus((byte)2);
        payShopBatch.setSendType((byte)2);
        payShopBatchMapper.updateByPrimaryKeySelective(payShopBatch);
        // 获取待发券的个人
        List<ShopBatchCouponInfo> couponsList = new ArrayList<>();
        //查询该批次所有的券
        PayShopBatchCouponExample couponExample = new PayShopBatchCouponExample();
        PayShopBatchCouponExample.Criteria couponCriteria = couponExample.createCriteria();
        couponCriteria.andCompanyIdEqualTo(companyId);
        couponCriteria.andOrderIdEqualTo(payShopBatch.getOrderId());
        couponCriteria.andIsActiveEqualTo((byte)0);
        couponCriteria.andIsExpiredEqualTo((byte)0);
        List<PayShopBatchCoupon> findCoupons = payShopBatchCouponMapper.selectByExample(couponExample);
        Iterator<PayShopBatchCoupon> iterator = findCoupons.iterator();
        for (int i=0; i<list.size(); i++) {
            LinkedHashMap<String,Object> singlePerson = list.get(i);
            String name = singlePerson.get("name").toString();
            String phone = singlePerson.get("phone").toString();
            String value = singlePerson.get("dou").toString();
            String idno = singlePerson.get("idno") == null ? "" : singlePerson.get("idno").toString();
            while(iterator.hasNext()){
                PayShopBatchCoupon payShopBatchCoupon =iterator.next();
                if(payShopBatchCoupon.getDou().compareTo(new BigDecimal(value))==0&&payShopBatchCoupon.getSendType()==null){
                    ShopBatchCouponInfo shopBatchCouponInfo = new ShopBatchCouponInfo();
                    shopBatchCouponInfo.setActivePhone(list.get(i).get("phone").toString());
                    shopBatchCouponInfo.setActiveCode(payShopBatchCoupon.getActiveCode());
                    couponsList.add(shopBatchCouponInfo);
                    //payShopBatchCoupon.setActivePhone(shopCustomerInfo.getPhone());
                    payShopBatchCoupon.setSendTime(new Date());
                    payShopBatchCoupon.setSendType((byte)2);
                    payShopBatchCoupon.setUpdatetime(new Date());
                    payShopBatchCouponMapper.updateByPrimaryKeySelective(payShopBatchCoupon);
                    //保存发放记录
                    PayShopConponExcel payShopConponExcel = new PayShopConponExcel();
                    payShopConponExcel.setAddtime(new Date());
                    payShopConponExcel.setBatchId(batchId);
                    payShopConponExcel.setBatchNo(payShopBatch.getBatchNo());
                    payShopConponExcel.setCompanyId(companyId);
                    payShopConponExcel.setCompanyName(payShopBatch.getCompanyName());
                    payShopConponExcel.setIdcard(idno);
                    payShopConponExcel.setOrderId(payShopBatch.getOrderId());
                    //payShopConponExcel.setOrderNo(payShopBatch);
                    payShopConponExcel.setUsePhone(phone);
                    payShopConponExcel.setUserName(name);
                    //增加保留欣券的id
                    payShopConponExcel.setCouponId(payShopBatchCoupon.getId());
                    payShopConponExcelMapper.insertSelective(payShopConponExcel);
                    iterator.remove();
                    break;
                }
            }
        }
        //更改订单状态
        PayShopCouponOrder payShopCouponOrder = payShopCouponOrderMapper.selectByPrimaryKey(payShopBatch.getOrderId());
        payShopCouponOrder.setStatus((byte)4);
        payShopCouponOrderMapper.updateByPrimaryKeySelective(payShopCouponOrder);
        return couponsList;
    }
}
