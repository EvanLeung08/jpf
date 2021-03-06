package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayWeixinMp;
import com.joiest.jpf.common.po.PayWeixinMpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayWeixinMpMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayWeixinMpExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayWeixinMpExample example);

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
    int insert(PayWeixinMp record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayWeixinMp record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayWeixinMp> selectByExample(PayWeixinMpExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayWeixinMp selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayWeixinMp record, @Param("example") PayWeixinMpExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayWeixinMp record, @Param("example") PayWeixinMpExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayWeixinMp record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayWeixinMp record);
}