package cn.lfy.common.filehandler;

public class ResourceIdentifier {

	private String url;

	private String canonicalPath;
	
	private String relativePath;

	public ResourceIdentifier( String url, String canonicalPath, String relativePath) {
		this.url = url;
		this.canonicalPath = canonicalPath;
		this.relativePath = relativePath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl( String url ) {
		this.url = url;
	}

	public String getCanonicalPath() {
		return canonicalPath;
	}

	public void setCanonicalPath( String canonicalPath ) {
		this.canonicalPath = canonicalPath;
	}
	/**
	 * 文件服务器物理相对路径
	 * @return
	 */
	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
}
