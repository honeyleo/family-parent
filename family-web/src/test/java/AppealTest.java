import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;

public class AppealTest {

	@Test
	public void publish() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6ImZhZDc5NGY2NTgzYjQ5OTc4MGZhZTIwOWYwYTY2ZWExIiwiciI6IjM3N2E0NDEwMGExOTRiZmE4ZWQ4MmM1NDRmNjUxMzVjIiwidCI6MTQ4ODIxMDc1OSwidSI6MTd9");
		reqParams.put("content", "在深圳的朋友，帮帮我吧");
		reqParams.put("lng", "12.1");
		reqParams.put("lat", "34");
		reqParams.put("phone", "123456789");
		try {
			Map<String, byte[]> files = new HashMap<String, byte[]>();
			FileInputStream in = new FileInputStream("C:\\Users\\wendy\\Desktop\\轮播\\img\\1.jpg");
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			in.close();
			files.put("1.jpg", bytes);
			FileInputStream in2 = new FileInputStream("C:\\Users\\wendy\\Desktop\\轮播\\img\\2.jpg");
			byte[] bytes2 = new byte[in2.available()];
			in2.read(bytes2);
			files.put("2.jpg", bytes2);
			in2.close();
			String response = HttpClient.postFile(Constant.HOST + "/app/appeal/publish", reqParams, files);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void mylist() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6ImZhZDc5NGY2NTgzYjQ5OTc4MGZhZTIwOWYwYTY2ZWExIiwiciI6IjM3N2E0NDEwMGExOTRiZmE4ZWQ4MmM1NDRmNjUxMzVjIiwidCI6MTQ4ODIxMDc1OSwidSI6MTd9");
		reqParams.put("start", "0");
		reqParams.put("limit", "10");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/appeal/mylist", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
