package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudTask;
import com.joiest.jpf.common.po.PayCloudTaskExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudTaskMapper;
import com.joiest.jpf.dto.CloudTaskRequest;
import com.joiest.jpf.dto.CloudTaskResponse;
import com.joiest.jpf.entity.CloudTaskInfo;
import com.joiest.jpf.facade.CloudTaskServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class CloudTaskServiceFacadeImpl implements CloudTaskServiceFacade {

    @Autowired
    private PayCloudTaskMapper payCloudTaskMapper;

    @Override
    public CloudTaskResponse getTasks(CloudTaskRequest request){
        CloudTaskResponse cloudTaskResponse = new CloudTaskResponse();

        PayCloudTaskExample e = new PayCloudTaskExample();
        e.setPageNo(request.getPage());
        e.setPageSize(request.getRows());
        e.setOrderByClause("id DESC");
        List<CloudTaskInfo> infos = new ArrayList<>();

        List<PayCloudTask> list = payCloudTaskMapper.selectByExample(e);
        cloudTaskResponse.setCount(payCloudTaskMapper.countByExample(e));
        for (PayCloudTask payCloudTask:list){
            CloudTaskInfo cloudTaskInfo = new CloudTaskInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudTask.class, CloudTaskInfo.class, false);
            beanCopier.copy(payCloudTask, cloudTaskInfo, null);
            infos.add(cloudTaskInfo);
        }
        cloudTaskResponse.setList(infos);

        return cloudTaskResponse;
    }

    @Override
    public int insTask(CloudTaskInfo cloudTaskInfo){
        PayCloudTask payCloudTask = new PayCloudTask();
        BeanCopier beanCopier = BeanCopier.create(CloudTaskInfo.class, PayCloudTask.class, false);
        beanCopier.copy(cloudTaskInfo, payCloudTask, null);

        return payCloudTaskMapper.insert(payCloudTask);
    }

    @Override
    public CloudTaskInfo getOneTask(String id){
        PayCloudTask payCloudTask = payCloudTaskMapper.selectByPrimaryKey(id);
        CloudTaskInfo cloudTaskInfo = new CloudTaskInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudTask.class, CloudTaskInfo.class, false);
        beanCopier.copy(payCloudTask, cloudTaskInfo, null);

        return cloudTaskInfo;
    }

    @Override
    public int updateColumn(CloudTaskInfo cloudTaskInfo){
        PayCloudTask payCloudTask = new PayCloudTask();

        BeanCopier beanCopier = BeanCopier.create(CloudTaskInfo.class, PayCloudTask.class, false);
        beanCopier.copy(cloudTaskInfo, payCloudTask, null);

        return payCloudTaskMapper.updateByPrimaryKeySelective(payCloudTask);
    }
}
