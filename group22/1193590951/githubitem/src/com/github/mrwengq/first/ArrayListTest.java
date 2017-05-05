package com.github.mrwengq.first;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {
	ArrayList list = new ArrayList();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		int[] o = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		for (int i = 0; i < list.size(); i++) {
			assertEquals(o[i], o[i]);
		}

	}

	@Test
	public void testAddIntObject() {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(5, 9);
		int[] o = new int[] { 1, 2, 3, 4, 5, 9, 6, 7 };
		for (int i = 0; i < list.size(); i++) {
			assertEquals(o[i], o[i]);
		}
	}

	@Test
	public void testGet() {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		assertEquals(list.get(5), 6);
		assertEquals(list.get(2), 3);
		assertEquals(list.get(4), 5);
		assertEquals(list.get(6), 7);
	}

	@Test
	public void testRemove() {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.remove(3);
		assertEquals(list.get(3), 5);
	}

	@Test
	public void testSize() {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		assertEquals(list.size(), 5);
	}

	@Test
	public void testIterator() {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		Iterator iter = list.iterator();
		int i = 0;
		int[] o = new int[] { 1, 2, 3, 4, 5};
 
		while(iter.hasNext()){
			
			assertEquals(iter.next(),o[i]);
			i++;
		}
	}

}
