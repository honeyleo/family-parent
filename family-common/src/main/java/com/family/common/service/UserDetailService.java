package com.family.common.service;

import com.family.common.model.UserDetail;

public interface UserDetailService {

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
    int updateAvatar(Long id, String avatar);
    /**
     * 更新个人资料
     * @param id
     * @param userDetail
     * @return
     */
    int updateMy(Long id, UserDetail userDetail);
}