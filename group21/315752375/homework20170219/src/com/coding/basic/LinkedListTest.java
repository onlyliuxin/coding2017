package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;




public class LinkedListTest {

	@Test
	public void testLinkedList() {
		fail("Not yet implemented");
		LinkedList linkedList=new LinkedList();
	}

	@Test
	public void testAddObject() {
		fail("Not yet implemented");
		LinkedList linkedList=new LinkedList();
		linkedList.add("1");
	}

	@Test
	public void testAddIntObject() {
		fail("Not yet implemented");
		LinkedList linkedList=new LinkedList();
		linkedList.add(0,"2");
	}
	@Test
	public void testGet() {
		fail("Not yet implemented");
		LinkedList linkedList=new LinkedList();
		linkedList.add(0,"2");
		System.out.println(linkedList.get(0));
	}
	@Test
	public void testRemove() {
		fail("Not yet implemented");
		LinkedList linkedList=new LinkedList();
		linkedList.add(0,"2");
		System.out.println(linkedList.size());
		linkedList.remove(0);
		System.out.println(linkedList.size());
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
		LinkedList linkedList=new LinkedList();
		System.out.println(linkedList.size());
		linkedList.add(0,"2");
		linkedList.add(0,"2");
		linkedList.add(0,"2");
		linkedList.add(0,"2");
		linkedList.add(0,"2");
		linkedList.add(0,"2");
		linkedList.add(0,"2");
		linkedList.add(0,"2");
		System.out.println(linkedList.size());
		linkedList.remove(0);
		System.out.println(linkedList.size());
	}

	@Test
	public void testAddFirst() {
		fail("Not yet implemented");
		LinkedList linkedList=new LinkedList();
		linkedList.add(0,"1");
		linkedList.add(1,"2");
		linkedList.addFirst("3");
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
		System.out.println(linkedList.get(2));
	}

	@Test
	public void testAddLast() {
		fail("Not yet implemented");
		LinkedList linkedList=new LinkedList();
		linkedList.add(0,"1");
		linkedList.add(1,"2");
		linkedList.addLast("3");
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
		System.out.println(linkedList.get(2));
		
	}

	@Test
	public void testRemoveFirst() {
		fail("Not yet implemented");
		LinkedList linkedList=new LinkedList();
		linkedList.add(0,"1");
		linkedList.add(1,"2");
		linkedList.addLast("3");
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
		System.out.println(linkedList.get(2));
		linkedList.removeFirst();
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
	}

	@Test
	public void testRemoveLast() {
		fail("Not yet implemented");
		LinkedList linkedList=new LinkedList();
		linkedList.add(0,"1");
		linkedList.add(1,"2");
		linkedList.addLast("3");
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
		System.out.println(linkedList.get(2));
		linkedList.removeLast();
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
	}

	@Test
	public void testIterator() {
		LinkedList linkedList=new LinkedList();
		linkedList.add(0,"1");
		linkedList.add(1,"2");
		linkedList.add(2,"3");
		linkedList.add(3,"4");
		linkedList.add(4,"5");
		linkedList.add(5,"6");
		linkedList.add(6,"7");
		linkedList.add(7,"8");
		Iterator iterator=linkedList.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}
