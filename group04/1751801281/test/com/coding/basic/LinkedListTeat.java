package com.coding.basic;

import org.junit.Test;

public class LinkedListTeat {

	@Test
	public void testLinkedList(){
		LinkedList list = new LinkedList();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add(1, "3");
		list.addFirst("4");
		list.addLast("5");
		list.removeFirst();
		list.removeLast();
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		System.out.println(list.get(1));
		System.out.println("========");
		
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.print(it.next()+" ");
		}
	}
}
