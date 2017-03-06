package com.github.chaoswang.learning.java.collection.myown;

import org.junit.Assert;
import org.junit.Test;

public class MyLinkedListTest {

	@Test
	public void testAdd(){
		MyLinkedList<String> myList = new MyLinkedList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		Assert.assertEquals(3, myList.size());
		myList.add("4");
		Assert.assertEquals(4, myList.size());
		System.out.println(myList);
		String str = myList.get(2);
		Assert.assertEquals("3", str);
		
	}
	
	@Test
	public void testInsert(){
		MyLinkedList<String> myList = new MyLinkedList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("4");
		String str = myList.get(2);
		Assert.assertEquals("4", str);
		myList.add(2,"3");
		str = myList.get(2);
		Assert.assertEquals("3", str);
	}
	
	@Test
	public void testAddFirst(){
		MyLinkedList<String> myList = new MyLinkedList<String>();
		myList.add("2");
		myList.add("3");
		myList.add("4");
		System.out.println(myList);
		Assert.assertEquals("2", myList.get(0));
		myList.addFirst("1");
		Assert.assertEquals("1", myList.get(0));
		System.out.println(myList);
	}
	
	@Test
	public void testRemoveFirst(){
		MyLinkedList<String> myList = new MyLinkedList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		String str = myList.removeFirst();
		System.out.println(myList);
		Assert.assertEquals("1", str);
		Assert.assertEquals("2", myList.get(0));
	}
	
	@Test
	public void testRemove(){
		MyLinkedList<String> myList = new MyLinkedList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		String str = myList.remove(2);
		Assert.assertEquals("3", str);
		str = myList.get(2);
		Assert.assertEquals("4", str);
		Assert.assertEquals(3, myList.size());
	}
	
}
