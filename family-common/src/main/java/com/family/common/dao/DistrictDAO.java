package com.family.common.dao;

import java.util.List;

import cn.lfy.base.model.District;

public interface DistrictDAO {

    /**
     * 根据主键删除记录
     */
    int delete(Long id);

    /**
     * 保存记录
     */
    int insert(District record);

    /**
     * 根据主键查询记录
     */
    District get(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int update(District record);
    /**
     * 新聞评论列表
     * @param newsId
     * @param start
     * @param limit
     * @return
     */
    List<District> all();
}