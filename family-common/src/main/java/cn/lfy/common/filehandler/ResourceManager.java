package cn.lfy.common.filehandler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

	private final Map<String, ResourceHandler> resourceHandlers;

	public ResourceManager( Map<String, ResourceHandler> resourceHandlers ) {
		if( null == resourceHandlers ) {
			this.resourceHandlers = new HashMap<String, ResourceHandler>();
		} else {
			this.resourceHandlers = Collections.unmodifiableMap( resourceHandlers );
		}
	}
	
	public ResourceIdentifier processResource( String handlerName, String fileName ) throws IOException {
		ResourceHandler h = resourceHandlers.get( handlerName );
		return h.processResource( fileName );
	}

	public ResourceIdentifier processResource( String handlerName, String fileName, String newFileName ) throws IOException {
		ResourceHandler h = resourceHandlers.get( handlerName );
		return h.processResource( fileName, newFileName );
	}

	public ResourceIdentifier processResource(String handlerName,String fileName, String dirNo, String newFileName)throws IOException  {
		ResourceHandler h = resourceHandlers.get( handlerName );
		return h.processResource( fileName, dirNo,newFileName );
	}
	
}
