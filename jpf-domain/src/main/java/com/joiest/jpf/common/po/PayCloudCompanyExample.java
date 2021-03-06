package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayCloudCompanyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayCloudCompanyExample() {
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

        public Criteria andMerchNoIsNull() {
            addCriterion("merch_no is null");
            return (Criteria) this;
        }

        public Criteria andMerchNoIsNotNull() {
            addCriterion("merch_no is not null");
            return (Criteria) this;
        }

        public Criteria andMerchNoEqualTo(String value) {
            addCriterion("merch_no =", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoNotEqualTo(String value) {
            addCriterion("merch_no <>", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoGreaterThan(String value) {
            addCriterion("merch_no >", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoGreaterThanOrEqualTo(String value) {
            addCriterion("merch_no >=", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoLessThan(String value) {
            addCriterion("merch_no <", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoLessThanOrEqualTo(String value) {
            addCriterion("merch_no <=", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoLike(String value) {
            addCriterion("merch_no like", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoNotLike(String value) {
            addCriterion("merch_no not like", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoIn(List<String> values) {
            addCriterion("merch_no in", values, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoNotIn(List<String> values) {
            addCriterion("merch_no not in", values, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoBetween(String value1, String value2) {
            addCriterion("merch_no between", value1, value2, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoNotBetween(String value1, String value2) {
            addCriterion("merch_no not between", value1, value2, "merchNo");
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

        public Criteria andMerchNameIsNull() {
            addCriterion("merch_name is null");
            return (Criteria) this;
        }

        public Criteria andMerchNameIsNotNull() {
            addCriterion("merch_name is not null");
            return (Criteria) this;
        }

        public Criteria andMerchNameEqualTo(String value) {
            addCriterion("merch_name =", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameNotEqualTo(String value) {
            addCriterion("merch_name <>", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameGreaterThan(String value) {
            addCriterion("merch_name >", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameGreaterThanOrEqualTo(String value) {
            addCriterion("merch_name >=", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameLessThan(String value) {
            addCriterion("merch_name <", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameLessThanOrEqualTo(String value) {
            addCriterion("merch_name <=", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameLike(String value) {
            addCriterion("merch_name like", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameNotLike(String value) {
            addCriterion("merch_name not like", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameIn(List<String> values) {
            addCriterion("merch_name in", values, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameNotIn(List<String> values) {
            addCriterion("merch_name not in", values, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameBetween(String value1, String value2) {
            addCriterion("merch_name between", value1, value2, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameNotBetween(String value1, String value2) {
            addCriterion("merch_name not between", value1, value2, "merchName");
            return (Criteria) this;
        }

        public Criteria andPhonenameIsNull() {
            addCriterion("phonename is null");
            return (Criteria) this;
        }

        public Criteria andPhonenameIsNotNull() {
            addCriterion("phonename is not null");
            return (Criteria) this;
        }

        public Criteria andPhonenameEqualTo(String value) {
            addCriterion("phonename =", value, "phonename");
            return (Criteria) this;
        }

        public Criteria andPhonenameNotEqualTo(String value) {
            addCriterion("phonename <>", value, "phonename");
            return (Criteria) this;
        }

        public Criteria andPhonenameGreaterThan(String value) {
            addCriterion("phonename >", value, "phonename");
            return (Criteria) this;
        }

        public Criteria andPhonenameGreaterThanOrEqualTo(String value) {
            addCriterion("phonename >=", value, "phonename");
            return (Criteria) this;
        }

        public Criteria andPhonenameLessThan(String value) {
            addCriterion("phonename <", value, "phonename");
            return (Criteria) this;
        }

        public Criteria andPhonenameLessThanOrEqualTo(String value) {
            addCriterion("phonename <=", value, "phonename");
            return (Criteria) this;
        }

        public Criteria andPhonenameLike(String value) {
            addCriterion("phonename like", value, "phonename");
            return (Criteria) this;
        }

        public Criteria andPhonenameNotLike(String value) {
            addCriterion("phonename not like", value, "phonename");
            return (Criteria) this;
        }

        public Criteria andPhonenameIn(List<String> values) {
            addCriterion("phonename in", values, "phonename");
            return (Criteria) this;
        }

        public Criteria andPhonenameNotIn(List<String> values) {
            addCriterion("phonename not in", values, "phonename");
            return (Criteria) this;
        }

        public Criteria andPhonenameBetween(String value1, String value2) {
            addCriterion("phonename between", value1, value2, "phonename");
            return (Criteria) this;
        }

        public Criteria andPhonenameNotBetween(String value1, String value2) {
            addCriterion("phonename not between", value1, value2, "phonename");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andBslicenseIsNull() {
            addCriterion("bslicense is null");
            return (Criteria) this;
        }

        public Criteria andBslicenseIsNotNull() {
            addCriterion("bslicense is not null");
            return (Criteria) this;
        }

        public Criteria andBslicenseEqualTo(String value) {
            addCriterion("bslicense =", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseNotEqualTo(String value) {
            addCriterion("bslicense <>", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseGreaterThan(String value) {
            addCriterion("bslicense >", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseGreaterThanOrEqualTo(String value) {
            addCriterion("bslicense >=", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseLessThan(String value) {
            addCriterion("bslicense <", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseLessThanOrEqualTo(String value) {
            addCriterion("bslicense <=", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseLike(String value) {
            addCriterion("bslicense like", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseNotLike(String value) {
            addCriterion("bslicense not like", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseIn(List<String> values) {
            addCriterion("bslicense in", values, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseNotIn(List<String> values) {
            addCriterion("bslicense not in", values, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseBetween(String value1, String value2) {
            addCriterion("bslicense between", value1, value2, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseNotBetween(String value1, String value2) {
            addCriterion("bslicense not between", value1, value2, "bslicense");
            return (Criteria) this;
        }

        public Criteria andAptitudeIsNull() {
            addCriterion("aptitude is null");
            return (Criteria) this;
        }

        public Criteria andAptitudeIsNotNull() {
            addCriterion("aptitude is not null");
            return (Criteria) this;
        }

        public Criteria andAptitudeEqualTo(String value) {
            addCriterion("aptitude =", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeNotEqualTo(String value) {
            addCriterion("aptitude <>", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeGreaterThan(String value) {
            addCriterion("aptitude >", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeGreaterThanOrEqualTo(String value) {
            addCriterion("aptitude >=", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeLessThan(String value) {
            addCriterion("aptitude <", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeLessThanOrEqualTo(String value) {
            addCriterion("aptitude <=", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeLike(String value) {
            addCriterion("aptitude like", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeNotLike(String value) {
            addCriterion("aptitude not like", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeIn(List<String> values) {
            addCriterion("aptitude in", values, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeNotIn(List<String> values) {
            addCriterion("aptitude not in", values, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeBetween(String value1, String value2) {
            addCriterion("aptitude between", value1, value2, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeNotBetween(String value1, String value2) {
            addCriterion("aptitude not between", value1, value2, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAttestationIsNull() {
            addCriterion("attestation is null");
            return (Criteria) this;
        }

        public Criteria andAttestationIsNotNull() {
            addCriterion("attestation is not null");
            return (Criteria) this;
        }

        public Criteria andAttestationEqualTo(Byte value) {
            addCriterion("attestation =", value, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationNotEqualTo(Byte value) {
            addCriterion("attestation <>", value, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationGreaterThan(Byte value) {
            addCriterion("attestation >", value, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationGreaterThanOrEqualTo(Byte value) {
            addCriterion("attestation >=", value, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationLessThan(Byte value) {
            addCriterion("attestation <", value, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationLessThanOrEqualTo(Byte value) {
            addCriterion("attestation <=", value, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationIn(List<Byte> values) {
            addCriterion("attestation in", values, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationNotIn(List<Byte> values) {
            addCriterion("attestation not in", values, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationBetween(Byte value1, Byte value2) {
            addCriterion("attestation between", value1, value2, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationNotBetween(Byte value1, Byte value2) {
            addCriterion("attestation not between", value1, value2, "attestation");
            return (Criteria) this;
        }

        public Criteria andIssmsIsNull() {
            addCriterion("issms is null");
            return (Criteria) this;
        }

        public Criteria andIssmsIsNotNull() {
            addCriterion("issms is not null");
            return (Criteria) this;
        }

        public Criteria andIssmsEqualTo(Byte value) {
            addCriterion("issms =", value, "issms");
            return (Criteria) this;
        }

        public Criteria andIssmsNotEqualTo(Byte value) {
            addCriterion("issms <>", value, "issms");
            return (Criteria) this;
        }

        public Criteria andIssmsGreaterThan(Byte value) {
            addCriterion("issms >", value, "issms");
            return (Criteria) this;
        }

        public Criteria andIssmsGreaterThanOrEqualTo(Byte value) {
            addCriterion("issms >=", value, "issms");
            return (Criteria) this;
        }

        public Criteria andIssmsLessThan(Byte value) {
            addCriterion("issms <", value, "issms");
            return (Criteria) this;
        }

        public Criteria andIssmsLessThanOrEqualTo(Byte value) {
            addCriterion("issms <=", value, "issms");
            return (Criteria) this;
        }

        public Criteria andIssmsIn(List<Byte> values) {
            addCriterion("issms in", values, "issms");
            return (Criteria) this;
        }

        public Criteria andIssmsNotIn(List<Byte> values) {
            addCriterion("issms not in", values, "issms");
            return (Criteria) this;
        }

        public Criteria andIssmsBetween(Byte value1, Byte value2) {
            addCriterion("issms between", value1, value2, "issms");
            return (Criteria) this;
        }

        public Criteria andIssmsNotBetween(Byte value1, Byte value2) {
            addCriterion("issms not between", value1, value2, "issms");
            return (Criteria) this;
        }

        public Criteria andTipstypeIsNull() {
            addCriterion("tipsType is null");
            return (Criteria) this;
        }

        public Criteria andTipstypeIsNotNull() {
            addCriterion("tipsType is not null");
            return (Criteria) this;
        }

        public Criteria andTipstypeEqualTo(Byte value) {
            addCriterion("tipsType =", value, "tipstype");
            return (Criteria) this;
        }

        public Criteria andTipstypeNotEqualTo(Byte value) {
            addCriterion("tipsType <>", value, "tipstype");
            return (Criteria) this;
        }

        public Criteria andTipstypeGreaterThan(Byte value) {
            addCriterion("tipsType >", value, "tipstype");
            return (Criteria) this;
        }

        public Criteria andTipstypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("tipsType >=", value, "tipstype");
            return (Criteria) this;
        }

        public Criteria andTipstypeLessThan(Byte value) {
            addCriterion("tipsType <", value, "tipstype");
            return (Criteria) this;
        }

        public Criteria andTipstypeLessThanOrEqualTo(Byte value) {
            addCriterion("tipsType <=", value, "tipstype");
            return (Criteria) this;
        }

        public Criteria andTipstypeIn(List<Byte> values) {
            addCriterion("tipsType in", values, "tipstype");
            return (Criteria) this;
        }

        public Criteria andTipstypeNotIn(List<Byte> values) {
            addCriterion("tipsType not in", values, "tipstype");
            return (Criteria) this;
        }

        public Criteria andTipstypeBetween(Byte value1, Byte value2) {
            addCriterion("tipsType between", value1, value2, "tipstype");
            return (Criteria) this;
        }

        public Criteria andTipstypeNotBetween(Byte value1, Byte value2) {
            addCriterion("tipsType not between", value1, value2, "tipstype");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserIsNull() {
            addCriterion("serviclinkuser is null");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserIsNotNull() {
            addCriterion("serviclinkuser is not null");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserEqualTo(String value) {
            addCriterion("serviclinkuser =", value, "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserNotEqualTo(String value) {
            addCriterion("serviclinkuser <>", value, "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserGreaterThan(String value) {
            addCriterion("serviclinkuser >", value, "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserGreaterThanOrEqualTo(String value) {
            addCriterion("serviclinkuser >=", value, "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserLessThan(String value) {
            addCriterion("serviclinkuser <", value, "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserLessThanOrEqualTo(String value) {
            addCriterion("serviclinkuser <=", value, "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserLike(String value) {
            addCriterion("serviclinkuser like", value, "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserNotLike(String value) {
            addCriterion("serviclinkuser not like", value, "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserIn(List<String> values) {
            addCriterion("serviclinkuser in", values, "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserNotIn(List<String> values) {
            addCriterion("serviclinkuser not in", values, "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserBetween(String value1, String value2) {
            addCriterion("serviclinkuser between", value1, value2, "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserNotBetween(String value1, String value2) {
            addCriterion("serviclinkuser not between", value1, value2, "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andLinkphoneIsNull() {
            addCriterion("linkphone is null");
            return (Criteria) this;
        }

        public Criteria andLinkphoneIsNotNull() {
            addCriterion("linkphone is not null");
            return (Criteria) this;
        }

        public Criteria andLinkphoneEqualTo(String value) {
            addCriterion("linkphone =", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneNotEqualTo(String value) {
            addCriterion("linkphone <>", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneGreaterThan(String value) {
            addCriterion("linkphone >", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneGreaterThanOrEqualTo(String value) {
            addCriterion("linkphone >=", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneLessThan(String value) {
            addCriterion("linkphone <", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneLessThanOrEqualTo(String value) {
            addCriterion("linkphone <=", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneLike(String value) {
            addCriterion("linkphone like", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneNotLike(String value) {
            addCriterion("linkphone not like", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneIn(List<String> values) {
            addCriterion("linkphone in", values, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneNotIn(List<String> values) {
            addCriterion("linkphone not in", values, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneBetween(String value1, String value2) {
            addCriterion("linkphone between", value1, value2, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneNotBetween(String value1, String value2) {
            addCriterion("linkphone not between", value1, value2, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkemailIsNull() {
            addCriterion("linkemail is null");
            return (Criteria) this;
        }

        public Criteria andLinkemailIsNotNull() {
            addCriterion("linkemail is not null");
            return (Criteria) this;
        }

        public Criteria andLinkemailEqualTo(String value) {
            addCriterion("linkemail =", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailNotEqualTo(String value) {
            addCriterion("linkemail <>", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailGreaterThan(String value) {
            addCriterion("linkemail >", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailGreaterThanOrEqualTo(String value) {
            addCriterion("linkemail >=", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailLessThan(String value) {
            addCriterion("linkemail <", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailLessThanOrEqualTo(String value) {
            addCriterion("linkemail <=", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailLike(String value) {
            addCriterion("linkemail like", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailNotLike(String value) {
            addCriterion("linkemail not like", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailIn(List<String> values) {
            addCriterion("linkemail in", values, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailNotIn(List<String> values) {
            addCriterion("linkemail not in", values, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailBetween(String value1, String value2) {
            addCriterion("linkemail between", value1, value2, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailNotBetween(String value1, String value2) {
            addCriterion("linkemail not between", value1, value2, "linkemail");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyIsNull() {
            addCriterion("private_key is null");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyIsNotNull() {
            addCriterion("private_key is not null");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyEqualTo(String value) {
            addCriterion("private_key =", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyNotEqualTo(String value) {
            addCriterion("private_key <>", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyGreaterThan(String value) {
            addCriterion("private_key >", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyGreaterThanOrEqualTo(String value) {
            addCriterion("private_key >=", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyLessThan(String value) {
            addCriterion("private_key <", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyLessThanOrEqualTo(String value) {
            addCriterion("private_key <=", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyLike(String value) {
            addCriterion("private_key like", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyNotLike(String value) {
            addCriterion("private_key not like", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyIn(List<String> values) {
            addCriterion("private_key in", values, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyNotIn(List<String> values) {
            addCriterion("private_key not in", values, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyBetween(String value1, String value2) {
            addCriterion("private_key between", value1, value2, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyNotBetween(String value1, String value2) {
            addCriterion("private_key not between", value1, value2, "privateKey");
            return (Criteria) this;
        }

        public Criteria andAddadminidIsNull() {
            addCriterion("addadminid is null");
            return (Criteria) this;
        }

        public Criteria andAddadminidIsNotNull() {
            addCriterion("addadminid is not null");
            return (Criteria) this;
        }

        public Criteria andAddadminidEqualTo(String value) {
            addCriterion("addadminid =", value, "addadminid");
            return (Criteria) this;
        }

        public Criteria andAddadminidNotEqualTo(String value) {
            addCriterion("addadminid <>", value, "addadminid");
            return (Criteria) this;
        }

        public Criteria andAddadminidGreaterThan(String value) {
            addCriterion("addadminid >", value, "addadminid");
            return (Criteria) this;
        }

        public Criteria andAddadminidGreaterThanOrEqualTo(String value) {
            addCriterion("addadminid >=", value, "addadminid");
            return (Criteria) this;
        }

        public Criteria andAddadminidLessThan(String value) {
            addCriterion("addadminid <", value, "addadminid");
            return (Criteria) this;
        }

        public Criteria andAddadminidLessThanOrEqualTo(String value) {
            addCriterion("addadminid <=", value, "addadminid");
            return (Criteria) this;
        }

        public Criteria andAddadminidIn(List<String> values) {
            addCriterion("addadminid in", values, "addadminid");
            return (Criteria) this;
        }

        public Criteria andAddadminidNotIn(List<String> values) {
            addCriterion("addadminid not in", values, "addadminid");
            return (Criteria) this;
        }

        public Criteria andAddadminidBetween(String value1, String value2) {
            addCriterion("addadminid between", value1, value2, "addadminid");
            return (Criteria) this;
        }

        public Criteria andAddadminidNotBetween(String value1, String value2) {
            addCriterion("addadminid not between", value1, value2, "addadminid");
            return (Criteria) this;
        }

        public Criteria andEditadminidIsNull() {
            addCriterion("editadminid is null");
            return (Criteria) this;
        }

        public Criteria andEditadminidIsNotNull() {
            addCriterion("editadminid is not null");
            return (Criteria) this;
        }

        public Criteria andEditadminidEqualTo(String value) {
            addCriterion("editadminid =", value, "editadminid");
            return (Criteria) this;
        }

        public Criteria andEditadminidNotEqualTo(String value) {
            addCriterion("editadminid <>", value, "editadminid");
            return (Criteria) this;
        }

        public Criteria andEditadminidGreaterThan(String value) {
            addCriterion("editadminid >", value, "editadminid");
            return (Criteria) this;
        }

        public Criteria andEditadminidGreaterThanOrEqualTo(String value) {
            addCriterion("editadminid >=", value, "editadminid");
            return (Criteria) this;
        }

        public Criteria andEditadminidLessThan(String value) {
            addCriterion("editadminid <", value, "editadminid");
            return (Criteria) this;
        }

        public Criteria andEditadminidLessThanOrEqualTo(String value) {
            addCriterion("editadminid <=", value, "editadminid");
            return (Criteria) this;
        }

        public Criteria andEditadminidIn(List<String> values) {
            addCriterion("editadminid in", values, "editadminid");
            return (Criteria) this;
        }

        public Criteria andEditadminidNotIn(List<String> values) {
            addCriterion("editadminid not in", values, "editadminid");
            return (Criteria) this;
        }

        public Criteria andEditadminidBetween(String value1, String value2) {
            addCriterion("editadminid between", value1, value2, "editadminid");
            return (Criteria) this;
        }

        public Criteria andEditadminidNotBetween(String value1, String value2) {
            addCriterion("editadminid not between", value1, value2, "editadminid");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNull() {
            addCriterion("isshow is null");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNotNull() {
            addCriterion("isshow is not null");
            return (Criteria) this;
        }

        public Criteria andIsshowEqualTo(Byte value) {
            addCriterion("isshow =", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotEqualTo(Byte value) {
            addCriterion("isshow <>", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThan(Byte value) {
            addCriterion("isshow >", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThanOrEqualTo(Byte value) {
            addCriterion("isshow >=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThan(Byte value) {
            addCriterion("isshow <", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThanOrEqualTo(Byte value) {
            addCriterion("isshow <=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowIn(List<Byte> values) {
            addCriterion("isshow in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotIn(List<Byte> values) {
            addCriterion("isshow not in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowBetween(Byte value1, Byte value2) {
            addCriterion("isshow between", value1, value2, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotBetween(Byte value1, Byte value2) {
            addCriterion("isshow not between", value1, value2, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsFreezeIsNull() {
            addCriterion("is_freeze is null");
            return (Criteria) this;
        }

        public Criteria andIsFreezeIsNotNull() {
            addCriterion("is_freeze is not null");
            return (Criteria) this;
        }

        public Criteria andIsFreezeEqualTo(Byte value) {
            addCriterion("is_freeze =", value, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeNotEqualTo(Byte value) {
            addCriterion("is_freeze <>", value, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeGreaterThan(Byte value) {
            addCriterion("is_freeze >", value, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_freeze >=", value, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeLessThan(Byte value) {
            addCriterion("is_freeze <", value, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeLessThanOrEqualTo(Byte value) {
            addCriterion("is_freeze <=", value, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeIn(List<Byte> values) {
            addCriterion("is_freeze in", values, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeNotIn(List<Byte> values) {
            addCriterion("is_freeze not in", values, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeBetween(Byte value1, Byte value2) {
            addCriterion("is_freeze between", value1, value2, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeNotBetween(Byte value1, Byte value2) {
            addCriterion("is_freeze not between", value1, value2, "isFreeze");
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

        public Criteria andCloudmoneyIsNull() {
            addCriterion("cloudmoney is null");
            return (Criteria) this;
        }

        public Criteria andCloudmoneyIsNotNull() {
            addCriterion("cloudmoney is not null");
            return (Criteria) this;
        }

        public Criteria andCloudmoneyEqualTo(BigDecimal value) {
            addCriterion("cloudmoney =", value, "cloudmoney");
            return (Criteria) this;
        }

        public Criteria andCloudmoneyNotEqualTo(BigDecimal value) {
            addCriterion("cloudmoney <>", value, "cloudmoney");
            return (Criteria) this;
        }

        public Criteria andCloudmoneyGreaterThan(BigDecimal value) {
            addCriterion("cloudmoney >", value, "cloudmoney");
            return (Criteria) this;
        }

        public Criteria andCloudmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cloudmoney >=", value, "cloudmoney");
            return (Criteria) this;
        }

        public Criteria andCloudmoneyLessThan(BigDecimal value) {
            addCriterion("cloudmoney <", value, "cloudmoney");
            return (Criteria) this;
        }

        public Criteria andCloudmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cloudmoney <=", value, "cloudmoney");
            return (Criteria) this;
        }

        public Criteria andCloudmoneyIn(List<BigDecimal> values) {
            addCriterion("cloudmoney in", values, "cloudmoney");
            return (Criteria) this;
        }

        public Criteria andCloudmoneyNotIn(List<BigDecimal> values) {
            addCriterion("cloudmoney not in", values, "cloudmoney");
            return (Criteria) this;
        }

        public Criteria andCloudmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cloudmoney between", value1, value2, "cloudmoney");
            return (Criteria) this;
        }

        public Criteria andCloudmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cloudmoney not between", value1, value2, "cloudmoney");
            return (Criteria) this;
        }

        public Criteria andCloudcodeIsNull() {
            addCriterion("cloudcode is null");
            return (Criteria) this;
        }

        public Criteria andCloudcodeIsNotNull() {
            addCriterion("cloudcode is not null");
            return (Criteria) this;
        }

        public Criteria andCloudcodeEqualTo(String value) {
            addCriterion("cloudcode =", value, "cloudcode");
            return (Criteria) this;
        }

        public Criteria andCloudcodeNotEqualTo(String value) {
            addCriterion("cloudcode <>", value, "cloudcode");
            return (Criteria) this;
        }

        public Criteria andCloudcodeGreaterThan(String value) {
            addCriterion("cloudcode >", value, "cloudcode");
            return (Criteria) this;
        }

        public Criteria andCloudcodeGreaterThanOrEqualTo(String value) {
            addCriterion("cloudcode >=", value, "cloudcode");
            return (Criteria) this;
        }

        public Criteria andCloudcodeLessThan(String value) {
            addCriterion("cloudcode <", value, "cloudcode");
            return (Criteria) this;
        }

        public Criteria andCloudcodeLessThanOrEqualTo(String value) {
            addCriterion("cloudcode <=", value, "cloudcode");
            return (Criteria) this;
        }

        public Criteria andCloudcodeLike(String value) {
            addCriterion("cloudcode like", value, "cloudcode");
            return (Criteria) this;
        }

        public Criteria andCloudcodeNotLike(String value) {
            addCriterion("cloudcode not like", value, "cloudcode");
            return (Criteria) this;
        }

        public Criteria andCloudcodeIn(List<String> values) {
            addCriterion("cloudcode in", values, "cloudcode");
            return (Criteria) this;
        }

        public Criteria andCloudcodeNotIn(List<String> values) {
            addCriterion("cloudcode not in", values, "cloudcode");
            return (Criteria) this;
        }

        public Criteria andCloudcodeBetween(String value1, String value2) {
            addCriterion("cloudcode between", value1, value2, "cloudcode");
            return (Criteria) this;
        }

        public Criteria andCloudcodeNotBetween(String value1, String value2) {
            addCriterion("cloudcode not between", value1, value2, "cloudcode");
            return (Criteria) this;
        }

        public Criteria andAdvanceMoneyIsNull() {
            addCriterion("advance_money is null");
            return (Criteria) this;
        }

        public Criteria andAdvanceMoneyIsNotNull() {
            addCriterion("advance_money is not null");
            return (Criteria) this;
        }

        public Criteria andAdvanceMoneyEqualTo(BigDecimal value) {
            addCriterion("advance_money =", value, "advanceMoney");
            return (Criteria) this;
        }

        public Criteria andAdvanceMoneyNotEqualTo(BigDecimal value) {
            addCriterion("advance_money <>", value, "advanceMoney");
            return (Criteria) this;
        }

        public Criteria andAdvanceMoneyGreaterThan(BigDecimal value) {
            addCriterion("advance_money >", value, "advanceMoney");
            return (Criteria) this;
        }

        public Criteria andAdvanceMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("advance_money >=", value, "advanceMoney");
            return (Criteria) this;
        }

        public Criteria andAdvanceMoneyLessThan(BigDecimal value) {
            addCriterion("advance_money <", value, "advanceMoney");
            return (Criteria) this;
        }

        public Criteria andAdvanceMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("advance_money <=", value, "advanceMoney");
            return (Criteria) this;
        }

        public Criteria andAdvanceMoneyIn(List<BigDecimal> values) {
            addCriterion("advance_money in", values, "advanceMoney");
            return (Criteria) this;
        }

        public Criteria andAdvanceMoneyNotIn(List<BigDecimal> values) {
            addCriterion("advance_money not in", values, "advanceMoney");
            return (Criteria) this;
        }

        public Criteria andAdvanceMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("advance_money between", value1, value2, "advanceMoney");
            return (Criteria) this;
        }

        public Criteria andAdvanceMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("advance_money not between", value1, value2, "advanceMoney");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeIsNull() {
            addCriterion("advance_code is null");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeIsNotNull() {
            addCriterion("advance_code is not null");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeEqualTo(String value) {
            addCriterion("advance_code =", value, "advanceCode");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeNotEqualTo(String value) {
            addCriterion("advance_code <>", value, "advanceCode");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeGreaterThan(String value) {
            addCriterion("advance_code >", value, "advanceCode");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("advance_code >=", value, "advanceCode");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeLessThan(String value) {
            addCriterion("advance_code <", value, "advanceCode");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeLessThanOrEqualTo(String value) {
            addCriterion("advance_code <=", value, "advanceCode");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeLike(String value) {
            addCriterion("advance_code like", value, "advanceCode");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeNotLike(String value) {
            addCriterion("advance_code not like", value, "advanceCode");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeIn(List<String> values) {
            addCriterion("advance_code in", values, "advanceCode");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeNotIn(List<String> values) {
            addCriterion("advance_code not in", values, "advanceCode");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeBetween(String value1, String value2) {
            addCriterion("advance_code between", value1, value2, "advanceCode");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeNotBetween(String value1, String value2) {
            addCriterion("advance_code not between", value1, value2, "advanceCode");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyIsNull() {
            addCriterion("freeze_money is null");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyIsNotNull() {
            addCriterion("freeze_money is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyEqualTo(BigDecimal value) {
            addCriterion("freeze_money =", value, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyNotEqualTo(BigDecimal value) {
            addCriterion("freeze_money <>", value, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyGreaterThan(BigDecimal value) {
            addCriterion("freeze_money >", value, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_money >=", value, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyLessThan(BigDecimal value) {
            addCriterion("freeze_money <", value, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_money <=", value, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyIn(List<BigDecimal> values) {
            addCriterion("freeze_money in", values, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyNotIn(List<BigDecimal> values) {
            addCriterion("freeze_money not in", values, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_money between", value1, value2, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_money not between", value1, value2, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeIsNull() {
            addCriterion("freeze_code is null");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeIsNotNull() {
            addCriterion("freeze_code is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeEqualTo(String value) {
            addCriterion("freeze_code =", value, "freezeCode");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeNotEqualTo(String value) {
            addCriterion("freeze_code <>", value, "freezeCode");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeGreaterThan(String value) {
            addCriterion("freeze_code >", value, "freezeCode");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("freeze_code >=", value, "freezeCode");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeLessThan(String value) {
            addCriterion("freeze_code <", value, "freezeCode");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeLessThanOrEqualTo(String value) {
            addCriterion("freeze_code <=", value, "freezeCode");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeLike(String value) {
            addCriterion("freeze_code like", value, "freezeCode");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeNotLike(String value) {
            addCriterion("freeze_code not like", value, "freezeCode");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeIn(List<String> values) {
            addCriterion("freeze_code in", values, "freezeCode");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeNotIn(List<String> values) {
            addCriterion("freeze_code not in", values, "freezeCode");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeBetween(String value1, String value2) {
            addCriterion("freeze_code between", value1, value2, "freezeCode");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeNotBetween(String value1, String value2) {
            addCriterion("freeze_code not between", value1, value2, "freezeCode");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdIsNull() {
            addCriterion("cloudpaypwd is null");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdIsNotNull() {
            addCriterion("cloudpaypwd is not null");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdEqualTo(String value) {
            addCriterion("cloudpaypwd =", value, "cloudpaypwd");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdNotEqualTo(String value) {
            addCriterion("cloudpaypwd <>", value, "cloudpaypwd");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdGreaterThan(String value) {
            addCriterion("cloudpaypwd >", value, "cloudpaypwd");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdGreaterThanOrEqualTo(String value) {
            addCriterion("cloudpaypwd >=", value, "cloudpaypwd");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdLessThan(String value) {
            addCriterion("cloudpaypwd <", value, "cloudpaypwd");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdLessThanOrEqualTo(String value) {
            addCriterion("cloudpaypwd <=", value, "cloudpaypwd");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdLike(String value) {
            addCriterion("cloudpaypwd like", value, "cloudpaypwd");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdNotLike(String value) {
            addCriterion("cloudpaypwd not like", value, "cloudpaypwd");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdIn(List<String> values) {
            addCriterion("cloudpaypwd in", values, "cloudpaypwd");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdNotIn(List<String> values) {
            addCriterion("cloudpaypwd not in", values, "cloudpaypwd");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdBetween(String value1, String value2) {
            addCriterion("cloudpaypwd between", value1, value2, "cloudpaypwd");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdNotBetween(String value1, String value2) {
            addCriterion("cloudpaypwd not between", value1, value2, "cloudpaypwd");
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

        public Criteria andCertificateIsNull() {
            addCriterion("certificate is null");
            return (Criteria) this;
        }

        public Criteria andCertificateIsNotNull() {
            addCriterion("certificate is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateEqualTo(String value) {
            addCriterion("certificate =", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotEqualTo(String value) {
            addCriterion("certificate <>", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateGreaterThan(String value) {
            addCriterion("certificate >", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateGreaterThanOrEqualTo(String value) {
            addCriterion("certificate >=", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLessThan(String value) {
            addCriterion("certificate <", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLessThanOrEqualTo(String value) {
            addCriterion("certificate <=", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLike(String value) {
            addCriterion("certificate like", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotLike(String value) {
            addCriterion("certificate not like", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateIn(List<String> values) {
            addCriterion("certificate in", values, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotIn(List<String> values) {
            addCriterion("certificate not in", values, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateBetween(String value1, String value2) {
            addCriterion("certificate between", value1, value2, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotBetween(String value1, String value2) {
            addCriterion("certificate not between", value1, value2, "certificate");
            return (Criteria) this;
        }

        public Criteria andPhoneemailIsNull() {
            addCriterion("phoneemail is null");
            return (Criteria) this;
        }

        public Criteria andPhoneemailIsNotNull() {
            addCriterion("phoneemail is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneemailEqualTo(String value) {
            addCriterion("phoneemail =", value, "phoneemail");
            return (Criteria) this;
        }

        public Criteria andPhoneemailNotEqualTo(String value) {
            addCriterion("phoneemail <>", value, "phoneemail");
            return (Criteria) this;
        }

        public Criteria andPhoneemailGreaterThan(String value) {
            addCriterion("phoneemail >", value, "phoneemail");
            return (Criteria) this;
        }

        public Criteria andPhoneemailGreaterThanOrEqualTo(String value) {
            addCriterion("phoneemail >=", value, "phoneemail");
            return (Criteria) this;
        }

        public Criteria andPhoneemailLessThan(String value) {
            addCriterion("phoneemail <", value, "phoneemail");
            return (Criteria) this;
        }

        public Criteria andPhoneemailLessThanOrEqualTo(String value) {
            addCriterion("phoneemail <=", value, "phoneemail");
            return (Criteria) this;
        }

        public Criteria andPhoneemailLike(String value) {
            addCriterion("phoneemail like", value, "phoneemail");
            return (Criteria) this;
        }

        public Criteria andPhoneemailNotLike(String value) {
            addCriterion("phoneemail not like", value, "phoneemail");
            return (Criteria) this;
        }

        public Criteria andPhoneemailIn(List<String> values) {
            addCriterion("phoneemail in", values, "phoneemail");
            return (Criteria) this;
        }

        public Criteria andPhoneemailNotIn(List<String> values) {
            addCriterion("phoneemail not in", values, "phoneemail");
            return (Criteria) this;
        }

        public Criteria andPhoneemailBetween(String value1, String value2) {
            addCriterion("phoneemail between", value1, value2, "phoneemail");
            return (Criteria) this;
        }

        public Criteria andPhoneemailNotBetween(String value1, String value2) {
            addCriterion("phoneemail not between", value1, value2, "phoneemail");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeIsNull() {
            addCriterion("taxpayerType is null");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeIsNotNull() {
            addCriterion("taxpayerType is not null");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeEqualTo(String value) {
            addCriterion("taxpayerType =", value, "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeNotEqualTo(String value) {
            addCriterion("taxpayerType <>", value, "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeGreaterThan(String value) {
            addCriterion("taxpayerType >", value, "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeGreaterThanOrEqualTo(String value) {
            addCriterion("taxpayerType >=", value, "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeLessThan(String value) {
            addCriterion("taxpayerType <", value, "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeLessThanOrEqualTo(String value) {
            addCriterion("taxpayerType <=", value, "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeLike(String value) {
            addCriterion("taxpayerType like", value, "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeNotLike(String value) {
            addCriterion("taxpayerType not like", value, "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeIn(List<String> values) {
            addCriterion("taxpayerType in", values, "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeNotIn(List<String> values) {
            addCriterion("taxpayerType not in", values, "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeBetween(String value1, String value2) {
            addCriterion("taxpayerType between", value1, value2, "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeNotBetween(String value1, String value2) {
            addCriterion("taxpayerType not between", value1, value2, "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTinIsNull() {
            addCriterion("tin is null");
            return (Criteria) this;
        }

        public Criteria andTinIsNotNull() {
            addCriterion("tin is not null");
            return (Criteria) this;
        }

        public Criteria andTinEqualTo(String value) {
            addCriterion("tin =", value, "tin");
            return (Criteria) this;
        }

        public Criteria andTinNotEqualTo(String value) {
            addCriterion("tin <>", value, "tin");
            return (Criteria) this;
        }

        public Criteria andTinGreaterThan(String value) {
            addCriterion("tin >", value, "tin");
            return (Criteria) this;
        }

        public Criteria andTinGreaterThanOrEqualTo(String value) {
            addCriterion("tin >=", value, "tin");
            return (Criteria) this;
        }

        public Criteria andTinLessThan(String value) {
            addCriterion("tin <", value, "tin");
            return (Criteria) this;
        }

        public Criteria andTinLessThanOrEqualTo(String value) {
            addCriterion("tin <=", value, "tin");
            return (Criteria) this;
        }

        public Criteria andTinLike(String value) {
            addCriterion("tin like", value, "tin");
            return (Criteria) this;
        }

        public Criteria andTinNotLike(String value) {
            addCriterion("tin not like", value, "tin");
            return (Criteria) this;
        }

        public Criteria andTinIn(List<String> values) {
            addCriterion("tin in", values, "tin");
            return (Criteria) this;
        }

        public Criteria andTinNotIn(List<String> values) {
            addCriterion("tin not in", values, "tin");
            return (Criteria) this;
        }

        public Criteria andTinBetween(String value1, String value2) {
            addCriterion("tin between", value1, value2, "tin");
            return (Criteria) this;
        }

        public Criteria andTinNotBetween(String value1, String value2) {
            addCriterion("tin not between", value1, value2, "tin");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressPersonIsNull() {
            addCriterion("address_person is null");
            return (Criteria) this;
        }

        public Criteria andAddressPersonIsNotNull() {
            addCriterion("address_person is not null");
            return (Criteria) this;
        }

        public Criteria andAddressPersonEqualTo(String value) {
            addCriterion("address_person =", value, "addressPerson");
            return (Criteria) this;
        }

        public Criteria andAddressPersonNotEqualTo(String value) {
            addCriterion("address_person <>", value, "addressPerson");
            return (Criteria) this;
        }

        public Criteria andAddressPersonGreaterThan(String value) {
            addCriterion("address_person >", value, "addressPerson");
            return (Criteria) this;
        }

        public Criteria andAddressPersonGreaterThanOrEqualTo(String value) {
            addCriterion("address_person >=", value, "addressPerson");
            return (Criteria) this;
        }

        public Criteria andAddressPersonLessThan(String value) {
            addCriterion("address_person <", value, "addressPerson");
            return (Criteria) this;
        }

        public Criteria andAddressPersonLessThanOrEqualTo(String value) {
            addCriterion("address_person <=", value, "addressPerson");
            return (Criteria) this;
        }

        public Criteria andAddressPersonLike(String value) {
            addCriterion("address_person like", value, "addressPerson");
            return (Criteria) this;
        }

        public Criteria andAddressPersonNotLike(String value) {
            addCriterion("address_person not like", value, "addressPerson");
            return (Criteria) this;
        }

        public Criteria andAddressPersonIn(List<String> values) {
            addCriterion("address_person in", values, "addressPerson");
            return (Criteria) this;
        }

        public Criteria andAddressPersonNotIn(List<String> values) {
            addCriterion("address_person not in", values, "addressPerson");
            return (Criteria) this;
        }

        public Criteria andAddressPersonBetween(String value1, String value2) {
            addCriterion("address_person between", value1, value2, "addressPerson");
            return (Criteria) this;
        }

        public Criteria andAddressPersonNotBetween(String value1, String value2) {
            addCriterion("address_person not between", value1, value2, "addressPerson");
            return (Criteria) this;
        }

        public Criteria andMerchNoLikeInsensitive(String value) {
            addCriterion("upper(merch_no) like", value.toUpperCase(), "merchNo");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andMerchNameLikeInsensitive(String value) {
            addCriterion("upper(merch_name) like", value.toUpperCase(), "merchName");
            return (Criteria) this;
        }

        public Criteria andPhonenameLikeInsensitive(String value) {
            addCriterion("upper(phonename) like", value.toUpperCase(), "phonename");
            return (Criteria) this;
        }

        public Criteria andPhoneLikeInsensitive(String value) {
            addCriterion("upper(phone) like", value.toUpperCase(), "phone");
            return (Criteria) this;
        }

        public Criteria andBslicenseLikeInsensitive(String value) {
            addCriterion("upper(bslicense) like", value.toUpperCase(), "bslicense");
            return (Criteria) this;
        }

        public Criteria andAptitudeLikeInsensitive(String value) {
            addCriterion("upper(aptitude) like", value.toUpperCase(), "aptitude");
            return (Criteria) this;
        }

        public Criteria andServiclinkuserLikeInsensitive(String value) {
            addCriterion("upper(serviclinkuser) like", value.toUpperCase(), "serviclinkuser");
            return (Criteria) this;
        }

        public Criteria andLinkphoneLikeInsensitive(String value) {
            addCriterion("upper(linkphone) like", value.toUpperCase(), "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkemailLikeInsensitive(String value) {
            addCriterion("upper(linkemail) like", value.toUpperCase(), "linkemail");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyLikeInsensitive(String value) {
            addCriterion("upper(private_key) like", value.toUpperCase(), "privateKey");
            return (Criteria) this;
        }

        public Criteria andCloudcodeLikeInsensitive(String value) {
            addCriterion("upper(cloudcode) like", value.toUpperCase(), "cloudcode");
            return (Criteria) this;
        }

        public Criteria andAdvanceCodeLikeInsensitive(String value) {
            addCriterion("upper(advance_code) like", value.toUpperCase(), "advanceCode");
            return (Criteria) this;
        }

        public Criteria andFreezeCodeLikeInsensitive(String value) {
            addCriterion("upper(freeze_code) like", value.toUpperCase(), "freezeCode");
            return (Criteria) this;
        }

        public Criteria andCloudpaypwdLikeInsensitive(String value) {
            addCriterion("upper(cloudpaypwd) like", value.toUpperCase(), "cloudpaypwd");
            return (Criteria) this;
        }

        public Criteria andCertificateLikeInsensitive(String value) {
            addCriterion("upper(certificate) like", value.toUpperCase(), "certificate");
            return (Criteria) this;
        }

        public Criteria andPhoneemailLikeInsensitive(String value) {
            addCriterion("upper(phoneemail) like", value.toUpperCase(), "phoneemail");
            return (Criteria) this;
        }

        public Criteria andTaxpayertypeLikeInsensitive(String value) {
            addCriterion("upper(taxpayerType) like", value.toUpperCase(), "taxpayertype");
            return (Criteria) this;
        }

        public Criteria andTinLikeInsensitive(String value) {
            addCriterion("upper(tin) like", value.toUpperCase(), "tin");
            return (Criteria) this;
        }

        public Criteria andAddressLikeInsensitive(String value) {
            addCriterion("upper(address) like", value.toUpperCase(), "address");
            return (Criteria) this;
        }

        public Criteria andAddressPersonLikeInsensitive(String value) {
            addCriterion("upper(address_person) like", value.toUpperCase(), "addressPerson");
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