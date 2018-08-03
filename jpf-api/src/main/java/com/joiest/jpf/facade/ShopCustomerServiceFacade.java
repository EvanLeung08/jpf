package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopCustomerRequest;
import com.joiest.jpf.dto.GetShopCustomerResponse;

public interface ShopCustomerServiceFacade {

    /**
     * 客户列表---后台
     */
    public GetShopCustomerResponse getList(GetShopCustomerRequest request);

    /**
     * 公司停用和启用
     */
    public JpfResponseDto delCompanyCustomer(String id,int type);

}
