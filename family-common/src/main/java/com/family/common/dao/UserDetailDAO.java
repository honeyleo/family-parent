package com.family.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.ContributionRank;
import com.family.common.model.UserDetail;
import com.family.common.model.UserDetailDTO;

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
    /**
     * 关联查出用户ID列表中的所有用户详情
     * @param userIdList
     * @return
     */
    List<UserDetailDTO> getUserDetailDTOList(@Param("list") List<Long> userIdList);
    /**
     * 获取用户详情
     * @param id
     * @return
     */
    UserDetailDTO getUserDetailDTO(@Param("id") Long id);
    /**
     * 更新手机号
     * @param id
     * @param phones
     * @return
     */
    int updatePhones(@Param("id") Long id, @Param("phones") String phones);
    /**
     * 更新个人信誉
     * @param id
     * @param credit
     * @return
     */
    int updateCredit(@Param("id") Long id, @Param("credit") Integer credit);
    /**
     * 加贡献值
     * @param id
     * @param contribution
     * @return
     */
    int addContribution(@Param("id") Long id, @Param("contribution")Integer contribution);
    /**
     * 减贡献值
     * @param id
     * @param contribution
     * @return
     */
    int minusContribution(@Param("id") Long id, @Param("contribution") Integer contribution);
    /**
     * 获取该用户贡献值排行
     * @param id
     * @return
     */
    ContributionRank getContributionRank(Long id);
}