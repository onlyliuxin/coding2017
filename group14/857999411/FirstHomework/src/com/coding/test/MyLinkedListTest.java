package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.MyLinkedList;

public class MyLinkedListTest {

	@Test
	public void test() {
		MyLinkedList sl=new MyLinkedList();
		sl.addFirst("java003");
		sl.addFirst("java002");
		sl.addFirst("java001");
		sl.addLast("java004");
		sl.addLast("java005");
		sl.addLast("java006");
		sl.add(6, "java007");
		
		
		System.out.println(sl.get(5));
		System.out.println(sl.remove(4));
		System.out.println(sl.size());
		System.out.println(sl.size);	
		sl.removeFirst();
		System.out.println(sl.size);	
		
		System.out.println(sl.removeFirst());	
		System.out.println(sl.removeFirst());	
		System.out.println(sl.removeFirst());	
		
		System.out.println(sl.removeLast());	
		System.out.println(sl.removeLast());	
	}

}
