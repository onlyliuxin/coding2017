package com.coderising.jvm.loader;

public class TestByteArrayToInt {
	public static void main(String[] args) {
		byte a[] = {1,1};
		//   System.out.println(a[0]<<7); 左移，丢弃最高的 k 位，右端补 k 个0
		// int b = a[0]<<8 + a[1];
		int b = (a[0]<<8) + a[1];
		// System.out.println(a[0]); 左移对原内存中的值没有影响
		System.out.println(b);
	}
}
