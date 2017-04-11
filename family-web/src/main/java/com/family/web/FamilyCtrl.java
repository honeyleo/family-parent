package com.family.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.family.common.model.Member;
import com.family.common.model.MemberDTOWrapper;
import com.family.common.service.MemberService;
import com.family.model.CurrentUser;

import cn.lfy.common.filehandler.ResourceIdentifier;
import cn.lfy.common.filehandler.ResourceManager;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.model.Message;
import cn.lfy.common.utils.Validators;
import cn.lfy.common.web.BaseController;

@Controller
public class FamilyCtrl  extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(FamilyCtrl.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ResourceManager resourceManager;
	
	@RequestMapping("/app/family/tree")
	@ResponseBody
	public Object tree(CurrentUser user, 
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/family/tree");
		MemberDTOWrapper result = memberService.familyTree(user.getId());
		builder.data(result);
		return builder.build();
	}
	
	@RequestMapping("/app/member/add")
	@ResponseBody
	public Object add(CurrentUser user, 
			@RequestParam(name = "memberId") long memberId,
			@RequestParam(name = "relation") int relation,
			MultipartFile file, 
			Member member,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/member/add");
		member.setUserId(0L);
		member.setCreatorUserId(user.getId());
		member.setSpouseId(0L);
		member.setFatherId(0L);
		member.setMotherId(0L);
		if(member.getAlive() != null && member.getAlive() == 0) {
			member.setDieTime(0L);
		} else if(member.getAlive() != null && member.getAlive() == 1) {
			Validators.isFalse(member.getDieTime() == null, ErrorCode.PARAM_ILLEGAL, "逝世时间");
		}
		try {
			if(file != null) {
				ResourceIdentifier dest = handleFile("avatar_image", file, resourceManager);
				member.setAvatar(dest.getRelativePath());
			} else {
				member.setAvatar("");
			}
		} catch (Exception e) {
			LOG.error("上传成员头像出错", e);
		}
		memberService.addMember(user.getId(), memberId, relation, member);
		MemberDTOWrapper result = memberService.familyTree(user.getId());
		builder.data(result);
		return builder.build();
	}
	
	@RequestMapping("/app/member/update")
	@ResponseBody
	public Object update(CurrentUser user, 
			MultipartFile file, 
			Member member,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/member/update");
		if(member.getAlive() != null && member.getAlive() == 0) {
			member.setDieTime(0L);
		} else if(member.getAlive() != null && member.getAlive() == 1) {
			Validators.isFalse(member.getDieTime() == null, ErrorCode.PARAM_ILLEGAL, "逝世时间");
		}
		try {
			if(file != null) {
				ResourceIdentifier dest = handleFile("avatar_image", file, resourceManager);
				member.setAvatar(dest.getRelativePath());
			}
		} catch (Exception e) {
			LOG.error("上传成员头像出错", e);
		}
		memberService.update(member);
		return builder.build();
	}
	
	@RequestMapping("/app/member/del")
	@ResponseBody
	public Object del(CurrentUser user, 
			@RequestParam(name = "memberId") long memberId,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/member/del");
		memberService.delete(memberId);
		return builder.build();
	}
	
	@RequestMapping("/app/member/detail")
	@ResponseBody
	public Object detail(CurrentUser user, 
			@RequestParam(name = "memberId") long memberId,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/member/detail");
		Member member = memberService.get(memberId);
		builder.data(member);
		return builder.build();
	}
}
