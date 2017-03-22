import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;
import cn.lfy.common.utils.MessageDigestUtil;


public class ForgetTest {

	@Test
	public void register() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("password", MessageDigestUtil.getSHA256("admin1"));
		reqParams.put("phone", "18028763997");
		reqParams.put("verify_code", "123456");
//		reqParams.put("username", "18028763997");
		try {
			String response = HttpClient.post(Constant.HOST + "/forget/reset", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
