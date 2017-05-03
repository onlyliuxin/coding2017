package com.coderising.data;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest extends LinkedList {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverse() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFirstHalf() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(11);
		linkedList.add(101);
		linkedList.add(201);
		linkedList.add(301);
		linkedList.removeFirstHalf();
		Assert.assertEquals(linkedList, linkedList);
	}

	@Test
	public void testRemoveIntInt() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(11);
		linkedList.add(101);
		linkedList.add(201);
		linkedList.add(301);
		linkedList.remove(3, 2);
	}

	@Test
	public void testGetElements() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(11);
		linkedList.add(101);
		linkedList.add(201);
		linkedList.add(301);
		linkedList.add(401);
		linkedList.add(501);
		linkedList.add(601);
		linkedList.add(701);
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(6);
		Assert.assertArrayEquals(new int[]{101,301,401,601}, linkedList.getElements(list));
	}

	@Test
	public void testSubtract() {
		
	}

	@Test
	public void testRemoveDuplicateValues() {
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(1);
		list.add(2);
		list.removeDuplicateValues();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Test
	public void testRemoveRange() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(11);
		linkedList.add(12);
		linkedList.add(13);
		linkedList.add(14);
		linkedList.add(16);
		linkedList.add(16);
		linkedList.add(19);
		linkedList.removeRange(10, 14);
	}

	@Test
	public void testIntersection() {
		LinkedList list1 = new LinkedList();
		list1.add(1);
		list1.add(6);
		list1.add(7);
		
		LinkedList list2 = new LinkedList();
		list2.add(2);
		list2.add(5);
		list2.add(6);
		
		LinkedList newList = list1.intersection(list2);
		for (int i = 0; i < newList.size(); i++) {
			System.out.println(newList.get(i));
		}
	}
}
