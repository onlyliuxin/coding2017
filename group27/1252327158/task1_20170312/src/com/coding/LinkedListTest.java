package com.coding;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	LinkedList<String> list;

	@Before
	public void setUp() throws Exception {
		list = new LinkedList<String>();
		list.add("first");
		list.add("second");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddT() {
		list.add("third");
		Assert.assertEquals("second", list.get(1));
		Assert.assertEquals("first", list.get(2));
	}

	@Test
	public void testAddIntT() {
		list.add(1,"third");
		Assert.assertEquals("third", list.get(1));
		Assert.assertEquals("first", list.get(2));
	}

	@Test
	public void testGet() {
		Assert.assertEquals("second", list.get(0));
	}

	@Test
	public void testRemoveInt() {
		list.remove(0);
		list.add("third");
		list.add("forth");
		list.remove(1);
		Assert.assertEquals("forth", list.get(0));
		Assert.assertEquals("first", list.get(1));
	}

	@Test
	public void testSize() {
		list.remove(0);
		list.add("third");
		list.add("forth");
		list.remove(1);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void testAddFirst() {
		list.addFirst("third");
		Assert.assertEquals("third", list.get(0));
		Assert.assertEquals("second", list.get(1));
	}

	@Test
	public void testAddLast() {
		list.addLast("third");
		Assert.assertEquals("third", list.get(2));
	}

	@Test
	public void testRemoveFirst() {
		list.removeFirst();
		Assert.assertEquals("first", list.get(0));
	}

	@Test
	public void testRemoveLast() {
		list.removeLast();
		Assert.assertEquals(1, list.size());
	}

	@Test
	public void testIterator() {
		Iterator<String> iter = list.iterator();
		if (iter.hasNext()) {
			Assert.assertEquals("second", iter.next());
		}
	}

	@Test
	public void reverse() throws Exception {
		list.add("third");
		list.add("forth");
		Assert.assertEquals("forth", list.get(0));
		list.reverse();
		Assert.assertEquals("first", list.get(0));
		Assert.assertEquals("second", list.get(1));
		Assert.assertEquals("third", list.get(2));
		Assert.assertEquals("forth", list.get(3));
	}

	@Test
	public void removeFirstHalf() throws Exception {
		list.add("third");
		list.add("forth");
		list.removeFirstHalf();
		Assert.assertEquals("second", list.get(0));
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void remove() throws Exception {
		list.add("third");
		list.add("forth");
		list.remove(1, 2);
		Assert.assertEquals("forth", list.get(0));
		Assert.assertEquals("first", list.get(1));
	}

	@Test
	public void getElements() throws Exception {
		LinkedList<Integer> intList = new LinkedList<>();
		intList.addLast(11);
		intList.addLast(101);
		intList.addLast(201);
		intList.addLast(301);
		intList.addLast(401);
		intList.addLast(501);
		intList.addLast(601);
		intList.addLast(701);
		LinkedList<Integer> searchList = new LinkedList<>();
		searchList.addLast(1);
		searchList.addLast(3);
		searchList.addLast(4);
		searchList.addLast(7);

		Assert.assertArrayEquals(new int[]{101,301,401,701}, intList.getElements(searchList));
	}

	@Test
	public void subtract() throws Exception {

	}

	@Test
	public void removeDuplicateValues() throws Exception {

	}

	@Test
	public void removeRange() throws Exception {

	}

	@Test
	public void intersection() throws Exception {

	}
}
