import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;

public class UserTest {

	@Test
	public void updateMy() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6IjAwMjU5MTE3ZGEyNTQ3NjVhY2JiYzYzMmMzODU5MjZiIiwiciI6ImE1NDJiOTQzZDhlZTQ3Y2Q5ZDA3MjA5ZGY5YzM2OWVmIiwidCI6MTQ4ODIwNzc3MSwidSI6MTd9");
		reqParams.put("zibei", "声");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user/update_my", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void detail() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6ImQzZDk0NDY4MDJhNDQyNTk3NTVkMzhlNmQxNjNlODIwIiwiciI6ImE1Y2I1ODRkMTdiMTRlNzU5MDBhY2Q2OTQyMzNmYWVjIiwidCI6MTQ4OTA3MDU1NCwidSI6MTB9");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user/user/17", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
