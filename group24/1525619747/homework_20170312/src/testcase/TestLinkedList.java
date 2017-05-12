package testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.basic.Iterator;
import com.basic.LinkedList;

public class TestLinkedList
{

	/*
	 * test add() and size()
	 */
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
			String errorInfo = "Invalid index to get:" + -1
					+ " out of range: [0," + linkedList.size() + "]";
			assertTrue(e.getMessage().equals(errorInfo));
			// System.out.println(e.getMessage());
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
			String errorInfo = "Invalid index to remove:" + -1
					+ " out of range: [0," + linkedList.size() + "]";
			assertTrue(e.getMessage().equals(errorInfo));
		}

		try {
			linkedList.remove(10);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNotNull(e);
			String errorInfo = "Invalid index to remove:" + 10
					+ " out of range: [0," + linkedList.size() + "]";
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
		// System.out.println(o);
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

		// Iterator it = linkedList.iterator();
		// for ( ; it.hasNext(); ) {
		// System.out.print(it.next() + " ");
		// }
		// System.out.println();
		//
		// linkedList.reverse();
		//
		// it = linkedList.iterator();
		// for ( ; it.hasNext(); ) {
		// System.out.print(it.next() + " ");
		// }
		// System.out.println();

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

	@Test
	public void testRemoveFirstHalf() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(7);
		linkedList.add(8);

		// Iterator it = linkedList.iterator();
		// for ( ; it.hasNext(); ) {
		// System.out.print(it.next() + " ");
		// }
		// System.out.println();

		linkedList.removeFirstHalf();
		assertTrue(linkedList.get(0).equals(7));

		linkedList = new LinkedList();
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.add(10);

		linkedList.removeFirstHalf();
		assertTrue(linkedList.get(2).equals(10));
	}

	@Test
	public void testRemoveIndexLength() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.add(10);

		linkedList.remove(0, 2);

		// Iterator it = linkedList.iterator();
		// for ( ; it.hasNext(); ) {
		// System.out.print(it.next() + " ");
		// }
		// System.out.println();
		assertTrue(linkedList.get(0).equals(7));

		linkedList = new LinkedList();
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.add(10);

		linkedList.remove(2, 2);
		assertTrue(linkedList.get(0).equals(2));
		assertTrue(linkedList.get(2).equals(10));

	}

	@Test
	public void testGetElements() {
		// 11->101->201->301->401->501->601->701 listB = 1->3->4->6
		LinkedList linkedList = new LinkedList();
		linkedList.add(11);
		linkedList.add(101);
		linkedList.add(201);
		linkedList.add(301);
		linkedList.add(401);
		linkedList.add(501);
		linkedList.add(601);
		linkedList.add(701);

		LinkedList indexLinkedList = new LinkedList();
		indexLinkedList.add(1);
		indexLinkedList.add(3);
		indexLinkedList.add(4);
		indexLinkedList.add(6);

		int[] elements = linkedList.getElements(indexLinkedList);

		// for (int i = 0; i < elements.length; i++) {
		// System.out.print(elements[i] + " ");
		// }
		// System.out.println();

		assertEquals(elements[0], linkedList.get(1));
		assertEquals(elements[1], linkedList.get(3));
		assertEquals(elements[2], linkedList.get(4));
		assertEquals(elements[3], linkedList.get(6));
	}

	@Test
	public void testSubstract() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(11);
		linkedList.add(101);
		linkedList.add(201);
		linkedList.add(301);
		linkedList.add(401);
		linkedList.add(501);
		linkedList.add(601);
		linkedList.add(701);

		LinkedList substractList = new LinkedList();
		substractList.add(11);
		substractList.add(301);
		substractList.add(404);
		substractList.add(501);
		substractList.add(701);

		linkedList.subtract(substractList);

		// Iterator it = linkedList.iterator();
		// for ( ; it.hasNext(); ) {
		// System.out.print(it.next() + " ");
		// }
		// System.out.println();

		assertFalse(linkedList.get(0).equals(11));
		assertTrue(linkedList.get(0).equals(101));
	}

	@Test
	public void testRemoveDuplicateValues() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(4);
		linkedList.add(5);

		linkedList.removeDuplicateValues();

		// Iterator it = linkedList.iterator();
		// for ( ; it.hasNext(); ) {
		// System.out.print(it.next() + " ");
		// }
		// System.out.println();

		assertTrue(linkedList.get(0).equals(1));
		assertTrue(linkedList.get(1).equals(2));
		assertTrue(linkedList.get(4).equals(5));

	}

	@Test
	public void testRemoveRange() {
		LinkedList linkedList = new LinkedList();
		for (int i = 0; i < 10; i++) {
			linkedList.add(i);
		}

		linkedList.removeRange(3, 7);

		// Iterator it = linkedList.iterator();
		// for ( ; it.hasNext(); ) {
		// System.out.print(it.next() + " ");
		// }
		// System.out.println();
		assertTrue(linkedList.get(3).equals(3));
		assertTrue(linkedList.get(4).equals(7));
	}

	@Test
	public void testIntersection() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);

		LinkedList secondList = new LinkedList();
		secondList.add(1);
		secondList.add(3);
		secondList.add(5);
		secondList.add(6);

		LinkedList intersection = linkedList.intersection(secondList);
		// Iterator it = intersection.iterator();
		// for ( ; it.hasNext(); ) {
		// System.out.print(it.next() + " ");
		// }
		// System.out.println();
		assertTrue(intersection.get(0).equals(1));
		assertTrue(intersection.get(1).equals(3));
		assertTrue(intersection.get(2).equals(5));
		assertTrue(intersection.get(3).equals(6));
	}

}
