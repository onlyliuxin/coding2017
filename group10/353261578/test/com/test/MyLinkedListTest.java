package com.test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sx.structures.MyLinkedList;
import com.sx.structures.MyList;

public class MyLinkedListTest {
	
	private MyLinkedList list;

	@Test
	public void testAddObject() {
		list.add(3);
	}

	@Test
	public void testAddIntObject() {
		list.add(0,"t-0");
		list.add(1, "t-1");
	}

	@Test
	public void testGet() {
		System.out.println(list.get(1));
	}

	@Test
	public void testRemove() {
		list.remove(0);
	}

	@Test
	public void testSize() {
		System.out.println();
		System.out.println("	list-size="+list.size());
	}

	@Test
	public void testAddFirst() {
		list.addFirst("t-1");
	}

	@Test
	public void testAddLast() {
		list.addLast("T-last");
	}

	@Test
	public void testRemoveFirst() {
		list.removeFirst();
	}

	@Test
	public void testRemoveLast() {
		list.removeLast();
	}

	@After
	public void Print(){
		System.out.println("\n操作之后，List:");
		PrintList(list);
	}
	@Before
	public void createlist(){
		list = new MyLinkedList();
		for(int j=0;j<11;j++){
			list.add(j);
		}
		System.out.println("初始list:");
		PrintList(list);
	}
	
	public static void PrintList(MyList list){
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
	}

}
