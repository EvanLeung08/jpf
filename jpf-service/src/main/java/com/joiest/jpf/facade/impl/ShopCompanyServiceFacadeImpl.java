package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopCompany;
import com.joiest.jpf.common.po.PayShopCompanyExample;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopCompanyCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCompanyMapper;
import com.joiest.jpf.dto.GetShopCompanyRequest;
import com.joiest.jpf.dto.GetShopCompanyResponse;
import com.joiest.jpf.entity.ShopCompanyInfo;
import com.joiest.jpf.facade.ShopCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class ShopCompanyServiceFacadeImpl implements ShopCompanyServiceFacade {

    @Autowired
    private PayShopCompanyMapper payShopCompanyMapper;

    @Autowired
    private PayShopCompanyCustomMapper payShopCompanyCustomMapper;
    /**
     * 公司列表---后台
     */
    @Override
    public GetShopCompanyResponse getList(GetShopCompanyRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayShopCompanyExample example = new PayShopCompanyExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("addtime DESC");

        PayShopCompanyExample.Criteria c = example.createCriteria();
        if ( StringUtils.isNotBlank(request.getId()) ){
            c.andIdEqualTo(request.getId());
        }
        if ( StringUtils.isNotBlank(request.getMerchNo()))
        {
            c.andMerchNoEqualTo(request.getMerchNo() );
        }
        if( StringUtils.isNotBlank(request.getCompanyName())){
            c.andCompanyNameLike("%"+ request.getCompanyName() +"%");
        }
        if( StringUtils.isNotBlank(request.getSaleName())){
            c.andSaleNameEqualTo(request.getSaleName());
        }
        if(request.getStatus()!=null && request.getStatus().toString()!=""){
            c.andStatusEqualTo(request.getStatus());
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }

        List<PayShopCompany> list = payShopCompanyMapper.selectByExample(example);
        List<ShopCompanyInfo> infoList = new ArrayList<>();
        for (PayShopCompany one : list)
        {
            ShopCompanyInfo info = new ShopCompanyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCompany.class, ShopCompanyInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetShopCompanyResponse response = new GetShopCompanyResponse();
        response.setList(infoList);
        int count = payShopCompanyMapper.countByExample(example);
        response.setCount(count);
        return response;
    }

    /**
     * 公司添加
     */
    @Override
    public JpfResponseDto addCompany(GetShopCompanyRequest request,int account){

        //验证参数
        if(StringUtils.isBlank(request.getCompanyName())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "企业名称不能为空");

        }else if(StringUtils.isBlank(request.getContactName())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系人姓名不能为空");

        }else if(StringUtils.isBlank(request.getContactPhone())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系人电话不能为空");

        }else if(StringUtils.isBlank(request.getReceiveName())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人不能为空");

        }else if(StringUtils.isBlank(request.getReceivePhone())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人电话不能为空");

        }else if(StringUtils.isBlank(request.getReceiveEmail())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人邮箱不能为空");

        }else if(request.getPercent().equals("")){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "必须填写转让百分比");
        }
        String pattern = "^[1][3,4,5,7,8][0-9]{9}$";

        boolean isphone = Pattern.matches(pattern, request.getContactPhone());
        double percent = request.getPercent().doubleValue();
        if(percent > 0.9 || percent < 0.1){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "只能填写0.1到0.9之间的值");
        }
        if(isphone==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系电话不正确");
        }
        boolean isphonere = Pattern.matches(pattern, request.getReceivePhone());

        if(isphonere==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人电话不正确");
        }
        boolean isphoneresale = Pattern.matches(pattern, request.getSalePhone());

        if(isphoneresale==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "销售电话不正确");
        }
        String emailpattern="^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
        boolean isemail = Pattern.matches(emailpattern, request.getReceiveEmail());

        if(isemail==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人邮箱不正确");
        }
       //查询当前信息表中是否存在
        //查询当前添加的是否存在
        PayShopCompanyExample example= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c = example.createCriteria();

        c.andCompanyNameEqualTo(request.getCompanyName());

        List<PayShopCompany> CompanyList = payShopCompanyMapper.selectByExample(example);

        if(CompanyList != null && !CompanyList.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "企业名称已经存在");
        }

        PayShopCompanyExample exampleName= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c2 = exampleName.createCriteria();

        c2.andContactNameEqualTo(request.getContactName());

        List<PayShopCompany> CompanyListName = payShopCompanyMapper.selectByExample(exampleName);

        if(CompanyListName != null && !CompanyListName.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "联系人已经存在");
        }

        PayShopCompanyExample examplePhone= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c1 = examplePhone.createCriteria();

        c1.andContactPhoneEqualTo(request.getContactPhone());

        List<PayShopCompany> CompanyListContact = payShopCompanyMapper.selectByExample(examplePhone);

        if(CompanyListContact != null && !CompanyListContact.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "联系电话已经存在");
        }

        //验证
        PayShopCompanyExample exampleEmail= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c4 = exampleEmail.createCriteria();
        c4.andReceiveEmailEqualTo(request.getReceiveEmail());
        List<PayShopCompany> CompanyListEmail = payShopCompanyMapper.selectByExample(exampleEmail);
        if(CompanyListEmail != null && !CompanyListEmail.isEmpty()){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "邮箱已经存在");
        }

        //插表操作
        //生成商户编号
        Date date = new Date();
        String MerchnoNew=ToolUtils.createID();
        //查询商户号是否已经存在
        PayShopCompanyExample exampleMerchno= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria cMerno = exampleMerchno.createCriteria();
        cMerno.andMerchNoEqualTo(MerchnoNew);
        List<PayShopCompany> payCloudCompanyListMerno = payShopCompanyMapper.selectByExample(exampleMerchno);
        if(payCloudCompanyListMerno != null && !payCloudCompanyListMerno.isEmpty()){
            //重新生成商户编号
            MerchnoNew=ToolUtils.createID();
        }
        PayShopCompany payShopCompany =new PayShopCompany();
        payShopCompany.setCompanyName(request.getCompanyName());
        payShopCompany.setMerchNo(MerchnoNew);
        payShopCompany.setContactName(request.getContactName());
        payShopCompany.setContactPhone(request.getContactPhone());
        payShopCompany.setReceiveName(request.getReceiveName());
        payShopCompany.setReceivePhone(request.getReceivePhone());
        payShopCompany.setReceiveEmail(request.getReceiveEmail());
        payShopCompany.setSaleName(request.getSaleName());
        payShopCompany.setSalePhone(request.getSalePhone());
        payShopCompany.setPercent(request.getPercent());
        BigDecimal loanAmount = new BigDecimal("0.00");
        String accountReturn=loanAmount.toString();
        payShopCompany.setMoney(loanAmount);
        payShopCompany.setAddtime(date);
        payShopCompany.setLoginName(request.getReceiveEmail());
        Integer randPwd = ToolUtils.getRandomInt(100000,999999);
        payShopCompany.setLoginPwd(SHA1.getInstance().getMySHA1Code(randPwd.toString()));
        payShopCompany.setIsFirstLogin((byte)0);
        if(StringUtils.equals("1",request.getOpenAccent())){
            payShopCompany.setAccountStatus((byte)1);
        }else{
            payShopCompany.setAccountStatus((byte)0);
        }
        //获取刚插入的id
           int res = payShopCompanyCustomMapper.insertSelective(payShopCompany);
            String sprimatkey = payShopCompany.getId();
           //修改金额校验
            PayShopCompanyExample updateCode=new PayShopCompanyExample();
            PayShopCompanyExample.Criteria cUpdate=updateCode.createCriteria();
            cUpdate.andIdEqualTo(sprimatkey);
            PayShopCompany payShopCompanyUp=new PayShopCompany();
            String code=ToolUtils.CreateCode(accountReturn,sprimatkey);
            payShopCompanyUp.setMoneyCode(code);
            try {
                payShopCompanyMapper.updateByExampleSelective(payShopCompanyUp,updateCode);
                if(StringUtils.equals("1",request.getOpenAccent())){
                    sendMailToCompany(payShopCompany,randPwd);
                }
            } catch (Exception e) {
                throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "添加失败");
            }
        try {

        }catch (Exception e){
            throw new JpfException(JpfErrorInfo.SYSTEM_ERROR, "账户开通失败");
        }

        return new JpfResponseDto();
    }

    //
    private void sendMailToCompany(PayShopCompany company,Integer randPwd) throws Exception {
        String html="<div style='width: 600px;margin: 0 auto'><h3 style='color:#003E64; text-align:center; '>欣享爱生活帐号开通</h3>" +
                "<p style=''>尊敬的用户您好：</p><p style='text-indent: 2em'>感谢您使用欣享爱生活|企业管理后台，您的帐号已经开通,帐号信息如下：</p>" +
                "<p style='text-indent: 2em'>用户名："+company.getLoginName()+"</p>" +
                "<p style='text-indent: 2em'>登录密码："+randPwd+"</p>" +
                "<p style='text-align: right; color:#003E64; font-size: 20px;'>欣享科技团队</p></div>";
        SendMailUtil.sendHtmlEmail2(company.getReceiveEmail(),"欣享爱生活 --登录账号/密码","欣享爱生活用户",html);
    }

    /**
     * 公司单条记录
     */
    @Override
    public ShopCompanyInfo getCompanyOne(String id)
    {
        if ( StringUtils.isBlank(id))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayShopCompany  payShopCompany= payShopCompanyMapper.selectByPrimaryKey(id);

        ShopCompanyInfo shopCompanyInfo = new ShopCompanyInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopCompany.class, ShopCompanyInfo.class, false);
        beanCopier.copy(payShopCompany,shopCompanyInfo,null);

        return shopCompanyInfo;
    }

    /**
     * 公司修改
     */
    @Override
    public JpfResponseDto editCompany(GetShopCompanyRequest request,int account){

        //验证参数
        if(StringUtils.isBlank(request.getCompanyName())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "企业名称不能为空");

        }else if(StringUtils.isBlank(request.getContactName())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系人姓名不能为空");

        }else if(StringUtils.isBlank(request.getContactPhone())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系人电话不能为空");

        }else if(StringUtils.isBlank(request.getReceiveName())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人不能为空");

        }else if(StringUtils.isBlank(request.getReceivePhone())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人电话不能为空");

//        }else if(StringUtils.isBlank(request.getReceiveEmail())){
//
//            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人邮箱不能为空");

        }else if(request.getPercent().equals("")){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "必须填写转让百分比");
        }
        String pattern = "^[1][3,4,5,7,8][0-9]{9}$";

        boolean isphone = Pattern.matches(pattern, request.getContactPhone());
        double percent = request.getPercent().doubleValue();
        if(percent > 0.9 || percent < 0.1){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "只能填写0.1到0.9之间的值");
        }
        if(isphone==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系电话不正确");
        }
        boolean isphonere = Pattern.matches(pattern, request.getReceivePhone());

        if(isphonere==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人电话不正确");
        }
        boolean isphoneresale = Pattern.matches(pattern, request.getSalePhone());

        if(isphoneresale==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "销售电话不正确");
        }
//        String emailpattern="^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
//        boolean isemail = Pattern.matches(emailpattern, request.getReceiveEmail());
//
//        if(isemail==false){
//            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人邮箱不正确");
//        }

        //查询当前信息表中是否存在
        PayShopCompanyExample example= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c = example.createCriteria();

        c.andIdEqualTo(request.getId());

        List<PayShopCompany> CompanyList = payShopCompanyMapper.selectByExample(example);

        if(CompanyList == null && CompanyList.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "此条信息不存在");
        }

        PayShopCompanyExample exampleName= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c2 = exampleName.createCriteria();

        c2.andContactNameEqualTo(request.getContactName());
        c2.andIdNotEqualTo(request.getId());
        List<PayShopCompany> CompanyListName = payShopCompanyMapper.selectByExample(exampleName);

        if(CompanyListName != null && !CompanyListName.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "联系人已经存在");
        }

        PayShopCompanyExample examplePhone= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c1 = examplePhone.createCriteria();

        c1.andContactPhoneEqualTo(request.getContactPhone());
        c1.andIdNotEqualTo(request.getId());
        List<PayShopCompany> CompanyListContact = payShopCompanyMapper.selectByExample(examplePhone);

        if(CompanyListContact != null && !CompanyListContact.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "联系电话已经存在");
        }

        //修改表操作
        PayShopCompany payShopCompany =new PayShopCompany();
        payShopCompany.setCompanyName(request.getCompanyName());
        payShopCompany.setContactName(request.getContactName());
        payShopCompany.setContactPhone(request.getContactPhone());
        payShopCompany.setReceiveName(request.getReceiveName());
        payShopCompany.setReceivePhone(request.getReceivePhone());
        payShopCompany.setReceiveEmail(request.getReceiveEmail());
        payShopCompany.setSaleName(request.getSaleName());
        payShopCompany.setSalePhone(request.getSalePhone());
        payShopCompany.setPercent(request.getPercent());
        
        //修改基本信息表
        String sprimatkey = request.getId();
        PayShopCompanyExample exampleup= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria ca = exampleup.createCriteria();
        ca.andIdEqualTo(sprimatkey);
        payShopCompanyMapper.updateByExampleSelective(payShopCompany,exampleup);

        return new JpfResponseDto();
    }

    /**
     * 停用公司 更改状态 1启用 0停用
     */
    @Override
    public JpfResponseDto delCompanyShop(String merchNo,int type)
    {
        if ( StringUtils.isBlank(merchNo) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "商户编号不能为空");
        }
        //查询当前添加的是否存在
        PayShopCompanyExample example= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c = example.createCriteria();
        c.andMerchNoEqualTo(merchNo);

        List<PayShopCompany> payShopCompanyList = payShopCompanyMapper.selectByExample(example);
        if(payShopCompanyList.isEmpty() || payShopCompanyList==null){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "公司不存在");
        }
        Byte defaultStatus =payShopCompanyList.get(0).getStatus();
        PayShopCompany payShopCompany = new PayShopCompany();
        Byte status=-1;
        if(type==2){
            status = 0;
            if(defaultStatus==status){
                throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "该企业已被停用");
            }
        }else if (type==1){
            status = 1;
            if(defaultStatus==status){
                throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "该企业已被启用");
            }
        }
        payShopCompany.setStatus(status);
        payShopCompanyMapper.updateByExampleSelective(payShopCompany,example);
        return new JpfResponseDto();
    }

    /**
     * 公司单条记录
     */
    @Override
    public JpfResponseDto updateCompanyRecord(PayShopCompany payShopCompany)
    {
        if ( StringUtils.isBlank(payShopCompany.getId()))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        int count = payShopCompanyMapper.updateByPrimaryKeySelective(payShopCompany);
        if(count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "更新失败");
        }

        return new JpfResponseDto();
    }

    /**
     * 公司充值
     */
    @Override
    public int charge(String companyId, double chargeMoney){
        PayShopCompany payShopCompany = payShopCompanyMapper.selectByPrimaryKey(companyId);
        if ( !checkMoneyCode(companyId) ){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "该企业已被启用");
        }

        String money = payShopCompany.getMoney().toString();    // 原始余额
        Double newMoney = Double.parseDouble(money) + chargeMoney;                          // 充值后的余额
        BigDecimal newMoneyBD = new BigDecimal(newMoney).setScale(2,BigDecimal.ROUND_DOWN);
        PayShopCompany payShopCompanyUpdate = new PayShopCompany();
        payShopCompanyUpdate.setId(companyId);
        payShopCompanyUpdate.setMoney(newMoneyBD);                        // 充值后的余额
        payShopCompanyUpdate.setMoneyCode(getMoneyCode(companyId,newMoneyBD.toString()));     // 新的金额校验码

        return payShopCompanyMapper.updateByPrimaryKeySelective(payShopCompanyUpdate);
    }

    /**
     * 获取最新的余额校验码
     */
    @Override
    public String getMoneyCode(String companyId, String money){
        return ToolUtils.CreateCode(money,companyId);
    }

    /**
     * 验证金额校验码的准确性
     * 返回ture为验证通过
     * 返回false为验证失败
     */
    @Override
    public boolean checkMoneyCode(String companyId){
        PayShopCompany payShopCompany = payShopCompanyMapper.selectByPrimaryKey(companyId);
        String newCode = ToolUtils.CreateCode(payShopCompany.getMoney().toString(),payShopCompany.getId());

        if ( newCode.equals(payShopCompany.getMoneyCode()) ){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 通过企业名称获取企业
     */
    @Override
    public PayShopCompany getCompanyByName(String companyName){
        PayShopCompanyExample e = new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c = e.createCriteria();
        c.andCompanyNameEqualTo(companyName);
        List<PayShopCompany> list = payShopCompanyMapper.selectByExample(e);
        if ( list.isEmpty() || list == null ){
            return new PayShopCompany();
        }

        return list.get(0);
    }


    @Override
    public PayShopCompany getCompanyByUserNamnAndPasswd(String userName, String password) {
        PayShopCompanyExample e = new PayShopCompanyExample();
        PayShopCompanyExample.Criteria criteria = e.createCriteria();
        criteria.andLoginNameEqualTo(userName);
        criteria.andLoginPwdEqualTo(SHA1.getInstance().getMySHA1Code(password));
        List<PayShopCompany> list = payShopCompanyMapper.selectByExample(e);
        if (list!=null&&list.size()!=0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public PayShopCompany getById(String companyId) {
        return payShopCompanyMapper.selectByPrimaryKey(companyId);
    }


    @Override
    public void openAccount(String id)  throws Exception{
        PayShopCompany payShopCompany = payShopCompanyMapper.selectByPrimaryKey(id);
        if(StringUtils.isBlank(payShopCompany.getLoginName())){
            payShopCompany.setLoginName(payShopCompany.getReceiveEmail());
            payShopCompany.setIsFirstLogin((byte)0);
        }
        Integer randPwd = ToolUtils.getRandomInt(100000,999999);
        payShopCompany.setLoginPwd(SHA1.getInstance().getMySHA1Code(randPwd.toString()));
        payShopCompany.setAccountStatus((byte)1);
        payShopCompanyMapper.updateByPrimaryKeySelective(payShopCompany);
        //发送邮件
        sendMailToCompany(payShopCompany,randPwd);
    }

    @Override
    public void chargeSub(String companyId, String totalMoney) {
        PayShopCompany payShopCompany = payShopCompanyMapper.selectByPrimaryKey(companyId);
        if ( !checkMoneyCode(companyId) ){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "该企业已被启用");
        }
        PayShopCompany payShopCompanyUpdate = new PayShopCompany();
        payShopCompanyUpdate.setId(companyId);
        BigDecimal sub = ArithmeticUtils.sub(payShopCompany.getMoney().toString(), totalMoney);
        payShopCompanyUpdate.setMoney(sub);                        // 充值后的余额
        payShopCompanyUpdate.setMoneyCode(getMoneyCode(companyId,sub.toString()));     // 新的金额校验码
        payShopCompanyMapper.updateByPrimaryKeySelective(payShopCompanyUpdate);
    }
}
