package com.family.common.service;

import java.util.List;

import com.family.common.model.UserContributionRecord;

public interface UserContributionService {

	/**
	 * 获取用户贡献值记录列表
	 * @param userId
	 * @param inOrOutType 1-收入；2-支出；0-全部；
	 * @return
	 */
	List<UserContributionRecord> getUserContributionRecordList(long userId, int inOrOutType);
}
