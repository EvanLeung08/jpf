package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudDfMoneyInfo {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 代理聚合商户编号 merch_no
     */
    private String agentNo;

    /**
     * 聚合商户企业编号
     */
    private String merchNo;

    /**
     * 员工表用户签约状态
     */
    private String companyStaffIsActice;

    /**
     * 合同表合同签约状态
     */
    private String compactStaffCompactActive;

    /**
     * 企业添加人ID
     */
    private Long uid;

    /**
     * 充值到账对应的交易记录编号
     */
    private String fid;

    /**
     * pay_cloud_company_staff表主键ID
     */
    private Long busstaffid;

    /**
     * 手机号
     */
    private String username;

    /**
     * 预发金额
     */
    private BigDecimal preMoney;

    /**
     * 个人所得税费率
     */
    private BigDecimal incomeRate;

    /**
     * 发放金额
     */
    private BigDecimal commoney;

    /**
     * 银行卡号
     */
    private String bankno;

    /**
     * 收款人
     */
    private String banknickname;

    /**
     * 身份证号
     */
    private String idno;

    /**
     * 银行预留手机号
     */
    private String bankphone;

    /**
     * 开户行
     */
    private String bankname;

    /**
     * 开户行省
     */
    private String bankprovince;

    /**
     * 开户行市
     */
    private String bankcity;

    /**
     * 卡类型 例如：建行 工商
     */
    private String banktype;

    /**
     * 银行编码 如：ICBC，CCB等
     */
    private String bankcode;

    /**
     * 收款人账号属性 0=PRIVATE对私,1=PUBLIC对公
     */
    private Integer bankacctattr;

    /**
     * 发放时间
     */
    private Date addtime;

    /**
     * 姓名
     */
    private String realname;

    /**
     * 打款状态0:未申请打款 1:待打款，2=打款成功 3=打款失败，4=打款中
     */
    private Integer montype;

    /**
     * 打款备注
     */
    private String remark;

    /**
     * 虚拟类型 cat 001 类型
     */
    private Integer vid;

    /**
     * 用户代付状态  0 不可代付 1  已激活
     */
    private Integer isActive;

    /**
     * 是否冻结 1:未冻结 2:已冻结
     */
    private Byte isFreeze;

    /**
     * 操作信息
     */
    private String content;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 操作信息状态 0=申请中
     */
    private Integer operastate;

    /**
     * 第三方交易号
     */
    private String tranno;

    /**
     * 打款单号
     */
    private String orderid;

    /**
     * 打款单号顺序集合
     */
    private String orderids;

    /**
     * 应发金额
     */
    private BigDecimal payablemoney;

    /**
     * 代扣金额
     */
    private BigDecimal withholdmoney;

    /**
     * 是否提交报税 1=未申报 2=已申报
     */
    private Integer invostatus;

    /**
     * 关联批次订单表的id
     */
    private String companyMoneyId;

    /**
     * 企业充值合同编号
     */
    private String pactno;

    //财务报税报表字段
    /**
     * 征收项目
     */
    private String aproject;

    /**
     * 征收品目
     */

    private String Collection;

    /**
     * 征收子目
     */
    private String subtitle;

    /**
     * 税款所属起始
     */
    private String startTime;

    /**
     * 税款所属起止
     */
    private String endTime;


    /**
     * 应发金额
     */
    private BigDecimal realmoney;

    /**
     * 税率
     */
    private BigDecimal tax;

    /**
     * 应代征税额
     */
    private BigDecimal taxMoney;

    /**
     * 已代征税额
     */
    private BigDecimal taxMoneyPass;


    /**
     * 代征次数
     */
    private Integer collentNum;


    //=====end=====
    /**
     * 服务内容
     */
    private String ticketcontent;

    /**
     * 个人统计总和金额
     */
    private BigDecimal countMoney;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Long getBusstaffid() {
        return busstaffid;
    }

    public void setBusstaffid(Long busstaffid) {
        this.busstaffid = busstaffid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getPreMoney() {
        return preMoney;
    }

    public void setPreMoney(BigDecimal preMoney) {
        this.preMoney = preMoney;
    }

    public BigDecimal getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(BigDecimal incomeRate) {
        this.incomeRate = incomeRate;
    }

    public BigDecimal getCommoney() {
        return commoney;
    }

    public void setCommoney(BigDecimal commoney) {
        this.commoney = commoney;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getBanknickname() {
        return banknickname;
    }

    public void setBanknickname(String banknickname) {
        this.banknickname = banknickname;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getBankphone() {
        return bankphone;
    }

    public void setBankphone(String bankphone) {
        this.bankphone = bankphone;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankprovince() {
        return bankprovince;
    }

    public void setBankprovince(String bankprovince) {
        this.bankprovince = bankprovince;
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity;
    }

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public Integer getBankacctattr() {
        return bankacctattr;
    }

    public void setBankacctattr(Integer bankacctattr) {
        this.bankacctattr = bankacctattr;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getMontype() {
        return montype;
    }

    public void setMontype(Integer montype) {
        this.montype = montype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Byte getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Byte isFreeze) {
        this.isFreeze = isFreeze;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getOperastate() {
        return operastate;
    }

    public void setOperastate(Integer operastate) {
        this.operastate = operastate;
    }

    public String getTranno() {
        return tranno;
    }

    public void setTranno(String tranno) {
        this.tranno = tranno;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderids() {
        return orderids;
    }

    public void setOrderids(String orderids) {
        this.orderids = orderids;
    }

    public BigDecimal getPayablemoney() {
        return payablemoney;
    }

    public void setPayablemoney(BigDecimal payablemoney) {
        this.payablemoney = payablemoney;
    }

    public BigDecimal getWithholdmoney() {
        return withholdmoney;
    }

    public void setWithholdmoney(BigDecimal withholdmoney) {
        this.withholdmoney = withholdmoney;
    }

    public Integer getInvostatus() {
        return invostatus;
    }

    public void setInvostatus(Integer invostatus) {
        this.invostatus = invostatus;
    }

    public String getCompanyMoneyId() {
        return companyMoneyId;
    }

    public void setCompanyMoneyId(String companyMoneyId) {
        this.companyMoneyId = companyMoneyId;
    }

    public String getPactno() {
        return pactno;
    }

    public void setPactno(String pactno) {
        this.pactno = pactno;
    }

    public String getCompanyStaffIsActice() {
        return companyStaffIsActice;
    }

    public void setCompanyStaffIsActice(String companyStaffIsActice) {
        this.companyStaffIsActice = companyStaffIsActice;
    }

    public String getCompactStaffCompactActive() {
        return compactStaffCompactActive;
    }

    public void setCompactStaffCompactActive(String compactStaffCompactActive) {
        this.compactStaffCompactActive = compactStaffCompactActive;
    }

    public String getTicketcontent() {
        return ticketcontent;
    }

    public void setTicketcontent(String ticketcontent) {
        this.ticketcontent = ticketcontent;
    }

    public String getAproject() {
        return aproject;
    }

    public void setAproject(String aproject) {
        this.aproject = aproject;
    }

    public String getCollection() {
        return Collection;
    }

    public void setCollection(String collection) {
        Collection = collection;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getRealmoney() {
        return realmoney;
    }

    public void setRealmoney(BigDecimal realmoney) {
        this.realmoney = realmoney;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTaxMoney() {
        return taxMoney;
    }

    public void setTaxMoney(BigDecimal taxMoney) {
        this.taxMoney = taxMoney;
    }

    public Integer getCollentNum() {
        return collentNum;
    }

    public void setCollentNum(Integer collentNum) {
        this.collentNum = collentNum;
   }

    public BigDecimal getTaxMoneyPass() {
        return taxMoneyPass;
    }

    public void setTaxMoneyPass(BigDecimal taxMoneyPass) {
        this.taxMoneyPass = taxMoneyPass;
    }

    public BigDecimal getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(BigDecimal countMoney) {
        this.countMoney = countMoney;
    }
}
