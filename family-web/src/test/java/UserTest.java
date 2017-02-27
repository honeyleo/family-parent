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
}
