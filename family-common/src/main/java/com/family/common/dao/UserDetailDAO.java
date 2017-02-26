package com.family.common.dao;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.UserDetail;

public interface UserDetailDAO {

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(UserDetail record);

    /**
     * 根据主键查询记录
     */
    UserDetail selectByPrimaryKey(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(UserDetail record);
    /**
     * 更新用户头像
     * @param id
     * @param avatar
     * @return
     */
    int updateAvatar(@Param("id") Long id, @Param("avatar") String avatar);
}