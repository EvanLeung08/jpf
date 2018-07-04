package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudCompanyStaffInfo;

import java.util.Map;

public interface CloudCompanyStaffServiceFacade {

    /*
    * 获取员工信息通过身份证号
    * */
    public CloudCompanyStaffInfo getCloudCompanyStaffByIdcard(String cardNo);

    /*
    * 通过身份证号更新员工信息
    * */
    public int upCloudCompanyStaffByIdcard(String idcard, Map<String,String> map);

    /*
     *获取员工信息通过身主键
    * */
    public CloudCompanyStaffInfo getCloudCompanyStaffById(String id);

}