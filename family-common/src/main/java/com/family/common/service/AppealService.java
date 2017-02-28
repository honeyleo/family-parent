package com.family.common.service;

import java.util.List;

import com.family.common.model.Appeal;

/**
 * 求助消息服务接口
 * @author wendy
 *
 */
public interface AppealService {

    /**
     * 根据主键删除记录
     */
    int delete(Long id);

    /**
     * 保存记录
     */
    int insert(Appeal record);

    /**
     * 根据主键查询记录
     */
    Appeal get(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int update(Appeal record);
    /**
     * 新聞评论列表
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    List<Appeal> list(long newsId, int start, int limit);
    
}