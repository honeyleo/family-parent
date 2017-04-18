package com.family.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.Appeal;

public interface AppealDAO {

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
     * 用户求助消息列表列表
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    List<Appeal> list(@Param("userId")long userId, @Param("start")int start, @Param("limit")int limit);
    /**
     * 求助消息列表列表
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    List<Appeal> familyAppealList(@Param("userId")long userId, @Param("start")int start, @Param("limit")int limit);
}