package org.coding.three.list.impl;

import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class LinkedListTest {
	private LinkedList linkedList;

	@Before
	public void setUp() throws Exception {
		linkedList = new LinkedList();
	}

	@After
	public void tearDown() throws Exception {
		linkedList = null;
	}

	@Test
	public void testAddObject() {
		int expected = 0;
		int actual = linkedList.size();
		Assert.assertEquals(expected, actual);

		linkedList.add(1);
		expected = 1;
		actual = linkedList.size();
		Assert.assertEquals(expected, actual);

		linkedList.add(2);
		expected = 2;
		actual = linkedList.size();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testAddIntObject() {
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		
		linkedList.add(0, 4);
		int expected = 4;
		int actual = (int) linkedList.get(0);
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(4, linkedList.size());
		
		linkedList.add(2, 5);
		Assert.assertEquals(5, linkedList.size());
		expected = 5;
		actual = (int) linkedList.get(2);
		Assert.assertEquals(expected, actual);
		
	}

	@Test
	public void testGet() {
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		
		int expected = 1;
		int actual = (int) linkedList.get(0);
		Assert.assertEquals(expected, actual);
		
		expected = 2;
		actual = (int) linkedList.get(1);
		Assert.assertEquals(expected, actual);
		
		expected = 3;
		actual = (int) linkedList.get(2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testRemoveInt() {
		linkedList.add(1);
		
		int v = (int) linkedList.remove(0);
		Assert.assertEquals(1, v);
		Assert.assertEquals(0, linkedList.size());
		
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		
		v = (int) linkedList.remove(1);
		Assert.assertEquals(2, v);
		Assert.assertEquals(2, linkedList.size());
		
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);
		
		v = (int) linkedList.remove(linkedList.size() - 1);
		Assert.assertEquals(6, v);
		Assert.assertEquals(4, linkedList.size());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(0, linkedList.size());
		linkedList.add(1);
		Assert.assertEquals(1, linkedList.size());
		linkedList.remove(0);
		Assert.assertEquals(0, linkedList.size());
	}

	@Test
	public void testAddFirst() {
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);
		linkedList.addFirst(1);
		Assert.assertEquals(4, linkedList.size());
		Assert.assertEquals(1, linkedList.get(0));
	}

	@Test
	public void testAddLast() {
		linkedList.addLast(1);
		Assert.assertEquals(1, linkedList.size());
		Assert.assertEquals(1, linkedList.get(0));
		linkedList.addLast(2);
		Assert.assertEquals(2, linkedList.size());
		Assert.assertEquals(2, linkedList.get(1));
	}

	@Test
	public void testRemoveFirst() {
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);
		int v = (int) linkedList.removeFirst();
		Assert.assertEquals(2, linkedList.size());
		Assert.assertEquals(4, v);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveFirstException() {
		linkedList.removeFirst();
	}

	@Test
	public void testRemoveLast() {
		linkedList.add(4);
		int v = (int) linkedList.removeLast();
		Assert.assertEquals(0, linkedList.size());
		Assert.assertEquals(4, v);
		
		linkedList.add(5);
		linkedList.add(6);
		v = (int) linkedList.removeLast();
		Assert.assertEquals(1, linkedList.size());
		Assert.assertEquals(6, v);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveLastException() {
		linkedList.removeLast();
	}
	@Test
	public void testIterator() {
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		Iterator<?> it = linkedList.iterator();
		int expected = 1;
		while(it.hasNext()) {
			Object v = it.next();
			Assert.assertEquals(expected++, v);
		}
		
	}

	@Test
	public void testIteratorRemove() {
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		Iterator<?> it = linkedList.iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
		Assert.assertEquals(0, linkedList.size());
		
	}
	@Test
	public void testReverse() {
		linkedList.add(3);
		linkedList.add(7);
		linkedList.add(10);
		linkedList.reverse();
		Assert.assertEquals(10, linkedList.get(0));
		Assert.assertEquals(7, linkedList.get(1));
		Assert.assertEquals(3, linkedList.get(2));
	}
	
	@Test
	public void testReverse2() {
		linkedList.add(3);
		linkedList.reverse();
		Assert.assertEquals(3, linkedList.get(0));
	}
	
	@Test
	public void testReverse3() {
		linkedList.add(3);
		linkedList.add(7);
		linkedList.reverse();
		Assert.assertEquals(7, linkedList.get(0));
		Assert.assertEquals(3, linkedList.get(1));
	}



	@Test
	public void testRemoveFirstHalf() {
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.removeFirstHalf();
		Assert.assertEquals(7, linkedList.get(0));
		Assert.assertEquals(8, linkedList.get(1));
	}

	@Test
	public void testRemoveFirstHalf2() {
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.add(10);
		linkedList.removeFirstHalf();
		Assert.assertEquals(7, linkedList.get(0));
		Assert.assertEquals(8, linkedList.get(1));
		Assert.assertEquals(10, linkedList.get(2));
	}

	@Test
	public void testRemoveIntInt() {
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.add(10);
		linkedList.remove(1, 2);
		Assert.assertEquals(3, linkedList.size());
		Assert.assertEquals(2, linkedList.get(0));
		Assert.assertEquals(8, linkedList.get(1));
		Assert.assertEquals(10, linkedList.get(2));
	}
	

	@Test
	public void testRemoveIntIntFull() {
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.add(10);
		linkedList.remove(0, 10);
		Assert.assertEquals(0, linkedList.size());
	}
	

	@Test
	public void testRemoveIntIntHead() {
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.add(10);
		linkedList.remove(0, 2);
		Assert.assertEquals(3, linkedList.size());
		Assert.assertEquals(7, linkedList.get(0));
		Assert.assertEquals(8, linkedList.get(1));
		Assert.assertEquals(10, linkedList.get(2));
	}

	@Test
	public void testGetElements() {
//		11->101->201->301->401->501->601->701
		linkedList.add(11);
		linkedList.add(101);
		linkedList.add(201);
		linkedList.add(301);
		linkedList.add(401);
		linkedList.add(501);
		linkedList.add(601);
		linkedList.add(701);
//		1->3->4->6
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(6);
		int[] actuals = linkedList.getElements(list );
		int[] expecteds = {101,301,401,601};
		Assert.assertArrayEquals(expecteds, actuals);
	}


	@Test
	public void testGetElements2() {
//		11->101->201->301->401->501->601->701
		linkedList.add(11);
		linkedList.add(101);
		linkedList.add(201);
		linkedList.add(301);
		linkedList.add(401);
		linkedList.add(501);
		linkedList.add(601);
		linkedList.add(701);
//		1->3->4->20
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(20);
		int[] actuals = linkedList.getElements(list );
		int[] expecteds = {101,301,401};
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testSubtract() {
		linkedList.add(11);
		linkedList.add(101);
		linkedList.add(201);
		linkedList.add(301);
		linkedList.add(401);
		linkedList.add(501);
		linkedList.add(601);
		linkedList.add(701);
		LinkedList list = new LinkedList();
		list.add(11);
		list.add(201);
		list.add(501);
		linkedList.subtract(list );
		Assert.assertEquals(5, linkedList.size());
		
	}
	
	@Test
	public void testSubtract2() {
		linkedList.add(11);
		linkedList.add(101);
		LinkedList list = new LinkedList();
		list.add(11);
		list.add(201);
		list.add(501);
		linkedList.subtract(list );
		Assert.assertEquals(1, linkedList.size());
		
	}


	@Test
	public void testRemoveDuplicateValues() {
		linkedList.add(11);
		linkedList.add(101);
		linkedList.add(201);
		linkedList.add(201);
		linkedList.add(201);
		linkedList.add(301);
		linkedList.add(301);
		linkedList.add(401);
		Assert.assertEquals(8, linkedList.size());
		linkedList.removeDuplicateValues();
		Assert.assertEquals(5, linkedList.size());
		Assert.assertEquals(301, linkedList.get(linkedList.size() - 2));
		Assert.assertEquals(201, linkedList.get(linkedList.size() - 3));
		
	}

	@Test
	public void testRemoveRange() {
		linkedList.add(1);
		linkedList.add(3);
		linkedList.add(5);
		linkedList.removeRange(4, 6);
		Assert.assertEquals(2, linkedList.size());
		
	}

	@Test
	public void testRemoveRange2() {
		linkedList.add(1);
		linkedList.add(3);
		linkedList.add(5);
		linkedList.removeRange(0, 6);
		Assert.assertEquals(0, linkedList.size());
		
	}

	@Test
	public void testRemoveRange3() {
		linkedList.add(1);
		linkedList.add(3);
		linkedList.add(5);
		linkedList.removeRange(3, 5);
		Assert.assertEquals(3, linkedList.size());
		
	}

	@Test
	public void testRemoveRange4() {
		linkedList.add(1);
		linkedList.add(3);
		linkedList.add(5);
		linkedList.removeRange(1, 3);
		Assert.assertEquals(3, linkedList.size());
		
	}

	@Test
	public void testRemoveRange5() {
		linkedList.add(1);
		linkedList.add(3);
		linkedList.add(5);
		linkedList.add(6);
		linkedList.removeRange(3, 5);
		Assert.assertEquals(4, linkedList.size());
		
	}

	@Test
	public void testIntersection() {
		linkedList.add(1);
		linkedList.add(3);
		linkedList.add(5);
		linkedList.add(6);
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(3);
		LinkedList newList = linkedList.intersection(list );
		Assert.assertEquals(2, newList.size());
		Assert.assertEquals(1, newList.get(0));
		Assert.assertEquals(3, newList.get(1));
	}

	@Test
	public void testIntersection2() {
		linkedList.add(1);
		linkedList.add(3);
		linkedList.add(5);
		linkedList.add(6);
		LinkedList list = new LinkedList();
		list.add(10);
		list.add(13);
		LinkedList newList = linkedList.intersection(list );
		Assert.assertEquals(0, newList.size());
	}

	@Test
	public void testIntersection3() {
		linkedList.add(3);
		linkedList.add(5);
		linkedList.add(6);
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(2);
		LinkedList newList = linkedList.intersection(list );
		Assert.assertEquals(0, newList.size());
	}

}
