package com.coding.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.ArrayList;

public class ArrayListTest {
	ArrayList listExpect = new ArrayList();
    @Before  
    public void before() throws Exception {  
    	listExpect.add("0");
    	listExpect.add("1");
    	listExpect.add("2");
    } 	
	@Test
	public void test() {
		System.out.println("顺序添加");
	}
	@Test
	public void test1() {
		System.out.println("插入");
		listExpect.add(0, "Str1_set");
	}
	@Test
	public void test2() {
		System.out.println("删除");
		listExpect.remove(0);
	}
	
    @After  
    public void after(){  
		for (int i = 0 ;i < listExpect.size(); i++) {
			System.out.println(listExpect.get(i));
		}
		System.out.println("长度："+listExpect.size());
		System.out.println("**************");
    }  	

}
