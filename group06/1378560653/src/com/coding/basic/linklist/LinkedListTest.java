package com.coding.basic.linklist;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;


public class LinkedListTest {
	@Test
	public void testAdd() {
		LinkedList  l1 = new LinkedList();
		
		Assert.assertEquals("[]", l1.toString());
		
		l1.add(1);	
		l1.add(2);
		l1.add(3);
		l1.add(4);
		
		Assert.assertEquals("[1,2,3,4]", l1.toString());
	}
	
	@Test
	public void TestSize(){
		LinkedList  l2 = new LinkedList();
		
		Assert.assertEquals(0, l2.size());
		
		l2.add(1);	
		l2.add(2);
		l2.add(3);
		
		Assert.assertEquals(3, l2.size());	
	}
	
	@Test
	public void testAddIndex(){
		LinkedList  l3 = new LinkedList();
		
		l3.add(1);
		l3.add(2);
		l3.add(3);
		l3.add(4);
		
		l3.add(2, 6);
		
		Assert.assertEquals("[1,2,6,3,4]", l3.toString());
	}
	
	@Test
	public void testGet(){
		LinkedList  l4 = new LinkedList();
		l4.add(1);
		l4.add(2);
		l4.add(3);
		l4.add(4);
		
		Assert.assertEquals(3, l4.get(2));
	}
	
	@Test
	public void testRemove(){
		LinkedList  l5 = new LinkedList();
		l5.add(1);
		l5.add(2);
		l5.add(3);
		l5.add(4);
		
		l5.remove(3);
		
		Assert.assertEquals("[1,2,3]", l5.toString());
	}
	
	@Test
	public void testAddFirst(){
		LinkedList  l6 = new LinkedList();
		
		l6.addFirst(1);
		
		Assert.assertEquals("[1]", l6.toString());
		
		l6.add(2);
		l6.add(3);
		
		l6.addFirst(2);
		
		Assert.assertEquals("[2,1,2,3]", l6.toString());
	}
	@Test
	public void testAddLast(){
		LinkedList  l7 = new LinkedList();
		
		l7.addLast(1);
		
		Assert.assertEquals("[1]", l7.toString());
		
		l7.add(2);
		l7.add(3);
		
		l7.addLast(4);
		
		Assert.assertEquals("[1,2,3,4]", l7.toString());
	}
	@Test
	public void testRmemoveFirst(){
		LinkedList  l8 = new LinkedList();
		
		l8.removeFirst();
		
		Assert.assertEquals("[]", l8.toString());
		
		l8.add(2);
		l8.add(3);
		
		l8.removeFirst();
		
		Assert.assertEquals("[3]", l8.toString());
	}
	
	@Test
	public void testRmemoveLast(){
		LinkedList  l9 = new LinkedList();
		
		l9.removeLast();
		
		Assert.assertEquals("[]", l9.toString());
		
		l9.add(2);
		l9.add(3);
		
		l9.removeLast();
		
		Assert.assertEquals("[2]", l9.toString());
	}
	
	@Test
	public void testReverse(){
		LinkedList list1 = new LinkedList();
		
		list1.reverse();
		
		Assert.assertEquals("[]", list1.toString());
		
		list1.add(1);
		list1.add(2);
		list1.add(3);
		
		list1.reverse();
		
		Assert.assertEquals("[3,2,1]", list1.toString());
	}
	
	@Test
	public void testRemoveFirstHalf(){
		LinkedList list2 = new LinkedList();
		list2.removeFirstHalf();
		Assert.assertEquals("[]", list2.toString());
		
		list2.add(1);
		list2.removeFirstHalf();
		Assert.assertEquals("[1]", list2.toString());
		
		list2.add(2);
		list2.add(3);
		list2.add(4);
		list2.removeFirstHalf();
		
		Assert.assertEquals("[3,4]", list2.toString());
		
		list2.add(5);
		list2.removeFirstHalf();
		
		Assert.assertEquals("[4,5]", list2.toString());
		
	}
	@Test
	public void testRemoveLength(){
		LinkedList list3 = new LinkedList();
		list3.remove(1,3);
		Assert.assertEquals("[]", list3.toString());
		
		list3.add(1);
		list3.add(2);
		list3.add(3);
		list3.add(4);
		list3.add(5);
		list3.remove(0,1);
		
		Assert.assertEquals("[2,3,4,5]", list3.toString());
	}
	
	@Test
	public void testGetElements(){
		LinkedList list4 = new LinkedList();
		LinkedList list = new LinkedList();
		
		int[] array = null;
		array = list4.getElements(list);
		
		assertArrayEquals(new int[0], array);
		
		list4.add(11);
		list4.add(101);
		list4.add(201);
		list4.add(301);
		list4.add(401);
		list4.add(501);
		list4.add(601);
		list4.add(701);
		list.add(1);
		list.add(3);
		list.add(12);
		list.add(6);
		
		array = list4.getElements(list);
		int[] result = {101,301,601};
		
		assertArrayEquals(result, array);
		
	}
	@Test
	public void testSubtract(){
		LinkedList list5 = new LinkedList();
		LinkedList list = new LinkedList();
		
		list5.subtract(list);
		
		Assert.assertEquals("[]", list5.toString());
		
		list5.add(11);
		list5.add(101);
		list5.add(201);
		list5.add(301);
		list5.add(401);
		list5.add(501);
		list5.add(601);
		list5.add(701);
		list.add(11);
		list.add(301);
		list.add(12);
		list.add(401);
		
		list5.subtract(list);
		
		Assert.assertEquals("[101,201,501,601,701]", list5.toString());
	}
	@Test
	public void testRemoveDuplicateValues(){
		LinkedList list6 = new LinkedList();
		
		list6.removeDuplicateValues();
		
		Assert.assertEquals("[]", list6.toString());
		
		list6.add(1);
		list6.add(2);
		list6.add(2);
		list6.add(10);
		
		list6.removeDuplicateValues();
		
		Assert.assertEquals("[1,2,10]", list6.toString());
	}
	
	@Test
	public void testRemoveRange(){
		LinkedList list7 = new LinkedList();
		
		list7.removeRange(0, 10);
		
		Assert.assertEquals("[]", list7.toString());
		
		list7.add(1);
		list7.add(2);
		list7.add(3);
		list7.add(4);
		list7.add(5);
		list7.add(6);
		list7.add(12);
		
		list7.removeRange(3, 10);
		
		Assert.assertEquals("[1,2,12]", list7.toString());
	}
	
	@Test
	public void testIntersection(){
		LinkedList list8 = new LinkedList();
		
		list8.add(1);
		list8.add(2);
		list8.add(3);
		list8.add(4);
		
		LinkedList list9 = new LinkedList();
		list9.add(2);
		list9.add(3);
		list9.add(4);
		list9.add(8);
		
		LinkedList result = new LinkedList();
		result = list8.intersection(list9);
		
		Assert.assertEquals("[2,3,4]", result.toString());
		
	}
}
	
