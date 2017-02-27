import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.lfy.common.utils.HttpClient;

public class InterestTest {

	@Test
	public void update() {
		Map<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("access_token", "eyJpIjoiMTI3LjAuMC4xIiwibCI6ImZhZDc5NGY2NTgzYjQ5OTc4MGZhZTIwOWYwYTY2ZWExIiwiciI6IjM3N2E0NDEwMGExOTRiZmE4ZWQ4MmM1NDRmNjUxMzVjIiwidCI6MTQ4ODIxMDc1OSwidSI6MTd9");
		reqParams.put("interestId", "声");
		try {
			String response = HttpClient.post(Constant.HOST + "/app/user_interest/update", "interestId=13&interestId=12&interestId=11&access_token=eyJpIjoiMTI3LjAuMC4xIiwibCI6ImZhZDc5NGY2NTgzYjQ5OTc4MGZhZTIwOWYwYTY2ZWExIiwiciI6IjM3N2E0NDEwMGExOTRiZmE4ZWQ4MmM1NDRmNjUxMzVjIiwidCI6MTQ4ODIxMDc1OSwidSI6MTd9");
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
