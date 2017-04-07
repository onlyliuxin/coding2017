package com.coding.basic;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testIterator() {
		LinkedList list=new LinkedList();
		for (int i = 0; i < 8; i++) {
			list.add(i);
		}
		
		
		
		
	}
	
	@Test
	public void testReverse() {
		LinkedList list=new LinkedList();
		for (int i = 0; i < 8; i++) {
			list.add(i);
		}
		
		list.reverse();

		System.out.println(list.toString());
	
	}
	
	@Test
	public void testremoveFirstHalf() {
		LinkedList list=new LinkedList();
		for (int i = 0; i < 8; i++) {
			list.add(i);
		}
		
		list.removeFirstHalf();

		System.out.println(list.toString());
	
	}
	
	@Test
	public void testremove() {
		LinkedList list=new LinkedList();
		for (int i = 0; i < 8; i++) {
			list.add(i);
		}
		
		list.remove(2, 3);

		System.out.println(list.toString());
	
	}

	@Test
	public void testgetElements() {
		LinkedList list=new LinkedList();
		for (int i = 101; i < 108; i++) {
			list.add(i);
		}
		LinkedList listB=new LinkedList();
		for (int j = 3; j < 5; j++) {
			listB.add(j);
		}
		
		int [] result=list.getElements(listB);
		
		System.out.println(Arrays.toString(result));
	
	}
	
	@Test
	public void testSub() {
		LinkedList list=new LinkedList();
		
		for (int i = 101; i < 108; i++) {
			list.add(i);
		}
		LinkedList listB=new LinkedList();
		
		for (int j = 103; j <105; j++) {
			listB.add(j);
		}
		
		list.subtract(listB);
		
		System.out.println(list.toString());
	
	}
	
	@Test
	public void testremoveDuplicateValues() {
		LinkedList list=new LinkedList();
		for (int i = 0; i < 8; i++) {
			list.add(i);
		}
		System.out.println(list.toString());
		
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(9);
		
		list.removeDuplicateValues();
		System.out.println(list.toString());
	
	}
	
	@Test
	public void testremoveRange() {
		LinkedList list=new LinkedList();
		
		for (int i = 101; i < 108; i++) {
			list.add(i);
		}
		
		
		list.removeRange(99, 106);
		
		System.out.println(list.toString());
	
	}
	
	@Test
	public void testintersection() {
		LinkedList list=new LinkedList();
		
		for (int i = 101; i < 108; i++) {
			list.add(i);
		}
		
		LinkedList listB=new LinkedList();
		
		for (int j = 103; j <105; j++) {
			listB.add(j);
		}
		
	
		
		System.out.println(list.intersection(listB).toString());
	
	}
	
}
