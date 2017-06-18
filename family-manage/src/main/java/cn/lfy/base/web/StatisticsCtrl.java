package cn.lfy.base.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.family.common.service.UserService;

import cn.lfy.common.framework.exception.ApplicationException;

@Controller
@RequestMapping("/manager/statistics")
public class StatisticsCtrl {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/statistics_1")
    public String statistics_1(HttpServletRequest request) throws ApplicationException {
		Long today = userService.getTodayRegisterCount();
		Long total = userService.getTotalRegisterCount();
		request.setAttribute("today", today);
		request.setAttribute("total", total);
        return "system/statistics/statistics_1";
    }
}
