package com.coding.basic.homework_03.linkedListImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.homework_01.Iterator;

public class TestLinkedList {
	LinkedList list;
	@Before
	public void before(){
		list = new LinkedList();
	}
	
//	@Test
	public void test1(){
		list.add(1);
		list.add(2);
		list.add(3);
		list.remove(2);
		list.add(4);
		list.add(0, 0);
		list.addFirst(-1);
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println("size:" + list.size());
	}
	
	@Test
	public void testReverse(){
		list.add(3);
		list.add(7);
		list.add(10);
		list.reverse();
		String str = "";
		Iterator it = list.iterator();
		while(it.hasNext()){
			str  = str + it.next() + " ";
		}
		Assert.assertEquals("10 7 3 ", str);
	}
	
	@Test
	public void testRemoveFirstHalf(){
		list.add(2);
		list.add(5);
		list.add(7);
		list.add(8);
		list.add(10);
//		Object[] obj = new Object[list.size() / 2];
		Object[] obj = new Object[list.size() / 2 + 1];
		list.removeFirstHalf();
		for(int i = 0; i < obj.length; i++){
			obj[i] = list.get(i);
		}
//		Assert.assertArrayEquals(new Object[]{7, 8}, obj);
		Assert.assertArrayEquals(new Object[]{7, 8, 10}, obj);
	}
	
	@Test
	public void testRemove(){
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.remove(3, 4);
		Object[] obj = new Object[4];
		for (int i = 0; i < obj.length; i++) {
			obj[i] = list.get(i);
		}
		Assert.assertArrayEquals(new Object[]{0, 1, 2, 7}, obj);
	}
	
	@Test
	public void testGetElements(){
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(701);
		LinkedList listB = new LinkedList();
		listB.add(1);
		listB.add(3);
		listB.add(4);
		listB.add(6);
		int[] result = LinkedList.getElements(list, listB);
		Assert.assertArrayEquals(new int[]{101, 301, 401, 601}, result);
	}
	
	@Test
	public void testSubtract(){
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(701);
		LinkedList listB = new LinkedList();
		listB.add(11);
		listB.add(201);
		listB.add(601);
		list.subtract(listB);
		Object[] obj = new Object[list.size()]; 
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
			obj[i] = list.get(i);
		}
		Assert.assertArrayEquals(new Object[]{101, 301, 401, 501, 701}, obj);
	}

	@Test
	public void testRemoveDuplicateValues(){
		list.add(11);
		list.add(11);
		list.add(11);
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(201);
		list.add(201);
		list.add(301);
		list.removeDuplicateValues();
		Object[] obj = new Object[list.size()]; 
		for(int i = 0; i < list.size(); i++){
			obj[i] = list.get(i);
		}
		Assert.assertArrayEquals(new Object[]{11, 101, 201, 301}, obj);
	}
	
	@Test
	public void testRemoveRange(){
		list.add(1);
		list.add(5);
		list.add(7);
		list.add(9);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(20);
		list.add(30);
		list.removeRange(8, 17);
		Object[] obj = new Object[list.size()]; 
		for(int i = 0; i < list.size(); i++){
			obj[i] = list.get(i);
		}
		Assert.assertArrayEquals(new Object[]{1, 5, 7, 15, 20, 30}, obj);
	}
	
	@Test
	public void testIntersection(){
		list.add(1);
		list.add(2);
		list.add(4);
		list.add(7);
		list.add(9);
		list.add(11);
		list.add(15);
		list.add(20);
		LinkedList list1 = new LinkedList();
		list1.add(2);
		list1.add(3);
		list1.add(5);
		list1.add(6);
		list1.add(7);
		list1.add(8);
		list1.add(11);
		LinkedList result = list.intersection(list1);
		Object[] obj = new Object[result.size()]; 
		for(int i = 0; i < result.size(); i++){
			obj[i] = result.get(i);
		}
		Assert.assertArrayEquals(new Object[]{2, 7, 11}, obj);
	}
	
}



