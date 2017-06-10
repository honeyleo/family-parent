import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;
import cn.lfy.common.utils.MessageDigestUtil;

public class ApiTest {

	
	@Test
	public void login() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("username", "admin");
		reqParams.put("password", MessageDigestUtil.getSHA256("admin"));
		try {
			String response = HttpClient.post(Constant.HOST + "/oauth/login", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getVerifyCode() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("type", "REG");
		reqParams.put("phone", "18028763997");
		try {
			String response = HttpClient.post(Constant.HOST + "/verify_code/get", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkVerifyCode() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("type", "REG");
		reqParams.put("phone", "18948714536");
		reqParams.put("code", "345305");
		try {
			String response = HttpClient.post(Constant.HOST + "/verify_code/check", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void register() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("password", MessageDigestUtil.getSHA256("admin"));
		reqParams.put("phone", "18028763997");
		reqParams.put("verify_code", "123456");
		reqParams.put("surname", "廖");
		reqParams.put("name", "鹏");
		try {
			String response = HttpClient.post(Constant.HOST + "/register", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void refreshToken() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		try {
			String response = HttpClient.post(Constant.HOST + "/oauth/refresh_token", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void apply() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("friend_id", "2");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user_friend/apply", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void agree() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("friend_id", "1");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user_friend/agree", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void friendList() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user_friend/list", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void friendNotifys() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user_friend/notifys", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void search() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("query", "系统管理员");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user_friend/search", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkUpdate() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("type", "IOS");
		reqParams.put("channel", "100");
		reqParams.put("versionCode", "2");
		try {
			String response = HttpClient.post(Constant.HOST + "/version/check_update", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void may_know_family_list() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("pageSize", "1");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user_friend/may_know_family_list", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
