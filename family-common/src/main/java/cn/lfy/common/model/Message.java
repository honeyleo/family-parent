package cn.lfy.common.model;

import java.util.HashMap;
import java.util.Map;

public class Message extends HashMap<String,Object> {

	private static final long serialVersionUID = -8476898437116996416L;

	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static Builder newBuilder(String uri) {
		return new Builder(uri);
	}
	
	private Message(Builder builder) {
		this.put("ret", builder.ret);
		this.put("uri", builder.uri);
		this.put("msg", builder.msg);
		if(builder.data != null) {
			this.put("data", builder.data);
		}
		if(!builder.dataMap.isEmpty()) {
			this.put("data", builder.dataMap);
		}
		this.put("total", builder.total);
		this.putAll(builder.map);
	}
	public static class Builder {
		
		private int ret;
		
		private String uri;
		
		private String msg = "OK";
		
		private Object data;
		
		private Integer total;
		
		private Map<String,Object> map = new HashMap<String, Object>();
		
		private Map<String,Object> dataMap = new HashMap<String, Object>();
		
		public Builder() {
		}
		public Builder(String uri) {
			this.uri = uri;
		}
		
		public Builder setRet(int ret) {
			this.ret = ret;
			return this;
		}
		
		public Builder setUri(String uri) {
			this.uri = uri;
			return this;
		}
		public Builder setMsg(String msg) {
			this.msg = msg;
			return this;
		}
		
		public Builder data(Object data) {
			this.data = data;
			return this;
		}
		
		public Builder total(Integer total) {
			this.total = total;
			return this;
		}
		public Builder put(String key, Object value) {
			map.put(key, value);
			return this;
		}
		public Builder putData(String key, Object value) {
			dataMap.put(key, value);
			return this;
		}
		public Message build() {
			return new Message(this);
		}
	}
	
}
