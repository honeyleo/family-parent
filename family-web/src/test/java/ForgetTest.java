import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;
import cn.lfy.common.utils.MessageDigestUtil;


public class ForgetTest {

	@Test
	public void forgetPwd() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("password", MessageDigestUtil.getSHA256("admin1"));
		reqParams.put("phone", "180287639911");
		reqParams.put("verify_code", "123456");
		reqParams.put("username", "180287639911");
		try {
			String response = HttpClient.post(Constant.HOST + "/forget/pwd", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void forgetReset() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("password", MessageDigestUtil.getSHA256("admin1"));
		reqParams.put("phone", "180287639911");
		reqParams.put("verify_code", "123456");
		reqParams.put("username", "180287639911");
		try {
			String response = HttpClient.post(Constant.HOST + "/forget/reset", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
