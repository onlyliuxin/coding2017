package com.coderising.array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	LinkedList list1 = null;
	LinkedList list2 = null;
	
	@Before
	public void setUp() throws Exception {
		list1 = new LinkedList();
		list2 = new LinkedList();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverse() {
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.reverse();
		list2.add(3);
		list2.add(2);
		list2.add(1);
		list2.add(0);
		Assert.assertEquals(list2.toString(), list1.toString());
	}

	@Test
	public void testRemoveFirstHalf() {
		list1.add(2);
		list1.add(5);
		list1.add(7);
		list1.add(8);
		list1.add(10);
		list1.removeFirstHalf();
		list2.add(7);
		list2.add(8);
		list2.add(10);
		Assert.assertEquals(list2.toString(), list1.toString());
	}

	@Test
	public void testRemoveIntInt() {
		list1.add(2);
		list1.add(5);
		list1.add(7);
		list1.add(8);
		list1.add(10);
		list1.remove(0, 2);
		list2.add(7);
		list2.add(8);
		list2.add(10);
		Assert.assertEquals(list2.toString(), list1.toString());
	}

	@Test
	public void testGetElements() {
		list1.add(001);
		list1.add(101);
		list1.add(201);
		list1.add(301);
		list1.add(401);
		list1.add(501);
		list1.add(601);
		list2.add(1);
		list2.add(3);
		list2.add(4);
		list2.add(6);
		int[] array  = {101,301,401,601}; 
		int[] arr = list1.getElements(list2);
		Assert.assertArrayEquals(arr, array);
	}

	@Test
	public void testSubtract() {
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		list1.add(5);
		list1.add(6);
		list1.add(7);
		LinkedList list3 = new LinkedList();
		list3.add(1);
		list3.add(4);
		list3.add(7);
		list1.subtract(list3);
		list2.add(2);
		list2.add(3);
		list2.add(5);
		list2.add(6);
		Assert.assertEquals(list2.toString(), list1.toString());
	}

	@Test
	public void testRemoveDuplicateValues() {
		list1.add(1);
		list1.add(1);
		list1.add(3);
		list1.add(5);
		list1.add(7);
		list1.add(7);
		list1.add(7);
		list1.removeDuplicateValues();
		list2.add(1);
		list2.add(3);
		list2.add(5);
		list2.add(7);
		Assert.assertEquals(list2.toString(), list1.toString());
	}

	@Test
	public void testRemoveRange() {
		list1.add(3);
		list1.add(4);
		list1.add(5);
		list1.add(6);
		list1.add(8);
		list1.removeRange(3,8);
		list2.add(3);
		list2.add(8);
		Assert.assertEquals(list2.toString(), list1.toString());
	}

	@Test
	public void testIntersection() {
		list1.add(5);
		list1.add(7);
		list2.add(6);
		list2.add(8);
		LinkedList newList = list1.intersection(list2);
		LinkedList list3 = new LinkedList();
		list3.add(5);
		list3.add(6);
		list3.add(7);
		list3.add(8);
		Assert.assertEquals(list3.toString(), newList.toString());
	}

}
