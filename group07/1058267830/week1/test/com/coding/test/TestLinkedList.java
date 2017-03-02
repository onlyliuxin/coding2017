package com.coding.test;

import org.junit.Test;

import com.coding.basic.LinkedList;
import com.coding.basic.Node;

public class TestLinkedList {

	@Test
	public void test() {
		LinkedList list = new LinkedList();
		System.out.println(list.size());
		System.out.println("-------------");
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add(2, "x");
//		
//		System.out.println(list.size());
//		list.printData();
//		System.out.println("------------");
//		
//		Node node = (Node)list.get(3);
//		System.out.println(node.getData());
//		System.out.println("----------------");
//		
//		Node node1 = (Node)list.remove(3);
//		System.out.println(list.size());
//		list.printData();
		
		System.out.println("---------------");
		while(list.hasNext()){
			Node node2 = (Node)list.next();
			System.out.println(node2.getData());
		}
	}

}
