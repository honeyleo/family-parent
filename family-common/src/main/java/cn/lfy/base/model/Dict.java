package cn.lfy.base.model;

import java.io.Serializable;

public class Dict implements Serializable {

	private static final long serialVersionUID = -3704233938702500959L;
	
	private long id;
	
	private String type;
	
	private String name;
	
	private long parentId;
	
	private String parentIdPath;
	
	private String remark = "";
	
	private int seq;
	
	private long updateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getParentIdPath() {
		return parentIdPath;
	}

	public void setParentIdPath(String parentIdPath) {
		this.parentIdPath = parentIdPath;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
