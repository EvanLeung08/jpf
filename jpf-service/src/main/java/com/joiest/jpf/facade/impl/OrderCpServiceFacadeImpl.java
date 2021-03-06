package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.PayOrderCp;
import com.joiest.jpf.common.po.PayOrderCpExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderCpMapper;
import com.joiest.jpf.entity.OrderCpInterfaceInfo;
import com.joiest.jpf.facade.OrderCpServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class OrderCpServiceFacadeImpl implements OrderCpServiceFacade {

    @Autowired
    private PayOrderCpMapper payOrderCpMapper;

    /**
     * 根据 orderid 获取商户签约信息
     */
    @Override
    public OrderCpInterfaceInfo getOrderCpByorderid(String orderid)
    {
        PayOrderCpExample example = new PayOrderCpExample();
        PayOrderCpExample.Criteria c = example.createCriteria();
        c.andOrderidEqualTo(orderid);
        List<PayOrderCp> ordercpList = payOrderCpMapper.selectByExample(example);
        OrderCpInterfaceInfo orderCpInterfaceInfo = new OrderCpInterfaceInfo();
        if ( ordercpList == null || ordercpList.isEmpty())
        {
            return null;
        }
        PayOrderCp payOrderCp = ordercpList.get(0);

        BeanCopier beanCopier = BeanCopier.create( PayOrderCp.class, OrderCpInterfaceInfo.class, false);
        beanCopier.copy(payOrderCp, orderCpInterfaceInfo, null);
        return orderCpInterfaceInfo;
    }

    /**
     * 根据银行卡号判断用户是否已签约
     */
    @Override
    public OrderCpInterfaceInfo getOrderCpBybankaccountnumber(String bankaccountnumber){
        PayOrderCpExample example = new PayOrderCpExample();
        PayOrderCpExample.Criteria c = example.createCriteria();
//        c.andSignstatusEqualTo("2");
        c.andBankaccountnumberEqualTo(bankaccountnumber);
        List<PayOrderCp> ordercpList = payOrderCpMapper.selectByExample(example);

        if ( ordercpList == null || ordercpList.isEmpty() )
        {
            return new OrderCpInterfaceInfo();
        }

        OrderCpInterfaceInfo orderCpInterfaceInfo = new OrderCpInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create( PayOrderCp.class, OrderCpInterfaceInfo.class, false);
        beanCopier.copy(ordercpList.get(0), orderCpInterfaceInfo, null);

        return orderCpInterfaceInfo;
    }

    /**
     * 插入一条签约记录
     */
    @Override
    public int insRecord(OrderCpInterfaceInfo orderCpInterfaceInfo){
        PayOrderCp payOrderCp = new PayOrderCp();
        payOrderCp.setOrderid(orderCpInterfaceInfo.getOrderid());
        payOrderCp.setMtsid(orderCpInterfaceInfo.getMtsid());
        payOrderCp.setInterestmode(orderCpInterfaceInfo.getInterestmode());
        payOrderCp.setSubmerid(orderCpInterfaceInfo.getSubmerid());
        payOrderCp.setSubmername(orderCpInterfaceInfo.getSubmername());
        payOrderCp.setSubmerabbr(orderCpInterfaceInfo.getSubmerabbr());
        payOrderCp.setSignedname(orderCpInterfaceInfo.getSignedname());
        payOrderCp.setIdtype(orderCpInterfaceInfo.getIdtype());
        payOrderCp.setIdno(orderCpInterfaceInfo.getIdno());
        payOrderCp.setMobileno(orderCpInterfaceInfo.getMobileno());
        payOrderCp.setSelectfinacode(orderCpInterfaceInfo.getSelectfinacode());
        payOrderCp.setBankaccounttype(orderCpInterfaceInfo.getBankaccounttype());
        payOrderCp.setBankaccountnumber(orderCpInterfaceInfo.getBankaccountnumber());
        payOrderCp.setCvn2(orderCpInterfaceInfo.getCvn2());
        payOrderCp.setValiditycard(orderCpInterfaceInfo.getValiditycard());
        payOrderCp.setValidityyear(orderCpInterfaceInfo.getValidityyear());
        payOrderCp.setClientip(orderCpInterfaceInfo.getClientip());
        payOrderCp.setSignstatus(orderCpInterfaceInfo.getSignstatus());
        payOrderCp.setSysagreeno(orderCpInterfaceInfo.getSysagreeno());
        payOrderCp.setCreated(orderCpInterfaceInfo.getCreated());

        return payOrderCpMapper.insertSelective(payOrderCp);
    }

    /**
     * 更新聚合支付传过来的签约参数
     */
    @Override
    public int updateRecord(OrderCpInterfaceInfo orderCpInterfaceInfo){
        PayOrderCpExample e = new PayOrderCpExample();
        PayOrderCpExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(orderCpInterfaceInfo.getOrderid());

        PayOrderCp payOrderCp = new PayOrderCp();
        if ( orderCpInterfaceInfo.getTranno() != null ){
            payOrderCp.setTranno(orderCpInterfaceInfo.getTranno());
        }
        if ( orderCpInterfaceInfo.getSignstatus() != null ){
            payOrderCp.setSignstatus(orderCpInterfaceInfo.getSignstatus());
        }
        if ( orderCpInterfaceInfo.getSysagreeno() != null ){
            payOrderCp.setSysagreeno(orderCpInterfaceInfo.getSysagreeno());
        }
        if ( orderCpInterfaceInfo.getReturnContent() != null ){
            payOrderCp.setReturnContent(orderCpInterfaceInfo.getReturnContent());
        }
        if ( orderCpInterfaceInfo.getNewSignOrderid() != null ){
            payOrderCp.setOrderid(orderCpInterfaceInfo.getNewSignOrderid());
        }

        return payOrderCpMapper.updateByExampleSelective(payOrderCp, e);
    }
}
