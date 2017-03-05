package com.qsq.study;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrayListTest {

	@Test
	public void testIsEmpty() {
		ArrayList<Integer> list = new ArrayList<>();
		assertEquals(true, list.isEmpty());
		list.add(1);
		assertEquals(false, list.isEmpty());
	}

	@Test
	public void testSize() {
		ArrayList<Integer> list = new ArrayList<>();
		assertEquals(0, list.size());
		list.add(1);
		assertEquals(1, list.size());
		list.add(2);
		assertEquals(2, list.size());
		for (int i=3; i<=20; i++) {
			list.add(i);
		}
		assertEquals(20, list.size());
		
	}
	@Test
	public void testAdd() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testRemove() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(3, list.size());
		list.remove(1);
		assertEquals(2, list.size());
		list.remove(1);
		assertEquals(1, list.size());
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testGet() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		assertEquals(1, (int)list.get(0));
		assertEquals(2, (int)list.get(1));
	}

	@Test
	public void TestSet() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		assertEquals(1, (int)list.get(0));
		list.set(0, 2);
		assertEquals(2, (int)list.get(0));
	}
}
