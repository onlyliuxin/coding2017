package com.coding.test;

import org.junit.Assert;
import org.junit.Test;

import com.coding.basic.LinkedList;

public class LinkedListTest {

	
	@Test
	public void test01(){
		LinkedList linkedList = new LinkedList();
		linkedList.add(111);
		linkedList.add("hello");
		Assert.assertEquals(linkedList.get(0), 111);
		Assert.assertEquals(linkedList.get(1), "hello");
		linkedList.addFirst("000");
		Assert.assertEquals(linkedList.get(0), "000");
		linkedList.add(0, "222");
		Assert.assertEquals(linkedList.get(0), "222");
		linkedList.add(1,"333");
		Assert.assertEquals(linkedList.get(1), "333");
		linkedList.add(2, 444);
		Assert.assertEquals(linkedList.get(2), 444);
	}
	
	@Test
	public void test02(){
		LinkedList linkedList = new LinkedList();
 		linkedList.add(0,111);
		linkedList.add(1,222);
		linkedList.remove(0);
		Assert.assertEquals(linkedList.get(0), 222);
		for(int i=0;i<linkedList.size();i++){
			System.out.println(linkedList.get(i));
		}
		linkedList.add(1,333);
		Assert.assertEquals(linkedList.size(), 2);
		linkedList.remove(1);
		Assert.assertEquals(linkedList.size(), 1);
		
	}
}
