package com.joiest.jpf.facade;

import net.sf.json.JSONObject;

import java.util.Map;

public interface ShopStockCardServiceFacade {

    /**
     * 添加采购商品
     * */
    public int addShopStockCard(JSONObject stockCard,String OrderNo) throws Exception;

    /***
     *根据订单更新商品库存
     * */
    public int upProductStockByOrderNo(String order_no) throws Exception;
}