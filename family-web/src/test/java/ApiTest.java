import java.io.FileInputStream;
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
		reqParams.put("username", "qnyk66");
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
		reqParams.put("phone", "18948714536");
		reqParams.put("code", "345305");
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
		reqParams.put("verify_code", "345305");
		reqParams.put("surname", "廖");
		reqParams.put("name", "鹏");
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
	
	@Test
	public void avatar() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6IjY3NTZiNjU2NzQyMTQyZGM4NTMwN2U3NWI0ZTkzZGM0IiwiciI6ImQxYTQzOWQwY2Q2YzRlNTdiODEwZGE2NTA3Nzk4MDFmIiwidCI6MTQ4NjM5MDY0MiwidSI6MX0=");
		try {
			Map<String, byte[]> files = new HashMap<String, byte[]>();
			FileInputStream in = new FileInputStream("C:\\Users\\wendy\\Desktop\\轮播\\img\\1.jpg");
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			files.put("1.jpg", bytes);
			in.close();
			String response = HttpClient.postFile(HOST + "/app/user/avatar", reqParams, files);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void apply() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6IjdmMDU1YzVhNDhjNDRjYmNhYjI1ZTUyZmQ3YzY5ZjY2IiwiciI6IjEzNGE5NTExYTE1ZTRhZjliOTRmZDcyMDcxZTU4YTYwIiwidCI6MTQ4ODAwODcyNywidSI6MX0=");
		reqParams.put("friend_id", "2");
		try {
			String response = HttpClient.post(HOST + "/app/user_friend/apply", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void agree() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6IjY3OWI5OGI5NGFlMTQ0YzZiNzM1MmJiMjkxNTA0NDgwIiwiciI6ImM1MTExNWE2NmM3NzQzMTdhZjJjZDliMmExN2IxYjVhIiwidCI6MTQ4ODAwODg5NCwidSI6Mn0=");
		reqParams.put("friend_id", "1");
		try {
			String response = HttpClient.post(HOST + "/app/user_friend/agree", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void friendList() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6IjY3OWI5OGI5NGFlMTQ0YzZiNzM1MmJiMjkxNTA0NDgwIiwiciI6ImM1MTExNWE2NmM3NzQzMTdhZjJjZDliMmExN2IxYjVhIiwidCI6MTQ4ODAwODg5NCwidSI6Mn0=");
		try {
			String response = HttpClient.post(HOST + "/app/user_friend/list", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void friendNotifys() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6IjY3OWI5OGI5NGFlMTQ0YzZiNzM1MmJiMjkxNTA0NDgwIiwiciI6ImM1MTExNWE2NmM3NzQzMTdhZjJjZDliMmExN2IxYjVhIiwidCI6MTQ4ODAwODg5NCwidSI6Mn0=");
		try {
			String response = HttpClient.post(HOST + "/app/user_friend/notifys", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
