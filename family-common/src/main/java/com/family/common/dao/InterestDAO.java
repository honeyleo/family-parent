package com.family.common.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.Interest;
import com.family.common.model.UserInterest;

public interface InterestDAO {

    /**
     * 根据主键删除记录
     */
    int delete(Long id);

    /**
     * 保存记录
     */
    int insert(Interest record);

    /**
     * 根据主键查询记录
     */
    Interest get(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int update(Interest record);
    /**
     * 新聞评论列表
     * @param newsId
     * @param start
     * @param limit
     * @return
     */
    List<Interest> all();
    /**
     * 通过pid查询其下所有子节点
     * @param pid
     * @return
     */
    List<Interest> list(Long pid);
    /**
     * 获取用户的兴趣爱好列表
     * @param userId
     * @return
     */
    List<UserInterest> getUserInterests(Long userId);
    /**
     * 获取用户的兴趣爱好列表
     * @param userId
     * @return
     */
    List<Interest> getUserInterestList(Long userId);
    /**
     * 
     * @param userId
     * @param list
     * @return
     */
    int addUserInterests(@Param("userId")Long userId, @Param("list")Collection<Long> list);
    /**
     * 删除用户兴趣爱好
     * @param userId
     * @param list
     * @return
     */
    int deleteUserInterests(@Param("userId") Long userId, @Param("list") Collection<Long> list);
}