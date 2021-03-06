package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayMerchantsBank;
import com.joiest.jpf.common.po.PayMerchantsBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayMerchantsBankMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayMerchantsBankExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayMerchantsBankExample example);

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
    int insert(PayMerchantsBank record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayMerchantsBank record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayMerchantsBank> selectByExample(PayMerchantsBankExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayMerchantsBank selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayMerchantsBank record, @Param("example") PayMerchantsBankExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayMerchantsBank record, @Param("example") PayMerchantsBankExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayMerchantsBank record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayMerchantsBank record);
}