package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopCouponActive implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 顾客姓名
     */
    private String customerName;

    /**
     * 企业id
     */
    private Integer companyId;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 批次id
     */
    private Integer batchId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 券号
     */
    private String couponNo;

    /**
     * 激活码
     */
    private String activeCode;

    /**
     * 支付方式 0=欣豆 1=微信
     */
    private Byte payWay;

    /**
     * 面值
     */
    private BigDecimal money;

    /**
     * 豆数量
     */
    private BigDecimal dou;

    /**
     * 根据行为不同显示的内容不同
     */
    private String content;

    /**
     * 行为 0=激活 1=消费 2=退豆 3=过期 4=服务转让的冻结 5=卖家服务转让成功 6=服务转让的取消 7=买家收豆成功
     */
    private String type;

    /**
     * 到期时间
     */
    private Date expireTime;

    /**
     * 消费时对应订单表的id
     */
    private String orderId;

    /**
     * 消费或退款等对应的订单号
     */
    private String orderNo;

    /**
     * 转让服务订单id
     */
    private String bargainOrderId;

    /**
     * 转让服务订单号
     */
    private String bargainOrderNo;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 流水的来源，0代表之前的订单流水，1代表商城的订单流水
     */
    private String source;

    /**
     * 合同的余额
     */
    private BigDecimal contractSurplus;

    /**
     * 欣券的余额
     */
    private BigDecimal couponSurplus;

    /**
     * 欣券扣除类型 0扣除不可转让豆 1扣除可转让豆
     */
    private Byte subCouponType;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo == null ? null : couponNo.trim();
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode == null ? null : activeCode.trim();
    }

    public Byte getPayWay() {
        return payWay;
    }

    public void setPayWay(Byte payWay) {
        this.payWay = payWay;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getDou() {
        return dou;
    }

    public void setDou(BigDecimal dou) {
        this.dou = dou;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getBargainOrderId() {
        return bargainOrderId;
    }

    public void setBargainOrderId(String bargainOrderId) {
        this.bargainOrderId = bargainOrderId == null ? null : bargainOrderId.trim();
    }

    public String getBargainOrderNo() {
        return bargainOrderNo;
    }

    public void setBargainOrderNo(String bargainOrderNo) {
        this.bargainOrderNo = bargainOrderNo == null ? null : bargainOrderNo.trim();
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public BigDecimal getContractSurplus() {
        return contractSurplus;
    }

    public void setContractSurplus(BigDecimal contractSurplus) {
        this.contractSurplus = contractSurplus;
    }

    public BigDecimal getCouponSurplus() {
        return couponSurplus;
    }

    public void setCouponSurplus(BigDecimal couponSurplus) {
        this.couponSurplus = couponSurplus;
    }

    public Byte getSubCouponType() {
        return subCouponType;
    }

    public void setSubCouponType(Byte subCouponType) {
        this.subCouponType = subCouponType;
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
        sb.append(", customerId=").append(customerId);
        sb.append(", customerName=").append(customerName);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", batchId=").append(batchId);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", couponNo=").append(couponNo);
        sb.append(", activeCode=").append(activeCode);
        sb.append(", payWay=").append(payWay);
        sb.append(", money=").append(money);
        sb.append(", dou=").append(dou);
        sb.append(", content=").append(content);
        sb.append(", type=").append(type);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", bargainOrderId=").append(bargainOrderId);
        sb.append(", bargainOrderNo=").append(bargainOrderNo);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", source=").append(source);
        sb.append(", contractSurplus=").append(contractSurplus);
        sb.append(", couponSurplus=").append(couponSurplus);
        sb.append(", subCouponType=").append(subCouponType);
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
        PayShopCouponActive other = (PayShopCouponActive) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getBatchId() == null ? other.getBatchId() == null : this.getBatchId().equals(other.getBatchId()))
            && (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()))
            && (this.getCouponNo() == null ? other.getCouponNo() == null : this.getCouponNo().equals(other.getCouponNo()))
            && (this.getActiveCode() == null ? other.getActiveCode() == null : this.getActiveCode().equals(other.getActiveCode()))
            && (this.getPayWay() == null ? other.getPayWay() == null : this.getPayWay().equals(other.getPayWay()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getDou() == null ? other.getDou() == null : this.getDou().equals(other.getDou()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getBargainOrderId() == null ? other.getBargainOrderId() == null : this.getBargainOrderId().equals(other.getBargainOrderId()))
            && (this.getBargainOrderNo() == null ? other.getBargainOrderNo() == null : this.getBargainOrderNo().equals(other.getBargainOrderNo()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getContractSurplus() == null ? other.getContractSurplus() == null : this.getContractSurplus().equals(other.getContractSurplus()))
            && (this.getCouponSurplus() == null ? other.getCouponSurplus() == null : this.getCouponSurplus().equals(other.getCouponSurplus()))
            && (this.getSubCouponType() == null ? other.getSubCouponType() == null : this.getSubCouponType().equals(other.getSubCouponType()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getBatchId() == null) ? 0 : getBatchId().hashCode());
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        result = prime * result + ((getCouponNo() == null) ? 0 : getCouponNo().hashCode());
        result = prime * result + ((getActiveCode() == null) ? 0 : getActiveCode().hashCode());
        result = prime * result + ((getPayWay() == null) ? 0 : getPayWay().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getDou() == null) ? 0 : getDou().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getBargainOrderId() == null) ? 0 : getBargainOrderId().hashCode());
        result = prime * result + ((getBargainOrderNo() == null) ? 0 : getBargainOrderNo().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getContractSurplus() == null) ? 0 : getContractSurplus().hashCode());
        result = prime * result + ((getCouponSurplus() == null) ? 0 : getCouponSurplus().hashCode());
        result = prime * result + ((getSubCouponType() == null) ? 0 : getSubCouponType().hashCode());
        return result;
    }
}