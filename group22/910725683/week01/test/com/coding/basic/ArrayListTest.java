package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayListTest {

	ArrayList al = new ArrayList();

	@Test
	public void testAddObject() {
		for (int i=1;i<=5;i++){
			al.add(i);
		}
	}

	@Test
	public void testAddIntObject() {
		testAddObject();
		al.add(3, 12);
		System.out.println("inser index 3 value 12 : "+al.toString());
	}

	@Test
	public void testGet() {
		testAddObject();
		System.out.println("get index 4 : "+al.get(4));
	}

	@Test
	public void testRemove() {
		testAddObject();
		System.out.println("remove index 3 : "+al.remove(3));
	}

	@Test
	public void testSize() {
		testAddObject();
		System.out.println("get size : "+al.size());
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
