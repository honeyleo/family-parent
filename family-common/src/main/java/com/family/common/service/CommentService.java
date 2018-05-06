package com.family.common.service;

import java.util.List;

import com.family.common.model.Comment;
import com.family.common.model.UserNewsFavor;

public interface CommentService {

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
    List<Comment> list(long newsId, int start, int limit);
    
    /**
     * 收藏
     * @param userId
     * @param newsId
     * @return
     */
    int favor(long userId, long newsId);
    /**
     * 获取用户新闻收藏列表
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    List<UserNewsFavor> getUserNewsFavorList(long userId, int start, int limit);
    /**
     * 是否收藏
     * @param userId
     * @param newsId
     * @return
     */
    Boolean isFavor(long userId, long newsId);
    /**
     * 是否收藏
     * @param userId
     * @param newsId
     * @return
     */
    Comment getFavor(long userId, long newsId);
}