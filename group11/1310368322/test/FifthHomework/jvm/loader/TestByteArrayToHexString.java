package com.coderising.jvm.loader;

public class TestByteArrayToHexString {

	public static void main(String[] args) {
		byte [] a = { -54,-2,-70,-66};
		for(int i = 0; i < 2; i++){
			byte b = a[i];
			int j = b  & 0xFF;
			String s = Integer.toHexString(j);
			System.out.println(s.length());
			System.out.println(s);
		}
	}
}
