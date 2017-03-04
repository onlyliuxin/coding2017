package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coding.basic.list.ArrayList;
import com.coding.basic.list.Iterator;
import com.coding.basic.list.List;

public class ArrayListTest {

	private List list = new ArrayList();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAddObject() {
		for (int i = 0; i < 13; i++) {
			list.add(Integer.toString(i));
		}
		testIterator();
	}

	@Test
	public void testAddIntObject() {
		testAddObject();
		System.out.println("testAddIntObject");
		try {
			list.add(13, "10000");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("message == "+e.getMessage());
		} finally {
			System.out.println("size ==="+list.size());
		}
		
		testIterator();
		
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		testAddObject();
		System.out.println("testRemove");
		for (int i = 0; i < 3; i++) {
			list.remove(0);
		}
		testAddObject();
		testIterator();
	}

	@Test
	public void testIterator() {
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
}
