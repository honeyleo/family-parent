package com.family.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.Member;

public interface MemberDAO {

	/**
     * 根据主键删除记录
     */
    int delete(Long id);

    /**
     * 保存记录
     */
    int insert(Member record);

    /**
     * 根据主键查询记录
     */
    Member get(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int update(@Param("entity")Member record);
    /**
     * 获取家谱成员中的自己
     * @param userId
     * @return
     */
    Member getSelf(Long userId);
    /**
     * 获取父亲
     * @param memberId
     * @return
     */
    Member getFather(Long memberId);
    /**
     * 获取母亲
     * @param memberId
     * @return
     */
    Member getMother(Long memberId);
    /**
     * 更新父辈
     * @param member
     * @return
     */
    int updateParent(Member member);
    /**
     * 通过创建者获取家谱成员列表
     * @param creatorUserId
     * @return
     */
    List<Member> list(Long creatorUserId);
    /**
     * 获取女配偶（一房）
     * @param spouseId
     * @return
     */
    Member getFemaleSpouse(Long spouseId);
}
