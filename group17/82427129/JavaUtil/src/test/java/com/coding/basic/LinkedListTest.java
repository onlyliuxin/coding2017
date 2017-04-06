package com.coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	private LinkedList<String> l =null;
	@Before
	public void setUp() throws Exception {
		l = new LinkedList<String>() ;
	}

	@After
	public void tearDown() throws Exception {
		l = null;
	}

	@Test
	public void testAddE() {
		l.add("1");
		Assert.assertTrue(l.size() == 1);
		l.add("1");
		Assert.assertTrue(l.size() == 2);
		l.add("1");
		Assert.assertTrue(l.size() == 3);
	}

	@Test
	public void testAddIntE() {
		l.add("1");
		l.add("1");
		l.add("1");
		l.add("1");
		l.add(2,"2");
		Assert.assertTrue(l.size() == 5);
	}

	@Test
	public void testAddFirst() {
		l.add("1");
		l.add("1");
		l.add("1");
		l.add("1");
		l.addFirst("first");
		Assert.assertTrue(l.size() == 5);
	}

	@Test
	public void testAddLast() {
		l.add("1");
		l.add("1");
		l.add("1");
		l.add("1");
		l.addLast("first");
		Assert.assertTrue(l.size() == 5);
	}

	@Test
	public void testGet() {
		l.add("1");
		l.add("1");
		l.add("1");
		l.add("1");
		l.addLast("first");
		Assert.assertTrue(l.get(4).equals("first"));
		Assert.assertTrue(l.get(3).equals("1"));
	}

	@Test
	public void testRemoveInt() {
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		Assert.assertTrue(l.size()==4);
		l.remove(2);
		Assert.assertTrue(l.size()==3);
		Assert.assertTrue(l.get(2).equals("4"));
	}
	@Test
	public void testClear() {
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		Assert.assertTrue(l.size()==4);
		l.clear();
		Assert.assertTrue(l.size()==0);
	}

	@Test
	public void testSize() {
		Assert.assertTrue(l.size()==0);
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		Assert.assertTrue(l.size()==4);
	}

	@Test
	public void testRemoveFirst() {
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		l.removeFirst();
		Assert.assertTrue(l.get(0).equals("2"));
	}

	@Test
	public void testRemoveLast() {
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		Assert.assertTrue(l.size()==4);
		Assert.assertTrue(l.get(2).equals("3"));
		l.removeLast();
		Assert.assertTrue(l.size()==3);
		Assert.assertTrue(l.get(0).equals("1"));
		Assert.assertTrue(l.get(1).equals("2"));
		Assert.assertTrue(l.get(2).equals("3"));
	}

	@Test
	public void testReverse() {
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		Assert.assertTrue(l.get(0).equals("1"));
		Assert.assertTrue(l.get(1).equals("2"));
		Assert.assertTrue(l.get(2).equals("3"));
		Assert.assertTrue(l.get(3).equals("4"));
		l.reverse();
		Assert.assertTrue(l.get(3).equals("1"));
		Assert.assertTrue(l.get(2).equals("2"));
		Assert.assertTrue(l.get(1).equals("3"));
		Assert.assertTrue(l.get(0).equals("4"));
	}

	@Test
	public void testRemoveFirstHalf() {
		l.add("2");
		l.add("5");
		l.add("7");
		l.add("8");
		l.add("9");
		Assert.assertTrue(l.size()==5);
		l.removeFirstHalf();
		Assert.assertTrue(l.size()==3);
		Assert.assertTrue(l.get(0).equals("7"));
		Assert.assertTrue(l.get(1).equals("8"));
		Assert.assertTrue(l.get(2).equals("9"));
	}

	@Test
	public void testRemoveIntInt() {
		l.add("2");
		l.add("5");
		l.add("7");
		l.add("8");
		l.add("9");
		l.remove(2, 2);
		Assert.assertTrue(l.get(0).equals("2"));
		Assert.assertTrue(l.get(1).equals("5"));
		Assert.assertTrue(l.get(2).equals("9"));
		Assert.assertTrue(l.size()==3);
	}

	@Test
	public void testGetElements() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(2);
		l.add(5);
		l.add(7);
		l.add(8);
		l.add(9);
		LinkedList<Integer> l2 = new LinkedList<Integer>();
		l2.add(1);
		l2.add(2);
		l2.add(4);
		int[] elements = l.getElements(l2);
		int[] exp = {5,7,9};
		Assert.assertArrayEquals(exp, elements);
	}

	@Test
	public void testSubtract() {
		l.add("2");
		l.add("5");
		l.add("7");
		l.add("8");
		l.add("9");
		LinkedList<String> l2 = new LinkedList<String>();
		l2.add("2");
		l2.add("9");
		l2.add("8");
		l.subtract(l2);
		Assert.assertTrue(l.size()==2);
		Assert.assertTrue(l.get(0).equals("5"));
		Assert.assertTrue(l.get(1).equals("7"));
	}

	@Test
	public void testRemoveDuplicateValues() {
		l.add("2");
		l.add("5");
		l.add("5");
		l.add("8");
		l.add("8");
		l.removeDuplicateValues();
		Assert.assertTrue(l.size()==3);
		Assert.assertTrue(l.get(0).equals("2"));
		Assert.assertTrue(l.get(1).equals("5"));
		Assert.assertTrue(l.get(2).equals("8"));
	}

	@Test
	public void testRemoveRange() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(2);
		l.add(5);
		l.add(7);
		l.add(8);
		l.add(9);
		System.out.println(l);
		l.removeRange(3, 7);
		System.out.println(l);
		Assert.assertTrue(l.size()==4);
		Assert.assertTrue(l.get(0)==2);
		Assert.assertTrue(l.get(1)==7);
		Assert.assertTrue(l.get(2)==8);
		Assert.assertTrue(l.get(3)==9);
	}

	@Test
	public void testIntersection() {
		l.add("2");
		l.add("5");
		l.add("7");
		l.add("8");
		l.add("9");
		LinkedList<String> l2 = new LinkedList<String>();
		l2.add("1");
		l2.add("2");
		l2.add("3");
		l2.add("5");
		LinkedList<String> intersection = l.intersection(l2);
		Assert.assertTrue(intersection.size()==2);
		Assert.assertTrue(intersection.get(0).equals("2"));
		Assert.assertTrue(intersection.get(1).equals("5"));
	}

}
