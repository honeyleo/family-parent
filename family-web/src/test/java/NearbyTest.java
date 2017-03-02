import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;

public class NearbyTest {

	@Test
	public void list() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6ImQzZDk0NDY4MDJhNDQyNTk3NTVkMzhlNmQxNjNlODIwIiwiciI6ImQzNmYzZGRlMGVmZDRmYTBiNDFkZjk5NGVlNTIwMzY0IiwidCI6MTQ4ODQ2ODU4NiwidSI6MTB9");
		reqParams.put("lat", "22.4485150000");
		reqParams.put("lng", "114.1661120000");
		reqParams.put("start", "0");
		reqParams.put("limit", "100");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/nearby/list", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void list2() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6IjcwZWZkZjJlYzliMDg2MDc5Nzk1YzQ0MjYzNmI1NWZiIiwiciI6IjJlMjVhMDkxZDMwMzQzNTFiY2RkYzE2ZGJlMDYxODhhIiwidCI6MTQ4ODQ2ODUwOSwidSI6MTd9");
		reqParams.put("lat", "22.5485150000");
		reqParams.put("lng", "114.0661120000");
		reqParams.put("start", "0");
		reqParams.put("limit", "100");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/nearby/list", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
