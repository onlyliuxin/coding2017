package com.coding.basic;

import junit.framework.Assert;

import org.junit.Test;

public class LinkedListTest extends LinkedList {

	@Test
	public void test() {
		List list = new LinkedList();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(3, 33);
		list.add(0, 100);
		list.add(8,800);
		Assert.assertEquals(9, list.size());
		Assert.assertEquals(100, list.get(0));
		Assert.assertEquals(0, list.get(1));
		Assert.assertEquals(1, list.get(2));
		Assert.assertEquals(2, list.get(3));
		Assert.assertEquals(33, list.get(4));
		Assert.assertEquals(3, list.get(5));
		Assert.assertEquals(4, list.get(6));
		Assert.assertEquals(800, list.get(8));
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	@Test
	public void testReverse() {
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next());
		}
		System.out.println();
		list.reverse();
		Iterator iterator2 = list.iterator();
		while (iterator2.hasNext()) {
			System.out.print(iterator2.next());
		}
		System.out.println();
		list.removeFirstHalf();
		Iterator iterator3 = list.iterator();
		while (iterator3.hasNext()) {
			System.out.print(iterator3.next());
		}
		System.out.println();
		
	}
	@Test
	public void testRemove() {
		LinkedList list = new LinkedList();
		int i = 0;
		while( i < 20) {
			list.add(i);
			i ++;
		}
		list.remove(3, 10);
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next());
		}
	}
	
}
