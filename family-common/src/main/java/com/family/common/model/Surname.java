package com.family.common.model;

import java.io.Serializable;

public class Surname implements Serializable {

	private static final long serialVersionUID = -6064797157009597611L;

	private Long id;
	
	private String surname;
	
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
