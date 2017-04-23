import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;

public class NearbyTest {

	@Test
	public void list() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6IjcwZWZkZjJlYzliMDg2MDc5Nzk1YzQ0MjYzNmI1NWZiIiwiciI6Ijg1NmE1MGRiYzEzNDQ2MDVhYjMyZGNjMjFkM2JmMGY4IiwidCI6MTQ5MjY5ODM2OCwidSI6MTd9");
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
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6ImM4MWU3MjhkOWQ0YzJmNjM2ZjA2N2Y4OWNjMTQ4NjJjIiwiciI6ImRhMDBlOWI4MzMxNDQxMGJhNTM5ZjdlNGRmNDIyNzlhIiwidCI6MTQ5MjY5ODM5NSwidSI6Mn0=");
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
