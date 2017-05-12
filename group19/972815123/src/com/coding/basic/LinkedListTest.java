package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class LinkedListTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRemove(){
		LinkedList ll = new LinkedList();
		ll.add("1");
		ll.add("2");
		ll.add("3");
		ll.add("4");
		ll.remove(3);
		System.out.println(ll.toString());
		Assert.assertEquals("1,2,3,4", ll.toString());
	}
	
	@Test
	public void testToString() {
		LinkedList ll = new LinkedList();
		ll.add("1");
		ll.add("2");
		ll.add("3");
		ll.add("4");
		System.out.println(ll.toString());
		Assert.assertEquals("1,2,3,4", ll.toString());
	}

	@Test
	public void testReverse() {
		LinkedList ll = new LinkedList();
		ll.add("4");
		ll.add("3");
		ll.add("2");
		ll.add("1");
		System.out.println(ll.toString());
		ll.reverse();
		System.out.println(ll.toString());
		Assert.assertEquals("1,2,3,4", ll.toString());
	}

	@Test
	public void testRemoveFirstHalf() {
		LinkedList ll = new LinkedList();
		ll.add("4");
		ll.add("3");
		ll.add("2");
		ll.add("1");
		ll.add("0");
		System.out.println(ll.toString());
		ll.removeFirstHalf();
		System.out.println(ll.toString());
		Assert.assertEquals("2,1,0", ll.toString());
	}

	@Test
	public void testRemoveIntInt() {
		LinkedList ll = new LinkedList();
		ll.add("1");
		ll.add("2");
		ll.add("3");
		ll.add("4");
		ll.add("5");
		ll.add("6");
		ll.add("7");
		ll.add("8");
		ll.add("9");
		ll.remove(3,2);
		System.out.println(ll.toString());
		Assert.assertEquals("1,2,3,6,7,8,9", ll.toString());
	}

	@Test
	public void testGetElements() {
		LinkedList ll = new LinkedList();
		ll.add("0");
		ll.add("1");
		ll.add("2");
		ll.add("3");
		ll.add("4");
		ll.add("5");
		ll.add("6");
		ll.add("7");
		ll.add("8");
		ll.add("9");
		LinkedList pointerList = new LinkedList();
		pointerList.add(1);
		pointerList.add(3);
		pointerList.add(5);
		pointerList.add(7);
		Object[] result = ll.getElements(pointerList);
		for(Object o : result){
			System.out.print((String)o);
			System.out.print(",");
		}
		Assert.assertEquals((String)result[3], "7");
	}

	@Test
	public void testSubtract() {
		LinkedList ll = new LinkedList();
		ll.add(0);
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(4);
		ll.add(5);
		ll.add(6);
		ll.add(7);
		ll.add(8);
		LinkedList pointerList = new LinkedList();
		pointerList.add(1);
		pointerList.add(3);
		pointerList.add(5);
		pointerList.add(7);
		ll.subtract(pointerList);
		System.out.println(ll.toString());
		Assert.assertEquals("0,2,4,6,8", ll.toString());
	}

	@Test
	public void testRemoveDuplicateValues() {
		LinkedList ll = new LinkedList();
		ll.add(0);
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(3);
		ll.add(3);
		ll.add(4);
		ll.add(5);
		ll.add(5);
		ll.add(6);
		ll.add(7);
		ll.add(8);
		ll.removeDuplicateValues();
		System.out.println(ll.toString());
		Assert.assertEquals("0,1,2,3,4,5,6,7,8", ll.toString());
	}

	@Test
	public void testRemoveRange() {
		LinkedList ll = new LinkedList();
		ll.add(0);
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(3);
		ll.add(3);
		ll.add(4);
		ll.add(5);
		ll.add(5);
		ll.add(6);
		ll.add(7);
		ll.add(8);
		ll.removeRange(3,7);
		System.out.println(ll.toString());
		Assert.assertEquals("0,1,2,8", ll.toString());
	}

	@Test
	public void testIntersection() {
		LinkedList ll = new LinkedList();
		ll.add(0);
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(4);
		ll.add(5);
		ll.add(6);
		ll.add(7);
		ll.add(8);
		LinkedList pointerList = new LinkedList();
		pointerList.add(1);
		pointerList.add(5);
		pointerList.add(3);
		pointerList.add(11);
		pointerList.add(7);
		ll = ll.intersection(pointerList);
		System.out.println(ll.toString());
		Assert.assertEquals("1,3,5,7", ll.toString());
	}

}
