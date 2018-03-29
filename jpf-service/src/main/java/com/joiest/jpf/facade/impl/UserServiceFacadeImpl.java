package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.constant.EnumConstants;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayUser;
import com.joiest.jpf.common.po.PayUserExample;
import com.joiest.jpf.common.util.SHA1;
import com.joiest.jpf.dao.repository.mapper.generate.PayUserMapper;
import com.joiest.jpf.dto.LoginVerifyResponse;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.UserServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class UserServiceFacadeImpl implements UserServiceFacade {

    private static final Logger logger = LogManager.getLogger(UserServiceFacadeImpl.class);

    @Autowired
    private PayUserMapper payUserMapper;

    @Override
    public List<UserInfo> getUsers(String userName, String status, long pageNo, long pageSize) {
        if (pageNo<=0) {
            pageNo = 1;
        }
        if (pageSize<=0) {
            pageSize = 20;
        }
        PayUserExample example = new PayUserExample();
        example.setPageNo(pageNo);
        example.setPageSize(pageSize);
        example.setOrderByClause("CREATE_TIME DESC");
        PayUserExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotBlank(userName)) {
            c.andUserNameEqualTo(userName);
        }
        if (StringUtils.isNotBlank(status)) {
            c.andStatusEqualTo(status);
        }
        List<PayUser> payUserList = payUserMapper.selectByExample(example);
        List<UserInfo> userInfoList = new ArrayList<>();
        for (PayUser payUser : payUserList) {
            UserInfo userInfo = new UserInfo();
            BeanCopier beanCopier = BeanCopier.create(PayUser.class, UserInfo.class, false);
            beanCopier.copy(payUser, userInfo, null);
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    @Override
    public int getUsersCount(String userName,String status){
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotBlank(userName)) {
            c.andUserNameEqualTo(userName);
        }
        if (StringUtils.isNotBlank(status)) {
            c.andStatusEqualTo(status);
        }
        return payUserMapper.countByExample(example);
    }

    @Override
    public JpfResponseDto addUser(String userName, String pwd) {
        if (StringUtils.isBlank(userName)) {
            return setResponse("9999", "用户名不能为空");
        }
        if(StringUtils.isBlank(pwd)){
            return setResponse("9999", "密码不能为空");
        }
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        c.andUserNameEqualTo(userName.trim());
        List<PayUser> payUserList = payUserMapper.selectByExample(example);
        if(payUserList!=null&&!payUserList.isEmpty()){
            return setResponse("9999", "用户已经存在");
        }
        PayUser payUser = new PayUser();
        payUser.setUserName(userName);
        payUser.setPassword(SHA1.getInstance().getMySHA1Code(pwd));
        payUser.setStatus(EnumConstants.UserStatus.normal.value());
        int index = payUserMapper.insertSelective(payUser);
        if(index !=1){
            return setResponse("9999","添加失败");
        }
        return setResponse("0000","操作成功");
    }

    @Override
    public LoginVerifyResponse loginVerify(String userName, String pwd) {
        LoginVerifyResponse loginVerifyResponse= new LoginVerifyResponse();
        if (StringUtils.isBlank(userName)) {
            logger.debug("请求参数错误：用户名不能为空");
            loginVerifyResponse.setRetCode("9999");
            loginVerifyResponse.setRetMsg("用户名或密码错误！");
            return loginVerifyResponse;
        }
        if (StringUtils.isBlank(pwd)) {
            logger.debug("请求参数错误：密码不能为空");
            loginVerifyResponse.setRetCode("9999");
            loginVerifyResponse.setRetMsg("用户名或密码错误！");
            return loginVerifyResponse;
        }
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        c.andUserNameEqualTo(userName.trim());
        List<PayUser> payUserList = payUserMapper.selectByExample(example);
        if(payUserList==null||payUserList.isEmpty()){
            logger.debug("用户信息不存在userName={}",userName);
            loginVerifyResponse.setRetCode("9999");
            loginVerifyResponse.setRetMsg("用户名或密码错误！");
            return loginVerifyResponse;
        }
        PayUser payUser = payUserList.get(0);
        if(!SHA1.getInstance().getMySHA1Code(pwd).equals(payUser.getPassword())){
            logger.debug("密码错误userName={}",userName);
            loginVerifyResponse.setRetCode("9999");
            loginVerifyResponse.setRetMsg("用户名或密码错误！");
            return loginVerifyResponse;
        }
        if(!EnumConstants.UserStatus.normal.value().equals(payUser.getStatus())){
            logger.debug("状态异常status={}",payUser.getStatus());
            loginVerifyResponse.setRetCode("9999");
            loginVerifyResponse.setRetMsg("用户状态异常！");
            return loginVerifyResponse;
        }
        UserInfo userInfo = new UserInfo();
        BeanCopier beanCopier = BeanCopier.create(PayUser.class, UserInfo.class, false);
        beanCopier.copy(payUser,userInfo,null);
        loginVerifyResponse.setRetCode("0000");
        loginVerifyResponse.setRetMsg("操作完成！");
        loginVerifyResponse.setUserInfo(userInfo);
        return loginVerifyResponse;
    }

    @Override
    public JpfResponseDto modifyPwd(String userName, String oldPwd, String newPwd) {
        if (StringUtils.isBlank(userName)) {
            return setResponse("9999", "用户名不能为空");
        }
        if(StringUtils.isBlank(oldPwd)){
            return setResponse("9999", "原密码不能为空");
        }
        if (StringUtils.isBlank(newPwd)) {
            return setResponse("9999", "新密码不能为空");
        }
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        c.andUserNameEqualTo(userName.trim());
        List<PayUser> payUserList = payUserMapper.selectByExample(example);
        if(payUserList==null||payUserList.isEmpty()){
            return setResponse("9999", "用户不存在");
        }
        PayUser payUser = payUserList.get(0);
        if(!SHA1.getInstance().getMySHA1Code(oldPwd).equals(payUser.getPassword())){
            return setResponse("9999", "密码不正确");
        }
        if(!EnumConstants.UserStatus.normal.value().equals(payUser.getStatus())){
            return setResponse("9999", "状态异常不可以操作");
        }

        PayUser user = new PayUser();
        user.setId(payUser.getId());
        user.setPassword(SHA1.getInstance().getMySHA1Code(newPwd));
        payUserMapper.updateByPrimaryKeySelective(user);
        return setResponse("0000", "操作完成");
    }

    @Override
    public JpfResponseDto resetPwd(String userName){
        if (StringUtils.isBlank(userName)) {
            return setResponse("9999", "用户名不能为空");
        }
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        c.andUserNameEqualTo(userName.trim());
        List<PayUser> payUserList = payUserMapper.selectByExample(example);
        if(payUserList==null||payUserList.isEmpty()){
            return setResponse("9999", "用户不存在");
        }
        PayUser payUser = payUserList.get(0);
        if(!EnumConstants.UserStatus.normal.value().equals(payUser.getStatus())){
            return setResponse("9999", "状态异常不可以操作");
        }

        PayUser user = new PayUser();
        user.setId(payUser.getId());
        user.setPassword(SHA1.getInstance().getMySHA1Code("abc123"));
        payUserMapper.updateByPrimaryKeySelective(user);
        return setResponse("0000", "操作完成");
    }

    @Override
    public JpfResponseDto alterStatus(String userName, String status) {
        if (StringUtils.isBlank(userName)) {
            return setResponse("9999", "用户名不能为空");
        }
        if (StringUtils.isBlank(status)) {
            return setResponse("9999", "状态不能为空");
        }
        if(!EnumConstants.UserStatus.normal.value().equals(status)&&!EnumConstants.UserStatus.forbid.value().equals(status)){
            return setResponse("9999", "状态不匹配");
        }
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        c.andUserNameEqualTo(userName);
        PayUser user = new PayUser();
        user.setStatus(status);
        payUserMapper.updateByExampleSelective(user,example);
        return setResponse("0000", "操作完成");
    }

    private JpfResponseDto setResponse(String errorCode,String errorMsg) {
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        jpfResponseDto.setRetCode(errorCode);
        jpfResponseDto.setRetMsg(errorMsg);
        return jpfResponseDto;
    }
}
