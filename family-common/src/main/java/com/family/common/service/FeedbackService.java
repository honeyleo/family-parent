package com.family.common.service;

import com.family.common.model.Feedback;

import cn.lfy.common.page.Page;

public interface FeedbackService {

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
     * 列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Feedback> list(Integer status, int pageIndex, int pageSize);
    
}