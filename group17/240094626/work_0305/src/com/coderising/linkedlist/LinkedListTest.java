package com.coderising.linkedlist;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class LinkedListTest {
	LinkedList<Object> list = new LinkedList<Object>(); 
	@Before
	public void setUp() throws Exception {
		list = new LinkedList<Object>();
	}

	@After
	public void tearDown() throws Exception {
		list = new LinkedList<Object>();
	}

	@Test
	public void testReverse() {
		list.add(3);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(9);
		list.add(8);
		Assert.assertEquals(3, list.get(0));
		Assert.assertEquals(5, list.get(1));
		Assert.assertEquals(6, list.get(2));
		Assert.assertEquals(7, list.get(3));
		Assert.assertEquals(9, list.get(4));
		Assert.assertEquals(8, list.get(5));
		list.reverse();
		Assert.assertEquals(8, list.get(0));
		Assert.assertEquals(9, list.get(1));
		Assert.assertEquals(7, list.get(2));
		Assert.assertEquals(6, list.get(3));
		Assert.assertEquals(5, list.get(4));
		Assert.assertEquals(3, list.get(5));
	}
	@Test
	public void testRemove(){
		list.add(3);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(9);
		list.remove(0);
		Assert.assertEquals(5, list.get(0));
		list.remove(1);
		Assert.assertEquals(7, list.get(1));
	}
	@Test
	public void testRemoveFirstHalf(){
		list.add(3);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(9);
		list.add(8);
		list.removeFirstHalf();
		Assert.assertEquals(7, list.get(0));
		Assert.assertEquals(9, list.get(1));
		Assert.assertEquals(8, list.get(2));
	}
	
	@Test
	public void testGetElements(){
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(701);
		list.add(801);
		list.add(901);
		list.add(1001);
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.add(0);
		list1.add(2);
		list1.add(4);
		list1.add(6);
		int[] a = list.getElements(list1);
		Assert.assertEquals(301, a[0]);
		Assert.assertEquals(501, a[1]);
		Assert.assertEquals(701, a[2]);
		Assert.assertEquals(901, a[3]);
	}
	@Test
	public void testSubtract(){
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(701);
		list.add(801);
		list.add(901);
		list.add(1001);
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.add(401);
		list1.add(701);
		list1.add(801);
		list.subtract(list1);
		Assert.assertEquals(301, list.get(0));
		Assert.assertEquals(501, list.get(1));
		Assert.assertEquals(901, list.get(3));
	}
	@Test
	public void testRemoveDuplicateValues(){
		list.add(301);
		list.add(401);
		list.add(401);
		list.add(601);
		list.add(601);
		list.add(801);
		list.add(901);
		list.add(1001);
		list.removeDuplicateValues();
		Assert.assertEquals(6, list.size);
	}
	
	@Test
	public void testRemoveRange(){
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(801);
		list.add(901);
		list.removeRange(500, 600);
		Assert.assertEquals(301, list.get(0));
		Assert.assertEquals(401, list.get(1));
		Assert.assertEquals(601, list.get(2));
	}

	@Test
	public void testIntersection(){
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(801);
		list.add(901);
		LinkedList<Object> list1 = new LinkedList<Object>();
		list1.add(401);
		list1.add(601);
		list1.add(701);
		LinkedList<Object> c = list.intersection(list1);
		Assert.assertEquals(401,c.get(0));
		Assert.assertEquals(601,c.get(1));
	}
	public static void main(String[] args) {
		LinkedList<Object> list = new LinkedList<Object>();
		list.add(3);
		list.add(5);
		list.add(6);
		list.add(8);
//		list.reverse();
		java.util.LinkedList list1 =null;
	}
}
