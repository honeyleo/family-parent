import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import cn.lfy.common.utils.HttpClient;
import cn.lfy.common.utils.HttpUtils;

public class UserCertTest {

	
	@Test
	public void cert() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("name", "廖鹏");
		reqParams.put("id_card_no", "450923198709106317");
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6ImQzZDk0NDY4MDJhNDQyNTk3NTVkMzhlNmQxNjNlODIwIiwiciI6ImMyZmUzN2I2NmU4ZTQyZTlhNTQwZDQwZmI2OGFlMDg0IiwidCI6MTQ4ODYyNzQ3OCwidSI6MTB9");
		try {
			Map<HttpClient.FileInfo, byte[]> files = new HashMap<HttpClient.FileInfo, byte[]>();
			FileInputStream in = new FileInputStream("E:\\tmp\\idCardNo.jpg");
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			files.put(new HttpClient.FileInfo("front_img", "idCardNo.jpg"), bytes);
			in.close();
			
			FileInputStream in2 = new FileInputStream("E:\\tmp\\idCardNo3.jpg");
			byte[] bytes2 = new byte[in2.available()];
			in2.read(bytes2);
			files.put(new HttpClient.FileInfo("back_img", "idCardNo3.jpg"), bytes2);
			in2.close();
			
			FileInputStream in3 = new FileInputStream("E:\\tmp\\idCardNo2.jpg");
			byte[] bytes3 = new byte[in3.available()];
			in3.read(bytes3);
			files.put(new HttpClient.FileInfo("hand_img", "idCardNo2.jpg"), bytes3);
			in3.close();
			
			String response = HttpClient.postFile(Constant.HOST + "/app/user_cert/certification", reqParams, files);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void info() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6ImQzZDk0NDY4MDJhNDQyNTk3NTVkMzhlNmQxNjNlODIwIiwiciI6ImMyZmUzN2I2NmU4ZTQyZTlhNTQwZDQwZmI2OGFlMDg0IiwidCI6MTQ4ODYyNzQ3OCwidSI6MTB9");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user_cert/info", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		String host = "https://dm-51.data.aliyun.com";
	    String path = "/rest/160601/ocr/ocr_idcard.json";
	    String method = "POST";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE 77a7b0f55fd74d1fbb6d6b50912ce3db");
	    Map<String, String> querys = new HashMap<String, String>();
	    String base64 = "", base642 = "";
	    try {
	    	FileInputStream in = new FileInputStream("E:\\tmp\\idCardNo.jpg");
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			in.close();
			base64 = Base64.encodeBase64String(bytes);
			
			FileInputStream in2 = new FileInputStream("E:\\tmp\\idCardNo3.jpg");
			byte[] bytes2 = new byte[in2.available()];
			in2.read(bytes2);
			in2.close();
			base642 = Base64.encodeBase64String(bytes2);
	    } catch(Throwable t) {
	    	t.printStackTrace();
	    }
	    String bodys = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\" " + base64 + "\"},\"configure\":{\"dataType\":50,\"dataValue\":\"{\\\"side\\\":\\\"face\\\"}\"}},"
	    		+ "{\"image\":{\"dataType\":50,\"dataValue\":\" " + base642 + "\"},\"configure\":{\"dataType\":50,\"dataValue\":\"{\\\"side\\\":\\\"back\\\"}\"}}]}";

//		AliIdCertDTO aliIdCertDTO = new AliIdCertDTO();
//		aliIdCertDTO.setConfigure(new AliIdCertDTO.Configure(50, new AliIdCertDTO.Configure.DataValue(AliIdCertDTO.SIDE_FACE)));
//		
//		aliIdCertDTO.setImage(new AliIdCertDTO.Image(50, base64));
//		
//		JSONObject body = new JSONObject();
//		JSONArray array = new JSONArray();
//		array.add(aliIdCertDTO);
//		body.put("inputs", array);
	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	System.out.println(EntityUtils.toString(response.getEntity()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	
}
