package com.coding.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	LinkedList linkedList = new LinkedList();
    @Before  
    public void before() throws Exception {  
		linkedList.add("0");
		linkedList.add("1");
		linkedList.add("2");
    } 	

	@Test
	public void test() {
		System.out.println("顺序添加");
	}
	@Test
	public void test1() {
		System.out.println("插入");
		linkedList.add(0, "add_0");
		linkedList.add(1, "add_1");
		linkedList.add(2, "add_2");
	}
	@Test
	public void test2() {
		linkedList.remove(0);
		System.out.println("删除");
	}
	
    @After  
    public void after(){  
		for (int i = 0 ;i < linkedList.size(); i++) {
			System.out.println(linkedList.get(i));
		}
		System.out.println("长度："+linkedList.size());
		System.out.println("**************");		
    }  

}
