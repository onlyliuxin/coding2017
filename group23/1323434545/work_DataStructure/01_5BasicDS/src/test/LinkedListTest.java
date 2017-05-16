package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Iterator;
import main.LinkedList;

public class LinkedListTest {
	private LinkedList list;
	
	@Before
	public void init(){
		list = new LinkedList();
	}

	@Test
	public void testAddObject() {
		list.add(0);
		list.add(1);
		list.add(2);
		assertEquals(0, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
	}

	@Test
	public void testAddIntObject() {
		list.add("b");
		list.add("d");
		list.add("e");
		list.add(0, "a");
		list.add(2, "c");
		list.add(5, "f");
		assertEquals("a", list.get(0));
		assertEquals("c", list.get(2));
		assertEquals("f", list.get(5));
		try {
			list.add(-1, "hh");
			fail("-1 cann't be index");
		} catch (Exception e) {
		}

		try {
			list.add(7, "xx");
			fail("index should <=size");
		} catch (Exception e) {}
	}

	@Test
	public void testRemove() {
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		assertEquals("d", list.remove(3));
		assertEquals("a", list.remove(0));
		assertEquals("c", list.remove(1));
		try {
			list.remove(-1);
			fail("-1 cann't be index");
		} catch (Exception e) {
		}

		try {
			list.remove(1);
			fail("index should <=size");
		} catch (Exception e) {
		}

	}

	@Test
	public void testGet() {

		list.add("a");
		assertEquals("a", list.get(0));

		try {
			list.get(-1);
			fail("-1 cann't be index");
		} catch (Exception e) {
		}

		try {
			list.get(1);
			fail("index should <=size");
		} catch (Exception e) {
		}
	}

	@Test
	public void testSize() {
		list.add("a");
		assertEquals(1, list.size());
		list.remove(0);
		assertEquals(0, list.size());
	}

	@Test
	public void testIterator() {
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		Iterator i = list.iterator();
		assertTrue(i.hasNext());
		assertEquals("a", i.next());
		assertTrue(i.hasNext());
		assertEquals("b", i.next());
		assertTrue(i.hasNext());
		assertEquals("c", i.next());
		assertTrue(i.hasNext());
		assertEquals("d", i.next());
		assertFalse(i.hasNext());

		try {
			i.next();
			fail("index should <=size");
		} catch (Exception e) {
		}
	}

}
