package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudStaffBanksInfo;

import java.math.BigInteger;

public interface CloudStaffBanksServiceFacade {

    /**
     * 通过银行卡号、员工号获取员工银行卡信息
     * */
    public CloudStaffBanksInfo getStaffBankByNumSid(String num, BigInteger id);

    /**
     * 通过员工号、手机号获取员工银行卡信息
     * */
    public CloudStaffBanksInfo getStaffBankBySidPhone(String id,String mobile);

    /**
     * 根据指定条件获取信息
     */
    public CloudStaffBanksInfo getStaffBankByInfo(CloudStaffBanksInfo cloudStaffBanksInfo);

    /**
     * 插入员工银行卡信息
     */
    public int addStaffBank(CloudStaffBanksInfo cloudStaffBanksInfo);

    /**
     * 更新字段
     */
    public int updateColumn(CloudStaffBanksInfo cloudStaffBanksInfo);

}
