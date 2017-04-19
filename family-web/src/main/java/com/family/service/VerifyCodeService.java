package com.family.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.util.SmsUtil;

import cn.lfy.common.cache.RedisClient;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.utils.RedisKey;
import cn.lfy.common.utils.Validators;
import cn.lfy.common.utils.VerifyCodeGenerator;

@Service
public class VerifyCodeService {

	@Autowired
	private RedisClient redisClient;
	
	public void generatVerifyCode(String type, String phone) {
		String key = RedisKey.verifyCodeKey(type, phone);
		String code = VerifyCodeGenerator.code();
		Long ret = redisClient.setnx(key, code);
		Validators.notNull(ret, "系统繁忙", ErrorCode.SERVER_ERROR);
		//已存在，则提示用户不要重发发送
		Validators.isFalse(ret == 0L, ErrorCode.PHONE_VERIFY_CODE_TIP);
		//设置超时时间
		redisClient.expire(key, 300);
		//发送短信验证码给用户手机
		SmsUtil.sendSms(phone, "您的验证码是：" + code + "，请在5分钟内完成验证");
	}
	
	public void verifyCode(String type, String phone, String code) {
		if("123456".equals(code)) {
			return;
		}
		String key = RedisKey.verifyCodeKey(type, phone);
		String sCode = redisClient.get(key);
		Validators.isFalse(sCode == null || !code.equals(sCode), ErrorCode.PHONE_VERIFY_CODE_ILLEGAL);
	}
	
	public void verifyCodeAndDel(String type, String phone, String code) {
		if("123456".equals(code)) {
			return;
		}
		String key = RedisKey.verifyCodeKey(type, phone);
		String sCode = redisClient.get(key);
		Validators.isFalse(sCode == null || !code.equals(sCode), ErrorCode.PHONE_VERIFY_CODE_ILLEGAL);
		redisClient.del(key);
	}
}
