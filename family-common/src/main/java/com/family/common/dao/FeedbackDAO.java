package com.family.common.dao;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.Feedback;

import cn.lfy.common.page.Page;

public interface FeedbackDAO {

	/**
     * 根据主键删除记录
     */
    int delete(Long id);

    /**
     * 保存记录
     */
    int insert(Feedback record);

    /**
     * 根据主键查询记录
     */
    Feedback get(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int update(Feedback record);
	/**
	 * 分页获取版本列表
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	Page<Feedback> list(@Param("status") Integer status, @Param("pageIndex")Integer pageIndex, @Param("pageSize") Integer pageSize);
}
