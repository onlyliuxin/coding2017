package com.coding.test;

import org.junit.Test;

import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;
import com.coding.basic.Stack;

public class MyTest {
	
	@Test
	public void StackTest(){
		Stack stack = new Stack();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
	}
	
	
	@Test
	public void myTest(){
		ArrayList arrayList = new ArrayList();
		arrayList.add("a");
		arrayList.add("b");
		arrayList.add("c");
		arrayList.add("d");
		arrayList.add("e");
		arrayList.add(3,"f");
		Iterator iterator = arrayList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			
		}
	}
	
	@Test
	public void LinkedListTest(){
		LinkedList linkedList = new LinkedList();
		linkedList.add("a");
		linkedList.add("b");
		linkedList.addFirst("c");
		linkedList.addLast("d");
		//linkedList.remove(2);
		linkedList.removeLast();
		System.out.println(linkedList.size());
		//System.out.println(linkedList.get(2));
		Iterator iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}
	
}
