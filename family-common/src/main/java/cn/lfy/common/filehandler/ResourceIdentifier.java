package cn.lfy.common.filehandler;

public class ResourceIdentifier {

	private String url;

	private String canonicalPath;

	public ResourceIdentifier( String url, String canonicalPath ) {
		this.url = url;
		this.canonicalPath = canonicalPath;
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
}
