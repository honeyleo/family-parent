package cn.lfy.common.utils;

public class RedisKey {

	private static String KEY_TOKEN = "token:%s";
	
	private static String KEY_CURRENT_USER = "cu:%d";
	
	private static String KEY_VERIFY_CODE = "vc:%s:%s";
	
	public static String tokenKey(String token) {
		return String.format(KEY_TOKEN, token);
	}
	
	public static String currentUserKey(long uid) {
		return String.format(KEY_CURRENT_USER, uid);
	}
	
	public static String verifyCodeKey(String type, String phone) {
		return String.format(KEY_VERIFY_CODE, type, phone);
	}
}
