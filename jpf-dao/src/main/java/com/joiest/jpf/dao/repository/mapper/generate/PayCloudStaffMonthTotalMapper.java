package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudStaffMonthTotal;
import com.joiest.jpf.common.po.PayCloudStaffMonthTotalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudStaffMonthTotalMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudStaffMonthTotalExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudStaffMonthTotalExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayCloudStaffMonthTotal record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudStaffMonthTotal record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudStaffMonthTotal> selectByExample(PayCloudStaffMonthTotalExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudStaffMonthTotal selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudStaffMonthTotal record, @Param("example") PayCloudStaffMonthTotalExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudStaffMonthTotal record, @Param("example") PayCloudStaffMonthTotalExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudStaffMonthTotal record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudStaffMonthTotal record);
}