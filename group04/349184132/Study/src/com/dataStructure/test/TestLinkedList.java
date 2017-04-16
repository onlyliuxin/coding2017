package com.dataStructure.test;

import org.junit.Test;

import com.dataStructure.Iterator;
import com.dataStructure.LinkedList;

public class TestLinkedList {
	LinkedList link = new LinkedList();
	@Test
	public void testAddObject() {
		link.add(1);
		link.add(2);
		link.add(3);
	}

	@Test
	public void testAddIntObject() {
		link.add(1);
		link.add(2);
		link.add(3);
		
		link.add(2,4);
	}

	@Test
	public void testGet() {
		link.add(1);
		link.add(2);
		link.add(3);
		link.get(1);
	}

	@Test
	public void testRemove() {
		link.add(1);
		link.add(2);
		link.add(3);
		
		link.remove(1);
	}

	@Test
	public void testSize() {
		link.size();
	}

	@Test
	public void testIsEmpty() {
		link.isEmpty();
	}

	@Test
	public void testAddFirst() {
		link.add(1);
		link.add(2);
		link.add(3);
		
		link.addFirst(9);
	}

	@Test
	public void testAddLast() {
		link.add(1);
		link.add(2);
		link.add(3);
		
		link.addLast(0);
	}

	@Test
	public void testRemoveFirst() {
		link.add(1);
		link.add(2);
		link.add(3);
		
		link.removeFirst();
	}

	@Test
	public void testRemoveLast() {
		link.add(1);
		link.add(2);
		link.add(3);
		
		link.removeLast();
	}

	@Test
	public void testIterator() {
		Iterator iterator = link.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}
