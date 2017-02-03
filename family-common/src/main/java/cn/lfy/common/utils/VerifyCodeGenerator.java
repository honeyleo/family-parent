package cn.lfy.common.utils;

import java.util.Random;

public class VerifyCodeGenerator {

	private static char[] CODES = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public static String code() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 6; i++) {
			Random r = new Random();
			sb.append(CODES[r.nextInt(6)]);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(code());
	}
}
