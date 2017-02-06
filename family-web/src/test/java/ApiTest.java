import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;
import cn.lfy.common.utils.MessageDigestUtil;

public class ApiTest {

	public static final String HOST = "http://localhost:8080";
	
	@Test
	public void login() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("username", "dev");
		reqParams.put("password", MessageDigestUtil.getSHA256("admin"));
		try {
			String response = HttpClient.post(HOST + "/oauth/login", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void me() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6IjIxMzYzYTc0NmMwMDRmODk5NGEyMTY1ZTRkNmZmNzQzIiwiciI6IjYwZDU1ZGQxYzNmNDQ0M2NhNmRlZjQ3ODM3Yzg5NjIzIiwidCI6MTQ4NjM4NjY4NywidSI6MX0=");
		try {
			String response = HttpClient.post(HOST + "/app/user/me", reqParams);
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
			String response = HttpClient.post(HOST + "/verify_code/get", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkVerifyCode() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("type", "REG");
		reqParams.put("phone", "18028763997");
		reqParams.put("code", "432430");
		try {
			String response = HttpClient.post(HOST + "/verify_code/check", reqParams);
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
		reqParams.put("code", "034522");
		try {
			String response = HttpClient.post(HOST + "/register", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void refreshToken() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6IjY3NTZiNjU2NzQyMTQyZGM4NTMwN2U3NWI0ZTkzZGM0IiwiciI6ImQxYTQzOWQwY2Q2YzRlNTdiODEwZGE2NTA3Nzk4MDFmIiwidCI6MTQ4NjM5MDY0MiwidSI6MX0=");
		try {
			String response = HttpClient.post(HOST + "/oauth/refresh_token", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
