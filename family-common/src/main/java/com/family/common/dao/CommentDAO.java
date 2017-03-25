package com.family.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.Comment;

public interface CommentDAO {

    /**
     * 根据主键删除记录
     */
    int delete(Long id);

    /**
     * 保存记录
     */
    int insert(Comment record);

    /**
     * 根据主键查询记录
     */
    Comment get(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int update(Comment record);
    /**
     * 新聞评论列表
     * @param newsId
     * @param start
     * @param limit
     * @return
     */
    List<Comment> list(@Param("newsId")long newsId, @Param("start")int start, @Param("limit")int limit);
    
    /**
     * 通过用户ID获取收藏列表
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    List<Comment> getFavorListByUserId(@Param("userId")long userId, @Param("start")int start, @Param("limit")int limit);
}