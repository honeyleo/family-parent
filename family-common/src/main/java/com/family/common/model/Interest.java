package com.family.common.model;

import java.io.Serializable;

/**
 * 
 * @author Leo.liao
 *
 */
public class Interest implements Serializable {

	private static final long serialVersionUID = 7205723240575473977L;

	private Long id;
	
	private String name;
	
	private Long pid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}
	
}
