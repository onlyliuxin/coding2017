package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayListTest {

	@Test
	public void testAddObject() {
		ArrayList list = new ArrayList();
		list.add(5);
		assertEquals(5, list.get(0));
		
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);
		assertEquals(1, list.get(4));
		
		// size equals to 5
		assertEquals(5, list.size());
	}

	@Test
	public void testAddIntObject() {
		ArrayList list = new ArrayList();
		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);
		
		// change position 2 element
		list.add(2, 10);
		
		// pos 2 has 10
		assertEquals(10, list.get(2));
		
		// last element is 1
		assertEquals(1, list.get(5));
		
		// size is 6
		assertEquals(6, list.size());
	}

	@Test
	public void testRemove() {
		ArrayList list = new ArrayList();
		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);
		
		Object removed = list.remove(2);
		assertEquals(removed, 3);
		
		assertEquals(2, list.get(2));
		assertEquals(4, list.size());
		assertEquals(null, list.get(4));
		
		list.add(6);
		assertEquals(6, list.get(4));
	}

	@Test
	public void testIterator() {
		ArrayList list = new ArrayList();
		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);
		
		Iterator it = list.iterator();
		if(it.hasNext()) {
			assertEquals(5, it.next());
			assertEquals(4, it.next());
		}
		
	}	
	
}
