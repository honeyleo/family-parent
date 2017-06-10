package com.family.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.UserContributionRecord;

public interface UserContributionDAO {

	/**
	 * 添加用户贡献值收支记录
	 * @param entity
	 * @return
	 */
	int addUserContributionRecord(UserContributionRecord entity);
	/**
	 * 获取用户贡献值记录列表
	 * @param userId
	 * @param inOrOutType 1-收入；2-支出；0-全部；
	 * @return
	 */
	List<UserContributionRecord> getUserContributionRecordList(@Param("userId") Long userId, @Param("inOrOutType") Integer inOrOutType);
}
