package com.joiest.jpf.facade;

import com.joiest.jpf.dto.OrderRequest;
import com.joiest.jpf.dto.OrderResponse;
import com.joiest.jpf.entity.OrderInterfaceInfo;

import java.util.Map;

public interface OrderInterfaceServiceFacade {

    /**
     * 根据 orderid 获取商户信息&产品信息&订单支付方式
     */
    public OrderInterfaceInfo getOrder(String orderid);
}
