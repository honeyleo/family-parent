package com.family.common.service;

import java.util.List;

import com.family.common.model.Interest;

public interface InterestService {

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
     * 所有
     * @return
     */
    List<Interest> all();
    
    /**
     * 通过pid查询其下所有子节点
     * @param pid
     * @return
     */
    List<Interest> list(Long pid);
    int updateUserInterest(Long userId, Long[] interestId);
}