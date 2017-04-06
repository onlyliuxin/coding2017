package com.coding.basic;

import org.junit.Before;
import org.junit.Test;

/**
 * @author 李兵兵
 * @Time：2017年3月12日 上午11:02:54
 * @version 1.0
 */
public class TestLinkedList {
	private LinkedList list;
	@Before
	public void beforeTest() {
		list = new LinkedList();
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		//list.add(5);
		
	}

	@Test
	public void testAddObject() {
		list.add(5);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testAddInt() {
		list.add(5);
		list.add(2, 9);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testRemoveInt() {
		Object o = list.remove(0);
		System.out.println(o);
		System.out.println(list.size());
	}


	@Test
	public void testAddFirst() {
		list.addFirst(5);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Test
	public void testAddLast() {
		list.addLast(5);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Test
	public void testRemoveFirst() {
		list.addFirst(8);
		Object o = list.removeFirst();
		System.out.println(o);
	}

	@Test
	public void testRemoveLast() {
		Object o = list.removeLast();
		System.out.println(o);
	}

	
	@Test
	public void testRemoveDuplicateValues() {
		list.removeDuplicateValues();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testRemoveRange() {
		list.removeRange(2,5);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testGetElements() {
		LinkedList list1 = new LinkedList();
		list1.add(2);
		list1.add(5);
		int[] a = list.getElements(list1);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	
	@Test
	public void testRemove() {
		list.remove(2,2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testRemoveFirstHalf() {
		list.removeFirstHalf();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testSubtract() {
		LinkedList list1 = new LinkedList();
		list1.add(8);
		list1.add(5);
		list.subtract(list1);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testReverse() {
		list.reverse();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
