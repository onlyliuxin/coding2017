package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.ArrayList;

public class ArrayListTest {
	
	private ArrayList list;

	@Before
	public void setUp() throws Exception {
		list = new ArrayList();
	}

	@Test
	public void testAddObject() {
		list.add(null);
		System.out.println(list);
		list.add(1);list.add(1);list.add(1);list.add(1);list.add(1);
		list.add(1);list.add(1);list.add(1);list.add(1);list.add(2);
		System.out.println(list);
	}

	@Test
	public void testAddIntObject() {
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		System.out.println(list);
		list.add(9, 55);
		System.out.println(list);
	}

	@Test
	public void testGet() {
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		System.out.println(list.get(8));
	}

	@Test
	public void testRemove() {
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		System.out.println(list.remove(9));
		System.out.println(list);
		System.out.println(list.size());
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

}
