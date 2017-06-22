package com.coding.basic;

import org.junit.Test;

import com.coding.basic.array.ArrayList;

public class TestArrayList {
	@Test
	public void testAdd(){
		List list = new ArrayList();
		for (int i = 0; i < 11; i++) {
			list.add(i);
		}
		System.out.println(list.size());
	}
}
