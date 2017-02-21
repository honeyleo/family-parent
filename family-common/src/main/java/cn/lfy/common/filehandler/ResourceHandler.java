package cn.lfy.common.filehandler;

import java.io.IOException;

public interface ResourceHandler {

	/**
	 * 保存文件
	 * @param fileName 待保存的文件名
	 * @return 文件标示
	 * @throws IOException
	 */
	ResourceIdentifier processResource( String fileName ) throws IOException;

	/**
	 * 保存文件
	 * @param fileName 待保存的文件名
	 * @param newFileName 保存后的文件名，传null则以随机数命名
	 * @return 文件标示
	 * @throws IOException
	 */
	ResourceIdentifier processResource( String fileName, String newFileName ) throws IOException;
	
	/**
	 * 保存文件
	 * @param fileName 待保存的文件名
	 * @param dirNo 自定义子目录
	 * @param newFileName 保存后的文件名，传null则以随机数命名
	 * @return 文件标示
	 * @throws IOException
	 */
	ResourceIdentifier processResource( String fileName,String dirNo, String newFileName ) throws IOException;
	
	/**
	 * 保存文件
	 * @param file 文件
	 * @param newFileName 新名称
	 * @param useDirNo 是否使用编号
	 * @param sync 是否进行双源站同步
	 * @return
	 * @throws IOException
	 */
	ResourceIdentifier processResource( byte[] file, String newFileName, boolean useDirNo, boolean sync ) throws IOException;
	
	ResourceIdentifier processResource( String fileName, String newFileName, boolean useDirNo) throws IOException;
}
