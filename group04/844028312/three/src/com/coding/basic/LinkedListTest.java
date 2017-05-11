package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	private LinkedList linkedList;
	@Before
	public void setUp() throws Exception {
		linkedList=new LinkedList();
		for(int i=0;i<10;i++){
			linkedList.add(i);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		
		System.out.println(linkedList.size());
	}

	@Test
	public void testAddIntObject() {
		linkedList.add(10, "@");
		System.out.println(linkedList.size());
	}

	@Test
	public void testGet() {
		System.out.println(linkedList.get(100));
	}

	@Test
	public void testRemoveInt() {
		System.out.println(linkedList.remove(9));
		System.out.println(linkedList.size());
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFirst() {
		linkedList.addFirst("aa");
		System.out.println(linkedList.size());
	}

	@Test
	public void testAddLast() {
		linkedList.addLast("bb");
		System.out.println(linkedList.size());
	}

	@Test
	public void testRemoveFirst() {
		linkedList.removeFirst();
		System.out.println(linkedList.size());
	}

	@Test
	public void testRemoveLast() {
		linkedList.removeLast();
		System.out.println(linkedList.size());
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverse() {
		linkedList.reverse();
		System.out.println(linkedList.size());
	}

	@Test
	public void testRemoveFirstHalf() {
		linkedList.removeFirstHalf();
		System.out.println(linkedList.size());
	}

	@Test
	public void testRemoveIntInt() {
		linkedList.remove(2, 5);//0 1 2 3 4 5 6 7 8 9
		System.out.println(linkedList.size());
	}

	@Test
	public void testGetElements() {
		LinkedList list=new LinkedList();
		list.add(1);
		list.add(3);
		list.add(2);
		list.add(7);
		int [] a=linkedList.getElements(list);
		System.out.println(a);
		
	}

	@Test
	public void testSubtract() {
		LinkedList list=new LinkedList();
		list.add(1);
		list.add(3);
		list.add(2);
		list.add(10);
		linkedList.subtract(list);
		System.out.println(linkedList);
	}

	@Test
	public void testRemoveDuplicateValues() {
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.removeDuplicateValues();
		System.out.println(linkedList);
	}

	@Test
	public void testRemoveRange() {
		linkedList.removeRange(2, 5);
		System.out.println(linkedList);
	}

	@Test
	public void testIntersection() {
		LinkedList list=new LinkedList();
		list.add(5);
		list.add(6);
		LinkedList c=linkedList.intersection(list);
		System.out.println(c);
	}

}
