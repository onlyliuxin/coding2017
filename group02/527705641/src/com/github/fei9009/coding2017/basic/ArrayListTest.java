package com.github.fei9009.coding2017.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {

	private static ArrayList testArray = new ArrayList();
	@Before
	public void setUp() throws Exception {
		//testArray.clear();
	}

	@Test
	public void testArrayList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAddObject() {
		testArray.add(10);
		assertEquals(10, testArray.get(0));
		//fail("Not yet implemented");
	}

	@Test
	public void testAddIntObject() {
		testArray.add(10);
		testArray.add(0, 3);
		testArray.add(0, 2);
		assertEquals(3, testArray.get(1));
		assertEquals(2, testArray.get(0));
		//fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		testArray.add(10);
		assertEquals(10, testArray.get(0));
		//fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

}
