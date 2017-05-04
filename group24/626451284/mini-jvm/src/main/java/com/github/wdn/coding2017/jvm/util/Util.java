package com.github.wdn.coding2017.jvm.util;

public class Util {
	public static int byteToInt(byte[] codes){
    	String s1 = byteToHexString(codes);
    	return Integer.valueOf(s1, 16).intValue();
    }
    
    
    
	public static String byteToHexString(byte[] codes ){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<codes.length;i++){
			byte b = codes[i];
			int value = b & 0xFF;
			String strHex = Integer.toHexString(value);
			if(strHex.length()< 2){
				strHex = "0" + strHex;
			}
			buffer.append(strHex);
		}
		return buffer.toString();
	}
	public static String hexString2String(String src) {
		String temp = "";
		for (int i = 0; i < src.length() / 2; i++) {
			temp = temp
					+ (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2),
					16).byteValue();
		}
		return temp;
	}

	public static void main(String[] args) {
		System.out.println(Util.hexString2String("436f6465"));
	}
}
