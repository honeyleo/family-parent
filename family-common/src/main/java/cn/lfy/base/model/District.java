package cn.lfy.base.model;

import java.io.Serializable;

/**
 * 地区类
 * @author leo.liao
 *
 */
public class District implements Serializable {

	private static final long serialVersionUID = 1986343382730855448L;

	private int id;
	/**
	 * 父节点ID
	 */
	private int pid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 级别：0-国家；1-省；2-市；3-区/县
	 */
	private int level;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
