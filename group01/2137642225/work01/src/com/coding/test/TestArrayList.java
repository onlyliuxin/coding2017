package com.coding.test;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.mybasic.ArrayList;
import com.coding.mybasic.Iterator;
import com.coding.mybasic.List;

public class TestArrayList {

	private List list;
	@Before
	public void before() {
		list = new ArrayList();
	}
	
	@Test
	public void testAddObject() {
		list.add("ele");
		Assert.assertEquals("ele", list.get(0));
	}

	@Test
	public void testAddIntObject() {
		
		for (int i = 0; i < 5; i++) {
			list.add(i,i);
			Assert.assertEquals(i, list.get(i));
		}
		
	}

	@Test
	public void testGet() {
		list.add("ss");
		Assert.assertEquals("ss", list.get(0));
	}

	@Test
	public void testRemove() {
		list.add("we");
		list.add(1, "gga");
		list.add(0, "start");
		list.add(3, "end");
		
		Assert.assertEquals("end", list.remove(3));
		
	}

	@Test
	public void testSize() {
		
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		
		Assert.assertEquals(10, list.size());
	}
	
	@Test
	public void testIterator() {
		
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		Iterator iterator = list.iterator();
		int i = 0;
		while(iterator.hasNext()){
			Assert.assertEquals(i++, iterator.next());
		}
	}

	@After
	public void after(){
		
	}
}
