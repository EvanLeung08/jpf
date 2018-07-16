package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.CloudCompanyInfo;


public interface CloudCompanyServiceFacade {


    /**
     * 代理列表---后台
     */
    public GetCloudCompanyResponse getAgentList(GetCloudCompanyRequest request);
    /**
     * 业务公司列表---后台
     */
    public GetCloudCompanyResponse getSaleList(GetCloudCompanyRequest request);
    /**
     * 云账户金额校验
     */
    public Boolean checkCompanyMoneyVerify(String id);

    /**
     * 添加公司
     */
    public JpfResponseDto addCloudCompany(GetCloudCompanyRequest request,int account) throws Exception;


    /**
     * 修改公司
     */

    public JpfResponseDto  editCloudCompany(GetCloudCompanyRequest request,int account) throws Exception;


    /**
     * 获取公司单条信息
     */

    public CloudCompanyInfo getCompanyOne(String id,int type);

    /**
     * 锁定公司
     */
    public JpfResponseDto delCompany(String merchNo,int type);
    /**
     * 获取公司信息，不区分类型
     */
    public GetCloudCompanysResponse getAllCompanys(GetCloudCompanysRequest request);

    /**
     * 根据主键id获取单个公司的信息
     */
    public CloudCompanyInfo getRecById(String id);
    /**
     * 商户后台
     * 根据商户编号获取单个公司的信息
     */
    public CloudCompanyInfo getMerchInfoByMerchNo(String merchNo);
}
