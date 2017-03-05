package com.family.common.service;

import com.family.common.model.UserCert;

public interface UserCertService {

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

    /**
     * 根据主键更新属性不为空的记录
     */
    int update(UserCert record);
    /**
     * 根据主键查询记录
     */
    UserCert getByUserId(Long userId);
    /**
     * 认证
     * @param record
     * @return
     */
    int certification(UserCert record, byte[] frontImg, byte[] backImg);
}