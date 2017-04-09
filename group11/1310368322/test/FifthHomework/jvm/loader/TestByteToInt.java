package com.coderising.jvm.loader;

public class TestByteToInt {
	public static void main(String[] args) {
		byte a = -1;
		int b = a;// 这样会把a的值直接赋给 b，不会发生改变，在内存中， 会将 前面的数全部变成 1， -128在 byte中的内存表示为： 1000 0000，然后变成int会是： 1111 1111 1111 1111 1111 1111 1000 0000（-128的int型补码）
		int c = a & 0xFFFF;// 按位与是在内存中进行的，也就是说，是对a的补码进行操作的， a & 0xFF 中的 a 类型提升，按位和0xFF(默认是int型的) 进行与操作
		System.out.println(b);
		System.out.println(c);
		System.out.println(a>>>24);
	}
}
