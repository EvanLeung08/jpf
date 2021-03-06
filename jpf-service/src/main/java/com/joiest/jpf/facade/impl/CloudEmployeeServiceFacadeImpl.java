package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudEmployee;
import com.joiest.jpf.common.po.PayCloudEmployeeExample;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudEmployeeMapper;
import com.joiest.jpf.entity.CloudEmployeeInfo;
import com.joiest.jpf.facade.CloudEmployeeServiceFacade;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CloudEmployeeServiceFacadeImpl implements CloudEmployeeServiceFacade {

    @Autowired
    private PayCloudEmployeeMapper payCloudEmployeeMapper;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    public CloudEmployeeInfo getEmployeeInfoByMerchNo(String merchNo){
        PayCloudEmployeeExample example= new PayCloudEmployeeExample();
        PayCloudEmployeeExample.Criteria c = example.createCriteria();
        c.andMerchNoEqualTo(merchNo);
        List<PayCloudEmployee> employeeInfoList = payCloudEmployeeMapper.selectByExample(example);
        if(employeeInfoList.size() != 1 || employeeInfoList == null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "无效商户号");
        }

        CloudEmployeeInfo cloudEmployeeRep = new CloudEmployeeInfo();
        for (PayCloudEmployee one : employeeInfoList)
        {
            BeanCopier beanCopier = BeanCopier.create(PayCloudEmployee.class, CloudEmployeeInfo.class, false);
            beanCopier.copy(one, cloudEmployeeRep, null);
        }

        return cloudEmployeeRep;
    }

    /**
     * 获取公司登录信息通过邮箱
     **/
    public CloudEmployeeInfo getCompayLoginInfoByEmail(String email){

        PayCloudEmployeeExample example = new PayCloudEmployeeExample();
        PayCloudEmployeeExample.Criteria c = example.createCriteria();

        c.andLinkemailEqualTo(email);

        List<PayCloudEmployee> payCloudEmployee = payCloudEmployeeMapper.selectByExample(example);
        if(payCloudEmployee == null || payCloudEmployee.isEmpty()) return null;

        PayCloudEmployee payCloudEmployee1 = payCloudEmployee.get(0);

        CloudEmployeeInfo cloudEmployeeInfo = new CloudEmployeeInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudEmployee.class,CloudEmployeeInfo.class,false);
        beanCopier.copy(payCloudEmployee1,cloudEmployeeInfo,null);

        return cloudEmployeeInfo;
    }
    /*
    * 获取公司登录信息通过主键id
    * */
    public CloudEmployeeInfo getCompayEmployeeByUid(Integer uid){

        PayCloudEmployee payCloudEmployee = payCloudEmployeeMapper.selectByPrimaryKey(uid);

        if(payCloudEmployee == null) return null;

        CloudEmployeeInfo cloudEmployeeInfo = new CloudEmployeeInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudEmployee.class,CloudEmployeeInfo.class,false);
        beanCopier.copy(payCloudEmployee,cloudEmployeeInfo,null);

        return cloudEmployeeInfo;
    }

    /**
     * 修改登录密码
     **/
    public int upCompanyEmployeePwdByUid(Map<String,String> comInfo, Integer uid){

        PayCloudEmployee payCloudEmployee = new PayCloudEmployee();

        payCloudEmployee.setUid(uid);
        payCloudEmployee.setCloudloginpwd(comInfo.get("cloudloginpwd"));

        return payCloudEmployeeMapper.updateByPrimaryKeySelective(payCloudEmployee);
    }


    /**
     * 获取商户信息通过token
     * */
    public CloudEmployeeInfo companyIsLogin(String token){

        Map<String,String> resultMap = new HashMap<>();
        String uid_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("CLOUD_EMPLOY_LOGIN_KEY") + token);
        String uid = null;
        CloudEmployeeInfo companyInfo = null;

        if (StringUtils.isBlank(uid_encrypt)) {

            return null;
        }
        uid = AESUtils.decrypt(uid_encrypt, ConfigUtil.getValue("AES_KEY"));
        String reg_mid = "^\\d{1,10}$";
        Boolean uidIsTrue = Pattern.compile(reg_mid).matcher(uid).matches();
        if ( !uidIsTrue ) {

            return null;
        }
        companyInfo = getCompayEmployeeByUid(new Integer(uid));
        return companyInfo;
    }
}
