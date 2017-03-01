package com.coding.test;

import org.junit.Test;

import com.coding.basic.ArrayList;

public class TestArrayList {

	@Test
	public void test() {
		ArrayList list = new ArrayList(1);
		System.out.println(list.getLength());
		System.out.println(list.size());
		System.out.println("-------------");
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		System.out.println(list.size());
		System.out.println(list.getLength());
		System.out.println("---------------");
		
//		list.add(7, "b");
//		System.out.println(list.size());
//		System.out.println(list.getLength());
		
//		System.out.println("------------");
//		
//		System.out.println(list.get(0));
//		System.out.println(list.get(1));
//		System.out.println("-----------------");
//		
//		list.remove(1);
//		System.out.println(list.get(0));
//		System.out.println(list.get(1));
//		System.out.println(list.get(2));
//		System.out.println(list.size());
//		System.out.println(list.getLength());
		while(list.hasNext()){
			System.out.println(list.next());
		}
		
		
		
	}

}
