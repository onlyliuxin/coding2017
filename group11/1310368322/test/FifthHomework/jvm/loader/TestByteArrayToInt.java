package com.coderising.jvm.loader;

public class TestByteArrayToInt {
	public static void main(String[] args) {
		byte a[] = {1,1};
		//   System.out.println(a[0]<<7); ���ƣ�������ߵ� k λ���Ҷ˲� k ��0
		// int b = a[0]<<8 + a[1];
		int b = (a[0]<<8) + a[1];
		// System.out.println(a[0]); ���ƶ�ԭ�ڴ��е�ֵû��Ӱ��
		System.out.println(b);
	}
}
