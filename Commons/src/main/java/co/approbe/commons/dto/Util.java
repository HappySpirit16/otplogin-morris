package co.approbe.commons.dto;

import java.util.Arrays;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class Util {
	public static synchronized String fillString(String dataStr, String filler) {
		int inputSize = dataStr.length() + 8;
		while(dataStr.length() != inputSize)
			dataStr+= filler;
		while((dataStr.getBytes().length % 8) != 0)
			dataStr+= filler;
	    return dataStr;
	}
	
	
	public static synchronized String bytesToHex(byte[] bytes) {
		HexBinaryAdapter adapter = new HexBinaryAdapter();
		return  adapter.marshal(bytes);
	}
	
	public static byte[] hexToBytes(String hexString) {
		HexBinaryAdapter adapter = new HexBinaryAdapter();
		byte[] bytes = adapter.unmarshal(hexString);
		return bytes;
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	
	public static byte[] zeroBytePadding(byte[] byteArray) {
		int padding = byteArray.length % 16;
		int cryptedTextBufferPadding = byteArray.length + 16 - padding;
		byte[] padded = new byte[cryptedTextBufferPadding];
		System.arraycopy(byteArray, 0, padded, 0, byteArray.length);
		Arrays.fill(padded, byteArray.length, cryptedTextBufferPadding, (byte)0x0);
		return padded;

	}

}
