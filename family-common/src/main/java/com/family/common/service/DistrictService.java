package com.family.common.service;

import java.util.List;

import cn.lfy.base.model.District;

public interface DistrictService {

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
    
    /**
     * 通过pid查询其下所有子节点
     * @param pid
     * @return
     */
    List<District> list(Long pid);
    /**
     * 先通过缓存获取，获取不到从数据库获取
     * @param id
     * @return
     */
    District getByCache(Long id);
}