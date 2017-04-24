package com.github.ipk2015.coding2017.basic.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.ipk2015.coding2017.basic.array.ArrayList;



public class ArrayListTest {
	ArrayList list;
	@Before
	public void setUp() throws Exception {
		list=new ArrayList();
		
	}
	@Test
	public void testAddObject() {
		list.add("hehe1");
		assertEquals("hehe1", list.get(0));
	}

	@Test
	public void testAddIntObject() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.add(1, "arm");
		assertEquals("arm", list.get(1));  
	}

	@Test
	public void testGet() {
		list.add("hehe1");
		assertEquals("hehe1", list.get(0));  
	}

	@Test
	public void testRemove() {
		list.add("hehe1");
		assertEquals("hehe1", list.remove(0));  
	}

	@Test
	public void testSize() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		
		assertEquals(3, list.size());   
	}

}
