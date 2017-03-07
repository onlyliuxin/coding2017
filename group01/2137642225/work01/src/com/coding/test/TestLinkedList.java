package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.mybasic.Iterator;
import com.coding.mybasic.LinkedList;
import com.coding.mybasic.List;

public class TestLinkedList {

	private List list;
	
	@Before
	public void before(){
		list = new LinkedList();
	}
	
	@Test
	public void testAddObject() {
		list.add(1);
		
		System.out.println(list.get(0));
		assertEquals(1, list.get(0));
		assertEquals(1, list.size());
	}

	@Test
	public void testAddIntObject() {
		list.add(0,1);
		System.out.println(list.get(0));
		assertEquals(1, list.get(0));
		assertEquals(1, list.size());
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		list.add(0,1);
		System.out.println(list.remove(0));
		assertEquals(0, list.size());
	}

	@Test
	public void testSize() {
		
		for(int i = 0; i < 10; i++){
			list.add(i, i);
		}
		
		assertEquals(10, list.size());
	}
	
	@Test
	public void testIterator() {
		
		for(int i = 0; i < 10; i++){
			list.add(i, i);
		}
		Iterator iterator = list.iterator();
		int i = 0;
		while(iterator.hasNext()){
			assertEquals(i++, iterator.next());
		}
		//iterator.next();
	}
}
