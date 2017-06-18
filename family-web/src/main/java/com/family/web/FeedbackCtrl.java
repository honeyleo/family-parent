package com.family.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.family.common.model.Feedback;
import com.family.common.service.FeedbackService;
import com.family.model.CurrentUser;

import cn.lfy.common.filehandler.ResourceIdentifier;
import cn.lfy.common.filehandler.ResourceManager;
import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.model.Message;
import cn.lfy.common.web.BaseController;

@Controller
@RequestMapping("/app/feedback")
public class FeedbackCtrl extends BaseController {

	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private ResourceManager resourceManager;
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(CurrentUser user, 
			@RequestParam(name = "description", defaultValue = "0") String description,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/feedback/add");
		Feedback record = new Feedback();
		record.setDescription(description);
		record.setUserId(user.getId());
		if(file != null) {
			ResourceIdentifier dest = handleFile("feedback_image", file, resourceManager);
			if(dest == null) {
				throw new ApplicationException(ErrorCode.SERVER_ERROR);
			}
			record.setImages(dest.getRelativePath());
		}
		feedbackService.insert(record);
		return builder.build();
	}
}
