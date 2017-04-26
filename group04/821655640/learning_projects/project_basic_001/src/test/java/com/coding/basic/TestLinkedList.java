package com.coding.basic;


import java.util.Arrays;

import org.junit.Test;

public class TestLinkedList {

	@Test
	public void testReverse() {
		LinkedList link = new LinkedList();
		for(int i=0;i<10;i++) {
			link.add(i);
		}
		System.out.println(link);
		link.reverse();
		System.out.println(link);
	}
	
	@Test
	public void testRemoveFirstHalf() {
		LinkedList link = new LinkedList();
		for(int i=0;i<9;i++) {
			link.add(i);
		}
		System.out.println(link);
		link.removeFirstHalf();
		System.out.println(link);
	}
	
	@Test
	public void testRemove() {
		LinkedList link = new LinkedList();
		for(int i=0;i<9;i++) {
			link.add(i);
		}
		System.out.println(link);
		link.remove(2,3);
		System.out.println(link);
	}
	
	@Test
	public void testGetElements() {
		LinkedList link = new LinkedList();
		for(int i=0;i<9;i++) {
			link.add(i+ new java.util.Random().nextInt(10));
		}
		LinkedList linkB = new LinkedList();
		linkB.add(2);
		linkB.add(4);
		linkB.add(6);
		System.out.println(link);
		Integer printArray[] = link.getElements(linkB);
		System.out.println(Arrays.toString(printArray));
	}
	
	@Test
	public void testSubtract() {
		LinkedList link = new LinkedList();
		for(int i=0;i<9;i++) {
			link.add(i);
		}
		LinkedList linkB = new LinkedList();
		linkB.add(2);
		linkB.add(4);
		linkB.add(6);
		System.out.println(link);
		link.subtract(linkB);
		System.out.println(link);
	}
	
	@Test
	public void testRemoveDuplicateValues() {
		LinkedList link = new LinkedList();
		for(int i=0;i<9;i++) {
			link.add(i);
			link.add(i);
		}
		System.out.println(link);
		link.removeDuplicateValues();
		System.out.println(link);
	}
	@Test
	public void testRemoveRange() {
		LinkedList link = new LinkedList();
		for(int i=0;i<9;i++) {
			link.add(i);
		}
		System.out.println(link);
		link.removeRange(2, 5);
		System.out.println(link);
	}
	@Test
	public void testIntersection() {
		LinkedList link = new LinkedList();
		for(int i=3;i<9;i++) {
			link.add(i);
		}
		LinkedList linkB = new LinkedList();
		for(int i=0;i<7;i++) {
			linkB.add(i);
		}
		
		System.out.println(link);
		System.out.println(linkB);
		System.out.println(link.intersection(linkB));
	}

}
