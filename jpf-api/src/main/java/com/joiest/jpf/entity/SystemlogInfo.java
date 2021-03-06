package com.joiest.jpf.entity;

import java.util.Date;

public class SystemlogInfo {

    /**
     * id
     */
    private Long id;

    /**
     * 来源 0：前台 1：后台
     */
    private Integer logtype;

    /**
     * 操作者uid
     */
    private Integer operatorUid;

    /**
     * 操作者用户名
     */
    private String operatorName;

    /**
     * 操作者ip地址
     */
    private String ip;

    /**
     * 唯一识别码
     */
    private String ip1;

    /**
     * 客户端
     */
    private Integer clients;

    /**
     * 表名
     */
    private String tablename;

    /**
     * 操作者uid
     */
    private String record;

    /**
     * 操作类型：删除数据，修改数据，添加数据，等
     */
    private String action;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * sql语句
     */
    private String content;
    private long rows;

    private long page;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLogtype() {
        return logtype;
    }

    public void setLogtype(Integer logtype) {
        this.logtype = logtype;
    }

    public Integer getOperatorUid() {
        return operatorUid;
    }

    public void setOperatorUid(Integer operatorUid) {
        this.operatorUid = operatorUid;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp1() {
        return ip1;
    }

    public void setIp1(String ip1) {
        this.ip1 = ip1;
    }

    public Integer getClients() {
        return clients;
    }

    public void setClients(Integer clients) {
        this.clients = clients;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }
}
