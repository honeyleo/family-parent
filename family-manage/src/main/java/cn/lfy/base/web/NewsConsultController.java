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

import com.alibaba.fastjson.JSONObject;
import com.family.common.enums.NewsType;
import com.family.common.model.NewsHome;
import com.family.common.service.NewsHomeService;
import com.google.common.base.Joiner;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.PageInfo;
import cn.lfy.common.filehandler.ResourceManager;
import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.model.Message;
import cn.lfy.common.utils.RequestUtil;
import cn.lfy.common.web.BaseController;

@Controller
@RequestMapping("/manager/news_consult")
public class NewsConsultController extends BaseController {

	@Value("${fileserver.image.dir}")
	private String imageDir;
	
	@Value("${fileserver.image.url}")
	private String imageUrl;
	
	@Autowired
	private ResourceManager resourceManager;
	
    @Autowired
    private NewsHomeService newsHomeService;

    /**
     * 用户详情列表
     * 
     * @param request
     * @return ModelAndView
     * @throws ApplicationException
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) throws ApplicationException {
        return new ModelAndView("/system/consult/list");
    }
    
    @RequestMapping(value = "/api/list")
    @ResponseBody
    public Object api_list(HttpServletRequest request) throws ApplicationException {
        Integer pageNum = RequestUtil.getInteger(request, "currentPage");
        Integer pageSize = RequestUtil.getInteger(request, "pageSize");
        Criteria criteria = new Criteria();
        criteria.put("newsType", NewsType.NEWS_CONSULT.getValue());
        Integer type = RequestUtil.getInteger(request, "type");
        if(type != 0) {
        	criteria.put("type", type);
        }
        PageInfo<NewsHome> result = newsHomeService.findListByCriteria(criteria, pageNum, pageSize);
        JSONObject json = new JSONObject();
        json.put("ret", 0);
        json.put("total", result.getTotal());
        json.put("value", result.getData());
        return json;
    }
    
    /**
     * 删除用户
     * 
     * @param request
     * @return ModelAndView
     * @throws ApplicationException
     */
    @RequestMapping("/del")
    @ResponseBody
    public Object del(HttpServletRequest request) throws ApplicationException {
        Long id = RequestUtil.getLong(request, "id");
        NewsHome record = new NewsHome();
        record.setId(id);
        record.setStatus(0);
        newsHomeService.updateByIdSelective(record);
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
        NewsHome record = newsHomeService.getById(id);
        List<String> list = getImgsList(record.getImgs(), imageUrl);
        record.setImgs(Joiner.on(",").join(list));
        record.setContent(htmlContentImageAppendDomain(record.getContent(), imageUrl));
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
    public Object add(NewsHome form, HttpServletRequest request) throws ApplicationException {
    	Message.Builder builder = Message.newBuilder();
    	String pathRoot = request.getSession().getServletContext().getRealPath( "/" );
        
        List<String> list = uploadImageHandle(form.getImgs(), pathRoot, resourceManager, pathRoot);
        form.setImgs(Joiner.on(",").join(list));
		String content = form.getContent();
		form.setContent(ueditorContentImgHandle(content, pathRoot, resourceManager));
		form.setNewsType(NewsType.NEWS_CONSULT.getValue());
        newsHomeService.insert(form);
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
    public Object update(NewsHome form, HttpServletRequest request,
            HttpServletResponse response) throws ApplicationException {
    	Message.Builder builder = Message.newBuilder();
    	String pathRoot = request.getSession().getServletContext().getRealPath( "/" );
    	List<String> list = uploadImageHandle(form.getImgs(), pathRoot, resourceManager, imageUrl);
    	form.setImgs(Joiner.on(",").join(list));
		String content = form.getContent();
		content = content.replaceAll(imageUrl, "");
		form.setContent(ueditorContentImgHandle(content, pathRoot, resourceManager));
    	newsHomeService.updateByIdSelective(form);
        return builder.build();
    }
    
    /**
     * 更新
     * 
     * @param request
     * @return ModelAndView
     * @throws ApplicationException
     */
    @RequestMapping("/update_status")
    @ResponseBody
    public Object updateSstatus(NewsHome form, HttpServletRequest request,
            HttpServletResponse response) throws ApplicationException {
    	Message.Builder builder = Message.newBuilder();
    	newsHomeService.updateByIdSelective(form);
        return builder.build();
    }
}
