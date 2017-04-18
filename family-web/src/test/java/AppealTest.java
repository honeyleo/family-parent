import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;

public class AppealTest {

	@Test
	public void publish() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("content", "在深圳的朋友，帮帮我吧");
		reqParams.put("lng", "12.1");
		reqParams.put("lat", "34");
		reqParams.put("phone", "123456789");
		try {
			Map<HttpClient.FileInfo, byte[]> files = new HashMap<HttpClient.FileInfo, byte[]>();
			FileInputStream in = new FileInputStream("E:\\tmp\\banner.png");
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			in.close();
			files.put(new HttpClient.FileInfo("flie", "banner.png"), bytes);
			FileInputStream in2 = new FileInputStream("E:\\tmp\\banner.png");
			byte[] bytes2 = new byte[in2.available()];
			in2.read(bytes2);
			files.put(new HttpClient.FileInfo("file", "png2.jpg"), bytes2);
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
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("start", "0");
		reqParams.put("limit", "10");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/appeal/mylist", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void list() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("start", "0");
		reqParams.put("limit", "10");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/appeal/list", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
