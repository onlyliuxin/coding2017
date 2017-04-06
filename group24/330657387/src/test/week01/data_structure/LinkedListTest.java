package test.week01.data_structure;

import static org.junit.Assert.*;
import main.week01.data_structure.LinkedList;
import main.week01.data_structure.LinkedList.LinkedListIterator;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	
	private LinkedList list;

	@Before
	public void setUp() throws Exception {
		list = new LinkedList();
	}

	@Test
	public void testGet() {
		list.add("A");
		list.add("B");
		list.add(0, "C");
		assertEquals("C", list.get(0));
	}

	@Test
	public void testRemove() {
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add(0, "E");
		assertEquals("E", list.remove(0));
		assertEquals("D", list.remove(list.size()-1));
		assertEquals(3, list.size());
	}

	@Test
	public void testIterator() {
		LinkedListIterator iter = list.iterator();
		list.add("A");
		list.add("B");
		list.add(0, "C");
		assertTrue(iter.hasNext());
		assertEquals("C", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("A", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("B", iter.next());
		assertFalse(iter.hasNext());
	}

}
