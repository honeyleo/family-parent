package com.family.common.enums;

public enum ContributionType {

	/**
	 * 创建家谱
	 */
	CREATE_FAMILY_TREE(1),
	/**
	 * 首页文章
	 */
	HOME_ARTICLE(2),
	/**
	 * 资讯文章
	 */
	CONSULT_ARTICLE(3),
	/**
	 * 求助消息
	 */
	APPEAL(4),
	/**
	 * 家族基金
	 */
	FAMILY_FOUNDATION(5),
	/**
	 * 用户推荐
	 */
	USER_RECOMMEND(6),
	;
	private int type;
	private ContributionType(int type) {
		this.type = type;
	}
	public int getType() {
		return this.type;
	}
	public static ContributionType valueOf(int type) {
		for(ContributionType t : values()) {
			if(t.type == type) {
				return t;
			}
		}
		return null;
	}
}
