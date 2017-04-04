package cn.lfy.common.utils;

public class RedisKey {

	private static String PREFIX = "F:";
	
	private static String KEY_TOKEN = PREFIX + "token:%d";
	
	private static String KEY_CURRENT_USER = PREFIX + "user:%d";
	
	private static String KEY_VERIFY_CODE = PREFIX + "vc:%s:%s";
	
	private static final String NEARBY_SURNAME_PEOPLE = PREFIX + "neaby:surname:%s:people";
	
	private static final String KEY_DISTRICT = PREFIX + "district:%d";
	
	public static String tokenKey(long uid) {
		return String.format(KEY_TOKEN, uid);
	}
	
	public static String currentUserKey(long uid) {
		return String.format(KEY_CURRENT_USER, uid);
	}
	
	public static String verifyCodeKey(String type, String phone) {
		return String.format(KEY_VERIFY_CODE, type, phone);
	}
	
	public static String nearbySurnamePeopleKey(String surname) {
		return String.format(NEARBY_SURNAME_PEOPLE, surname);
	}
	
	public static String districtKey(long id) {
		return String.format(KEY_DISTRICT, id);
	}
}
