package com.family.common.service;

import java.util.List;

import com.family.common.model.Phone;
import com.family.common.model.UserDetail;
import com.family.common.model.UserDetailDTO;

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
    /**
     * 更新手机号
     * @param id
     * @param phones
     * @return
     */
    List<Phone> updatePhones(Long id, String phones);
    
    /**
     * 关联查出用户ID列表中的所有用户详情
     * @param userIdList
     * @return
     */
    List<UserDetailDTO> getUserDetailDTOList(List<Long> userIdList);
    
    /**
     * 获取用户详情
     * @param id
     * @return
     */
    UserDetailDTO getUserDetailDTO(Long id);
}