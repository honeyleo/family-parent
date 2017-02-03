package com.family.model;

import java.io.Serializable;

/**
 * 好友关系：<userId,friendId>和<friendId,userId>是一样的记录，不重复添加
 * 为了判断两个人是不是好友，可以在程序层插入数据前加一个限制：userId < friendId
 * 查询好友列表时用：UNION ALL
 * @author honeyleo
 *
 */
public class UserRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3199625304960248751L;

	private Long id;
	
	private Long userId;
	
	private Long friendId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}
	
	
}
