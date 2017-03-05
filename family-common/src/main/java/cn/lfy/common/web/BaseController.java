package cn.lfy.common.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import cn.lfy.common.filehandler.ResourceIdentifier;
import cn.lfy.common.filehandler.ResourceManager;
import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.utils.ManageHelper;
import cn.lfy.common.utils.PathFormat;

public class BaseController {

	/**
	 * 根据给定的文件名,获取其后缀信息
	 * @param filename
	 * @return
	 */
	public static String getSuffixByFilename ( String filename ) {
		
		return filename.substring( filename.lastIndexOf( "." ) ).toLowerCase();
		
	}
	
	protected boolean isMore(List<?> list, int limit ) {
		boolean more = false;
		if(list != null) {
			more = list.size() > limit;
		} else {
			more = false;
		}
		return more;
	}
	
	public static String getNewFileName(String originFileName) {
		String suffix = getSuffixByFilename(originFileName);
    	originFileName = originFileName.substring(0,
				originFileName.length() - suffix.length());
		String savePath = "{yyyy}/{mm}/{dd}/{time}{rand:6}" + suffix;
    	String fileName = PathFormat.parse(savePath, originFileName);
    	return fileName;
	}
	
	public static List<String> getImgsList(String imgs, String imageUrl) {
		if(!StringUtils.isBlank(imgs)) {
        	Iterable<String> itb = Splitter.on(",").split(imgs);
        	Iterator<String> it = itb.iterator();
        	List<String> imgsList = new ArrayList<String>();
        	while(it.hasNext()) {
        		String img = it.next();
        		if(StringUtils.isNotBlank(img)) {
        			imgsList.add(imageUrl + img);
        		}
        	}
        	return imgsList;
        }
		return Lists.newArrayList();
	}
	
	public ResourceIdentifier handleFile(String handlerName, MultipartFile file, ResourceManager resourceManager) {
		if(file != null) {
            try {
            	String originFileName = file.getOriginalFilename();
            	String fileName = getNewFileName(originFileName);
    			ResourceIdentifier dest = resourceManager.processResource(handlerName, file.getBytes(), fileName);
    			return dest;
    		} catch (IOException e) {
    			throw new ApplicationException(ErrorCode.SERVER_ERROR);
    		}
        }
		return null;
	}
	
	public String htmlContentImageAppendDomain(String content, String imageUrl) {
		if(StringUtils.isNotBlank(content)) {
        	Set<String> imgs = ManageHelper.getImgsFromText( content );
    		for( String img : imgs ) {
    			content = content.replace(img, imageUrl + img);
    		}
    		return content;
        }
		return "";
	}
	public List<String> uploadImageHandle(String imgs, String pathRoot, ResourceManager resourceManager, String handlerName, String imageUrl) {
		if(StringUtils.isNotBlank(imgs)) {
        	Iterable<String> itb = Splitter.on(",").split(imgs);
        	Iterator<String> it = itb.iterator();
        	List<String> list = new ArrayList<String>();
        	while(it.hasNext()) {
        		String im = it.next();
        		if(StringUtils.isNotBlank(im)) {
        			boolean isUpload = ManageHelper.isUpload(im);
        			if(isUpload) {
        				try {
        					String newFileName = im.replace("/upload/", "");
        					ResourceIdentifier dest = resourceManager.processResource(handlerName, pathRoot + im, newFileName, false );
        					list.add(dest.getRelativePath());
						} catch (IOException e) {
							e.printStackTrace();
						}
        			} else {
        				im = im.replaceAll(imageUrl, "");
        				list.add(im);
        			}
        		}
        	}
        	return list;
    	}
		return Lists.newArrayList();
	}
	public List<String> uploadImageHandle(String imgs, String pathRoot, ResourceManager resourceManager, String imageUrl) {
		return uploadImageHandle(imgs, pathRoot, resourceManager, "news", imageUrl);
	}
	
	public String ueditorContentImgHandle(String content, String pathRoot, ResourceManager resourceManager) {
		Set<String> imgs = ManageHelper.getImgsFromText( content );
		for( String img : imgs ) {
			// 如果是上传的
			boolean isUpload = ManageHelper.isUeditor( img );
			if( isUpload ) {
				try {
					String newFileName = img.replace("/ueditor/", "");
					ResourceIdentifier dest = resourceManager.processResource(
							"content_image", pathRoot + img, newFileName, false );
					content = content.replace( img, dest.getRelativePath());
				} catch( IOException e ) {
					
				}
			}
		}
		return content;
	}
}
