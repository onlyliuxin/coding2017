package testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.basic.Iterator;
import com.basic.LinkedList;

public class TestLinkedList
{

	/*
	 * test add() and size()
	 * */
	@Test 
	public void testAdd() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(3);
		linkedList.add("hello world");
		assertTrue(linkedList.size() == 2);
	}


	@Test
	public void testGet() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(3);
		linkedList.add("hello world");
		assertTrue(linkedList.get(0).equals(3));
		assertFalse(linkedList.get(0).equals("hello world"));
		assertTrue(linkedList.get(1).equals("hello world"));
		
		try {
			linkedList.get(-1);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNotNull(e);
			String errorInfo = "Invalid index to get:" + -1 + " out of range: [0," + linkedList.size() + "]";
			assertTrue(e.getMessage().equals(errorInfo));
//			System.out.println(e.getMessage());
		}
	}


	@Test
	public void testRemove() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(3);
		linkedList.add("hello world");
		linkedList.add(4);
		linkedList.add("Leon Deng");

		try {
			linkedList.remove(-1);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNotNull(e);
			String errorInfo = "Invalid index to remove:" + -1 + " out of range: [0," + linkedList.size() + "]";
			assertTrue(e.getMessage().equals(errorInfo));
		}
		
		try {
			linkedList.remove(10);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNotNull(e);
			String errorInfo = "Invalid index to remove:" + 10 + " out of range: [0," + linkedList.size() + "]";
			assertTrue(e.getMessage().equals(errorInfo));
		}
		
		Object o = null; 
		try {
			o = linkedList.remove(0);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNull(e);		
		}	
		assertTrue(o.equals(3));
		
		try {
			o = linkedList.remove(2);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNull(e);		
		}	
//		System.out.println(o);
		assertTrue(o.equals("Leon Deng"));
	}


	@Test
	public void testAddFirst() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(3);
		linkedList.add("hello world");
		
		linkedList.addFirst("Leon Deng");
		assertTrue(linkedList.get(0).equals("Leon Deng"));
		assertTrue(linkedList.get(1).equals(3));
	}

	@Test
	public void testAddLast() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(3);
		linkedList.add("hello world");
		
		linkedList.addLast("Leon Deng");
		assertTrue(linkedList.get(linkedList.size() - 1).equals("Leon Deng"));
	}
	
	@Test
	public void testRemoveFirst() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(3);
		linkedList.add("hello world");
		
		Object o = linkedList.removeFirst();
		assertTrue(o.equals(3));
		o = linkedList.removeFirst();
		assertTrue(o.equals("hello world"));
	}
	
	@Test
	public void testRemoveLast() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(3);
		linkedList.add("hello world");
		linkedList.add("Leon Deng");
		
		Object o = linkedList.removeLast();
		assertTrue(o.equals("Leon Deng"));
		o = linkedList.removeLast();
		assertTrue(o.equals("hello world"));
	}
	
	@Test
	public void testInterator() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(3);
		linkedList.add("Leon Deng");

		Iterator it = linkedList.iterator();
		assertTrue(it.hasNext());
		assertTrue(it.next().equals(3));
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("Leon Deng"));
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testReverse() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);

//		Iterator it = linkedList.iterator();
//		for ( ; it.hasNext(); ) {
//			System.out.print(it.next() + " ");
//		}
//		System.out.println();
//		
//		linkedList.reverse();
//		
//		it = linkedList.iterator();
//		for ( ; it.hasNext(); ) {
//			System.out.print(it.next() + " ");
//		}
//		System.out.println();
		
		Object o1 = linkedList.get(0);
		Object o2 = linkedList.get(linkedList.size() - 1);
		
		linkedList.reverse();
		
		Object o3 = linkedList.get(0);
		Object o4 = linkedList.get(linkedList.size() - 1);

		assertEquals(o1, o4);
		assertEquals(o2, o3);
		
		linkedList.reverse();
		Object o5 = linkedList.get(0);
		Object o6 = linkedList.get(linkedList.size() - 1);
		
		assertEquals(o1, o5);
		assertEquals(o2, o6);
	}
	
	
	
}
