package cn.lfy.base.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.ueditor.ActionEnter;

@Controller
@RequestMapping("/ued")
public class UEditorController {
 
	@Value("${env}")
	private String env;
	
	@Value("${fileserver.image.dir}")
	private String rootPath;
	
    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
 
        response.setContentType("application/json");
        
        String action = request.getParameter("action"); 
        try {
//        	if("test".equals(env)) {
//        		rootPath = request.getSession()
//                        .getServletContext().getRealPath("/") + "/fileserver";
//        	}
        	rootPath = request.getSession()
                    .getServletContext().getRealPath("/");
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            if( action!=null && (action.equals("listfile") || action.equals("listimage") ) ){    
                rootPath = rootPath.replace("\\", "/");    
                exec = exec.replaceAll(rootPath, "/");  
            }    
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
}
