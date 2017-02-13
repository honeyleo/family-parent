package cn.lfy.base.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baidu.ueditor.um.Uploader;

/**
 * 通用上传组件<br>
 * 支持上传所有文件类型
 *
 */
@Controller
public class ImageUpController {

	@RequestMapping( "/image/upload" )
	public void upload( MultipartHttpServletRequest request, HttpServletResponse response ) throws Exception {
	    Uploader up = new Uploader(request);
	    up.setSavePath("upload");
	    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
	    up.setAllowFiles(fileType);
	    up.setMaxSize(10000); //单位KB
	    up.upload();

	    String callback = request.getParameter("callback");

	    String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";

	    result = result.replaceAll( "\\\\", "\\\\" );

	    if( callback == null ){
	        response.getWriter().print( result );
	    }else{
	        response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
	    }
	}
}
