package com.coding.basic;

import org.junit.Test;

public class ArrayListTest {

	@Test
	public void testArrayList(){
		ArrayList list = new ArrayList();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add(2,"9");
		list.add("10");
		list.add(3,"11");
		System.out.println(list.get(3));
		System.out.println("======");
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.print(it.next()+" ");
		}
		
	}
}
