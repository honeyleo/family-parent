package cn.lfy.base.web;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.family.common.model.Feedback;
import com.family.common.service.FeedbackService;
import com.google.common.base.Joiner;

import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.model.Message;
import cn.lfy.common.page.Page;
import cn.lfy.common.utils.RequestUtil;
import cn.lfy.common.web.BaseController;

@Controller
@RequestMapping("/manager/feedback")
public class FeedbackController extends BaseController {

	
	@Value("${fileserver.image.url}")
	private String imageUrl;
	
    @Autowired
    private FeedbackService feedbackService;

    /**
     * 详情列表
     * 
     * @param request
     * @return ModelAndView
     * @throws ApplicationException
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) throws ApplicationException {
        return new ModelAndView("/system/feedback/list");
    }
    
    @RequestMapping(value = "/api/list")
    @ResponseBody
    public Object api_list(Integer status, HttpServletRequest request) throws ApplicationException {
    	Message.Builder builder = Message.newBuilder();
        Integer pageNum = RequestUtil.getInteger(request, "currentPage");
        Integer pageSize = RequestUtil.getInteger(request, "pageSize");
        Page<Feedback> result = feedbackService.list(status, pageNum, pageSize);
        for(Feedback feedback : result.getList()) {
        	List<String> list = getImgsList(feedback.getImages(), imageUrl);
        	feedback.setImages(Joiner.on(",").join(list));
        }
        builder.total(result.getTotalResult()).put("value", result.getList());
        return builder.build();
    }
    
    /**
     * 删除
     * 
     * @param request
     * @return ModelAndView
     * @throws ApplicationException
     */
    @RequestMapping("/del")
    @ResponseBody
    public Object del(HttpServletRequest request) throws ApplicationException {
        Long id = RequestUtil.getLong(request, "id");
        feedbackService.delete(id);
        Message.Builder builder = Message.newBuilder();
        return builder.build();
    }
    
    /**
     * 详情
     * 
     * @param request
     * @return
     * @throws ApplicationException
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Object detail(HttpServletRequest request) throws ApplicationException {
    	Message.Builder builder = Message.newBuilder();
        Long id = RequestUtil.getLong(request, "id");
        Feedback record = feedbackService.get(id);
        builder.data(record);
        return builder.build();
    }

    /**
     * 添加
     * 
     * @param request
     * @throws ApplicationException
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(Feedback form, HttpServletRequest request) throws ApplicationException {
    	Message.Builder builder = Message.newBuilder();
        feedbackService.insert(form);
        return builder.build();
    }

    /**
     * 更新
     * 
     * @param request
     * @return ModelAndView
     * @throws ApplicationException
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object update(Feedback form, HttpServletRequest request,
            HttpServletResponse response) throws ApplicationException {
    	Message.Builder builder = Message.newBuilder();
        feedbackService.update(form);
        return builder.build();
    }
    
}
