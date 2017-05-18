package com.springsecurity.utilities;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
	private static final String ALGORITHM = "AES"; 
	
	private static String strKey = "xJ78Z49sF0w4B8NKNvl51Q";
	private static Encoder encoder = Base64.getEncoder();
	private static Decoder decoder = Base64.getDecoder();
	private static SecretKey key = new SecretKeySpec(decoder.decode(strKey), "AES");
	
	public static String encrypt(String toEncrypt){
		try {
	        final Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	        final byte[] encValue = cipher.doFinal(toEncrypt.getBytes());
	        return new String(encoder.encode(encValue));
	    } catch(Exception ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
	
	public static String decrypt(String toEncrypt){
		try {
			
	        final Cipher c = Cipher.getInstance(ALGORITHM);
	        c.init(Cipher.DECRYPT_MODE, key);
	        byte[] decValue = decoder.decode(toEncrypt);
	        return new String(c.doFinal(decValue));
	    } catch(Exception ex) {
	    	ex.printStackTrace();
	        return null;
	    }
	}
}
