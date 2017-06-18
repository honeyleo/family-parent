import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;

public class FamilyTest {

	@Test
	public void tree() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		try {
			String response = HttpClient.post(Constant.HOST + "/app/family/tree", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void add() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("memberId", "153");
		reqParams.put("relation", "22");
		reqParams.put("name", "高文");
		reqParams.put("gender", "2");
		reqParams.put("birthday", "" + System.currentTimeMillis()/1000);
		reqParams.put("zibei", "文");
		reqParams.put("ranking", "1");
		reqParams.put("birthplaceCountryId", "1");
		reqParams.put("birthplaceProvinceId", "2");
		reqParams.put("birthplaceCityId", "0");
		reqParams.put("birthplaceCountyId", "0");
		reqParams.put("nativePlaceCountryId", "1");
		reqParams.put("nativePlaceProvinceId", "2");
		reqParams.put("nativePlaceCityId", "0");
		reqParams.put("nativePlaceCountyId", "0");
		reqParams.put("ancestralCountryId", "1");
		reqParams.put("ancestralProvinceId", "2");
		reqParams.put("ancestralCityId", "0");
		reqParams.put("ancestralCountyId", "0");
		reqParams.put("alive", "1");
		reqParams.put("dieTime", "0");
		try {
			
			Map<HttpClient.FileInfo, byte[]> files = new HashMap<HttpClient.FileInfo, byte[]>();
			FileInputStream in = new FileInputStream("C:\\Users\\honeyleo\\Desktop\\image1\\favor.png");
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			files.put(new HttpClient.FileInfo("file", "favor.png"), bytes);
			in.close();
			
			String response = HttpClient.postFile(Constant.HOST + "/app/member/add", reqParams, files);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("id", "43");
		reqParams.put("name", "梅梅");
		reqParams.put("gender", "2");
		reqParams.put("birthday", "" + System.currentTimeMillis()/1000);
		reqParams.put("zibei", "枚");
		reqParams.put("ranking", "1");
		reqParams.put("birthplaceCountryId", "1");
		reqParams.put("birthplaceProvinceId", "2");
		reqParams.put("birthplaceCityId", "0");
		reqParams.put("birthplaceCountyId", "0");
		reqParams.put("nativePlaceCountryId", "1");
		reqParams.put("nativePlaceProvinceId", "2");
		reqParams.put("nativePlaceCityId", "0");
		reqParams.put("nativePlaceCountyId", "0");
		reqParams.put("ancestralCountryId", "1");
		reqParams.put("ancestralProvinceId", "2");
		reqParams.put("ancestralCityId", "0");
		reqParams.put("ancestralCountyId", "0");
		reqParams.put("alive", "1");
		reqParams.put("dieTime", "0");
		try {
			
			Map<HttpClient.FileInfo, byte[]> files = new HashMap<HttpClient.FileInfo, byte[]>();
			FileInputStream in = new FileInputStream("C:\\Users\\honeyleo\\Desktop\\image\\dd.png");
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			files.put(new HttpClient.FileInfo("file", "dd.jpg"), bytes);
			in.close();
			
			String response = HttpClient.postFile(Constant.HOST + "/app/member/update", reqParams, files);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void detail() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("memberId", "1");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/member/detail", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void del() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", Constant.access_token);
		reqParams.put("memberId", "154");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/member/del", reqParams);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
