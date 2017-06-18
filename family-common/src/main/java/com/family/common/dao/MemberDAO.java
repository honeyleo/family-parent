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
    int updateParent(@Param("entity")Member member);
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
    /**
     * 更新父亲ID等于fatherId的人的母亲为motherId
     * @param fatherId
     * @param motherId
     * @return
     */
    int updateChildrenOfMother(@Param("fatherId")Long fatherId, @Param("motherId")Long motherId);
    /**
     * 更新母亲ID等于motherId的人的父亲为fatherId
     * @param motherId
     * @param fatherId
     * @return
     */
    int updateChildrenOfFather(@Param("motherId")Long motherId, @Param("fatherId")Long fatherId);
    /**
     * 更新父亲Id等于fatherId的所有孩子的fatherId为0
     * @param fatherId
     * @return
     */
    int updateChildrenOfFatherId(Long fatherId);
    /**
     * 更新母亲Id等于motherId的所有孩子的motherId为0
     * @param motherId
     * @return
     */
    int updateChildrenOfMotherId(Long motherId);
    /**
     * 更新配偶Id等于spouseId的女性的spouseId为0
     * @param spouseId
     * @return
     */
    int updateSpouseIdForZero(Long spouseId);
    /**
     * 删除母亲Id等于motherId并且userId不等于userId的孩子
     * @param motherId
     * @param userId
     * @return
     */
    int deleteChildren(@Param("motherId")Long motherId, @Param("userId")Long userId);
    /**
     * 是否有孙子孙女
     * @param motherId
     * @return
     */
    boolean hasGrandChildren(@Param("motherId")Long motherId);
    /**
     * 是否有女婿
     * @param motherId
     * @return
     */
    Boolean hasDaughtershusband(@Param("motherId")Long motherId);
    /**
     * 是否有丈夫
     * @param id
     * @return
     */
    Boolean hasHusband(Long id);
}
