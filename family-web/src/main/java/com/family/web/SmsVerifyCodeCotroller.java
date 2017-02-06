package com.family.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.service.VerifyCodeService;

import cn.lfy.common.model.Message;

@Controller
public class SmsVerifyCodeCotroller {

	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@RequestMapping("/verify_code/get")
	@ResponseBody
	public Object get(@RequestParam(name = "type") String type, @RequestParam(name = "phone") String phone) {
		verifyCodeService.generatVerifyCode(type, phone);
		return Message.newBuilder("/verify_code/get").build();
	}
	
	@RequestMapping("/verify_code/check")
	@ResponseBody
	public Object check(@RequestParam(name = "type") String type, @RequestParam(name = "phone") String phone, @RequestParam(name = "code") String code) {
		verifyCodeService.verifyCode(type, phone, code);
		return Message.newBuilder("/verify_code/check").build();
	}
}
