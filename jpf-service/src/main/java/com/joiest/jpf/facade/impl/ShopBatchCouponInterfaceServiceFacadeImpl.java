package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.generate.*;
import com.joiest.jpf.facade.ShopBatchCouponInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ShopBatchCouponInterfaceServiceFacadeImpl implements ShopBatchCouponInterfaceServiceFacade {

    @Autowired
    private PayShopBatchCouponMapper payShopBatchCouponMapper;

    @Autowired
    private PayShopCustomerMapper payShopCustomerMapper;

    @Autowired
    private PayShopBatchMapper payShopBatchMapper;

    @Autowired
    private PayShopCouponActiveMapper payShopCouponActiveMapper;


    @Autowired
    private PayShopCouponRemainMapper payShopCouponRemainMapper;
    /**
     * 根据激活码获取券并且激活
     */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public int  getCouponByCouponNo(String couponNo,String uid){

        PayShopBatchCouponExample e = new PayShopBatchCouponExample();
        PayShopBatchCouponExample.Criteria c = e.createCriteria();
        c.andActiveCodeEqualTo(couponNo);
        List<PayShopBatchCoupon> list = payShopBatchCouponMapper.selectByExample(e);

        if ( list == null || list.isEmpty() )
        {
            return  3;//激活码有误
        }
        if(list.get(0).getIsActive()==1){
           return 2;//已经激活
        }

        //根据批次id查处当前批次信息
        String  batchid=list.get(0).getBatchId();

        PayShopBatchExample batche=new PayShopBatchExample();
        PayShopBatchExample.Criteria cbatch=batche.createCriteria();
        cbatch.andIdEqualTo(batchid);
        List<PayShopBatch> listBatch = payShopBatchMapper.selectByExample(batche);

        if ( listBatch == null || listBatch.isEmpty() )
        {
            return  4;//批次信息有误
        }
        //根据uid查出用户信息
        PayShopCustomerExample ecustom = new PayShopCustomerExample();
        PayShopCustomerExample.Criteria ccustom = ecustom.createCriteria();
        ccustom.andIdEqualTo(uid);
        List<PayShopCustomer> listCustom = payShopCustomerMapper.selectByExample(ecustom);

        if ( listCustom == null || listCustom.isEmpty() )
        {
            return  5;//用户信息有误
        }
        String name=listCustom.get(0).getName();

        //进行金额校验
        Integer dou=listCustom.get(0).getDou();
        String code=listCustom.get(0).getCode();
        Boolean res= ToolUtils.ValidateCode(code,uid,dou.toString());
        if(res==false){
            return 6;//金额校验失败
        }
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.MONTH, list.get(0).getExpireMonth());  //设置为几个月
        dBefore = calendar.getTime();   //得到几个月后的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd 00:00:00"); //设置时间格式
        String LastM= sdf.format(dBefore.getTime() + 24 * 3600 * 1000);    //格式化几个月后的时间
        Date dateAddMonth=null;

        try {
             dateAddMonth= sdf.parse(LastM);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        //添加active欣券行为明细
        PayShopCouponActive active =new PayShopCouponActive();

        active.setCustomerId(uid);
        active.setCustomerName(listCustom.get(0).getName());
        active.setCompanyId(listCustom.get(0).getCompanyId().intValue());
        active.setCompanyName(listCustom.get(0).getCompanyName());
        //字符串转换interger
        active.setBatchId(Integer.valueOf(batchid));
        active.setBatchNo(listBatch.get(0).getBatchNo());
        active.setCouponNo(list.get(0).getCouponNo());
        active.setActiveCode(couponNo);
        active.setPayWay((byte)0);
        BigDecimal newMoney = new BigDecimal(list.get(0).getMoney());
        active.setMoney(newMoney);
        active.setDou(list.get(0).getDou());
        active.setContent("欣券激活");
        active.setType("0");
        active.setExpireTime(dateAddMonth);
        active.setAddtime(dNow);
        int activeIsert=payShopCouponActiveMapper.insertSelective(active);
        //修改券表
        PayShopBatchCoupon coupon =new PayShopBatchCoupon();
        coupon.setIsActive((byte)1);
        coupon.setActiveTime(dNow);
        coupon.setExpireTime(dateAddMonth);
        coupon.setUpdatetime(dNow);
        PayShopBatchCouponExample exampleup= new PayShopBatchCouponExample();
        PayShopBatchCouponExample.Criteria ca = exampleup.createCriteria();
        ca.andActiveCodeEqualTo(couponNo);
        int upcoupon=payShopBatchCouponMapper.updateByExampleSelective(coupon,exampleup);

        //修改客户表
        PayShopCustomer custoner=new PayShopCustomer();
        Integer douAll=dou+list.get(0).getDou();
        custoner.setDou(douAll);
        //充值校验码
        String newCodeALL = Md5Encrypt.md5(uid+douAll+"test","UTF-8");
        custoner.setCode(newCodeALL);
        custoner.setUpdatetime(dNow);
        PayShopCustomerExample custexple=new PayShopCustomerExample();
        PayShopCustomerExample.Criteria explec=custexple.createCriteria();
        explec.andIdEqualTo(uid);
        int upcustom=payShopCustomerMapper.updateByExampleSelective(custoner,custexple);

        //添加欣券行为批次表a=remain
        PayShopCouponRemain  remain = new  PayShopCouponRemain();
        remain.setCouponId(list.get(0).getId());
        remain.setCouponNo(list.get(0).getCouponNo());
        remain.setCouponActiveCode(couponNo);
        remain.setCustomerId(uid);
        remain.setCouponDou(list.get(0).getDou());
        remain.setCouponDouLeft(list.get(0).getDou());
        remain.setStatus((byte)0);
        remain.setExpireTime(dateAddMonth);
        remain.setAddtime(dNow);
        int insertRemain=payShopCouponRemainMapper.insertSelective(remain);
        //修改批次的激活数量
        PayShopBatch payShopBatch =new PayShopBatch();
        payShopBatch.setActivetedNum(listBatch.get(0).getActivetedNum()+1);
        payShopBatchMapper.updateByExampleSelective(payShopBatch,batche);
        if(activeIsert==1 && upcoupon==1 && upcustom ==1 && insertRemain==1 ){
            return 1;//激活成功
        }else{
            return 7;//激活失败
        }
    }
}
