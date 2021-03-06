package com.joiest.jpf.facade;

import com.joiest.jpf.dto.OrderRequest;
import com.joiest.jpf.dto.OrderResponse;
import com.joiest.jpf.entity.OrderInfo;

import java.util.List;
import java.util.Map;

public interface OrderServiceFacade {

    public Long getOrdersCount();

    public OrderResponse getOrders(OrderRequest orderRequest);

    public Map<String, Object> getStatistics(OrderRequest orderRequest);

    public int insOrder(OrderInfo orderInfo);

    public OrderInfo getOrderByOrderidAndForeignOrderid(String orderid, String platformOrderid, boolean forInterface);

    public int updateOrdername(OrderInfo orderInfo, boolean forInterface);

    // 根据orderid获取订单信息
    public OrderInfo getOrderByOrderid(String orderid, boolean forInterface);

    public int updataSignOrderid(OrderInfo orderInfo);

    // 更新指定字段,必须指定orderid
    public int updateColumnByOrderid(OrderInfo orderInfo);

    public List<OrderInfo> getAllOrders();
}
