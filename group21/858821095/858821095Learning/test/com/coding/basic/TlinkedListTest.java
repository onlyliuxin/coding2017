package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TlinkedListTest {

	TlinkedList tl = new TlinkedList();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testAdd() {
		tl.add(6);
		tl.add(8);
		Assert.assertEquals(2, tl.size());
		Assert.assertEquals(6, tl.get(0));
		Assert.assertEquals(8, tl.get(1));
	}

	@Test
	public void testAddBefore() {
		tl.add(7);
		tl.add(null);
		Assert.assertEquals(2, tl.size());
		Assert.assertEquals(7, tl.get(0));
		Assert.assertEquals(null, tl.get(1));
		
	}

	@Test
	public void testAddIntObject() {
		tl.add(7);
		tl.add(null);
		tl.add(0, 8);
		Assert.assertEquals(3, tl.size());
		Assert.assertEquals(8, tl.get(0));
		Assert.assertEquals(7, tl.get(1));
		Assert.assertEquals(null, tl.get(2));
	}

	@Test
	public void testGet() {
		tl.add(7);
		tl.add(9);
		tl.add(0, 8);
		Assert.assertEquals(3, tl.size());
		Assert.assertEquals(8, tl.get(0));
	}

	@Test
	public void testRemoveInt() {
		tl.add(7);
		tl.add(null);
		tl.add(0, 8);
		Assert.assertEquals(3, tl.size());
		Assert.assertEquals(8, tl.remove(0));
		Assert.assertEquals(2, tl.size());
	}

	@Test
	public void testSize() {
		tl.add(7);
		tl.add(null);
		tl.add(0, 8);
		Assert.assertEquals(3, tl.size());
	}

	@Test
	public void testAddFirst() {
		tl.add(7);
		tl.add(null);
		tl.addFirst(9);
		Assert.assertEquals(3, tl.size());
		Assert.assertEquals(9, tl.get(0));
	}

	@Test
	public void testAddLast() {
		tl.add(7);
		//tl.add(null);
		tl.add(8);
		tl.addLast(9);
		Assert.assertEquals(3, tl.size());
		Assert.assertEquals(9, tl.get(2));
	}

	@Test
	public void testRemoveFirst() {
		tl.add(7);
		tl.add(null);
		tl.removeFirst();
		Assert.assertEquals(1, tl.size());
		Assert.assertEquals(null, tl.get(0));
	}

	@Test
	public void testRemoveLast() {
		tl.add(7);
		tl.add(null);
		tl.add(9);
		tl.add(90);
		tl.removeLast();
		Assert.assertEquals(3, tl.size());
		Assert.assertEquals(7, tl.get(0));
	}

	@Test
	public void testClear() {
		tl.add(7);
		tl.add(null);
		tl.clear();
		Assert.assertEquals(0, tl.size());
	}

	@Test
	public void testContains() {
		tl.add(7);
		tl.add(null);
		Assert.assertEquals(true, tl.contains(7));
		Assert.assertEquals(true, tl.contains(null));
	}

	@Test
	public void testIndexOf() {
		tl.add(7);
		tl.add(null);
		Assert.assertEquals(0, tl.indexOf(7));
		Assert.assertEquals(1, tl.indexOf(null));
}
	@Test
	public void testReverse(){
		tl.add(8);
		tl.add(9);
		tl.add(10);
		tl.reverse();
	}

}
