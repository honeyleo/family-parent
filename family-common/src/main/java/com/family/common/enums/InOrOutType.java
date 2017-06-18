package com.family.common.enums;

public enum InOrOutType {

	/**
	 * 收入
	 */
	INCOME(1),
	/**
	 * 支出
	 */
	OUT(2),
	;
	private int type;
	private InOrOutType(int type) {
		this.type = type;
	}
	public int getType() {
		return this.type;
	}
	public static InOrOutType valueOf(int type) {
		for(InOrOutType t : values()) {
			if(t.type == type) {
				return t;
			}
		}
		return null;
	}
}
