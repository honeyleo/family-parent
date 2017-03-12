package cn.lfy.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AgeUtil {

	public static int getAge(long birthday) {

		if (birthday <= 0) {
			return 0;
		}
		int age = 0;

		Date birthDate = new Date(birthday*1000);
		Date now = new Date();

		SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
		SimpleDateFormat format_M = new SimpleDateFormat("MM");

		String birth_year = format_y.format(birthDate);
		String this_year = format_y.format(now);

		String birth_month = format_M.format(birthDate);
		String this_month = format_M.format(now);

		// 初步，估算
		age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);

		// 如果未到出生月份，则age - 1
		if (this_month.compareTo(birth_month) < 0)
			age -= 1;
		if (age < 0)
			age = 0;
		return age;
	}
}
