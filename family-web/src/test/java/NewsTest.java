import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;

public class NewsTest {

	@Test
	public void list() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6ImQzZDk0NDY4MDJhNDQyNTk3NTVkMzhlNmQxNjNlODIwIiwiciI6ImMyZmUzN2I2NmU4ZTQyZTlhNTQwZDQwZmI2OGFlMDg0IiwidCI6MTQ4ODYyNzQ3OCwidSI6MTB9");
		reqParams.put("type", "1");
		reqParams.put("start", "0");
		reqParams.put("limit", "10");
		reqParams.put("last_update_time", "0");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/news_home/list", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void comment() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6ImQzZDk0NDY4MDJhNDQyNTk3NTVkMzhlNmQxNjNlODIwIiwiciI6ImMyZmUzN2I2NmU4ZTQyZTlhNTQwZDQwZmI2OGFlMDg0IiwidCI6MTQ4ODYyNzQ3OCwidSI6MTB9");
		reqParams.put("newsId", "8");
		reqParams.put("content", "评论咯");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/news_home/comment", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void comments() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6ImQzZDk0NDY4MDJhNDQyNTk3NTVkMzhlNmQxNjNlODIwIiwiciI6ImMyZmUzN2I2NmU4ZTQyZTlhNTQwZDQwZmI2OGFlMDg0IiwidCI6MTQ4ODYyNzQ3OCwidSI6MTB9");
		reqParams.put("newsId", "8");
		reqParams.put("start", "0");
		reqParams.put("limit", "10");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/news_home/comments", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
