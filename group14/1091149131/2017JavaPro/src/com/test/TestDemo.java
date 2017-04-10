package com.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestDemo {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String str="123bbb45ddd5ccc567ddd012";
		String[] data=str.split("[0-9]+");
		System.out.println("共拆分"+data.length);
		for (String s : data) {
			System.out.println(s);
		}
		System.out.println("====");
		fail("Not yet implemented");
	}

}
