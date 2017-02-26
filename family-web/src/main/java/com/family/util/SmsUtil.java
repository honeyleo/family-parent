package com.family.util;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;

import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import com.esms.common.entity.MTPack.SendType;

import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.framework.exception.ErrorCode;

public class SmsUtil implements InitializingBean {

	static Account ac;
	static PostMsg pm;
	
	public static void sendSms(String phone, String content) {
		
		MTPack pack = new MTPack();
		pack.setBatchID(UUID.randomUUID());
		pack.setBatchName("短信测试批次");
		pack.setMsgType(MTPack.MsgType.SMS);
		pack.setBizType(0);
		pack.setDistinctFlag(false);
		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
		
		/** 单发，一号码一内容 */
		msgs.add(new MessageData(phone, content));
		pack.setMsgs(msgs);
		
		pack.setSendType(SendType.MASS);
		GsmsResponse resp;
		try {
			resp = pm.post(ac, pack);
			System.out.println(resp);
		} catch (Exception e) {
			throw new ApplicationException(ErrorCode.SERVER_ERROR);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ac = new Account("ydjt@jxkl", "C021krVw");//
		pm = new PostMsg();
		pm.getCmHost().setHost("211.147.239.62", 9080);//设置网关的IP和port，用于发送信息
		pm.getWsHost().setHost("211.147.239.62", 9070);//设置网关的 IP和port，用于获取账号信息、上行、状态报告等等
	}
}
