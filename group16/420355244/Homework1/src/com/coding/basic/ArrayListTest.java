package com.coding.basic;

import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {

	private static ArrayList arrayList = new ArrayList();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddObject() {
		for(int i = 0 ;i < 150; i++){
			arrayList.add("aaa");
		}
		System.out.println(arrayList);
		System.out.println(arrayList.size());
	}

	@Test
	public void testAddIntObject() {
		arrayList.add("aaa");
		arrayList.add("bbb");
		arrayList.add("ccc");
		arrayList.add("ddd");
		arrayList.add(1,"eee");
		System.out.println(arrayList);
	}

	@Test
	public void testGet() {
		arrayList.add("aaa");
		arrayList.add("bbb");
		arrayList.add("ccc");
		arrayList.add("ddd");
		Object object = arrayList.get(0);
		System.out.println(object);
	}

	@Test
	public void testRemove() {
		arrayList.add("aaa");
		arrayList.add("bbb");
		arrayList.add("ccc");
		arrayList.add("ddd");
		arrayList.remove(0);
		System.out.println(arrayList);
	}

	@Test
	public void testSize() {
		arrayList.add("aaa");
		arrayList.add("bbb");
		arrayList.add("ccc");
		arrayList.add("ddd");
		System.out.println(arrayList.size());
	}

}
