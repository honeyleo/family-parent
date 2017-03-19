import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.family.common.model.Phone;
import com.google.common.collect.Lists;

import cn.lfy.common.utils.HttpClient;

public class UserTest {

	@Test
	public void me() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user/me", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void avatar() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		try {
			Map<HttpClient.FileInfo, byte[]> files = new HashMap<HttpClient.FileInfo, byte[]>();
			FileInputStream in = new FileInputStream("C:\\Users\\wendy\\Desktop\\html\\轮播\\img\\1.jpg");
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			files.put(new HttpClient.FileInfo("file", "1.jpg"), bytes);
			in.close();
			String response = HttpClient.postFile(Constant.HOST + "/app/user/avatar", reqParams, files);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateMy() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("gender", "1");
		reqParams.put("birthday", "" + System.currentTimeMillis()/1000);
		reqParams.put("birthplaceCountryId", "1");
		reqParams.put("birthplaceProvinceId", "2");
		reqParams.put("birthplaceCityId", "0");
		reqParams.put("birthplaceCountyId", "0");
		reqParams.put("ranking", "1");
		reqParams.put("nativePlaceCountryId", "1");
		reqParams.put("nativePlaceProvinceId", "2");
		reqParams.put("nativePlaceCityId", "0");
		reqParams.put("nativePlaceCountyId", "0");
		reqParams.put("ancestralCountryId", "1");
		reqParams.put("ancestralProvinceId", "2");
		reqParams.put("ancestralCityId", "0");
		reqParams.put("ancestralCountyId", "0");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user/update_my", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateMyInfo() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("industry", "26");
		reqParams.put("jobField", "56");
		reqParams.put("company", "脉承");
		reqParams.put("workplaceCountryId", "1");
		reqParams.put("workplaceProvinceId", "2");
		reqParams.put("workplaceCityId", "0");
		reqParams.put("tel", "0755-2012332");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user/update_my_info", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void detail() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user/detail/17", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updatePhones() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		Phone phone = new Phone();
		phone.setPhone("18028763997");
		phone.setMain(true);
		
		Phone phone2 = new Phone();
		phone2.setPhone("18028763996");
		phone2.setMain(false);
		
		List<Phone> list = Lists.newArrayList(phone, phone2);
		reqParams.put("phones", JSON.toJSONString(list));
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user/update_phones", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
