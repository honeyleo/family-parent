package cn.lfy.common.filehandler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import cn.lfy.common.framework.exception.ApplicationException;

public class ResourceHandlerImpl implements ResourceHandler {

	private static final int DIR_MAX_FILE_NUM = 500;

	private String baseDir;

	private String childDir;

	private int start = 1;

	private String baseURL;

	@Override
	public ResourceIdentifier processResource( String fileName ) throws IOException {
		return processResource( fileName, null );
	}

	@Override
	public ResourceIdentifier processResource( String fileName, String newFileName ) throws IOException {
		File srcFile = new File( fileName );
		if( null == srcFile || !srcFile.exists() ) {
			throw new ApplicationException( 192000 );
		}

		int dirNo = currentDirNo();
		String parentDirName = baseDir + childDir + File.separatorChar + dirNo;
		File parentDir = new File( parentDirName );
		if( !parentDir.exists() ) {
			parentDir.mkdir();
		}
		String destFileName = generateFileName( fileName, newFileName );
		File destFile = new File( parentDir, destFileName );

		FileUtils.copyFile( srcFile, destFile );
		FileUtils.forceDelete(srcFile);
		return new ResourceIdentifier( baseURL + ( baseURL.endsWith( "/" ) ? "" : "/" )
				+ childDir + "/" + dirNo + "/" + destFileName,
				parentDirName + File.separatorChar + destFileName, childDir + "/" + dirNo + "/" + destFileName );
	}
	
	@Override
	public ResourceIdentifier processResource( String fileName,String dirNo, String newFileName ) throws IOException {
		File srcFile = new File( fileName );
		if( null == srcFile || !srcFile.exists() ) {
			throw new ApplicationException( 192000 );
		}

		String parentDirName = baseDir + childDir + File.separatorChar + dirNo;
		File parentDir = new File( parentDirName );
		if( !parentDir.exists() ) {
			parentDir.mkdir();
		}
		String destFileName = generateFileName( fileName, newFileName );
		File destFile = new File( parentDir, destFileName );

		FileUtils.copyFile( srcFile, destFile );
		FileUtils.forceDelete(srcFile);
		return new ResourceIdentifier( baseURL + ( baseURL.endsWith( "/" ) ? "" : "/" )
				+ childDir + "/" + dirNo + "/" + destFileName,
				parentDirName + File.separatorChar + destFileName, childDir + "/" + dirNo + "/" + destFileName );
	}
	
	@Override
	public ResourceIdentifier processResource( String fileName, String newFileName, boolean useDirNo) throws IOException {

		File srcFile = new File( fileName );
		if( null == srcFile || !srcFile.exists() ) {
			throw new ApplicationException( 192000 );
		}

		int dirNo = currentDirNo();
		String parentDirName = baseDir + childDir;
		if(useDirNo) {
			parentDirName = parentDirName + File.separatorChar + dirNo;
		}
		File parentDir = new File( parentDirName );
		if( !parentDir.exists() ) {
			parentDir.mkdir();
		}
		String destFileName = generateFileName( fileName, newFileName );
		File destFile = new File( parentDir, destFileName );

		FileUtils.copyFile( srcFile, destFile );
		FileUtils.forceDelete(srcFile);
		return new ResourceIdentifier( baseURL + ( baseURL.endsWith( "/" ) ? "" : "/" )
				+ childDir + (useDirNo ? ("/" + dirNo) : "") + "/" + destFileName,
				parentDirName + File.separatorChar + destFileName, childDir + (useDirNo ? ("/" + dirNo) : "") + "/" + destFileName );
	}
	@Override
	public ResourceIdentifier processResource( byte[] file, String newFileName, boolean useDirNo, boolean sync ) throws IOException {

		if( StringUtils.isEmpty( newFileName ) ) {
			throw new ApplicationException( 192000 );
		}

		String parentDirName = baseDir + childDir;
		if( useDirNo ) {
			int dirNo = currentDirNo();
			newFileName = File.separatorChar + dirNo + newFileName;
		}

		File destFile = new File( parentDirName + File.separatorChar + newFileName );
		File parentDir = new File( destFile.getParent() );
		if( !parentDir.exists() ) {
			parentDir.mkdir();
		}

		InputStream fileInputStream = new ByteArrayInputStream( file );
		FileUtils.copyInputStreamToFile( fileInputStream, destFile );

		String url = baseURL + ( baseURL.endsWith( "/" ) ? "" : "/" ) + childDir + "/" + newFileName;
		return new ResourceIdentifier( url, parentDirName + File.separatorChar + newFileName, childDir + "/" + newFileName );
	}

	protected synchronized int currentDirNo() {
		File parent = new File( baseDir + childDir );
		if( !parent.exists() ) {
			parent.mkdirs();
		}
		if( !parent.isDirectory() ) {
			throw new ApplicationException( 192000 );
		}
		File[] pFiles = parent.listFiles();
		if( null == pFiles || 0 == pFiles.length ) {
			return start;
		} else {
			File child = new File( baseDir + childDir + File.separatorChar + start );
			File[] cFiles = child.listFiles();
			if( null == cFiles || DIR_MAX_FILE_NUM > cFiles.length ) {
				return start;
			} else {
				// 如果当前的起始数与文件夹个数不一样，从当前文件夹个数开始
				if( start != pFiles.length ) {
					start = pFiles.length;
				}
				return start++;
			}
		}
	}

	protected String generateFileName( String oldName ) {
		String newName = UUID.randomUUID().toString();
		newName = newName.replaceAll( "\\-", "" );
		newName += FilenameUtils.EXTENSION_SEPARATOR_STR;
		newName += FilenameUtils.getExtension( oldName );
		return newName;
	}

	protected String generateFileName( String oldName, String newFileName ) {
		if( !StringUtils.isEmpty( newFileName ) ) {
			return newFileName;
		}
		return generateFileName( oldName );
	}

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir( String baseDir ) {
		this.baseDir = baseDir;
	}

	public String getChildDir() {
		return childDir;
	}

	public void setChildDir( String childDir ) {
		this.childDir = childDir;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL( String baseURL ) {
		this.baseURL = baseURL;
	}
}
