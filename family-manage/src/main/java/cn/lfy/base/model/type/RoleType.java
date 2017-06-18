package cn.lfy.base.model.type;

public enum RoleType {

	STAFF(1),
	CUSTOMER(2),
	;
	
	private int type;
	
	private RoleType(int type) {
		this.type = type;
	}
	public int getType() {
		return this.type;
	}
	public static RoleType valueOf(int type) {
		for(RoleType roleType : values()) {
			if(roleType.getType() == type) {
				return roleType;
			}
		}
		return STAFF;
	}
}
