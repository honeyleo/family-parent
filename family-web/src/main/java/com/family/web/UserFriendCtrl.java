package com.family.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.family.common.model.UserDetailDTO;
import com.family.common.service.DistrictService;
import com.family.common.service.UserDetailService;
import com.family.common.service.UserService;
import com.family.model.CurrentUser;
import com.family.model.UserFriend;
import com.family.service.UserFriendService;
import com.family.service.UserProxyService;
import com.google.common.collect.Lists;

import cn.lfy.base.model.District;
import cn.lfy.base.model.User;
import cn.lfy.common.model.Message;
import cn.lfy.common.pinyin.HanyuPinyinHelper;
import cn.lfy.common.web.BaseController;

@Controller
public class UserFriendCtrl extends BaseController {

	@Value("${fileserver.image.url}")
	private String imageUrl;
	
	@Autowired
	private UserFriendService userFriendService;
	
	@Autowired
	private UserProxyService userProxyService;
	
	@Autowired
	private DistrictService districtService;
	
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
			@RequestParam(name = "remark", defaultValue = "") String remark, 
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/user_friend/apply");
		userFriendService.add(user.getId(), friendId, remark);
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
	 * user拒绝friendId的申请好友请求
	 * @param user
	 * @param friendId
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/user_friend/refuse")
	@ResponseBody
	public Object refuse(CurrentUser user, 
			@RequestParam(name = "friend_id", defaultValue = "0") long friendId, 
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/user_friend/refuse");
		userFriendService.refuse(user.getId(), friendId);
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
			JSONObject dto = build(uf);
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
			JSONObject dto = build(uf);
			notifyList.add(dto);
		}
		JSONObject data = new JSONObject();
		data.put("list", notifyList);
		builder.data(data);
		return builder.build();
	}
	
	private JSONObject build(UserFriend uf) {
		CurrentUser friend = userProxyService.getCurrentUser(uf.getFriendId());
		JSONObject dto = new JSONObject();
		dto.put("id", friend.getId());
		dto.put("username", friend.getUsername());
		dto.put("nickname", friend.getNickname());
		dto.put("gender", friend.getGender());
		if(StringUtils.isNotBlank(friend.getAvatar())) {
			dto.put("avatar", friend.getAvatar());
		} else {
			dto.put("avatar", "");
		}
		dto.put("surname", friend.getSurname());
		dto.put("name", friend.getName());
		dto.put("surnamePinyinFirst", HanyuPinyinHelper.getFirstLettersLo(friend.getSurname()));
		dto.put("namePinyin", HanyuPinyinHelper.getPinyinString(friend.getName()));
		dto.put("surnameAndNamePinyin", HanyuPinyinHelper.getPinyinString(friend.getSurname() + friend.getName()));
		dto.put("surnameAndNamePinyinFirst", HanyuPinyinHelper.getFirstLettersLo(friend.getSurname()));
		dto.put("credit", friend.getCredit());
		dto.put("contribution", friend.getContribution());
		dto.put("cert", friend.getCert());
		dto.put("remark", uf.getRemark());
		dto.put("status", uf.getStatus());
		District district = districtService.getByCache(friend.getBirthplaceProvinceId());
		String birthplaceProvince = "", birthplaceProvinceFirst = "", birthplaceProvincePinyin = "";
		if(district != null) {
			birthplaceProvince = district.getName();
			birthplaceProvinceFirst = HanyuPinyinHelper.getFirstLettersLo(district.getName());
			birthplaceProvincePinyin = HanyuPinyinHelper.getPinyinString(district.getName());
		}
		dto.put("birthplaceProvince", birthplaceProvince);
		dto.put("birthplaceProvinceFirst", birthplaceProvinceFirst);
		dto.put("birthplaceProvincePinyin", birthplaceProvincePinyin);
		return dto;
	}
	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailService userDetailService;
	
	@RequestMapping("/app/user_friend/search")
	@ResponseBody
	public Object search(CurrentUser user, 
			@RequestParam(name = "query") String query, 
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/user_friend/search");
		List<User> list = userService.search(query);
		List<Long> userIdList = Lists.newArrayList();
		for(User u : list) {
			userIdList.add(u.getId());
		}
		List<UserDetailDTO> detailList = userDetailService.getUserDetailDTOList(userIdList);
		for(UserDetailDTO dto : detailList) {
			if(StringUtils.isNotBlank(dto.getAvatar())) {
				dto.setAvatar(imageUrl + dto.getAvatar());
			}
			boolean isFriend = userFriendService.isFriend(user.getId(), dto.getId());
			dto.setIsFriend(isFriend);
		}
		JSONObject data = new JSONObject();
		data.put("list", detailList);
		builder.data(data);
		return builder.build();
	}
	
	@RequestMapping("/app/user_friend/may_know_family_list")
	@ResponseBody
	public Object mayKnowFamilyList(CurrentUser user, 
			@RequestParam(name = "pageIndex", defaultValue = "0") int pageIndex, 
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize, 
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/user_friend/may_know_family_list");
		List<Long> mayKnowFamilyList = userFriendService.mayKnowFamilyList(user.getId(), user.getSurname(), pageIndex, pageSize);
		JSONObject data = new JSONObject();
		if(!mayKnowFamilyList.isEmpty()) {
			Map<Long, CurrentUser> currentUsers = userProxyService.getCurrentUsers(mayKnowFamilyList);
			data.put("list", currentUsers.values());
			builder.data(data);
		} else {
			data.put("list", Lists.newArrayList());
		}
		return builder.build();
	}
}
