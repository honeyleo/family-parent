package cn.lfy.base.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.family.common.model.NewsHome;
import com.family.common.service.NewsHomeService;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.PageInfo;
import cn.lfy.common.filehandler.ResourceIdentifier;
import cn.lfy.common.filehandler.ResourceManager;
import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.model.Message;
import cn.lfy.common.utils.ManageHelper;
import cn.lfy.common.utils.RequestUtil;

@Controller
@RequestMapping("/manager/news_home")
public class NewsHomeController {

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
        return new ModelAndView("/system/home/list");
    }
    
    @RequestMapping(value = "/api/list")
    @ResponseBody
    public Object api_list(HttpServletRequest request) throws ApplicationException {
        Integer pageNum = RequestUtil.getInteger(request, "currentPage");
        Integer pageSize = RequestUtil.getInteger(request, "pageSize");
        Criteria criteria = new Criteria();
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
        newsHomeService.delete(id);
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
        if(!StringUtils.isBlank(record.getImgs())) {
        	Iterable<String> itb = Splitter.on(",").split(record.getImgs());
        	Iterator<String> it = itb.iterator();
        	List<String> list = new ArrayList<String>();
        	while(it.hasNext()) {
        		String img = it.next();
        		if(StringUtils.isNotBlank(img)) {
        			list.add(imageUrl + img);
        		}
        	}
        	record.setImgs(Joiner.on(",").join(list));
        }
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
        if(form.getImgs() != null) {
        	String imgs = form.getImgs();
        	String[] img = imgs.split(",");
        	List<String> list = new ArrayList<String>();
        	for(String im : img) {
        		if(!StringUtils.isBlank(im)) {
        			boolean isUpload = ManageHelper.isUpload(im);
        			if(isUpload) {
        				try {
        					ResourceIdentifier dest = resourceManager.processResource("news", pathRoot + im);
        					list.add(dest.getRelativePath());
						} catch (IOException e) {
							e.printStackTrace();
						}
        			}
        		}
        	}
        	if(!list.isEmpty()) {
        		imgs = Joiner.on(",").join(list);
        		form.setImgs(imgs);
        	}
    	}
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
    	if(form.getImgs() == null) {
    		form.setImgs("");
    	}
    	newsHomeService.updateByIdSelective(form);
        return builder.build();
    }
    
}
