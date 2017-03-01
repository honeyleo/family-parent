package com.family.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.common.service.UserService;
import com.family.service.VerifyCodeService;

import cn.lfy.base.model.User;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.model.Message;
import cn.lfy.common.utils.MessageDigestUtil;
import cn.lfy.common.utils.UUIDUtil;
import cn.lfy.common.utils.Validators;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@RequestMapping("/register")
	@ResponseBody
	public Object register(@RequestParam(name = "phone") String phone, 
			@RequestParam(name = "verify_code") String code, 
			@RequestParam(name = "password") String password, 
			@RequestParam(name = "surname") String surname,
			@RequestParam(name = "name") String name,
			HttpServletRequest request) {
		User user = userService.findByUsername(phone);
		Validators.isFalse(user != null, ErrorCode.EXIST);
		verifyCodeService.verifyCodeAndDel("REG", phone, code);
		User record = new User();
		record.setUsername(phone);
		String salt = UUIDUtil.salt();
		record.setSalt(salt);
		record.setPassword(MessageDigestUtil.getSHA256(password + salt));
		record.setPhone(phone);
		record.setSurname(surname);
		record.setName(name);
		record.setNickname(phone);
		record.setState(1);
		record.setEmail("");
		record.setCreateTime(new Date());
		userService.add(record);
		Message.Builder builder = Message.newBuilder("/register");
		return builder.build();
	}
	
}
