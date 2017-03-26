package com.family.common.service;

import com.family.common.model.Version;

import cn.lfy.common.page.Page;

public interface VersionService {

    /**
     * 根据主键删除记录
     */
    int delete(Long id);

    /**
     * 保存记录
     */
    int insert(Version record);

    /**
     * 根据主键查询记录
     */
    Version get(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int update(Version record);
    /**
     * 列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Version> list(int pageIndex, int pageSize);
    
}