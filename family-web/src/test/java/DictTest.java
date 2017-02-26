import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;

public class DictTest {

	public static final String HOST = "http://localhost:8080";
	
	@Test
	public void district() {
		Map<String, String> reqParams = new HashMap<String, String>();
		try {
			String response = HttpClient.post(HOST + "/district/all", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void districtList() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("pid", "0");
		try {
			String response = HttpClient.post(HOST + "/district/list", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void dict() {
		Map<String, String> reqParams = new HashMap<String, String>();
		try {
			String response = HttpClient.post(HOST + "/dict/JOB_FIELD/list", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
