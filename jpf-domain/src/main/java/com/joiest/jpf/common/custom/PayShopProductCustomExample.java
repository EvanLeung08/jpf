package com.joiest.jpf.common.custom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayShopProductCustomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayShopProductCustomExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdIsNull() {
            addCriterion("product_info_id is null");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdIsNotNull() {
            addCriterion("product_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdEqualTo(Integer value) {
            addCriterion("product_info_id =", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdNotEqualTo(Integer value) {
            addCriterion("product_info_id <>", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdGreaterThan(Integer value) {
            addCriterion("product_info_id >", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_info_id >=", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdLessThan(Integer value) {
            addCriterion("product_info_id <", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_info_id <=", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdIn(List<Integer> values) {
            addCriterion("product_info_id in", values, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdNotIn(List<Integer> values) {
            addCriterion("product_info_id not in", values, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("product_info_id between", value1, value2, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_info_id not between", value1, value2, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCardidIsNull() {
            addCriterion("cardid is null");
            return (Criteria) this;
        }

        public Criteria andCardidIsNotNull() {
            addCriterion("cardid is not null");
            return (Criteria) this;
        }

        public Criteria andCardidEqualTo(String value) {
            addCriterion("cardid =", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidNotEqualTo(String value) {
            addCriterion("cardid <>", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidGreaterThan(String value) {
            addCriterion("cardid >", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidGreaterThanOrEqualTo(String value) {
            addCriterion("cardid >=", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidLessThan(String value) {
            addCriterion("cardid <", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidLessThanOrEqualTo(String value) {
            addCriterion("cardid <=", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidLike(String value) {
            addCriterion("cardid like", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidNotLike(String value) {
            addCriterion("cardid not like", value, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidIn(List<String> values) {
            addCriterion("cardid in", values, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidNotIn(List<String> values) {
            addCriterion("cardid not in", values, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidBetween(String value1, String value2) {
            addCriterion("cardid between", value1, value2, "cardid");
            return (Criteria) this;
        }

        public Criteria andCardidNotBetween(String value1, String value2) {
            addCriterion("cardid not between", value1, value2, "cardid");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyIsNull() {
            addCriterion("recharge_money is null");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyIsNotNull() {
            addCriterion("recharge_money is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyEqualTo(Integer value) {
            addCriterion("recharge_money =", value, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyNotEqualTo(Integer value) {
            addCriterion("recharge_money <>", value, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyGreaterThan(Integer value) {
            addCriterion("recharge_money >", value, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("recharge_money >=", value, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyLessThan(Integer value) {
            addCriterion("recharge_money <", value, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("recharge_money <=", value, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyIn(List<Integer> values) {
            addCriterion("recharge_money in", values, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyNotIn(List<Integer> values) {
            addCriterion("recharge_money not in", values, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyBetween(Integer value1, Integer value2) {
            addCriterion("recharge_money between", value1, value2, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("recharge_money not between", value1, value2, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andBidIsNull() {
            addCriterion("bid is null");
            return (Criteria) this;
        }

        public Criteria andBidIsNotNull() {
            addCriterion("bid is not null");
            return (Criteria) this;
        }

        public Criteria andBidEqualTo(BigDecimal value) {
            addCriterion("bid =", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotEqualTo(BigDecimal value) {
            addCriterion("bid <>", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThan(BigDecimal value) {
            addCriterion("bid >", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bid >=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThan(BigDecimal value) {
            addCriterion("bid <", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bid <=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidIn(List<BigDecimal> values) {
            addCriterion("bid in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotIn(List<BigDecimal> values) {
            addCriterion("bid not in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bid between", value1, value2, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bid not between", value1, value2, "bid");
            return (Criteria) this;
        }

        public Criteria andDouIsNull() {
            addCriterion("dou is null");
            return (Criteria) this;
        }

        public Criteria andDouIsNotNull() {
            addCriterion("dou is not null");
            return (Criteria) this;
        }

        public Criteria andDouEqualTo(Integer value) {
            addCriterion("dou =", value, "dou");
            return (Criteria) this;
        }

        public Criteria andDouNotEqualTo(Integer value) {
            addCriterion("dou <>", value, "dou");
            return (Criteria) this;
        }

        public Criteria andDouGreaterThan(Integer value) {
            addCriterion("dou >", value, "dou");
            return (Criteria) this;
        }

        public Criteria andDouGreaterThanOrEqualTo(Integer value) {
            addCriterion("dou >=", value, "dou");
            return (Criteria) this;
        }

        public Criteria andDouLessThan(Integer value) {
            addCriterion("dou <", value, "dou");
            return (Criteria) this;
        }

        public Criteria andDouLessThanOrEqualTo(Integer value) {
            addCriterion("dou <=", value, "dou");
            return (Criteria) this;
        }

        public Criteria andDouIn(List<Integer> values) {
            addCriterion("dou in", values, "dou");
            return (Criteria) this;
        }

        public Criteria andDouNotIn(List<Integer> values) {
            addCriterion("dou not in", values, "dou");
            return (Criteria) this;
        }

        public Criteria andDouBetween(Integer value1, Integer value2) {
            addCriterion("dou between", value1, value2, "dou");
            return (Criteria) this;
        }

        public Criteria andDouNotBetween(Integer value1, Integer value2) {
            addCriterion("dou not between", value1, value2, "dou");
            return (Criteria) this;
        }

        public Criteria andIntroIsNull() {
            addCriterion("intro is null");
            return (Criteria) this;
        }

        public Criteria andIntroIsNotNull() {
            addCriterion("intro is not null");
            return (Criteria) this;
        }

        public Criteria andIntroEqualTo(String value) {
            addCriterion("intro =", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotEqualTo(String value) {
            addCriterion("intro <>", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThan(String value) {
            addCriterion("intro >", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThanOrEqualTo(String value) {
            addCriterion("intro >=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThan(String value) {
            addCriterion("intro <", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThanOrEqualTo(String value) {
            addCriterion("intro <=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLike(String value) {
            addCriterion("intro like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotLike(String value) {
            addCriterion("intro not like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroIn(List<String> values) {
            addCriterion("intro in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotIn(List<String> values) {
            addCriterion("intro not in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroBetween(String value1, String value2) {
            addCriterion("intro between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotBetween(String value1, String value2) {
            addCriterion("intro not between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andStoredIsNull() {
            addCriterion("stored is null");
            return (Criteria) this;
        }

        public Criteria andStoredIsNotNull() {
            addCriterion("stored is not null");
            return (Criteria) this;
        }

        public Criteria andStoredEqualTo(Integer value) {
            addCriterion("stored =", value, "stored");
            return (Criteria) this;
        }

        public Criteria andStoredNotEqualTo(Integer value) {
            addCriterion("stored <>", value, "stored");
            return (Criteria) this;
        }

        public Criteria andStoredGreaterThan(Integer value) {
            addCriterion("stored >", value, "stored");
            return (Criteria) this;
        }

        public Criteria andStoredGreaterThanOrEqualTo(Integer value) {
            addCriterion("stored >=", value, "stored");
            return (Criteria) this;
        }

        public Criteria andStoredLessThan(Integer value) {
            addCriterion("stored <", value, "stored");
            return (Criteria) this;
        }

        public Criteria andStoredLessThanOrEqualTo(Integer value) {
            addCriterion("stored <=", value, "stored");
            return (Criteria) this;
        }

        public Criteria andStoredIn(List<Integer> values) {
            addCriterion("stored in", values, "stored");
            return (Criteria) this;
        }

        public Criteria andStoredNotIn(List<Integer> values) {
            addCriterion("stored not in", values, "stored");
            return (Criteria) this;
        }

        public Criteria andStoredBetween(Integer value1, Integer value2) {
            addCriterion("stored between", value1, value2, "stored");
            return (Criteria) this;
        }

        public Criteria andStoredNotBetween(Integer value1, Integer value2) {
            addCriterion("stored not between", value1, value2, "stored");
            return (Criteria) this;
        }

        public Criteria andStoredSafeIsNull() {
            addCriterion("stored_safe is null");
            return (Criteria) this;
        }

        public Criteria andStoredSafeIsNotNull() {
            addCriterion("stored_safe is not null");
            return (Criteria) this;
        }

        public Criteria andStoredSafeEqualTo(Integer value) {
            addCriterion("stored_safe =", value, "storedSafe");
            return (Criteria) this;
        }

        public Criteria andStoredSafeNotEqualTo(Integer value) {
            addCriterion("stored_safe <>", value, "storedSafe");
            return (Criteria) this;
        }

        public Criteria andStoredSafeGreaterThan(Integer value) {
            addCriterion("stored_safe >", value, "storedSafe");
            return (Criteria) this;
        }

        public Criteria andStoredSafeGreaterThanOrEqualTo(Integer value) {
            addCriterion("stored_safe >=", value, "storedSafe");
            return (Criteria) this;
        }

        public Criteria andStoredSafeLessThan(Integer value) {
            addCriterion("stored_safe <", value, "storedSafe");
            return (Criteria) this;
        }

        public Criteria andStoredSafeLessThanOrEqualTo(Integer value) {
            addCriterion("stored_safe <=", value, "storedSafe");
            return (Criteria) this;
        }

        public Criteria andStoredSafeIn(List<Integer> values) {
            addCriterion("stored_safe in", values, "storedSafe");
            return (Criteria) this;
        }

        public Criteria andStoredSafeNotIn(List<Integer> values) {
            addCriterion("stored_safe not in", values, "storedSafe");
            return (Criteria) this;
        }

        public Criteria andStoredSafeBetween(Integer value1, Integer value2) {
            addCriterion("stored_safe between", value1, value2, "storedSafe");
            return (Criteria) this;
        }

        public Criteria andStoredSafeNotBetween(Integer value1, Integer value2) {
            addCriterion("stored_safe not between", value1, value2, "storedSafe");
            return (Criteria) this;
        }

        public Criteria andStoredTypeIsNull() {
            addCriterion("stored_type is null");
            return (Criteria) this;
        }

        public Criteria andStoredTypeIsNotNull() {
            addCriterion("stored_type is not null");
            return (Criteria) this;
        }

        public Criteria andStoredTypeEqualTo(Byte value) {
            addCriterion("stored_type =", value, "storedType");
            return (Criteria) this;
        }

        public Criteria andStoredTypeNotEqualTo(Byte value) {
            addCriterion("stored_type <>", value, "storedType");
            return (Criteria) this;
        }

        public Criteria andStoredTypeGreaterThan(Byte value) {
            addCriterion("stored_type >", value, "storedType");
            return (Criteria) this;
        }

        public Criteria andStoredTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("stored_type >=", value, "storedType");
            return (Criteria) this;
        }

        public Criteria andStoredTypeLessThan(Byte value) {
            addCriterion("stored_type <", value, "storedType");
            return (Criteria) this;
        }

        public Criteria andStoredTypeLessThanOrEqualTo(Byte value) {
            addCriterion("stored_type <=", value, "storedType");
            return (Criteria) this;
        }

        public Criteria andStoredTypeIn(List<Byte> values) {
            addCriterion("stored_type in", values, "storedType");
            return (Criteria) this;
        }

        public Criteria andStoredTypeNotIn(List<Byte> values) {
            addCriterion("stored_type not in", values, "storedType");
            return (Criteria) this;
        }

        public Criteria andStoredTypeBetween(Byte value1, Byte value2) {
            addCriterion("stored_type between", value1, value2, "storedType");
            return (Criteria) this;
        }

        public Criteria andStoredTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("stored_type not between", value1, value2, "storedType");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("operator_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(Integer value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(Integer value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(Integer value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(Integer value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<Integer> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<Integer> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("operator_id not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNull() {
            addCriterion("operator_name is null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNotNull() {
            addCriterion("operator_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameEqualTo(String value) {
            addCriterion("operator_name =", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotEqualTo(String value) {
            addCriterion("operator_name <>", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThan(String value) {
            addCriterion("operator_name >", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("operator_name >=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThan(String value) {
            addCriterion("operator_name <", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThanOrEqualTo(String value) {
            addCriterion("operator_name <=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLike(String value) {
            addCriterion("operator_name like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotLike(String value) {
            addCriterion("operator_name not like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIn(List<String> values) {
            addCriterion("operator_name in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotIn(List<String> values) {
            addCriterion("operator_name not in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameBetween(String value1, String value2) {
            addCriterion("operator_name between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotBetween(String value1, String value2) {
            addCriterion("operator_name not between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCmccProductIdIsNull() {
            addCriterion("cmcc_product_id is null");
            return (Criteria) this;
        }

        public Criteria andCmccProductIdIsNotNull() {
            addCriterion("cmcc_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andCmccProductIdEqualTo(Integer value) {
            addCriterion("cmcc_product_id =", value, "cmccProductId");
            return (Criteria) this;
        }

        public Criteria andCmccProductIdNotEqualTo(Integer value) {
            addCriterion("cmcc_product_id <>", value, "cmccProductId");
            return (Criteria) this;
        }

        public Criteria andCmccProductIdGreaterThan(Integer value) {
            addCriterion("cmcc_product_id >", value, "cmccProductId");
            return (Criteria) this;
        }

        public Criteria andCmccProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cmcc_product_id >=", value, "cmccProductId");
            return (Criteria) this;
        }

        public Criteria andCmccProductIdLessThan(Integer value) {
            addCriterion("cmcc_product_id <", value, "cmccProductId");
            return (Criteria) this;
        }

        public Criteria andCmccProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("cmcc_product_id <=", value, "cmccProductId");
            return (Criteria) this;
        }

        public Criteria andCmccProductIdIn(List<Integer> values) {
            addCriterion("cmcc_product_id in", values, "cmccProductId");
            return (Criteria) this;
        }

        public Criteria andCmccProductIdNotIn(List<Integer> values) {
            addCriterion("cmcc_product_id not in", values, "cmccProductId");
            return (Criteria) this;
        }

        public Criteria andCmccProductIdBetween(Integer value1, Integer value2) {
            addCriterion("cmcc_product_id between", value1, value2, "cmccProductId");
            return (Criteria) this;
        }

        public Criteria andCmccProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cmcc_product_id not between", value1, value2, "cmccProductId");
            return (Criteria) this;
        }

        public Criteria andCuccProductIdIsNull() {
            addCriterion("cucc_product_id is null");
            return (Criteria) this;
        }

        public Criteria andCuccProductIdIsNotNull() {
            addCriterion("cucc_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andCuccProductIdEqualTo(Integer value) {
            addCriterion("cucc_product_id =", value, "cuccProductId");
            return (Criteria) this;
        }

        public Criteria andCuccProductIdNotEqualTo(Integer value) {
            addCriterion("cucc_product_id <>", value, "cuccProductId");
            return (Criteria) this;
        }

        public Criteria andCuccProductIdGreaterThan(Integer value) {
            addCriterion("cucc_product_id >", value, "cuccProductId");
            return (Criteria) this;
        }

        public Criteria andCuccProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cucc_product_id >=", value, "cuccProductId");
            return (Criteria) this;
        }

        public Criteria andCuccProductIdLessThan(Integer value) {
            addCriterion("cucc_product_id <", value, "cuccProductId");
            return (Criteria) this;
        }

        public Criteria andCuccProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("cucc_product_id <=", value, "cuccProductId");
            return (Criteria) this;
        }

        public Criteria andCuccProductIdIn(List<Integer> values) {
            addCriterion("cucc_product_id in", values, "cuccProductId");
            return (Criteria) this;
        }

        public Criteria andCuccProductIdNotIn(List<Integer> values) {
            addCriterion("cucc_product_id not in", values, "cuccProductId");
            return (Criteria) this;
        }

        public Criteria andCuccProductIdBetween(Integer value1, Integer value2) {
            addCriterion("cucc_product_id between", value1, value2, "cuccProductId");
            return (Criteria) this;
        }

        public Criteria andCuccProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cucc_product_id not between", value1, value2, "cuccProductId");
            return (Criteria) this;
        }

        public Criteria andCtcProductIdIsNull() {
            addCriterion("ctc_product_id is null");
            return (Criteria) this;
        }

        public Criteria andCtcProductIdIsNotNull() {
            addCriterion("ctc_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andCtcProductIdEqualTo(Integer value) {
            addCriterion("ctc_product_id =", value, "ctcProductId");
            return (Criteria) this;
        }

        public Criteria andCtcProductIdNotEqualTo(Integer value) {
            addCriterion("ctc_product_id <>", value, "ctcProductId");
            return (Criteria) this;
        }

        public Criteria andCtcProductIdGreaterThan(Integer value) {
            addCriterion("ctc_product_id >", value, "ctcProductId");
            return (Criteria) this;
        }

        public Criteria andCtcProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ctc_product_id >=", value, "ctcProductId");
            return (Criteria) this;
        }

        public Criteria andCtcProductIdLessThan(Integer value) {
            addCriterion("ctc_product_id <", value, "ctcProductId");
            return (Criteria) this;
        }

        public Criteria andCtcProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("ctc_product_id <=", value, "ctcProductId");
            return (Criteria) this;
        }

        public Criteria andCtcProductIdIn(List<Integer> values) {
            addCriterion("ctc_product_id in", values, "ctcProductId");
            return (Criteria) this;
        }

        public Criteria andCtcProductIdNotIn(List<Integer> values) {
            addCriterion("ctc_product_id not in", values, "ctcProductId");
            return (Criteria) this;
        }

        public Criteria andCtcProductIdBetween(Integer value1, Integer value2) {
            addCriterion("ctc_product_id between", value1, value2, "ctcProductId");
            return (Criteria) this;
        }

        public Criteria andCtcProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ctc_product_id not between", value1, value2, "ctcProductId");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("addtime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("addtime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Date value) {
            addCriterion("addtime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("addtime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("addtime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("addtime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Date value) {
            addCriterion("addtime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("addtime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Date> values) {
            addCriterion("addtime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("addtime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("addtime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("addtime not between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andProductContentIdIsNull() {
            addCriterion("product_content_id is null");
            return (Criteria) this;
        }

        public Criteria andProductContentIdIsNotNull() {
            addCriterion("product_content_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductContentIdEqualTo(String value) {
            addCriterion("product_content_id =", value, "productContentId");
            return (Criteria) this;
        }

        public Criteria andProductContentIdNotEqualTo(String value) {
            addCriterion("product_content_id <>", value, "productContentId");
            return (Criteria) this;
        }

        public Criteria andProductContentIdGreaterThan(String value) {
            addCriterion("product_content_id >", value, "productContentId");
            return (Criteria) this;
        }

        public Criteria andProductContentIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_content_id >=", value, "productContentId");
            return (Criteria) this;
        }

        public Criteria andProductContentIdLessThan(String value) {
            addCriterion("product_content_id <", value, "productContentId");
            return (Criteria) this;
        }

        public Criteria andProductContentIdLessThanOrEqualTo(String value) {
            addCriterion("product_content_id <=", value, "productContentId");
            return (Criteria) this;
        }

        public Criteria andProductContentIdIn(List<String> values) {
            addCriterion("product_content_id in", values, "productContentId");
            return (Criteria) this;
        }

        public Criteria andProductContentIdNotIn(List<String> values) {
            addCriterion("product_content_id not in", values, "productContentId");
            return (Criteria) this;
        }

        public Criteria andProductContentIdBetween(String value1, String value2) {
            addCriterion("product_content_id between", value1, value2, "productContentId");
            return (Criteria) this;
        }

        public Criteria andProductContentIdNotBetween(String value1, String value2) {
            addCriterion("product_content_id not between", value1, value2, "productContentId");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andCardidLikeInsensitive(String value) {
            addCriterion("upper(cardid) like", value.toUpperCase(), "cardid");
            return (Criteria) this;
        }

        public Criteria andImageLikeInsensitive(String value) {
            addCriterion("upper(image) like", value.toUpperCase(), "image");
            return (Criteria) this;
        }

        public Criteria andIntroLikeInsensitive(String value) {
            addCriterion("upper(intro) like", value.toUpperCase(), "intro");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLikeInsensitive(String value) {
            addCriterion("upper(operator_name) like", value.toUpperCase(), "operatorName");
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