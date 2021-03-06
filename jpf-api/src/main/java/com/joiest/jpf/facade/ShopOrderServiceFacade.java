package com.joiest.jpf.facade;

import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.entity.ShopOrderInfo;

import java.util.List;

public interface ShopOrderServiceFacade {

    /**
     * 公司列表---后台
     */
    public GetShopOrderResponse getList(GetShopOrderRequest request);



    /**
     * 获取公司单条信息
     */

    public ShopOrderInfo getOne(String orderNo);

    List<PayShopOrderCustom> getExcelList(GetShopOrderRequest request);
}
