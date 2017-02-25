package com.family.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.family.model.UserFriend;

public interface UserFriendDAO {

    /**
     * 根据主键删除记录
     */
    int delete(Long id);

    /**
     * 保存记录
     */
    int insert(UserFriend record);

    /**
     * 根据主键查询记录
     */
    UserFriend get(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int update(UserFriend record);
    /**
     * 新聞评论列表
     * @param userId
     * @return
     */
    List<UserFriend> list(@Param("userId")long userId);
    /**
     * 好友通知
     * @param userId
     * @return
     */
    List<UserFriend> listOfFriendNotify(@Param("userId")long userId);
    /**
     * 判断用户是否是好友关系
     * @param userId
     * @param friendId
     * @return
     */
    boolean isFriend(@Param("userId")long userId, @Param("friendId")long friendId);
    /**
     * 被申请人respondentUserId同意加好友申请
     * @param respondentUserId
     * @param userId
     * @param friendId
     * @return
     */
    int agree(@Param("respondentUserId")long respondentUserId, @Param("userId")long userId, @Param("friendId")long friendId);
    
}