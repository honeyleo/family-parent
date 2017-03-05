package com.family.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.family.model.CurrentUser;
import com.family.model.UserFriend;
import com.family.service.UserFriendService;
import com.family.service.UserProxyService;

import cn.lfy.common.model.Message;
import cn.lfy.common.web.BaseController;

@Controller
public class UserFriendCtrl extends BaseController {

	@Autowired
	private UserFriendService userFriendService;
	
	@Autowired
	private UserProxyService userProxyService;
	
	/**
	 * user申请加friendId为好友
	 * @param user
	 * @param friendId
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/user_friend/apply")
	@ResponseBody
	public Object apply(CurrentUser user, 
			@RequestParam(name = "friend_id", defaultValue = "0") long friendId, 
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/user_friend/apply");
		userFriendService.add(user.getId(), friendId);
		return builder.build();
	}
	
	/**
	 * user同意friendId的申请好友请求
	 * @param user
	 * @param friendId
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/user_friend/agree")
	@ResponseBody
	public Object agree(CurrentUser user, 
			@RequestParam(name = "friend_id", defaultValue = "0") long friendId, 
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/user_friend/agree");
		userFriendService.agree(user.getId(), friendId);
		return builder.build();
	}
	
	/**
	 * user同意friendId的申请好友请求
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/user_friend/list")
	@ResponseBody
	public Object list(CurrentUser user, 
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/user_friend/list");
		List<UserFriend> list = userFriendService.list(user.getId());
		List<JSONObject> friendList = new ArrayList<JSONObject>();
		for(UserFriend uf : list) {
			CurrentUser friend = userProxyService.getCurrentUser(uf.getFriendId());
			JSONObject dto = new JSONObject();
			dto.put("id", friend.getId());
			dto.put("gender", friend.getGender());
			dto.put("avatar", friend.getAvatar());
			dto.put("username", friend.getUsername());
			dto.put("nickname", friend.getNickname());
			dto.put("surname", friend.getSurname());
			dto.put("name", friend.getName());
			friendList.add(dto);
		}
		JSONObject data = new JSONObject();
		data.put("list", friendList);
		builder.data(data);
		return builder.build();
	}
	
	/**
	 * user同意friendId的申请好友请求
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/user_friend/notifys")
	@ResponseBody
	public Object notifys(CurrentUser user, 
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/user_friend/list");
		List<UserFriend> list = userFriendService.notifys(user.getId());
		List<JSONObject> notifyList = new ArrayList<JSONObject>();
		for(UserFriend uf : list) {
			CurrentUser friend = userProxyService.getCurrentUser(uf.getFriendId());
			JSONObject dto = new JSONObject();
			dto.put("id", friend.getId());
			dto.put("username", friend.getUsername());
			dto.put("nickname", friend.getNickname());
			dto.put("gender", friend.getGender());
			dto.put("avatar", friend.getAvatar());
			dto.put("surname", friend.getSurname());
			dto.put("name", friend.getName());
			notifyList.add(dto);
		}
		JSONObject data = new JSONObject();
		data.put("list", notifyList);
		builder.data(data);
		return builder.build();
	}
}
