package com.coding.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.LinkedList;

public class LinkedListTest {

	LinkedList mainList;
	
	private void initLinkedList(LinkedList list, int[] elements) {
		while (list.size() > 0) {
			list.removeFirst();
		}
		for (int e:elements) {
			list.add(new Integer(e));
		}
	}
	
	private int[] getIntegerArray(LinkedList list) {
		if (list == null) {
			return new int[0];
		}
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = (Integer)list.get(i);
		}
		return array;
	}
	
	private void printMainList() {
		for (int e:getIntegerArray(mainList)) {
			System.out.println(e);
		}
	}
	
	@Before
	public void setUp() {
		mainList = new LinkedList();
	}
	
	@After
	public void tearDown() {
		mainList = null;
	}
	
	@Test
	public void testReverse() {
		initLinkedList(mainList, new int[] {});
		mainList.reverse();
		Assert.assertArrayEquals(new int[0], getIntegerArray(mainList));
		initLinkedList(mainList, new int[] {1,2,3,4,5});
		mainList.reverse();
		Assert.assertArrayEquals(new int[] {5,4,3,2,1}, getIntegerArray(mainList));
	}
	
	@Test
	public void testRemoveFirstHalf() {
		initLinkedList(mainList, new int[] {1,2,3,4,5});
		mainList.removeFirstHalf();
		Assert.assertArrayEquals(new int[] {3,4,5}, getIntegerArray(mainList));
		initLinkedList(mainList, new int[] {1,2,3,4});
		mainList.removeFirstHalf();
		Assert.assertArrayEquals(new int[] {3,4}, getIntegerArray(mainList));
		initLinkedList(mainList, new int[] {1});
		mainList.removeFirstHalf();
		Assert.assertArrayEquals(new int[] {1}, getIntegerArray(mainList));
	}
	
	@Test
	public void testRemove() {
		initLinkedList(mainList, new int[] {1,2,3,4,5,6,7,8,9,10});
		mainList.remove(0,9);
		Assert.assertArrayEquals(new int[] {10}, getIntegerArray(mainList));
		initLinkedList(mainList, new int[] {1,2,3,4,5,6,7,8,9,10});
		mainList.remove(2,9);
		Assert.assertArrayEquals(new int[] {1,2}, getIntegerArray(mainList));
		initLinkedList(mainList, new int[] {1,2,3,4,5,6,7,8,9,10});
		mainList.remove(3,1);
		Assert.assertArrayEquals(new int[] {1,2,3,5,6,7,8,9,10}, getIntegerArray(mainList));
	}
	
	@Test
	public void testGetElements() {
		initLinkedList(mainList, new int[] {0,10,20,30,40,50,60,70,80,90,100});
		LinkedList list = new LinkedList();
		initLinkedList(list, new int[] {0,3,4,7});
		Assert.assertArrayEquals(new int[] {0,30,40,70}, mainList.getElements(list));
	}
	
	@Test
	public void testBinarySort() {
		initLinkedList(mainList, new int[] {10,9,2,9,3,5,4,9,1});
		mainList = mainList.binaryTreeSort(mainList);
		Assert.assertArrayEquals(new int[] {1,2,3,4,5,9,10}, getIntegerArray(mainList));
		initLinkedList(mainList, new int[] {});
		mainList = mainList.binaryTreeSort(mainList);
		Assert.assertArrayEquals(new int[] {}, getIntegerArray(mainList));
	}
	
	@Test
	public void testSubtract() {
		initLinkedList(mainList, new int[] {1,2,3,4,5,6,7,8,9,10});
		LinkedList list = new LinkedList();
		//initLinkedList(list, new int[] {1,2,3,4,5,9,10});
		initLinkedList(list, new int[] {10,9,2,9,3,5,4,9,1});
		mainList.subtract(list);
		Assert.assertArrayEquals(new int[] {6,7,8}, getIntegerArray(mainList));
		//printMainList();
	}
	
	@Test
	public void testRemoveDuplicateValues() {
		initLinkedList(mainList, new int[] {1,2,3,3,4,4,5,5,5,5,5});
		mainList.removeDuplicateValues();
		Assert.assertArrayEquals(new int[] {1,2,3,4,5}, getIntegerArray(mainList));
	}
	
	@Test
	public void testRemoveRange() {
		initLinkedList(mainList, new int[] {1,2,3,4,5,6,7,8,9,10});
		mainList.removeRange(5, 5);
		Assert.assertArrayEquals(new int[] {1,2,3,4,5,6,7,8,9,10}, getIntegerArray(mainList));
	}
	
	@Test
	public void testIntersection() {
		initLinkedList(mainList, new int[] {1,2,3,4,5,6,7,8,9,10});
		LinkedList list = new LinkedList();
		initLinkedList(list, new int[] {1,2,3,4,5,9,10});
		mainList = mainList.intersection(list);
		//printMainList();
		Assert.assertArrayEquals(new int[] {1,2,3,4,5,9,10}, getIntegerArray(mainList));
	}

}
