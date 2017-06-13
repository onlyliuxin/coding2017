package com.coderising.linkedlist;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class LinkedListTest {

	LinkedList multiValLl = null;
	LinkedList emptyLl = null;
	LinkedList oneItemLl = null;
	LinkedList twoItemsLl = null;
	LinkedList nullLl = null;
	@Before
	public void setUp() throws Exception {
		multiValLl = new LinkedList();
		multiValLl.add(1);
		multiValLl.add(2);
		multiValLl.add(3);
		emptyLl = new LinkedList();
		oneItemLl = new LinkedList();	
		oneItemLl.add(1);
		twoItemsLl = new LinkedList();
		twoItemsLl.add(1);
		twoItemsLl.add(2);
	}

	@Test
	public void testReverse() {
		int[] expected = new int[]{3,2,1};
		multiValLl.reverse();
		for(int i = 0; i< multiValLl.size();i++){
			assertEquals(expected[i], multiValLl.get(i));
		}
		expected = new int[]{2,1};
		twoItemsLl.reverse();
		for(int i = 0; i< twoItemsLl.size();i++){
			assertEquals(expected[i], twoItemsLl.get(i));
		}
		emptyLl.reverse(); //no error or exception
		oneItemLl.reverse();
		assertEquals(1,oneItemLl.size());
		assertEquals(1, oneItemLl.get(0));
		
		
	}

	@Test
	public void testRemoveFirstHalf() {
		int[] expected = new int[]{2,3};
		multiValLl.removeFirstHalf();
		for(int i = 0; i< multiValLl.size();i++){
			assertEquals(expected[i], multiValLl.get(i));
		}
		expected = new int[]{2};
		twoItemsLl.removeFirstHalf();
		for(int i = 0; i< twoItemsLl.size();i++){
			assertEquals(expected[i], twoItemsLl.get(i));
		}
	}

	@Test
	public void testRemoveIntInt() {
		int[] expected = new int[]{1,3};
		multiValLl.remove(1, 1);
		for(int i = 0; i< multiValLl.size();i++){
			//System.out.println("i: "+ i + " " + multiValLl.get(i));
			assertEquals(expected[i], multiValLl.get(i));
		}
		expected = new int[]{2};
		twoItemsLl.remove(0,1);
		for(int i = 0; i< twoItemsLl.size();i++){
			assertEquals(expected[i], twoItemsLl.get(i));
		}
		
		oneItemLl.remove(0,1);
		assertEquals(0,oneItemLl.size());
	}

	@Test
	public void testGetElements() {
		int[] expected = new int[]{2,3};
		LinkedList indexList = new LinkedList();
		indexList.add(1);indexList.add(2);
		assertArrayEquals(expected, multiValLl.getElements(indexList));
	}

	@Test
	public void testSubtract() {
		LinkedList testLl = new LinkedList();
		int[] array = new int[]{2,4,6,6,8,9};
		testLl.insert(array);
		LinkedList toberemoved = new LinkedList();
		toberemoved.add(2);
		toberemoved.add(4);toberemoved.add(6);toberemoved.add(9);toberemoved.add(8);
		testLl.subtract(toberemoved);
		//System.out.println("result: " + testLl);
		int[] expected = new int[]{8,9};
		for(int i= 0;i<testLl.size(); i++){
			assertEquals(expected[i], testLl.get(i));
		}
	}

	@Test
	public void testRemoveDuplicateValues() {
		LinkedList testLl = new LinkedList();
		int[] array = new int[]{2,3,3,5,7,7,9};
		testLl.insert(array);
		testLl.removeDuplicateValues();
		int[] expected = new int[]{2,3,5,7,9};
		assertArrayEquals(expected, testLl.toIntArray());
	}

	@Test
	public void testRemoveRange1() {
		LinkedList testLl = new LinkedList();
		int[] array = new int[]{2,4,6,6,8,9};
		testLl.insert(array);
		testLl.removeRange(1, 7);
		//System.out.println(testLl);
		assertArrayEquals(new int[]{8,9}, testLl.toIntArray());
	}
	
	@Test
	public void testRemoveRange2() {
		LinkedList testLl = new LinkedList();
		int[] array = new int[]{2,4,6,6,8,9};
		testLl.insert(array);
		testLl.removeRange(2, 10);
		//System.out.println(testLl);
		assertArrayEquals(new int[]{2}, testLl.toIntArray());
	}
	
	@Test
	public void testRemoveRange3() {
		LinkedList testLl = new LinkedList();
		int[] array = new int[]{2,4,6,6,8,9};
		testLl.insert(array);
		testLl.removeRange(2, 8);
		//System.out.println(testLl);
		assertArrayEquals(new int[]{2, 8, 9}, testLl.toIntArray());
	}


	@Test
	public void testIntersection() {
		LinkedList testLl = new LinkedList();
		int[] array = new int[]{2,4,6,8,9};
		testLl.insert(array);
		LinkedList supportLl = new LinkedList();
		array = new int[] {3,5,6,8,9};
		supportLl.insert(array);
		LinkedList newll = testLl.intersection(supportLl);
		assertArrayEquals(new int[]{6,8,9}, newll.toIntArray());
	}

}
