package com.coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TlinkedListSimpleTest {

	TlinkedListSimple lls = new TlinkedListSimple();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		Assert.assertEquals(3, lls.size());
		Assert.assertEquals(8, lls.get(0));
		Assert.assertEquals(9, lls.get(1));
		Assert.assertEquals(10, lls.get(2));
		
	}

	@Test
	public void testAddIntObject() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(1, 12);
		Assert.assertEquals(4, lls.size());
		Assert.assertEquals(8, lls.get(0));
		Assert.assertEquals(12, lls.get(1));
		Assert.assertEquals(9, lls.get(2));
		Assert.assertEquals(10, lls.get(3));
	}

	@Test
	public void testGet() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(null);
		Assert.assertEquals(4, lls.size());
		Assert.assertEquals(8, lls.get(0));
		Assert.assertEquals(9, lls.get(1));
		Assert.assertEquals(10, lls.get(2));
		Assert.assertEquals(null, lls.get(3));
	}

	@Test
	public void testRemoveInt() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(null);
		lls.remove(2);
		Assert.assertEquals(3, lls.size());
		Assert.assertEquals(8, lls.get(0));
		Assert.assertEquals(9, lls.get(1));
		Assert.assertEquals(null, lls.get(2));
	}

	@Test
	public void testSize() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(null);
		Assert.assertEquals(4, lls.size());
	}

	@Test
	public void testAddFirst() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(null);
		lls.addFirst(90);
		Assert.assertEquals(5, lls.size());
		Assert.assertEquals(90, lls.get(0));
		Assert.assertEquals(8, lls.get(1));
		Assert.assertEquals(9, lls.get(2));
		Assert.assertEquals(10, lls.get(3));
		Assert.assertEquals(null, lls.get(4));
	}

	@Test
	public void testAddLast() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(null);
		lls.addLast(78);
		Assert.assertEquals(5, lls.size());
		Assert.assertEquals(8, lls.get(0));
		Assert.assertEquals(9, lls.get(1));
		Assert.assertEquals(10, lls.get(2));
		Assert.assertEquals(null, lls.get(3));
		Assert.assertEquals(78, lls.get(4));
	}

	@Test
	public void testRemoveFirst() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(null);
		lls.removeFirst();
		Assert.assertEquals(3, lls.size());
		Assert.assertEquals(9, lls.get(0));
		Assert.assertEquals(10, lls.get(1));
		Assert.assertEquals(null, lls.get(2));
	}

	@Test
	public void testRemoveLast() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(null);
		lls.removeLast();
		Assert.assertEquals(3, lls.size());
		Assert.assertEquals(8, lls.get(0));
		Assert.assertEquals(9, lls.get(1));
		Assert.assertEquals(10, lls.get(2));
	}

	@Test
	public void testReverse() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(12);
		lls.add(34);
		lls.reverse();
		Assert.assertEquals(34, lls.get(0));
		Assert.assertEquals(12, lls.get(1));
		Assert.assertEquals(10, lls.get(2));
		Assert.assertEquals(9, lls.get(3));
		Assert.assertEquals(8, lls.get(4));
		
	}

	@Test
	public void testRemoveFirstHalf() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(12);
		lls.add(34);
		lls.removeFirstHalf();
	}

	@Test
	public void testRemoveIntInt() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(12);
		lls.add(34);
		lls.remove(2,2);
	}

	@Test
	public void testGetElements() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(12);
		lls.add(34);
		lls.add(89);
		lls.add(90);
		TlinkedListSimple llsb = new TlinkedListSimple();
		llsb.add(1);
		llsb.add(3);
		llsb.add(5);
		int[] ex = {9, 12,89};
		int[] result = lls.getElements(llsb);
		for(int i=0;i<ex.length;i++)
		{
			Assert.assertEquals(ex[i], result[i]);
		}
	}

	@Test
	public void testSubtract() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(12);
		lls.add(34);
		lls.add(89);
		lls.add(90);
		TlinkedListSimple llsb = new TlinkedListSimple();
		llsb.add(1);
		llsb.add(3);
		llsb.add(5);
		lls.subtract(llsb);
		Assert.assertEquals(8, lls.get(0));
		Assert.assertEquals(10, lls.get(1));
		Assert.assertEquals(34, lls.get(2));
		Assert.assertEquals(90, lls.get(3));
		
	}

	@Test
	public void testRemoveDuplicateValues() {
		lls.add(8);
		lls.add(8);
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(12);
		lls.add(12);
		lls.add(34);
		lls.add(89);
		lls.add(89);
		lls.add(90);
		lls.removeDuplicateValues();
		Assert.assertEquals(8, lls.get(0));
		Assert.assertEquals(9, lls.get(1));
		Assert.assertEquals(10, lls.get(2));
		Assert.assertEquals(12, lls.get(3));
		Assert.assertEquals(34, lls.get(4));
		Assert.assertEquals(89, lls.get(5));
		Assert.assertEquals(90, lls.get(6));
		
		
		
	}

	@Test
	public void testRemoveRange() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(12);
		lls.add(20);
		lls.add(23);
		lls.add(30);
		lls.add(34);
		lls.add(89);
		lls.add(90);
		int min = 10;
		int max = 89;
		lls.removeRange(min, max);
		Assert.assertEquals(8, lls.get(0));
		Assert.assertEquals(9, lls.get(1));
		Assert.assertEquals(10, lls.get(2));
		Assert.assertEquals(89, lls.get(3));
		Assert.assertEquals(90, lls.get(4));
	}

	@Test
	public void testIntersection() {
		lls.add(8);
		lls.add(9);
		lls.add(10);
		lls.add(12);
		lls.add(20);
		lls.add(23);
//		lls.add(30);
//		lls.add(34);
//		lls.add(89);
//		lls.add(90);
		TlinkedListSimple llsb = new TlinkedListSimple();
		llsb.add(3);
		llsb.add(6);
		llsb.add(10);
		llsb.add(13);
		llsb.add(30);
		llsb.add(32);
		llsb.add(34);
		llsb.add(89);
		llsb.add(100);
		
		TlinkedListSimple ll = lls.intersection(llsb);
		
		Assert.assertEquals(3, ll.get(0));
		Assert.assertEquals(6, ll.get(1));
		Assert.assertEquals(8, ll.get(2));
		Assert.assertEquals(9, ll.get(3));
		Assert.assertEquals(10, ll.get(4));
		Assert.assertEquals(12, ll.get(5));
		Assert.assertEquals(13, ll.get(6));
		Assert.assertEquals(20, ll.get(7));
		Assert.assertEquals(23, ll.get(8));
		Assert.assertEquals(30, ll.get(9));
		Assert.assertEquals(32, ll.get(10));
		Assert.assertEquals(34, ll.get(11));
		Assert.assertEquals(89, ll.get(12));
		//Assert.assertEquals(90, ll.get(13));
		Assert.assertEquals(100, ll.get(13));
	}

}
