package com.family.web;

import java.util.List;

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
}
