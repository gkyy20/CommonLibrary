package jp.gkyy.common.util;

public class Encryptor {
	
	public static byte[] encrypt(String key, String text)
	    throws javax.crypto.IllegalBlockSizeException,
	        java.security.InvalidKeyException,
	        java.security.NoSuchAlgorithmException,
	        java.io.UnsupportedEncodingException,
	        javax.crypto.BadPaddingException,
	        javax.crypto.NoSuchPaddingException
	{
		javax.crypto.spec.SecretKeySpec sksSpec = 
		    new javax.crypto.spec.SecretKeySpec(key.getBytes(), "Blowfish");
		javax.crypto.Cipher cipher = 
		    javax.crypto.Cipher.getInstance("Blowfish");
		cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, sksSpec);
		byte[] encrypted = cipher.doFinal(text.getBytes());
		return encrypted;
	}
	
	public static String decrypt(String key, byte[] encrypted)
		    throws javax.crypto.IllegalBlockSizeException,
		        java.security.InvalidKeyException,
		        java.security.NoSuchAlgorithmException,
		        java.io.UnsupportedEncodingException,
		        javax.crypto.BadPaddingException,
		        javax.crypto.NoSuchPaddingException
	{
		javax.crypto.spec.SecretKeySpec sksSpec = 
		    new javax.crypto.spec.SecretKeySpec(key.getBytes(), "Blowfish");
		javax.crypto.Cipher cipher = 
		    javax.crypto.Cipher.getInstance("Blowfish");
		cipher.init(javax.crypto.Cipher.DECRYPT_MODE, sksSpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return new String(decrypted);
	}
}
