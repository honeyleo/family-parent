package com.family.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lfy.base.model.Criteria;

import com.family.common.model.UserDetail;

public interface UserDetailDAO {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(UserDetail record);

    /**
     * 根据条件查询记录集
     */
    List<UserDetail> selectByExample(Criteria example);

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