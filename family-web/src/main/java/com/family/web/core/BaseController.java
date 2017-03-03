package com.family.web.core;

import java.util.List;

import cn.lfy.common.utils.PathFormat;

public class BaseController {

	protected boolean isMore(List<?> list, int limit ) {
		boolean more = false;
		if(list != null) {
			more = list.size() > limit;
		} else {
			more = false;
		}
		return more;
	}
	
	/**
	 * 根据给定的文件名,获取其后缀信息
	 * @param filename
	 * @return
	 */
	public static String getSuffixByFilename ( String filename ) {
		
		return filename.substring( filename.lastIndexOf( "." ) ).toLowerCase();
		
	}
	
	public static String getNewFileName(String originFileName) {
		String suffix = getSuffixByFilename(originFileName);
    	originFileName = originFileName.substring(0,
				originFileName.length() - suffix.length());
		String savePath = "{yyyy}/{mm}/{dd}/{time}{rand:6}" + suffix;
    	String fileName = PathFormat.parse(savePath, originFileName);
    	return fileName;
	}
}
