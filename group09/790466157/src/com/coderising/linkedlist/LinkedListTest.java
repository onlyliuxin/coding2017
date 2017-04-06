package com.coderising.linkedlist;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	
	LinkedList linkedList;

	@Before
	public void setUp() throws Exception {
		linkedList = new LinkedList();
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
		linkedList.add(20);
		linkedList.reverse();
		Assert.assertEquals(20, linkedList.get(0));
		Assert.assertEquals(3, linkedList.get(1));
		Assert.assertEquals(7, linkedList.get(2));
		Assert.assertEquals(10, linkedList.get(3));
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
		Assert.assertEquals(2, linkedList.size());
		linkedList.addFirst(5);
		linkedList.addFirst(2);
		linkedList.addLast(10);
		linkedList.removeFirstHalf();
		Assert.assertEquals(7, linkedList.get(0));
		Assert.assertEquals(8, linkedList.get(1));
		Assert.assertEquals(10, linkedList.get(2));	
		Assert.assertEquals(3, linkedList.size());
	}

	@Test
	public void testRemoveIntInt() {
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.add(10);
		linkedList.remove(1,3);
		Assert.assertEquals(2, linkedList.get(0));
		Assert.assertEquals(10, linkedList.get(1));
		Assert.assertEquals(2, linkedList.size());
	}

	@Test
	public void testGetElements() {
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
		Assert.assertArrayEquals(new int[]{101,301,401,601},linkedList.getElements(list));
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
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(6);
		linkedList.subtract(list);
		Assert.assertEquals(11,linkedList.get(0));
		Assert.assertEquals(201,linkedList.get(1));
		Assert.assertEquals(501,linkedList.get(2));
		Assert.assertEquals(701,linkedList.get(3));
		Assert.assertEquals(4, linkedList.size());
	}

	@Test
	public void testRemoveDuplicateValues() {
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(3);
		linkedList.add(8);
		linkedList.add(8);
		linkedList.add(9);
		linkedList.removeDuplicateValues();
		Assert.assertEquals(1,linkedList.get(0));
		Assert.assertEquals(2,linkedList.get(1));
		Assert.assertEquals(3,linkedList.get(2));
		Assert.assertEquals(8,linkedList.get(3));
		Assert.assertEquals(9,linkedList.get(4));
		Assert.assertEquals(5, linkedList.size());
	}

	@Test
	public void testRemoveRange() {
		linkedList.add(11);
		linkedList.add(101);
		linkedList.add(201);
		linkedList.add(301);
		linkedList.add(401);
		linkedList.add(501);
		linkedList.add(601);
		linkedList.add(701);
		linkedList.removeRange(100, 600);
		Assert.assertEquals(11,linkedList.get(0));
		Assert.assertEquals(601,linkedList.get(1));
		Assert.assertEquals(701,linkedList.get(2));
		Assert.assertEquals(3, linkedList.size());
	}

	@Test
	public void testIntersection() {
		linkedList.add(11);
		linkedList.add(101);
		linkedList.add(201);
		linkedList.add(301);
		linkedList.add(401);
		linkedList.add(501);
		linkedList.add(601);
		linkedList.add(701);
		LinkedList list = new LinkedList();
		list.add(10);
		list.add(101);
		list.add(301);
		list.add(333);
		list.add(401);
		list.add(666);
		LinkedList newList = linkedList.intersection(list);
		Assert.assertEquals(101,newList.get(0));
		Assert.assertEquals(301,newList.get(1));
		Assert.assertEquals(401,newList.get(2));
		Assert.assertEquals(3, newList.size());
	}

}
