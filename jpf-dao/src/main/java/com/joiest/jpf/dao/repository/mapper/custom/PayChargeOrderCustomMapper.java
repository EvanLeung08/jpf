package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.po.PayChargeOrderExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PayChargeOrderCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayChargeOrderExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayChargeOrderExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayChargeOrder record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayChargeOrder record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayChargeOrder> selectByExample(PayChargeOrderExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayChargeOrder selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayChargeOrder record, @Param("example") PayChargeOrderExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayChargeOrder record, @Param("example") PayChargeOrderExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayChargeOrder record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayChargeOrder record);

    /**
     * 查询订单总金额
     */
    BigDecimal SuccessCount(PayChargeOrderExample example);

    /**
     * 增加方法
     * @param e
     * @return
     */
    List<PayChargeOrder> selectByExcelExample(PayChargeOrderExample e);
}