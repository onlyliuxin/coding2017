package com.nitaty.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.LinkedList;

public class LinkedListTest {
	
	LinkedList list;
	

	@Before
	public void setUp() throws Exception {
		list=new LinkedList();
		for (int i =0; i < 100; i++) {
			list.add(i);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverse() throws Exception {
		list.reverse();
		for (int i = 1; i < 100; i++) {
			Assert.assertEquals(100-i, list.get(i-1));
		}
	}

	@Test
	public void testRemoveFirstHalf() throws Exception {
		list.removeFirstHalf();
		for (int i = 50; i < 100; i++) {
			Assert.assertEquals(i, list.get(i-50));
		}
	}

	@Test
	public void testRemoveIntInt() {
		int startIndex= 0;
		int length=50;
		int stopIndex=Math.min(startIndex+length, list.size());
		list.remove(startIndex,length);
		for (int i = 0; i < startIndex; i++) {
			Assert.assertEquals(i, list.get(i));
		}
		
		for(int i=startIndex;i<list.size();i++){
			Assert.assertEquals(i+length, list.get(i));
		}
	}

	@Test
	public void testGetElements() {
		LinkedList listB=new LinkedList();
		listB.add(0);
		listB.add(1);
		listB.add(10);
		listB.add(50);
		listB.add(90);
		listB.add(99);
		int[] test={0,1,10,50,90,99};
		int[] arr=list.getElements(listB);
		Assert.assertArrayEquals(test,arr);
		
	}

	@Test
	public void testSubtract() {
		LinkedList listB=new LinkedList();
		listB.add(0);
		listB.add(1);
		listB.add(10);
		listB.add(50);
		listB.add(90);
		listB.add(99);
		listB.add(200);
		list.subtract(listB);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < listB.size(); j++) {
				Assert.assertNotEquals(listB.get(j), list.get(i));
			}
		}
	}

	@Test
	public void testRemoveDuplicateValues() {
		LinkedList dupList = new LinkedList();
		for(int i=0;i<100;i++){
			dupList.add(i);
			dupList.add(i);
			dupList.add(i);
		}
		dupList.removeDuplicateValues();
		for (int i = 0; i < list.size(); i++) {
			Assert.assertEquals(dupList.get(i), list.get(i));
		}
	}

	@Test
	public void testRemoveRange() {
		list.removeRange(-1, 60);
		for (int i = 0; i < list.size(); i++) {
			Assert.assertEquals(i+60, list.get(i));
		}
	}

	@Test
	public void testIntersection() {
		
		LinkedList listB=new LinkedList();
		listB.add(-2);
		listB.add(0);
		listB.add(1);
		listB.add(10);
		listB.add(50);
		listB.add(90);
		listB.add(99);
		listB.add(100);
		listB.add(200);
		LinkedList newList=list.intersection(listB);
		for (int i = 1; i < listB.size()-2; i++) {
			Assert.assertEquals(listB.get(i), newList.get(i-1));
		}
		
	}

}
