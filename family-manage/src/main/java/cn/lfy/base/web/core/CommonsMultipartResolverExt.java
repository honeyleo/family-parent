package cn.lfy.base.web.core;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CommonsMultipartResolverExt extends CommonsMultipartResolver {
	
	@Override  
    public boolean isMultipart(javax.servlet.http.HttpServletRequest request) {  
		String uri = request.getRequestURI();  
		//过滤使用百度UEditor的URI  
		if (uri.indexOf("ued/config") > 0 || uri.indexOf("/ued/config") > 0) {
			return false;  
		}  
		return super.isMultipart(request);  
    }  
}
