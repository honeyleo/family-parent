package com.family.common.model;

import java.util.List;

import com.google.common.collect.Lists;

public class MemberDTOWrapper {

	private List<MemberDTO> list = Lists.newArrayList();
	
	private int total;

	public MemberDTOWrapper() {
		super();
	}
	public MemberDTOWrapper(int total, List<MemberDTO> list) {
		this.total = total;
		this.list = list;
	}
	public List<MemberDTO> getList() {
		return list;
	}

	public void setList(List<MemberDTO> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
