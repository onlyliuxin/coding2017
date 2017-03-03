package com.github.chaoswang.learning.java.collection.myown;


import org.junit.Assert;
import org.junit.Test;

import com.github.chaoswang.learning.java.collection.myown.MyArrayList;

public class MyArrayListTest {
	
	@Test
	public void testAdd(){
		MyArrayList<String> myList = new MyArrayList<String>(3);
		myList.add("1");
		myList.add("2");
		myList.add("3");
		Assert.assertEquals(3, myList.size());
		myList.add("4");
		Assert.assertEquals(4, myList.size());
		String str = myList.get(2);
		Assert.assertEquals("3", str);
		
	}
	
	@Test
	public void testInsert(){
		MyArrayList<String> myList = new MyArrayList<String>(3);
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
	public void testRemove(){
		MyArrayList<String> myList = new MyArrayList<String>(3);
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
