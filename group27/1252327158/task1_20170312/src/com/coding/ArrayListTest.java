package com.coding;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ArrayListTest {

	ArrayList<String> list;

	@Before
	public void setUp() throws Exception {
		list = new ArrayList<String>();
		list.add(new String("first"));
		list.add(new String("second"));
		list.add(new String("third"));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		list.add(new String("forth"));
		Assert.assertEquals("forth", (String)list.get(3));
	}

	@Test
	public void testAddIntObject() {
		list.add(1, new String("add"));
		Assert.assertEquals("add", (String)list.get(1));
		Assert.assertEquals("second", (String)list.get(2));
	}

	@Test
	public void testGet() {
		Assert.assertEquals("third", list.get(2));
	}

	@Test
	public void testRemove() {
		list.remove(0);
		Assert.assertEquals("second", list.get(0));
	}

	@Test
	public void testSize() {
		Assert.assertEquals(3, list.size());
	}

	@Test
	public void testIterator() {
		Iterator it = list.iterator();
		if (it.hasNext()) {
			Assert.assertEquals("first", it.next());
		}
	}

}
