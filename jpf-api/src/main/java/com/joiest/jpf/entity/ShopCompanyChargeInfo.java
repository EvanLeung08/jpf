package com.joiest.jpf.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopCompanyChargeInfo implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 运营操作人id
     */
    private String operatorId;

    /**
     * 运营操作人姓名
     */
    private String operatorName;

    /**
     * 付款凭证图片地址（阿里云）
     */
    private String imgUrl;

    /**
     * 充值金额
     */
    private BigDecimal money;

    /**
     * 费率
     */
    private BigDecimal rate;

    /**
     * 合同金额
     */
    private BigDecimal contractMoney;

    /**
     * 财务审核操作者id
     */
    private String checkOperatorId;

    /**
     * 财务审核操作者姓名
     */
    private String checkOperatorName;

    /**
     * 审核时间
     */
    private Date checkTime;

    /**
     * 充值状态 -1=运营取消 0=新增，待审核 1=审核通过，已充值 2=审核驳回
     */
    private Byte status;

    /**
     * 充值状态 -1=运营取消 0=新增，待审核 1=审核通过，已充值 2=审核驳回
     */
    private String statusCn;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 合同号
     */
    private String contractNo;

    /**
     * 合同到期时间
     */
    private Date duetime;

    /**
     * 消费转让率
     */
    private BigDecimal transferRate;

    /**
     * 欣券金额
     */
    private BigDecimal couponMoney;

    /**
     * 服务金额
     */
    private BigDecimal serviceMoney;

    /**
     * 剩余金额
     */
    private BigDecimal balance;

    /**
     * 服务内容
     */
    private String serviceContent;

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Date getDuetime() {
        return duetime;
    }

    public void setDuetime(Date duetime) {
        this.duetime = duetime;
    }

    public BigDecimal getTransferRate() {
        return transferRate;
    }

    public void setTransferRate(BigDecimal transferRate) {
        this.transferRate = transferRate;
    }

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public BigDecimal getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(BigDecimal serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getCheckOperatorId() {
        return checkOperatorId;
    }

    public void setCheckOperatorId(String checkOperatorId) {
        this.checkOperatorId = checkOperatorId == null ? null : checkOperatorId.trim();
    }

    public String getCheckOperatorName() {
        return checkOperatorName;
    }

    public void setCheckOperatorName(String checkOperatorName) {
        this.checkOperatorName = checkOperatorName == null ? null : checkOperatorName.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(BigDecimal contractMoney) {
        this.contractMoney = contractMoney;
    }

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", money=").append(money);
        sb.append(", checkOperatorId=").append(checkOperatorId);
        sb.append(", checkOperatorName=").append(checkOperatorName);
        sb.append(", checkTime=").append(checkTime);
        sb.append(", status=").append(status);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }

    /**
     *
     * @param that
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ShopCompanyChargeInfo other = (ShopCompanyChargeInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getImgUrl() == null ? other.getImgUrl() == null : this.getImgUrl().equals(other.getImgUrl()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getCheckOperatorId() == null ? other.getCheckOperatorId() == null : this.getCheckOperatorId().equals(other.getCheckOperatorId()))
            && (this.getCheckOperatorName() == null ? other.getCheckOperatorName() == null : this.getCheckOperatorName().equals(other.getCheckOperatorName()))
            && (this.getCheckTime() == null ? other.getCheckTime() == null : this.getCheckTime().equals(other.getCheckTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getImgUrl() == null) ? 0 : getImgUrl().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getCheckOperatorId() == null) ? 0 : getCheckOperatorId().hashCode());
        result = prime * result + ((getCheckOperatorName() == null) ? 0 : getCheckOperatorName().hashCode());
        result = prime * result + ((getCheckTime() == null) ? 0 : getCheckTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }

    public String getStatusCn() {
        return statusCn;
    }

    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }
}