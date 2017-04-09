package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestArrayList {
	@Before
	public void setUp() throws Exception {
		
		
	}

	@Test
	public void testAdd() {
		List list = new ArrayList();
		list.add(0);
		list.add(2);
		assertEquals("[0, 2]", list.toString());
	}

	@Test
	public void testAddByIndex() {
		List list = new ArrayList();
		list.add(0);
		list.add(1);
		list.add(2);
		
		list.add(0, -1);
		assertEquals("[-1, 0, 1, 2]", list.toString());
		list.add(1, 5);
		assertEquals("[-1, 5, 0, 1, 2]", list.toString());
		list.add(5, 8);
		assertEquals("[-1, 5, 0, 1, 2, 8]", list.toString());
		
		try {
			list.add(7, 9);
			fail("检查是否超限失败！");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		
		
	}

	@Test
	public void testGet() {
		List list = new ArrayList();
		list.add(0);
		list.add(1);
		list.add(2);
		assertEquals(0, list.get(0));
		assertEquals(2, list.get(2));
		try {
			list.get(8);
			fail("检查是否超限失败！");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		
	}

	@Test
	public void testRemove() {
		List list = new ArrayList();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.remove(0);
		assertEquals("[1, 2, 3]",list.toString() );
//		assertEquals(2, list.get(3));
		list.remove(2);
		assertEquals("[1, 2]",list.toString() );
		try {
			list.remove(9);
			fail("检查是否超限失败！");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
	}

}
