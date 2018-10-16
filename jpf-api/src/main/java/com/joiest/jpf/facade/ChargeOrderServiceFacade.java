package com.joiest.jpf.facade;

import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.dto.GetChargeOrderRequest;
import com.joiest.jpf.dto.GetChargeOrderResponse;
import com.joiest.jpf.entity.ChargeOrderInfo;

import java.util.List;

public interface ChargeOrderServiceFacade {

    /**
     * 查询单条信息
     */
    public ChargeOrderInfo getOne(PayChargeOrder record);

    /**
     * 生成订单
     * */
    public int placeOrder(ChargeOrderInfo placeOrderInfo);

    /**
     * 更新订单新
     * */
    public int upOrderInfo(ChargeOrderInfo upOrderInfo);
    /*
     * 查询订单
     */
    public GetChargeOrderResponse getRecords(GetChargeOrderRequest request);

}
