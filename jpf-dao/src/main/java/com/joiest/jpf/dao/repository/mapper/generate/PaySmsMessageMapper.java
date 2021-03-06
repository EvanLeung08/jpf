package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PaySmsMessage;
import com.joiest.jpf.common.po.PaySmsMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaySmsMessageMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PaySmsMessageExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PaySmsMessageExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PaySmsMessage record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PaySmsMessage record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PaySmsMessage> selectByExample(PaySmsMessageExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PaySmsMessage selectByPrimaryKey(Integer id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PaySmsMessage record, @Param("example") PaySmsMessageExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PaySmsMessage record, @Param("example") PaySmsMessageExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PaySmsMessage record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PaySmsMessage record);
}