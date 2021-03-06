package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopBargainOrderRequest;
import com.joiest.jpf.dto.GetShopBargainOrderResponse;
import com.joiest.jpf.entity.ShopBargainOrderInfo;

public interface ShopBargainOrderServiceFacade {
    /**
     * 运营采购订单列表---后台
     */
    public GetShopBargainOrderResponse getList(GetShopBargainOrderRequest request);

    /**
     * 前台转让订单列表页
     */
    public GetShopBargainOrderResponse getFrontList(GetShopBargainOrderRequest request);

    /**
     * 获取单条信息
     */

    public ShopBargainOrderInfo getOne(String orderNo);


    /**
     *卖家转让下单
     * */
    public int sellerPlaceOrder(ShopBargainOrderInfo orderInfo);

    /*
     * 审核收购订单信息
     * */
    public JpfResponseDto AuditOrder(GetShopBargainOrderRequest request) throws Exception;


    /**
     * 获取订单详情
     * */
    public ShopBargainOrderInfo getBargainOrderByNo(String orderNo);

    /**
     * 获取
     * */
    public int getBargainOrderCountByCustomId(String customId,String type);

}
