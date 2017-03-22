package com.family.common.enums;

public enum NewsType {

	NEWS_HOME("news_home", 1),
	NEWS_CONSULT("consult", 2),
	;
	private String label;
	private int value;
	
	private NewsType(String label, int value) {
		this.label = label;
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
	public String getLabel() {
		return this.label;
	}
	public static NewsType parse(int value) {
		for(NewsType newsType : values()) {
			if(newsType.getValue() == value) {
				return newsType;
			}
		}
		return NEWS_HOME;
	}
	
	public static NewsType parse(String label) {
		for(NewsType newsType : values()) {
			if(newsType.getLabel().equals(label)) {
				return newsType;
			}
		}
		return NEWS_HOME;
	}
}
