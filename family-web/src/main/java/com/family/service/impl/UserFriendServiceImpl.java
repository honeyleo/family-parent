package com.family.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.dao.UserFriendDAO;
import com.family.model.UserFriend;
import com.family.service.UserFriendService;

import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.utils.Validators;
@Service
public class UserFriendServiceImpl implements UserFriendService {

	@Autowired
	private UserFriendDAO userFriendDAO;
	
	@Override
	public int add(long userId, long friendId, String remark) {
		UserFriend record = new UserFriend();
		record.setRemark(remark);
		boolean isFriend = userFriendDAO.isFriend(userId < friendId ? userId : friendId, userId < friendId ? friendId : userId);
		Validators.isFalse(isFriend, ErrorCode.ACCESS_TOKEN_INVALID);
		if(userId < friendId) {
			record.setFriendId(friendId);
			record.setUserId(userId);
		} else {
			record.setFriendId(userId);
			record.setUserId(friendId);
		}
		record.setRespondentUserId(friendId);
		record.setStatus(1);
		int ret = userFriendDAO.insert(record);
		return ret;
	}

	@Override
	public List<UserFriend> list(long userId) {
		return userFriendDAO.list(userId);
	}

	@Override
	public int agree(long userId, long friendId) {
		int ret = userFriendDAO.agree(userId, userId < friendId ? userId : friendId, userId < friendId ? friendId : userId);
		return ret;
	}
	
	@Override
	public int refuse(long userId, long friendId) {
		int ret = userFriendDAO.agree(userId, userId < friendId ? userId : friendId, userId < friendId ? friendId : userId);
		return ret;
	}
	
	@Override
	public List<UserFriend> notifys(long userId) {
		return userFriendDAO.listOfFriendNotify(userId);
	}

	@Override
	public boolean isFriend(long userId, long friendId) {
		if(userId > friendId) {
			long tmp = userId;
			userId = friendId;
			friendId = tmp;
		}
		return userFriendDAO.isFriend(userId, friendId);
	}

}
