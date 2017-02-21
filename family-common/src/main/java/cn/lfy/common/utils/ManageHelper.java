package cn.lfy.common.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ManageHelper {

	private static final Pattern PATTERN = Pattern.compile( ".*?(<img.*?src=\"(.*?)\").*?" );
	
	public static boolean isUpload( String fileName ) {
		if( StringUtils.isEmpty( fileName ) ) {
			return false;
		}
		return fileName.startsWith( "/upload/" ) || fileName.startsWith( "http://localhost:8080/upload/" );
	}
	
	public static boolean isUeditor( String fileName ) {
		if( StringUtils.isEmpty( fileName ) ) {
			return false;
		}
		return fileName.startsWith( "/ueditor/" ) || fileName.startsWith( "http://localhost:8080/ueditor/");
	}
	/**
	 * 从评论文章中获取图片信息
	 * @param content
	 * @return
	 */
	public static Set<String> getImgsFromText( String content ) {
		Set<String> imgs = new HashSet<String>();
		Matcher matcher = PATTERN.matcher( content );
		while( matcher.find() ) {
			imgs.add( matcher.group( 2 ) );
		}
		return imgs;
	}
}
