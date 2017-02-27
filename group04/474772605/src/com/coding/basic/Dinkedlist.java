package com.coding.basic;

public class Dinkedlist {
public static void main(String[] args) {
	LinkedList linkedlist = new LinkedList();
	String o = "1234";
	String a = "aaaaaaaa";
	String v = "vvvvvvvv";
	linkedlist.add(o);
	linkedlist.add(1,a);
	linkedlist.add(2,v);
	linkedlist.removeLast();
	for(int i =0 ; i<linkedlist.size();i++){
		System.out.println(linkedlist.get(i));
	}

	

	
	
}
}
