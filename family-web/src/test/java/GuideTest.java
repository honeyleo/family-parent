import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

import cn.lfy.common.utils.HttpClient;

public class GuideTest {

	@Test
	public void guide() {
		try {
			Map<String, String> reqParams = Maps.newHashMap();
			String response = HttpClient.post(Constant.HOST + "/guide", reqParams);
			System.out.println(response);
		} catch(Throwable t) {
			
		}
	}
}
