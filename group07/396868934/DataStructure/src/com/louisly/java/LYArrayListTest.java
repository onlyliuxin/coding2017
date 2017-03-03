package com.louisly.java;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class LYArrayListTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		LYArrayList list = new LYArrayList();
		list.addObject(new Integer(10));
		Assert.assertEquals(10, ((Integer)list.get(0)).intValue());
	}

	@Test
	public void testRemoveObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAtIndex() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

}
