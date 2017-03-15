package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.first.List;
import com.coding.basic.first.impl.ArrayList;

public class ArrayListTest {

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testSize() {
		List  list = new ArrayList (10);
		list.add(2);
		list.add(1);
		int str = (int)list.remove(1);
		assertEquals(1, str);
		assertEquals(1, list.size());
	}

	@Test
	public void testAddObject() {
		List  list = new ArrayList (10);
		list.add(1);
		list.add(3);
		list.add(2,2);
		
		assertEquals(2, list.get(2));
	}


	@Test
	public void testGet() {
		List  list = new ArrayList (10);
		list.add(1);
		list.add(3);
		list.add(0,2);
		assertEquals(2, list.get(0));
	}

	@Test
	public void testRemove() {
		List  list = new ArrayList (10);
		list.add(1);
		list.add(2);
		list.remove(1);
		assertEquals(1, list.get(0));
		assertEquals(1, list.size());
	}

}
