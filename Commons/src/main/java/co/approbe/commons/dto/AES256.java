package co.approbe.commons.dto;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

public class AES256 {
	
	public String encrypt(String key, String keySpec, String cipherMode, String value) {
		String data = "";
		try {
			byte[] iv = { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0 };
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			byte[] keyBytes = new byte[32];
			keyBytes = Util.hexStringToByteArray(key);
			SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, keySpec);
			Cipher cipher = Cipher.getInstance(cipherMode);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
			byte[] input = value.getBytes(StandardCharsets.UTF_8);
			byte[] padded = Util.zeroBytePadding(input);
			byte[] encrypted = cipher.doFinal(padded);
			data=Hex.encodeHexString(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return data;
	}
	
	public String decrypt(String key, String keySpec, String cipherMode, String encrypted) {
		String data = "";
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			byte[] keyBytes = new byte[32];
			keyBytes = Util.hexStringToByteArray(key);
			SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, keySpec);

			Cipher cipher = Cipher.getInstance(cipherMode);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);

			byte[] original = cipher.doFinal(Hex.decodeHex(encrypted));
			data =  new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return data;
	}

}
