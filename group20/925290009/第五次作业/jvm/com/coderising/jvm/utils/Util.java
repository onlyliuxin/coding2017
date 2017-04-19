package com.coderising.jvm.utils;

public class Util {

	public static int bytesToInt(byte[] by){
		String hexString = bytesToHexString(by);
		return Integer.valueOf(hexString, 16).intValue();
	}
	
	public static String bytesToHexString(byte[] by){
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < by.length; i++) {
			int value = by[i] & 0xFF;
			String strHex = Integer.toHexString(value);
			if(strHex.length()< 2){
				strHex = "0" + strHex;
			}		
			stringBuilder.append(strHex);
		}
		return stringBuilder.toString();
	}
}
