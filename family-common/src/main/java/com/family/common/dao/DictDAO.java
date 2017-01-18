package com.family.common.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.Dict;

public interface DictDAO {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Dict record);

    /**
     * 根据条件查询记录集
     */
    List<Dict> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    Dict selectByPrimaryKey(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Dict record);
    
    /**
     * 根據ID列表查詢列表
     * @param list
     */
    List<Dict> getDicts(@Param("list")Collection<Long> list);

}