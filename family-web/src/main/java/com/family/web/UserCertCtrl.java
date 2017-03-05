package com.family.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.family.common.model.UserCert;
import com.family.common.service.UserCertService;
import com.family.model.CurrentUser;

import cn.lfy.common.filehandler.ResourceIdentifier;
import cn.lfy.common.filehandler.ResourceManager;
import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.model.Message;
import cn.lfy.common.web.BaseController;

@Controller
public class UserCertCtrl extends BaseController {

	@Value("${fileserver.image.url}")
	private String imageUrl;
	
	@Autowired
	private UserCertService userCertService;
	
	@Autowired
	private ResourceManager resourceManager;
	
	@RequestMapping("/app/user_cert/info")
	@ResponseBody
	public Object info(CurrentUser user, 
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/user_cert/info");
		UserCert record = userCertService.getByUserId(user.getId());
		if(record != null) {
			record.setFrontImg(imageUrl + record.getFrontImg());
			record.setBackImg(imageUrl + record.getBackImg());
			record.setHandImg(imageUrl + record.getHandImg());
			builder.data(record);
		}
		return builder.build();
	}
	
	@RequestMapping("/app/user_cert/certification")
	@ResponseBody
	public Object certification(CurrentUser user, 
			@RequestParam(name = "name") String name,
			@RequestParam(name = "id_card_no") String idCardNo,
			@RequestParam("front_img") MultipartFile frontImg,
			@RequestParam("back_img") MultipartFile backImg,
			@RequestParam("hand_img") MultipartFile handImg,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/user_cert/certification");
		UserCert record = new UserCert();
		record.setUserId(user.getId());
		record.setName(name);
		record.setIdCardNo(idCardNo);
		record.setStatus(0);
		ResourceIdentifier dest = handleFile("cert_image", frontImg, resourceManager);
		if(dest == null) {
			throw new ApplicationException(ErrorCode.SERVER_ERROR);
		}
		record.setFrontImg(dest.getRelativePath());
		
		ResourceIdentifier dest2 = handleFile("cert_image", backImg, resourceManager);
		if(dest2 == null) {
			throw new ApplicationException(ErrorCode.SERVER_ERROR);
		}
		record.setBackImg(dest2.getRelativePath());
		ResourceIdentifier dest3 = handleFile("cert_image", handImg, resourceManager);
		if(dest3 == null) {
			throw new ApplicationException(ErrorCode.SERVER_ERROR);
		}
		record.setHandImg(dest3.getRelativePath());
		try {
			userCertService.certification(record, frontImg.getBytes(), backImg.getBytes());
		} catch(Throwable t) {
			throw new ApplicationException(ErrorCode.SERVER_ERROR);
		}
		record.setFrontImg(imageUrl + record.getFrontImg());
		record.setBackImg(imageUrl + record.getBackImg());
		record.setHandImg(imageUrl + record.getHandImg());
		builder.data(record);
		return builder.build();
	}
	
}
