package com.joiest.jpf.facade;

import com.joiest.jpf.dto.OrderYinjiaApiRequest;
import com.joiest.jpf.dto.OrderYinjiaApiResponse;
import com.joiest.jpf.entity.OrderYinjiaApiInfo;

public interface OrderYinjiaApiServiceFacade {

    public int insOrder(OrderYinjiaApiInfo orderYinjiaApiInfo);

    public OrderYinjiaApiInfo getOrderByOrderidAndForeignOrderid(String orderid, String platformOrderid, boolean forInterface);
    public OrderYinjiaApiInfo getOrderByForeignOrderid(String orderid);
    public OrderYinjiaApiInfo getOrderByOrderid(String orderid, boolean forInterface);

    public OrderYinjiaApiInfo getOrderBySignOrderid(String signOrerid, boolean forInterface);

    public int updataSignOrderid(OrderYinjiaApiInfo orderYinjiaApiInfo);

    public int updateColumnByOrderid(OrderYinjiaApiInfo orderYinjiaApiInfo);

    public int updateColumnBySignOrderid(OrderYinjiaApiInfo orderYinjiaApiInfo);

    public int updateOrdername(OrderYinjiaApiInfo orderYinjiaApiInfo, boolean forInterface);

    /**
     * 获取 YinjinApi order List ---后台
     */
    public OrderYinjiaApiResponse getOrderYinjiaApi(OrderYinjiaApiRequest request);

    /**
     * 根据 orderid 获取订单信息 ---后台
     * @param orderid
     * @return
     */
    public OrderYinjiaApiInfo getOrderYinjiaApiByOrderid(String orderid);

    /**
     * 获取 YinjinApi order getRecords  根据pay_orders_money_detail去重
     */
    public OrderYinjiaApiResponse getOrderYinjiaApiDuplicateRemove(OrderYinjiaApiRequest request);
}
