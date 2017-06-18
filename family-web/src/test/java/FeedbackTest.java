import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;

public class FeedbackTest {

	@Test
	public void add() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("description", "反馈测试信息");
		reqParams.put("access_token", Constant.access_token);
		try {
			Map<HttpClient.FileInfo, byte[]> files = new HashMap<HttpClient.FileInfo, byte[]>();
			FileInputStream in = new FileInputStream("E:\\tmp\\banner.png");
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			files.put(new HttpClient.FileInfo("file", "banner.png"), bytes);
			in.close();
			
			String response = HttpClient.postFile(Constant.HOST + "/app/feedback/add", reqParams, files);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
