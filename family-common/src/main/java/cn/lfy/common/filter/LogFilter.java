package cn.lfy.common.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.lfy.common.utils.ParamUtils;

public class LogFilter extends OncePerRequestFilter {

	private Logger log = LoggerFactory.getLogger(LogFilter.class);
	
	private CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		if(isMultipartContent(request)) {
			request = multipartResolver.resolveMultipart(request);
		}
		
		String requestUri = request.getRequestURI();
		Map<String, Object> paraMap = ParamUtils.getParameter(request);
		String queryString = paraMap.isEmpty() ? "" : "?" + ParamUtils.getParameterText(paraMap, null, "&");
		log.info(requestUri + queryString);
		
		filterChain.doFilter(request, response);
	}
	
	/** 
     * 判断是否是multipart/form-data请求 
     * @param request 
     * @return 
     */  
    public static boolean isMultipartContent(HttpServletRequest request) {  
        if(!"post".equals(request.getMethod().toLowerCase())) {  
            return false;  
        }  
          
        String contentType = request.getContentType();  //获取Content-Type  
        if((contentType != null) && (contentType.toLowerCase().startsWith("multipart/"))) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
}
