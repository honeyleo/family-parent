package com.family.common.dao;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.Version;

import cn.lfy.common.page.Page;

public interface VersionDAO {

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
	 * 分页获取版本列表
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	Page<Version> list(@Param("pageIndex")Integer pageIndex, @Param("pageSize") Integer pageSize);
}
