package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayCloudDfOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayCloudDfOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *
     * @param orderByClause
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     * @param distinct
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * @param criteria
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *
     * @param pageNo
     */
    public void setPageNo(long pageNo) {
        this.pageNo=pageNo;
    }

    /**
     *
     */
    public long getPageNo() {
        return pageNo;
    }

    /**
     *
     * @param pageSize
     */
    public void setPageSize(long pageSize) {
        this.pageSize=pageSize;
    }

    /**
     *
     */
    public long getPageSize() {
        return pageSize;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(String value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(String value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(String value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(String value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(String value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLike(String value) {
            addCriterion("orderid like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotLike(String value) {
            addCriterion("orderid not like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<String> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<String> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(String value1, String value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(String value1, String value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andBatchidIsNull() {
            addCriterion("batchid is null");
            return (Criteria) this;
        }

        public Criteria andBatchidIsNotNull() {
            addCriterion("batchid is not null");
            return (Criteria) this;
        }

        public Criteria andBatchidEqualTo(String value) {
            addCriterion("batchid =", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidNotEqualTo(String value) {
            addCriterion("batchid <>", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidGreaterThan(String value) {
            addCriterion("batchid >", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidGreaterThanOrEqualTo(String value) {
            addCriterion("batchid >=", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidLessThan(String value) {
            addCriterion("batchid <", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidLessThanOrEqualTo(String value) {
            addCriterion("batchid <=", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidLike(String value) {
            addCriterion("batchid like", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidNotLike(String value) {
            addCriterion("batchid not like", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidIn(List<String> values) {
            addCriterion("batchid in", values, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidNotIn(List<String> values) {
            addCriterion("batchid not in", values, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidBetween(String value1, String value2) {
            addCriterion("batchid between", value1, value2, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidNotBetween(String value1, String value2) {
            addCriterion("batchid not between", value1, value2, "batchid");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoIsNull() {
            addCriterion("request_batchno is null");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoIsNotNull() {
            addCriterion("request_batchno is not null");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoEqualTo(String value) {
            addCriterion("request_batchno =", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoNotEqualTo(String value) {
            addCriterion("request_batchno <>", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoGreaterThan(String value) {
            addCriterion("request_batchno >", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoGreaterThanOrEqualTo(String value) {
            addCriterion("request_batchno >=", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoLessThan(String value) {
            addCriterion("request_batchno <", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoLessThanOrEqualTo(String value) {
            addCriterion("request_batchno <=", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoLike(String value) {
            addCriterion("request_batchno like", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoNotLike(String value) {
            addCriterion("request_batchno not like", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoIn(List<String> values) {
            addCriterion("request_batchno in", values, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoNotIn(List<String> values) {
            addCriterion("request_batchno not in", values, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoBetween(String value1, String value2) {
            addCriterion("request_batchno between", value1, value2, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoNotBetween(String value1, String value2) {
            addCriterion("request_batchno not between", value1, value2, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdIsNull() {
            addCriterion("request_df_id is null");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdIsNotNull() {
            addCriterion("request_df_id is not null");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdEqualTo(String value) {
            addCriterion("request_df_id =", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdNotEqualTo(String value) {
            addCriterion("request_df_id <>", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdGreaterThan(String value) {
            addCriterion("request_df_id >", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdGreaterThanOrEqualTo(String value) {
            addCriterion("request_df_id >=", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdLessThan(String value) {
            addCriterion("request_df_id <", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdLessThanOrEqualTo(String value) {
            addCriterion("request_df_id <=", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdLike(String value) {
            addCriterion("request_df_id like", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdNotLike(String value) {
            addCriterion("request_df_id not like", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdIn(List<String> values) {
            addCriterion("request_df_id in", values, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdNotIn(List<String> values) {
            addCriterion("request_df_id not in", values, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdBetween(String value1, String value2) {
            addCriterion("request_df_id between", value1, value2, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdNotBetween(String value1, String value2) {
            addCriterion("request_df_id not between", value1, value2, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidIsNull() {
            addCriterion("request_orderid is null");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidIsNotNull() {
            addCriterion("request_orderid is not null");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidEqualTo(String value) {
            addCriterion("request_orderid =", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidNotEqualTo(String value) {
            addCriterion("request_orderid <>", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidGreaterThan(String value) {
            addCriterion("request_orderid >", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("request_orderid >=", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidLessThan(String value) {
            addCriterion("request_orderid <", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidLessThanOrEqualTo(String value) {
            addCriterion("request_orderid <=", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidLike(String value) {
            addCriterion("request_orderid like", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidNotLike(String value) {
            addCriterion("request_orderid not like", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidIn(List<String> values) {
            addCriterion("request_orderid in", values, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidNotIn(List<String> values) {
            addCriterion("request_orderid not in", values, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidBetween(String value1, String value2) {
            addCriterion("request_orderid between", value1, value2, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidNotBetween(String value1, String value2) {
            addCriterion("request_orderid not between", value1, value2, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidIsNull() {
            addCriterion("busstaffid is null");
            return (Criteria) this;
        }

        public Criteria andBusstaffidIsNotNull() {
            addCriterion("busstaffid is not null");
            return (Criteria) this;
        }

        public Criteria andBusstaffidEqualTo(Long value) {
            addCriterion("busstaffid =", value, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidNotEqualTo(Long value) {
            addCriterion("busstaffid <>", value, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidGreaterThan(Long value) {
            addCriterion("busstaffid >", value, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidGreaterThanOrEqualTo(Long value) {
            addCriterion("busstaffid >=", value, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidLessThan(Long value) {
            addCriterion("busstaffid <", value, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidLessThanOrEqualTo(Long value) {
            addCriterion("busstaffid <=", value, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidIn(List<Long> values) {
            addCriterion("busstaffid in", values, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidNotIn(List<Long> values) {
            addCriterion("busstaffid not in", values, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidBetween(Long value1, Long value2) {
            addCriterion("busstaffid between", value1, value2, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidNotBetween(Long value1, Long value2) {
            addCriterion("busstaffid not between", value1, value2, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andApplyamtIsNull() {
            addCriterion("applyAmt is null");
            return (Criteria) this;
        }

        public Criteria andApplyamtIsNotNull() {
            addCriterion("applyAmt is not null");
            return (Criteria) this;
        }

        public Criteria andApplyamtEqualTo(BigDecimal value) {
            addCriterion("applyAmt =", value, "applyamt");
            return (Criteria) this;
        }

        public Criteria andApplyamtNotEqualTo(BigDecimal value) {
            addCriterion("applyAmt <>", value, "applyamt");
            return (Criteria) this;
        }

        public Criteria andApplyamtGreaterThan(BigDecimal value) {
            addCriterion("applyAmt >", value, "applyamt");
            return (Criteria) this;
        }

        public Criteria andApplyamtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("applyAmt >=", value, "applyamt");
            return (Criteria) this;
        }

        public Criteria andApplyamtLessThan(BigDecimal value) {
            addCriterion("applyAmt <", value, "applyamt");
            return (Criteria) this;
        }

        public Criteria andApplyamtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("applyAmt <=", value, "applyamt");
            return (Criteria) this;
        }

        public Criteria andApplyamtIn(List<BigDecimal> values) {
            addCriterion("applyAmt in", values, "applyamt");
            return (Criteria) this;
        }

        public Criteria andApplyamtNotIn(List<BigDecimal> values) {
            addCriterion("applyAmt not in", values, "applyamt");
            return (Criteria) this;
        }

        public Criteria andApplyamtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("applyAmt between", value1, value2, "applyamt");
            return (Criteria) this;
        }

        public Criteria andApplyamtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("applyAmt not between", value1, value2, "applyamt");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceIsNull() {
            addCriterion("order_std_price is null");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceIsNotNull() {
            addCriterion("order_std_price is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceEqualTo(BigDecimal value) {
            addCriterion("order_std_price =", value, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceNotEqualTo(BigDecimal value) {
            addCriterion("order_std_price <>", value, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceGreaterThan(BigDecimal value) {
            addCriterion("order_std_price >", value, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_std_price >=", value, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceLessThan(BigDecimal value) {
            addCriterion("order_std_price <", value, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_std_price <=", value, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceIn(List<BigDecimal> values) {
            addCriterion("order_std_price in", values, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceNotIn(List<BigDecimal> values) {
            addCriterion("order_std_price not in", values, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_std_price between", value1, value2, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_std_price not between", value1, value2, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andRequeststrIsNull() {
            addCriterion("requestStr is null");
            return (Criteria) this;
        }

        public Criteria andRequeststrIsNotNull() {
            addCriterion("requestStr is not null");
            return (Criteria) this;
        }

        public Criteria andRequeststrEqualTo(String value) {
            addCriterion("requestStr =", value, "requeststr");
            return (Criteria) this;
        }

        public Criteria andRequeststrNotEqualTo(String value) {
            addCriterion("requestStr <>", value, "requeststr");
            return (Criteria) this;
        }

        public Criteria andRequeststrGreaterThan(String value) {
            addCriterion("requestStr >", value, "requeststr");
            return (Criteria) this;
        }

        public Criteria andRequeststrGreaterThanOrEqualTo(String value) {
            addCriterion("requestStr >=", value, "requeststr");
            return (Criteria) this;
        }

        public Criteria andRequeststrLessThan(String value) {
            addCriterion("requestStr <", value, "requeststr");
            return (Criteria) this;
        }

        public Criteria andRequeststrLessThanOrEqualTo(String value) {
            addCriterion("requestStr <=", value, "requeststr");
            return (Criteria) this;
        }

        public Criteria andRequeststrLike(String value) {
            addCriterion("requestStr like", value, "requeststr");
            return (Criteria) this;
        }

        public Criteria andRequeststrNotLike(String value) {
            addCriterion("requestStr not like", value, "requeststr");
            return (Criteria) this;
        }

        public Criteria andRequeststrIn(List<String> values) {
            addCriterion("requestStr in", values, "requeststr");
            return (Criteria) this;
        }

        public Criteria andRequeststrNotIn(List<String> values) {
            addCriterion("requestStr not in", values, "requeststr");
            return (Criteria) this;
        }

        public Criteria andRequeststrBetween(String value1, String value2) {
            addCriterion("requestStr between", value1, value2, "requeststr");
            return (Criteria) this;
        }

        public Criteria andRequeststrNotBetween(String value1, String value2) {
            addCriterion("requestStr not between", value1, value2, "requeststr");
            return (Criteria) this;
        }

        public Criteria andFinacodeIsNull() {
            addCriterion("finaCode is null");
            return (Criteria) this;
        }

        public Criteria andFinacodeIsNotNull() {
            addCriterion("finaCode is not null");
            return (Criteria) this;
        }

        public Criteria andFinacodeEqualTo(String value) {
            addCriterion("finaCode =", value, "finacode");
            return (Criteria) this;
        }

        public Criteria andFinacodeNotEqualTo(String value) {
            addCriterion("finaCode <>", value, "finacode");
            return (Criteria) this;
        }

        public Criteria andFinacodeGreaterThan(String value) {
            addCriterion("finaCode >", value, "finacode");
            return (Criteria) this;
        }

        public Criteria andFinacodeGreaterThanOrEqualTo(String value) {
            addCriterion("finaCode >=", value, "finacode");
            return (Criteria) this;
        }

        public Criteria andFinacodeLessThan(String value) {
            addCriterion("finaCode <", value, "finacode");
            return (Criteria) this;
        }

        public Criteria andFinacodeLessThanOrEqualTo(String value) {
            addCriterion("finaCode <=", value, "finacode");
            return (Criteria) this;
        }

        public Criteria andFinacodeLike(String value) {
            addCriterion("finaCode like", value, "finacode");
            return (Criteria) this;
        }

        public Criteria andFinacodeNotLike(String value) {
            addCriterion("finaCode not like", value, "finacode");
            return (Criteria) this;
        }

        public Criteria andFinacodeIn(List<String> values) {
            addCriterion("finaCode in", values, "finacode");
            return (Criteria) this;
        }

        public Criteria andFinacodeNotIn(List<String> values) {
            addCriterion("finaCode not in", values, "finacode");
            return (Criteria) this;
        }

        public Criteria andFinacodeBetween(String value1, String value2) {
            addCriterion("finaCode between", value1, value2, "finacode");
            return (Criteria) this;
        }

        public Criteria andFinacodeNotBetween(String value1, String value2) {
            addCriterion("finaCode not between", value1, value2, "finacode");
            return (Criteria) this;
        }

        public Criteria andPayeeacctIsNull() {
            addCriterion("payeeAcct is null");
            return (Criteria) this;
        }

        public Criteria andPayeeacctIsNotNull() {
            addCriterion("payeeAcct is not null");
            return (Criteria) this;
        }

        public Criteria andPayeeacctEqualTo(String value) {
            addCriterion("payeeAcct =", value, "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeeacctNotEqualTo(String value) {
            addCriterion("payeeAcct <>", value, "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeeacctGreaterThan(String value) {
            addCriterion("payeeAcct >", value, "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeeacctGreaterThanOrEqualTo(String value) {
            addCriterion("payeeAcct >=", value, "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeeacctLessThan(String value) {
            addCriterion("payeeAcct <", value, "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeeacctLessThanOrEqualTo(String value) {
            addCriterion("payeeAcct <=", value, "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeeacctLike(String value) {
            addCriterion("payeeAcct like", value, "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeeacctNotLike(String value) {
            addCriterion("payeeAcct not like", value, "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeeacctIn(List<String> values) {
            addCriterion("payeeAcct in", values, "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeeacctNotIn(List<String> values) {
            addCriterion("payeeAcct not in", values, "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeeacctBetween(String value1, String value2) {
            addCriterion("payeeAcct between", value1, value2, "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeeacctNotBetween(String value1, String value2) {
            addCriterion("payeeAcct not between", value1, value2, "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeenameIsNull() {
            addCriterion("payeeName is null");
            return (Criteria) this;
        }

        public Criteria andPayeenameIsNotNull() {
            addCriterion("payeeName is not null");
            return (Criteria) this;
        }

        public Criteria andPayeenameEqualTo(String value) {
            addCriterion("payeeName =", value, "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeenameNotEqualTo(String value) {
            addCriterion("payeeName <>", value, "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeenameGreaterThan(String value) {
            addCriterion("payeeName >", value, "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeenameGreaterThanOrEqualTo(String value) {
            addCriterion("payeeName >=", value, "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeenameLessThan(String value) {
            addCriterion("payeeName <", value, "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeenameLessThanOrEqualTo(String value) {
            addCriterion("payeeName <=", value, "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeenameLike(String value) {
            addCriterion("payeeName like", value, "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeenameNotLike(String value) {
            addCriterion("payeeName not like", value, "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeenameIn(List<String> values) {
            addCriterion("payeeName in", values, "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeenameNotIn(List<String> values) {
            addCriterion("payeeName not in", values, "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeenameBetween(String value1, String value2) {
            addCriterion("payeeName between", value1, value2, "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeenameNotBetween(String value1, String value2) {
            addCriterion("payeeName not between", value1, value2, "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrIsNull() {
            addCriterion("payeeAcctAttr is null");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrIsNotNull() {
            addCriterion("payeeAcctAttr is not null");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrEqualTo(String value) {
            addCriterion("payeeAcctAttr =", value, "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrNotEqualTo(String value) {
            addCriterion("payeeAcctAttr <>", value, "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrGreaterThan(String value) {
            addCriterion("payeeAcctAttr >", value, "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrGreaterThanOrEqualTo(String value) {
            addCriterion("payeeAcctAttr >=", value, "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrLessThan(String value) {
            addCriterion("payeeAcctAttr <", value, "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrLessThanOrEqualTo(String value) {
            addCriterion("payeeAcctAttr <=", value, "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrLike(String value) {
            addCriterion("payeeAcctAttr like", value, "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrNotLike(String value) {
            addCriterion("payeeAcctAttr not like", value, "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrIn(List<String> values) {
            addCriterion("payeeAcctAttr in", values, "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrNotIn(List<String> values) {
            addCriterion("payeeAcctAttr not in", values, "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrBetween(String value1, String value2) {
            addCriterion("payeeAcctAttr between", value1, value2, "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrNotBetween(String value1, String value2) {
            addCriterion("payeeAcctAttr not between", value1, value2, "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPhonenoIsNull() {
            addCriterion("phoneNo is null");
            return (Criteria) this;
        }

        public Criteria andPhonenoIsNotNull() {
            addCriterion("phoneNo is not null");
            return (Criteria) this;
        }

        public Criteria andPhonenoEqualTo(String value) {
            addCriterion("phoneNo =", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoNotEqualTo(String value) {
            addCriterion("phoneNo <>", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoGreaterThan(String value) {
            addCriterion("phoneNo >", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoGreaterThanOrEqualTo(String value) {
            addCriterion("phoneNo >=", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoLessThan(String value) {
            addCriterion("phoneNo <", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoLessThanOrEqualTo(String value) {
            addCriterion("phoneNo <=", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoLike(String value) {
            addCriterion("phoneNo like", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoNotLike(String value) {
            addCriterion("phoneNo not like", value, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoIn(List<String> values) {
            addCriterion("phoneNo in", values, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoNotIn(List<String> values) {
            addCriterion("phoneNo not in", values, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoBetween(String value1, String value2) {
            addCriterion("phoneNo between", value1, value2, "phoneno");
            return (Criteria) this;
        }

        public Criteria andPhonenoNotBetween(String value1, String value2) {
            addCriterion("phoneNo not between", value1, value2, "phoneno");
            return (Criteria) this;
        }

        public Criteria andCertnoIsNull() {
            addCriterion("certNo is null");
            return (Criteria) this;
        }

        public Criteria andCertnoIsNotNull() {
            addCriterion("certNo is not null");
            return (Criteria) this;
        }

        public Criteria andCertnoEqualTo(String value) {
            addCriterion("certNo =", value, "certno");
            return (Criteria) this;
        }

        public Criteria andCertnoNotEqualTo(String value) {
            addCriterion("certNo <>", value, "certno");
            return (Criteria) this;
        }

        public Criteria andCertnoGreaterThan(String value) {
            addCriterion("certNo >", value, "certno");
            return (Criteria) this;
        }

        public Criteria andCertnoGreaterThanOrEqualTo(String value) {
            addCriterion("certNo >=", value, "certno");
            return (Criteria) this;
        }

        public Criteria andCertnoLessThan(String value) {
            addCriterion("certNo <", value, "certno");
            return (Criteria) this;
        }

        public Criteria andCertnoLessThanOrEqualTo(String value) {
            addCriterion("certNo <=", value, "certno");
            return (Criteria) this;
        }

        public Criteria andCertnoLike(String value) {
            addCriterion("certNo like", value, "certno");
            return (Criteria) this;
        }

        public Criteria andCertnoNotLike(String value) {
            addCriterion("certNo not like", value, "certno");
            return (Criteria) this;
        }

        public Criteria andCertnoIn(List<String> values) {
            addCriterion("certNo in", values, "certno");
            return (Criteria) this;
        }

        public Criteria andCertnoNotIn(List<String> values) {
            addCriterion("certNo not in", values, "certno");
            return (Criteria) this;
        }

        public Criteria andCertnoBetween(String value1, String value2) {
            addCriterion("certNo between", value1, value2, "certno");
            return (Criteria) this;
        }

        public Criteria andCertnoNotBetween(String value1, String value2) {
            addCriterion("certNo not between", value1, value2, "certno");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNull() {
            addCriterion("bankName is null");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNotNull() {
            addCriterion("bankName is not null");
            return (Criteria) this;
        }

        public Criteria andBanknameEqualTo(String value) {
            addCriterion("bankName =", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotEqualTo(String value) {
            addCriterion("bankName <>", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThan(String value) {
            addCriterion("bankName >", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThanOrEqualTo(String value) {
            addCriterion("bankName >=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThan(String value) {
            addCriterion("bankName <", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThanOrEqualTo(String value) {
            addCriterion("bankName <=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLike(String value) {
            addCriterion("bankName like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotLike(String value) {
            addCriterion("bankName not like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameIn(List<String> values) {
            addCriterion("bankName in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotIn(List<String> values) {
            addCriterion("bankName not in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameBetween(String value1, String value2) {
            addCriterion("bankName between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotBetween(String value1, String value2) {
            addCriterion("bankName not between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBankprovinceIsNull() {
            addCriterion("bankProvince is null");
            return (Criteria) this;
        }

        public Criteria andBankprovinceIsNotNull() {
            addCriterion("bankProvince is not null");
            return (Criteria) this;
        }

        public Criteria andBankprovinceEqualTo(String value) {
            addCriterion("bankProvince =", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotEqualTo(String value) {
            addCriterion("bankProvince <>", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceGreaterThan(String value) {
            addCriterion("bankProvince >", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceGreaterThanOrEqualTo(String value) {
            addCriterion("bankProvince >=", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLessThan(String value) {
            addCriterion("bankProvince <", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLessThanOrEqualTo(String value) {
            addCriterion("bankProvince <=", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLike(String value) {
            addCriterion("bankProvince like", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotLike(String value) {
            addCriterion("bankProvince not like", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceIn(List<String> values) {
            addCriterion("bankProvince in", values, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotIn(List<String> values) {
            addCriterion("bankProvince not in", values, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceBetween(String value1, String value2) {
            addCriterion("bankProvince between", value1, value2, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotBetween(String value1, String value2) {
            addCriterion("bankProvince not between", value1, value2, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankcityIsNull() {
            addCriterion("bankCity is null");
            return (Criteria) this;
        }

        public Criteria andBankcityIsNotNull() {
            addCriterion("bankCity is not null");
            return (Criteria) this;
        }

        public Criteria andBankcityEqualTo(String value) {
            addCriterion("bankCity =", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotEqualTo(String value) {
            addCriterion("bankCity <>", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityGreaterThan(String value) {
            addCriterion("bankCity >", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityGreaterThanOrEqualTo(String value) {
            addCriterion("bankCity >=", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityLessThan(String value) {
            addCriterion("bankCity <", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityLessThanOrEqualTo(String value) {
            addCriterion("bankCity <=", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityLike(String value) {
            addCriterion("bankCity like", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotLike(String value) {
            addCriterion("bankCity not like", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityIn(List<String> values) {
            addCriterion("bankCity in", values, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotIn(List<String> values) {
            addCriterion("bankCity not in", values, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityBetween(String value1, String value2) {
            addCriterion("bankCity between", value1, value2, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotBetween(String value1, String value2) {
            addCriterion("bankCity not between", value1, value2, "bankcity");
            return (Criteria) this;
        }

        public Criteria andTrannoIsNull() {
            addCriterion("tranNo is null");
            return (Criteria) this;
        }

        public Criteria andTrannoIsNotNull() {
            addCriterion("tranNo is not null");
            return (Criteria) this;
        }

        public Criteria andTrannoEqualTo(String value) {
            addCriterion("tranNo =", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotEqualTo(String value) {
            addCriterion("tranNo <>", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoGreaterThan(String value) {
            addCriterion("tranNo >", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoGreaterThanOrEqualTo(String value) {
            addCriterion("tranNo >=", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLessThan(String value) {
            addCriterion("tranNo <", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLessThanOrEqualTo(String value) {
            addCriterion("tranNo <=", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLike(String value) {
            addCriterion("tranNo like", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotLike(String value) {
            addCriterion("tranNo not like", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoIn(List<String> values) {
            addCriterion("tranNo in", values, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotIn(List<String> values) {
            addCriterion("tranNo not in", values, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoBetween(String value1, String value2) {
            addCriterion("tranNo between", value1, value2, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotBetween(String value1, String value2) {
            addCriterion("tranNo not between", value1, value2, "tranno");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDfstatusIsNull() {
            addCriterion("dfStatus is null");
            return (Criteria) this;
        }

        public Criteria andDfstatusIsNotNull() {
            addCriterion("dfStatus is not null");
            return (Criteria) this;
        }

        public Criteria andDfstatusEqualTo(String value) {
            addCriterion("dfStatus =", value, "dfstatus");
            return (Criteria) this;
        }

        public Criteria andDfstatusNotEqualTo(String value) {
            addCriterion("dfStatus <>", value, "dfstatus");
            return (Criteria) this;
        }

        public Criteria andDfstatusGreaterThan(String value) {
            addCriterion("dfStatus >", value, "dfstatus");
            return (Criteria) this;
        }

        public Criteria andDfstatusGreaterThanOrEqualTo(String value) {
            addCriterion("dfStatus >=", value, "dfstatus");
            return (Criteria) this;
        }

        public Criteria andDfstatusLessThan(String value) {
            addCriterion("dfStatus <", value, "dfstatus");
            return (Criteria) this;
        }

        public Criteria andDfstatusLessThanOrEqualTo(String value) {
            addCriterion("dfStatus <=", value, "dfstatus");
            return (Criteria) this;
        }

        public Criteria andDfstatusLike(String value) {
            addCriterion("dfStatus like", value, "dfstatus");
            return (Criteria) this;
        }

        public Criteria andDfstatusNotLike(String value) {
            addCriterion("dfStatus not like", value, "dfstatus");
            return (Criteria) this;
        }

        public Criteria andDfstatusIn(List<String> values) {
            addCriterion("dfStatus in", values, "dfstatus");
            return (Criteria) this;
        }

        public Criteria andDfstatusNotIn(List<String> values) {
            addCriterion("dfStatus not in", values, "dfstatus");
            return (Criteria) this;
        }

        public Criteria andDfstatusBetween(String value1, String value2) {
            addCriterion("dfStatus between", value1, value2, "dfstatus");
            return (Criteria) this;
        }

        public Criteria andDfstatusNotBetween(String value1, String value2) {
            addCriterion("dfStatus not between", value1, value2, "dfstatus");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNull() {
            addCriterion("paytime is null");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNotNull() {
            addCriterion("paytime is not null");
            return (Criteria) this;
        }

        public Criteria andPaytimeEqualTo(Date value) {
            addCriterion("paytime =", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotEqualTo(Date value) {
            addCriterion("paytime <>", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThan(Date value) {
            addCriterion("paytime >", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("paytime >=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThan(Date value) {
            addCriterion("paytime <", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThanOrEqualTo(Date value) {
            addCriterion("paytime <=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeIn(List<Date> values) {
            addCriterion("paytime in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotIn(List<Date> values) {
            addCriterion("paytime not in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeBetween(Date value1, Date value2) {
            addCriterion("paytime between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotBetween(Date value1, Date value2) {
            addCriterion("paytime not between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andReturncontentIsNull() {
            addCriterion("returnContent is null");
            return (Criteria) this;
        }

        public Criteria andReturncontentIsNotNull() {
            addCriterion("returnContent is not null");
            return (Criteria) this;
        }

        public Criteria andReturncontentEqualTo(String value) {
            addCriterion("returnContent =", value, "returncontent");
            return (Criteria) this;
        }

        public Criteria andReturncontentNotEqualTo(String value) {
            addCriterion("returnContent <>", value, "returncontent");
            return (Criteria) this;
        }

        public Criteria andReturncontentGreaterThan(String value) {
            addCriterion("returnContent >", value, "returncontent");
            return (Criteria) this;
        }

        public Criteria andReturncontentGreaterThanOrEqualTo(String value) {
            addCriterion("returnContent >=", value, "returncontent");
            return (Criteria) this;
        }

        public Criteria andReturncontentLessThan(String value) {
            addCriterion("returnContent <", value, "returncontent");
            return (Criteria) this;
        }

        public Criteria andReturncontentLessThanOrEqualTo(String value) {
            addCriterion("returnContent <=", value, "returncontent");
            return (Criteria) this;
        }

        public Criteria andReturncontentLike(String value) {
            addCriterion("returnContent like", value, "returncontent");
            return (Criteria) this;
        }

        public Criteria andReturncontentNotLike(String value) {
            addCriterion("returnContent not like", value, "returncontent");
            return (Criteria) this;
        }

        public Criteria andReturncontentIn(List<String> values) {
            addCriterion("returnContent in", values, "returncontent");
            return (Criteria) this;
        }

        public Criteria andReturncontentNotIn(List<String> values) {
            addCriterion("returnContent not in", values, "returncontent");
            return (Criteria) this;
        }

        public Criteria andReturncontentBetween(String value1, String value2) {
            addCriterion("returnContent between", value1, value2, "returncontent");
            return (Criteria) this;
        }

        public Criteria andReturncontentNotBetween(String value1, String value2) {
            addCriterion("returnContent not between", value1, value2, "returncontent");
            return (Criteria) this;
        }

        public Criteria andQuerycountIsNull() {
            addCriterion("queryCount is null");
            return (Criteria) this;
        }

        public Criteria andQuerycountIsNotNull() {
            addCriterion("queryCount is not null");
            return (Criteria) this;
        }

        public Criteria andQuerycountEqualTo(Integer value) {
            addCriterion("queryCount =", value, "querycount");
            return (Criteria) this;
        }

        public Criteria andQuerycountNotEqualTo(Integer value) {
            addCriterion("queryCount <>", value, "querycount");
            return (Criteria) this;
        }

        public Criteria andQuerycountGreaterThan(Integer value) {
            addCriterion("queryCount >", value, "querycount");
            return (Criteria) this;
        }

        public Criteria andQuerycountGreaterThanOrEqualTo(Integer value) {
            addCriterion("queryCount >=", value, "querycount");
            return (Criteria) this;
        }

        public Criteria andQuerycountLessThan(Integer value) {
            addCriterion("queryCount <", value, "querycount");
            return (Criteria) this;
        }

        public Criteria andQuerycountLessThanOrEqualTo(Integer value) {
            addCriterion("queryCount <=", value, "querycount");
            return (Criteria) this;
        }

        public Criteria andQuerycountIn(List<Integer> values) {
            addCriterion("queryCount in", values, "querycount");
            return (Criteria) this;
        }

        public Criteria andQuerycountNotIn(List<Integer> values) {
            addCriterion("queryCount not in", values, "querycount");
            return (Criteria) this;
        }

        public Criteria andQuerycountBetween(Integer value1, Integer value2) {
            addCriterion("queryCount between", value1, value2, "querycount");
            return (Criteria) this;
        }

        public Criteria andQuerycountNotBetween(Integer value1, Integer value2) {
            addCriterion("queryCount not between", value1, value2, "querycount");
            return (Criteria) this;
        }

        public Criteria andLastresposeIsNull() {
            addCriterion("lastRespose is null");
            return (Criteria) this;
        }

        public Criteria andLastresposeIsNotNull() {
            addCriterion("lastRespose is not null");
            return (Criteria) this;
        }

        public Criteria andLastresposeEqualTo(String value) {
            addCriterion("lastRespose =", value, "lastrespose");
            return (Criteria) this;
        }

        public Criteria andLastresposeNotEqualTo(String value) {
            addCriterion("lastRespose <>", value, "lastrespose");
            return (Criteria) this;
        }

        public Criteria andLastresposeGreaterThan(String value) {
            addCriterion("lastRespose >", value, "lastrespose");
            return (Criteria) this;
        }

        public Criteria andLastresposeGreaterThanOrEqualTo(String value) {
            addCriterion("lastRespose >=", value, "lastrespose");
            return (Criteria) this;
        }

        public Criteria andLastresposeLessThan(String value) {
            addCriterion("lastRespose <", value, "lastrespose");
            return (Criteria) this;
        }

        public Criteria andLastresposeLessThanOrEqualTo(String value) {
            addCriterion("lastRespose <=", value, "lastrespose");
            return (Criteria) this;
        }

        public Criteria andLastresposeLike(String value) {
            addCriterion("lastRespose like", value, "lastrespose");
            return (Criteria) this;
        }

        public Criteria andLastresposeNotLike(String value) {
            addCriterion("lastRespose not like", value, "lastrespose");
            return (Criteria) this;
        }

        public Criteria andLastresposeIn(List<String> values) {
            addCriterion("lastRespose in", values, "lastrespose");
            return (Criteria) this;
        }

        public Criteria andLastresposeNotIn(List<String> values) {
            addCriterion("lastRespose not in", values, "lastrespose");
            return (Criteria) this;
        }

        public Criteria andLastresposeBetween(String value1, String value2) {
            addCriterion("lastRespose between", value1, value2, "lastrespose");
            return (Criteria) this;
        }

        public Criteria andLastresposeNotBetween(String value1, String value2) {
            addCriterion("lastRespose not between", value1, value2, "lastrespose");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Date value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Date value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Date value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Date value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Date> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Date> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Date value1, Date value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("updated not between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andOrderidLikeInsensitive(String value) {
            addCriterion("upper(orderid) like", value.toUpperCase(), "orderid");
            return (Criteria) this;
        }

        public Criteria andBatchidLikeInsensitive(String value) {
            addCriterion("upper(batchid) like", value.toUpperCase(), "batchid");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoLikeInsensitive(String value) {
            addCriterion("upper(request_batchno) like", value.toUpperCase(), "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdLikeInsensitive(String value) {
            addCriterion("upper(request_df_id) like", value.toUpperCase(), "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidLikeInsensitive(String value) {
            addCriterion("upper(request_orderid) like", value.toUpperCase(), "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequeststrLikeInsensitive(String value) {
            addCriterion("upper(requestStr) like", value.toUpperCase(), "requeststr");
            return (Criteria) this;
        }

        public Criteria andFinacodeLikeInsensitive(String value) {
            addCriterion("upper(finaCode) like", value.toUpperCase(), "finacode");
            return (Criteria) this;
        }

        public Criteria andPayeeacctLikeInsensitive(String value) {
            addCriterion("upper(payeeAcct) like", value.toUpperCase(), "payeeacct");
            return (Criteria) this;
        }

        public Criteria andPayeenameLikeInsensitive(String value) {
            addCriterion("upper(payeeName) like", value.toUpperCase(), "payeename");
            return (Criteria) this;
        }

        public Criteria andPayeeacctattrLikeInsensitive(String value) {
            addCriterion("upper(payeeAcctAttr) like", value.toUpperCase(), "payeeacctattr");
            return (Criteria) this;
        }

        public Criteria andPhonenoLikeInsensitive(String value) {
            addCriterion("upper(phoneNo) like", value.toUpperCase(), "phoneno");
            return (Criteria) this;
        }

        public Criteria andCertnoLikeInsensitive(String value) {
            addCriterion("upper(certNo) like", value.toUpperCase(), "certno");
            return (Criteria) this;
        }

        public Criteria andBanknameLikeInsensitive(String value) {
            addCriterion("upper(bankName) like", value.toUpperCase(), "bankname");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLikeInsensitive(String value) {
            addCriterion("upper(bankProvince) like", value.toUpperCase(), "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankcityLikeInsensitive(String value) {
            addCriterion("upper(bankCity) like", value.toUpperCase(), "bankcity");
            return (Criteria) this;
        }

        public Criteria andTrannoLikeInsensitive(String value) {
            addCriterion("upper(tranNo) like", value.toUpperCase(), "tranno");
            return (Criteria) this;
        }

        public Criteria andDfstatusLikeInsensitive(String value) {
            addCriterion("upper(dfStatus) like", value.toUpperCase(), "dfstatus");
            return (Criteria) this;
        }

        public Criteria andReturncontentLikeInsensitive(String value) {
            addCriterion("upper(returnContent) like", value.toUpperCase(), "returncontent");
            return (Criteria) this;
        }

        public Criteria andLastresposeLikeInsensitive(String value) {
            addCriterion("upper(lastRespose) like", value.toUpperCase(), "lastrespose");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}