package com.vvv.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	LinkedList list = null;
	
	@Before
	public void setUp() throws Exception {
	     list = new LinkedList();
	     list.add(100);
		 list.add(111);
		 list.add(222);
		 list.add(333);
		 list.add(444);
		 list.add(555);
		 list.add(666);
		 printList(list);
	}

	@After
	public void tearDown() throws Exception {
		printList(list);
		list = null;
	}
	
	@Test
	public void addTest() {
	}
	
	@Test
	public void addIndexTest() {
		 list.add(3, "3 444");
		 list.add(5, "5 add");
		 list.add(0, "0 add");
	}
	
	@Test
	public void removeTest() {
		 list.remove(4);
	}
	
	@Test
	public void removeFirstTest() {
		 list.removeFirst();
		 list.removeFirst();
	}
	
	@Test
	public void iteratorTest() {
		LinkedList list = new LinkedList();

		for (int i = 0; i < 5; i++) {
			list.add(i);
		}
		
		Iterator it = list.iterator();
		Assert.assertTrue(it.hasNext());

		int count = 0;
		while (it.hasNext()) {
			Object value = it.next();
			Assert.assertEquals(count, value);
			count++;
		}
	}
	
	private void printList(LinkedList list){
		if(list==null){
			return ;
		}		
		System.out.println("list size:"+list.size());
		for(int i=0; i<list.size(); i++){
			Object o = list.get(i);		
			System.out.println(o);
		}
	}
	
	@Test
	public void reverseTest(){
		list.reverse();		
	}		
	
	@Test
	public void removeFirstHalfTest(){
		list.removeFirstHalf();		
	}	
	
	@Test
	public void removeLengthTest(){
		list.remove(3,2);		
	}	
	
	@Test
	public void getElementsTest(){
		LinkedList ll = new LinkedList();		
		ll.add(0);
		ll.add(3);
		ll.add(2);
		
		int[] arr = list.getElements(ll);
		for(int i=0; i<arr.length; i++){
		    System.out.println("..."+arr[i]);
		}
	}	
	
	@Test
	public void subtractTest(){
		LinkedList ll = new LinkedList();
		ll.add(99);
		ll.add(111);
		ll.add(222);
		ll.add(333);
		printList(ll);
		list.subtract(ll);
	}	
	
	@Test
	public void removeDuplicateValuesTest(){
		list.add(1,111);
		list.addFirst(100);
		list.add(6,444);
		list.addLast(666);	
		printList(list);
		list.removeDuplicateValues();
	}	
	
	@Test
	public void removeRangeTest(){
		list.removeRange(200,500);
	}	

	@Test
	public void intersectionTest(){
		LinkedList ll = new LinkedList();
		ll.add(99);
		ll.add(111);
		ll.add(222);
		ll.add(333);
		printList(ll);
		LinkedList ret = list.intersection(ll);
		printList(ret);
	}
}
