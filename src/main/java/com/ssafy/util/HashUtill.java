package com.ssafy.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtill {
	private static HashUtill instance = new HashUtill();
	public HashUtill() {}
	public static HashUtill getInstance() {
		return instance;
	}
	
	public String Hashing(String pass, String salt)  {
		byte[] password = pass.getBytes();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			for (int i = 0; i < 1998; i++) {
				String temp = Byte_to_String(password);
				md.update(temp.getBytes());
				password = md.digest();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Byte_to_String(password);
	}
	
	
	// 솔트를 생성해주는 함수
	public String createSalt() throws Exception {
		SecureRandom rand = new SecureRandom();
		byte[] temp = new byte[20];
		rand.nextBytes(temp);
		
		return Byte_to_String(temp);
	}
	private String Byte_to_String(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for(byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}
}
