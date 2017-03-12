package com.family.service;

import java.util.List;

import com.family.model.UserFriend;

public interface UserFriendService {

    /**
     * userId向friendId发起添加好友申请
     */
    int add(long userId, long friendId, String remark);

    /**
     * userId的好友列表
     * @param userId
     * @return
     */
    List<UserFriend> list(long userId);
    /**
     * userId同意friendId的好友申请请求
     * @param userId
     * @param friendId
     * @return
     */
    int agree(long userId, long friendId);
    /**
     * userId拒绝friendId的好友申请请求
     * @param userId
     * @param friendId
     * @return
     */
    int refuse(long userId, long friendId);
    /**
     * userId的好友通知
     * @param userId
     * @return
     */
    List<UserFriend> notifys(long userId);
}