package com.coding.basic;


import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TarrayListTest {

	TarrayList al = new TarrayList();
	TarrayList bl = new TarrayList();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		al.add(8);
		Assert.assertEquals(1, al.size());
		Assert.assertEquals(8, al.get(0));
		al.add(9);
		Assert.assertEquals(2, al.size());
		Assert.assertEquals(8, al.get(0));
		Assert.assertEquals(9, al.get(1));
		al.add(null);
		Assert.assertEquals(3, al.size());
		Assert.assertEquals(8, al.get(0));
		Assert.assertEquals(9, al.get(1));
		Assert.assertEquals(null, al.get(2));
	}


	@Test
	public void testGet() {
		al.add(8);
		al.add(null);
		al.add(9);
		Assert.assertEquals(8, al.get(0));
		Assert.assertEquals(null, al.get(1));
		Assert.assertEquals(9, al.get(2));
	}

	@Test
	public void testRemove() {
		al.add(8);
		al.add(null);
		al.add(9);
		Assert.assertEquals(3, al.size());
		Assert.assertEquals(null, al.remove(1));
		Assert.assertEquals(2, al.size());
	}

	@Test
	public void testSet() {
		al.add(8);
		al.add(null);
		al.add(9);
		Assert.assertEquals(null, al.set(1, 90));
		Assert.assertEquals(3, al.size());
	}

	@Test
	public void testRemoveObject() {
		al.add(8);
		al.add(9);
		al.add(10);
		Assert.assertEquals(9, al.remove(1));
		Assert.assertEquals(10, al.get(1));
	}

	@Test
	public void testFastRemove() {
		al.add(8);
		al.add(9);
		al.add(10);
		al.fastRemove(0);
		Assert.assertEquals(9,al.get(0) );
	}

	@Test
	public void testSize() {
		al.add(8);
		al.add(9);
		al.add(10);
		Assert.assertEquals(3, al.size());
	}
//
//	@Test
//	public void testIterator() {
//		fail("Not yet implemented");
//	}

}
