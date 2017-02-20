package cn.lfy.common.utils;

import org.apache.commons.lang3.StringUtils;

public class ManageHelper {

	public static boolean isUpload( String fileName ) {
		if( StringUtils.isEmpty( fileName ) ) {
			return false;
		}
		return fileName.startsWith( "/upload/" ) || fileName.startsWith( "http://localhost:8080/upload/" );
	}
}
