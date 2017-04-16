package com.test;

import org.junit.Test;

import com.mycoding.ArrayList;
import com.mycoding.Iterator;
import com.mycoding.List;

public class ArrayListTest {
	
	@Test
	public void Test1() {
		List list = new ArrayList();
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
		
		list.remove(2);
		
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Object str = it.next();
			System.out.println(str);
		}
	}
	
	
	@Test
	public void Test2() {
		List list = new ArrayList();
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
		list.remove(9);
		System.out.println(list);
		System.out.println(list.size());
		
		list.add(0,"yyyyy");
		list.add(3, "aaaaa");
		list.add(11,"bbbb");
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(list.get(0));
		System.out.println(list.get(7));
	}
	
	
	
}
