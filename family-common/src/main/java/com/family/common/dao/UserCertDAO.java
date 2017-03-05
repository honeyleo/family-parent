package com.family.common.dao;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.UserCert;

public interface UserCertDAO {

    /**
     * 根据主键删除记录
     */
    int delete(Long id);

    /**
     * 保存记录
     */
    int insert(UserCert record);

    /**
     * 根据主键查询记录
     */
    UserCert get(Long id);

    UserCert getByUserId(Long userId);
    /**
     * 根据主键更新属性不为空的记录
     */
    int update(UserCert record);
    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(@Param("id")Long id, @Param("status")Integer status);
}