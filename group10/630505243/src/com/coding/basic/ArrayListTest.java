package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {
	ArrayList list = null;
	Integer a = new Integer(1);
	Integer b = new Integer(2);
	Integer c = new Integer(3);

	@Before
	public void setUp() throws Exception {
		list = new ArrayList();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		list.add(a);
		list.add(b);
		list.add(c);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		System.out.println("-----------------");
		list.remove(3);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		fail("Not yet implemented");
	}

	@Test
	public void testAddIntObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

}
