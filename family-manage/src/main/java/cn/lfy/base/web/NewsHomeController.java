package cn.lfy.base.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.PageInfo;
import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.model.Message;
import cn.lfy.common.utils.RequestUtil;

import com.alibaba.fastjson.JSONObject;
import com.family.common.model.NewsHome;
import com.family.common.service.NewsHomeService;

@Controller
@RequestMapping("/manager/news_home")
public class NewsHomeController {

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
        newsHomeService.insert(form);
        if(form.getImgs() != null) {
        	
    	}
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