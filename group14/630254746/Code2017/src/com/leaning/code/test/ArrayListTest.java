package com.leaning.code.test;

import org.junit.Test;

import com.leaning.code.ArrayList;
import com.leaning.code.LinkedList;

public class ArrayListTest {

	@Test
	public void test01(){
		ArrayList list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		
		System.out.println(list.remove(0));
		System.out.println(list);
		
	}
	
	@Test
	public void test02(){
		LinkedList list = new LinkedList();
		list.add("a");
		list.add("b");
		list.add("c");
		
		list.add(2, "d");
		
		System.out.println(list.remove(0));
		System.out.println(list.get(0));
		System.out.println(list.get(2));
		
		
	}
}
