package com.family.common.service;

import java.util.List;

import com.family.common.model.Member;
import com.family.common.model.MemberDTO;

/**
 * 家谱成员服务接口
 * @author honeyleo
 *
 */
public interface MemberService {

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
    int update(Member record);
    /**
     * 获取用户家谱
     * @param userId
     * @return
     */
    List<MemberDTO> familyTree(Long userId);
    /**
	 *  给指定用户添加
	 * @param userId 创建者
	 * @param memberId 给memberId添加关系成员member
	 * @param relation 11-父亲；12-母亲；22-配偶；31-兄弟；32-姐妹；41-儿子；42-女儿；
	 * @param member 添加的成员
	 * @return
	 */
    void addMember(Long userId, Long memberId, int relation, Member member);
}