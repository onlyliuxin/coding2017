package com.coderising.jvm.test;

public class HeapOOMTest {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		byte[] buffer = new byte[1024*1024*1024];
	}
}
