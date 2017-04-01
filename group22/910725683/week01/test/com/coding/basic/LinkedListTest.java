package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.LinkedList;

public class LinkedListTest {
	LinkedList ll =new LinkedList();
	@Test
	public void testAddObject() {
		for (int i=0;i<9;i++){
			ll.add(i);
		}
	}

	@Test
	public void testAddIntObject() {
		testAddObject();
		ll.add(4, 22);
		ll.add(0, 23);
		System.out.println("add int : " + ll.toString());
	}

	@Test
	public void testGet() {
		testAddObject();
		System.out.println("get index 3 : "+ll.get(3));
	}

	@Test
	public void testRemoveInt() {
		testAddObject();
		System.out.println("remove index 5 : "+ll.get(5));
	}

	@Test
	public void testSize() {
		testAddObject();
		System.out.println("get size : "+ll.size());
	}

	@Test
	public void testAddFirst() {
		testAddObject();
		ll.addFirst(12);
		System.out.println("add first : "+ll.toString());
	}

	@Test
	public void testAddLast() {
		testAddObject();
		ll.addLast(23);
		System.out.println("add first : "+ll.toString());
	}

	@Test
	public void testRemoveFirst() {
		testAddObject();
		ll.removeFirst();
		System.out.println("remove first : "+ll.toString());
	}

	@Test
	public void testRemoveLast() {
		testAddObject();
		ll.removeLast();
		System.out.println("remove last : "+ll.toString());
	}

	@Test
	public void testReverse() {
		testAddObject();
		ll.reverse();
		System.out.println("reverse : "+ll.toString());
	}

	@Test
	public void testRemoveFirstHalf() {
		testAddObject();
		ll.removeFirstHalf();
		System.out.println("remove first half : "+ll.toString());
	}

	@Test
	public void testRemoveIntInt() {
		testAddObject();
		ll.remove(2, 4);
		System.out.println("remove index 2 length 4 : "+ll.toString());
	}

	@Test
	public void testGetElements() {
		testAddObject();
		System.out.println("get index 2 : "+ll.get(2));
	}

	@Test
	public void testSubtract() {
		testAddObject();
		LinkedList test1 =new LinkedList();
		for (int i=2;i<5;i++){
			test1.add(i);
		}		
		ll.subtract(test1);
		System.out.println("subtract "+test1.toString()+" : "+ll.toString());
	}

	@Test
	public void testRemoveDuplicateValues() {
		testAddObject();
		for (int i=6;i>2;i--){
			ll.add(i,i);
		}		
		ll.removeDuplicateValues();
		System.out.println("remove dupl : "+ll.toString());
	}

	@Test
	public void testRemoveRange() {
		testAddObject();
		ll.removeRange(3, 6);
		System.out.println("remove range[3,6] : "+ll.toString());
	}

	@Test
	public void testIntersection() {
		testAddObject();
		LinkedList test2 =new LinkedList();
		for (int i=4;i<14;i=i+2){
			test2.add(i);
		}		
		System.out.println("intersection "+test2.toString()+" : "+ll.intersection(test2));
	}

}
