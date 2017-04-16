package com.dataStructure.test;

import org.junit.Test;

import com.dataStructure.ArrayList;
import com.dataStructure.Iterator;

public class TestArrayList {
	private ArrayList al = new ArrayList();
	@Test
	public void testAddObject() {
		al.add("1");
		al.add(12);
		al.add("wang");
		
	}

	@Test
	public void testAddIntObject() {
		al.add("1");
		al.add(12);
		al.add("wang");
		
		al.add(1, 13);
	}

	@Test
	public void testGet() {
		al.add("1");
		al.add(12);
		al.add("wang");
		
		al.get(1);
	}

	@Test
	public void testRemove() {
		al.add("1");
		al.add(12);
		al.add("wang");
		
		al.remove(0);
	}

	@Test
	public void testSize() {
		al.size();
	}
	

	@Test
	public void testIsEmpty() {
		al.isEmpty();
	}

	@Test
	public void testIterator() {
		al.add("1");
		al.add(12);
		al.add("wang");
		
		Iterator iterator = al.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
	}

}
