package com.family.common.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.CommentBox;

public interface CommentBoxDAO {

    /**
     * 根据主键删除记录
     */
    int delete(Long id);

    /**
     * 保存记录
     */
    int insert(CommentBox record);

    /**
     * 根据主键查询记录
     */
    CommentBox get(Long id);

    /**
     * 通过用户ID、类型、评论Id列表获取评论Id列表
     * @param userId
     * @param type
     * @param list
     * @return
     */
    List<Long> getCommentIdList(@Param("userId")long userId, @Param("type")int type, @Param("list")Collection<Long> list);
    /**
     * 判断用户是否已点赞评论
     * @param userId
     * @param commentId
     * @return
     */
    boolean isPraised(@Param("userId")long userId, @Param("commentId")long commentId);
}