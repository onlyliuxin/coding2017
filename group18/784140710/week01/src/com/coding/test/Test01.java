package com.coding.test;

import org.junit.Test;

import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;
import com.coding.basic.Stack;

public class Test01 {

	@Test
	public void testStack(){
		
		Stack stack = new Stack();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		Object o = stack.peek();
		Object o1 = stack.pop();
		System.out.println();
	}
	
	@Test
	public void test01(){
		ArrayList list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("asd");
		
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.println((String)it.next());
		}
//		list.add(2,"3");
//		list.remove(2);
//		Object o = list.get(4);
//		System.out.println(o);
	}
	
	@Test
	public void test02(){
		LinkedList list = new LinkedList();
		list.add("1");
		list.add("2");
		list.add("3");
//		list.addFirst("0");
		list.add("4");
		list.add("5");
//		list.removeFirst();
//		Object o1 = list.removeLast();
//		Object o = list.remove(3);
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
}
