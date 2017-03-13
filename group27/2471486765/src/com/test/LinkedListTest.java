package com.test;

import org.junit.Test;


import com.mycoding.Iterator;
import com.mycoding.LinkedList;
import com.mycoding.List;

public class LinkedListTest {
	
	@Test
	public void Test1() {
		List list = new LinkedList();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Object str = it.next();
			System.out.println(str);
		}
	}
	
	@Test
	public void Test2() {
		List list = new LinkedList();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		System.out.println(list);
		System.out.println(list.size());
		
		list.remove(0);
		list.remove(5);
		list.remove(8);
		System.out.println(list);
		System.out.println(list.size());
		
		list.add(0,"aaaa");
		list.add(3, "bbbb");
		list.add(10,"cccc");
		System.out.println(list);
		System.out.println(list.size());
		
		((LinkedList) list).addFirst("0000");
		((LinkedList) list).addLast("zzzz");
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(list.get(0));
		System.out.println(list.get(8));	
	}
}
