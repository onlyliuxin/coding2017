package com.coding.test;

import com.coding.basic.LinkedList;





public class Test {
	
	public static void main(String[] args) {
		LinkedList link = new LinkedList();
		link.add(1);
		link.add("bbb");
		link.add("ccc");
		link.reverse();
		System.out.println(link.get(0));
		System.out.println(link.get(1));
		System.out.println(link.get(2));
	}

}
