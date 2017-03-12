package cn.lfy.base.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.family.common.model.Surname;
import com.family.common.service.NewsHomeService;

import cn.lfy.common.framework.exception.ApplicationException;

@Controller
@RequestMapping("/public")
public class PublicCtrl {

	@Autowired
	private NewsHomeService newsHomeService;
	
	@RequestMapping(value = "/surname/getsurnamebyname")
    @ResponseBody
    public Object api_list(String surname, HttpServletRequest request) throws ApplicationException {
		List<Surname> list = newsHomeService.getSurnameBySurname(surname);
        JSONObject json = new JSONObject();
        json.put("ret", 0);
        json.put("value", list);
        return json;
    }
}
