package com.joiest.jpf.facade;

import com.joiest.jpf.entity.ShopOrderInterfaceInfo;

public interface ShopOrderInterfaceServiceFacade {

    /**
     * 添加订单
     */
    public int addOrder(ShopOrderInterfaceInfo info);

    /**
     * 获取单条订单信息
     */
    public ShopOrderInterfaceInfo getOrderOne(String orderNo, String uid);

    /**
     * 获取用户订单数量，已取消的不统计
     */
    public int getOrdersCount(String customerId);

}
