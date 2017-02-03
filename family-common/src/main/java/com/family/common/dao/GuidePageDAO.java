package com.family.common.dao;

import java.util.List;

import cn.lfy.base.model.Criteria;

import com.family.common.model.GuidePage;

public interface GuidePageDAO {
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
    int insert(GuidePage record);

    /**
     * 根据条件查询记录集
     */
    List<GuidePage> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    GuidePage selectByPrimaryKey(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(GuidePage record);
    
}