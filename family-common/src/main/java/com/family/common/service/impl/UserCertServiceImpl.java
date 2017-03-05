package com.family.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.common.dao.UserCertDAO;
import com.family.common.dao.UserDAO;
import com.family.common.model.UserCert;
import com.family.common.service.UserCertService;

import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.utils.DateUtils;
import cn.lfy.common.utils.HttpUtils;
import cn.lfy.common.utils.Validators;

@Service
public class UserCertServiceImpl implements UserCertService {

	private static final Logger LOG = LoggerFactory.getLogger(UserCertServiceImpl.class);
	
	@Value("${cert.aliyun.appCode}")
	private String appCode;
	
	@Autowired
	@Qualifier("userCertExecutor")
	private ThreadPoolTaskExecutor userCertExecutor;
	
	@Autowired
	private UserCertDAO userCertDAO;
	
	@Autowired
	private UserDAO userDAO;

	public int delete(Long id) {
		return userCertDAO.delete(id);
	}

	public int insert(UserCert record) {
		return userCertDAO.insert(record);
	}

	public UserCert get(Long id) {
		return userCertDAO.get(id);
	}

	public int update(UserCert record) {
		return userCertDAO.update(record);
	}
	
	public int certification(final UserCert record, final byte[] frontImg, final byte[] backImg) {
		UserCert tmp = userCertDAO.getByUserId(record.getUserId());
		Validators.isFalse(tmp != null && tmp.getStatus() == 1, "已认证过，不需要重复认证", ErrorCode.SERVER_ERROR);
		int ret = 0;
		if(tmp != null) {
			record.setId(tmp.getId());
			ret = userCertDAO.update(record);
		} else {
			ret = userCertDAO.insert(record);
		}
		
		if(ret > 0) {
			userCertExecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					aliyunCert(record.getId(), record.getUserId(), record.getName(), record.getIdCardNo(), frontImg, backImg);
				}
			});
		}
		return ret;
	}
	
	private void aliyunCert(long id, long userId, String name, String idCardNo, byte[] frontImg, byte[] backImg) {
		LOG.info("aliyunCert id={} userId={} start verify IdCardNo", id, userId);
		String host = "https://dm-51.data.aliyun.com";
	    String path = "/rest/160601/ocr/ocr_idcard.json";
	    String method = "POST";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appCode);
	    Map<String, String> querys = new HashMap<String, String>();
	    String front = Base64.encodeBase64String(frontImg);
	    String back = Base64.encodeBase64String(backImg);
	    
	    String bodys = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\" " + front + "\"},\"configure\":{\"dataType\":50,\"dataValue\":\"{\\\"side\\\":\\\"face\\\"}\"}},"
	    		+ "{\"image\":{\"dataType\":50,\"dataValue\":\" " + back + "\"},\"configure\":{\"dataType\":50,\"dataValue\":\"{\\\"side\\\":\\\"back\\\"}\"}}]}";
	    boolean faceVerify = false;
    	boolean backVerify = false;
	    try {
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	//获取response的body
	    	String responseContent = EntityUtils.toString(response.getEntity());
	    	LOG.info("aliyunCert responseContent={}", responseContent);
	    	JSONObject json = JSON.parseObject(responseContent);
	    	JSONArray outputs = json.getJSONArray("outputs");
	    	
	    	for(int i = 0; i < outputs.size(); i ++) {
	    		JSONObject obj = outputs.getJSONObject(i);
	    		String dataValueString = obj.getJSONObject("outputValue").getString("dataValue");
	    		JSONObject dataValue = JSON.parseObject(dataValueString);
	    		String side = JSON.parseObject(dataValue.getString("config_str")).getString("side");
	    		if("face".equals(side)) {
	    			String num = dataValue.getString("num");
	    			String tmpName = dataValue.getString("name");
	    			if(idCardNo.equals(num) && name.equals(tmpName)) {
	    				faceVerify = true;
	    			}
	    		} else {
	    			String endDate = dataValue.getString("end_date");
	    			long endDateLong = DateUtils.getStringFormData(endDate, "yyMMdd").getTime();
	    			if(System.currentTimeMillis() < endDateLong) {
	    				backVerify = true;
	    			}
	    		}
	    	}
	    } catch(Throwable t) {
	    	LOG.error("aliyunCert id={} userId={} verify failed.", id, userId);
	    }
	    String result = faceVerify&&backVerify ? "success" : "failed";
    	LOG.info("aliyunCert id={} userId={} start verify IdCardNo Result={}", new Object[]{id, userId, result});
    	if("success".equals(result)) {
    		updateCertStatus(id, userId, 1);
    	} else {
    		updateCertStatus(id, userId, 2);
    	}
	}
	
	@Transactional
	private void updateCertStatus(long id, long userId, int status) {
		userCertDAO.updateStatus(id, status);
		if(status == 1) {
			userDAO.updateCerted(userId);
		}
	}

	@Override
	public UserCert getByUserId(Long userId) {
		return userCertDAO.getByUserId(userId);
	}
}
