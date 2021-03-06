package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayOrder;
import com.joiest.jpf.common.po.PayOrderExample;
import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayOrderMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayOrderExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayOrderExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(BigInteger id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayOrder record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayOrder record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayOrder> selectByExample(PayOrderExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayOrder selectByPrimaryKey(BigInteger id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayOrder record, @Param("example") PayOrderExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayOrder record, @Param("example") PayOrderExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayOrder record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayOrder record);
}