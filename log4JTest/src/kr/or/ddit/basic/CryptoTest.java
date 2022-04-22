package kr.or.ddit.basic;


import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {

	public static void main(String[] args) throws Exception {
		String sourceText = "Hello, World!! 가나다라 12345 !@#$%";
		String key = "a1b2c3d4e5f6g7h8"; // 키값은 사용자가 임의로 정해서 사용한다.
		
		System.out.println("단방향 암호화...");
		System.out.println("SHA-512방식 : " + CryptoUtil.sha512(sourceText));
		
		System.out.println("양방향 암호화...");
		
		String encryptStr = CryptoUtil.encryptAES256(sourceText, key);
		System.out.println("원본 데이터 : " + sourceText);
		System.out.println("암호화한 데이터 : " + encryptStr);
		
		String decryptStr = CryptoUtil.decryptAES256(encryptStr, key);
		System.out.println("복호화한 데이터 : " + decryptStr);
	}

}
