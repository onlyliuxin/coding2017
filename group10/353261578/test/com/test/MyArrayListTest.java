package com.test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sx.structures.MyArrayList;
import com.sx.structures.MyList;

public class MyArrayListTest {

	private MyArrayList list ;

	@Test
	public void testAddObject() {
		for(int j=0;j<12;j++){
			list.add(j);
		}
	}

	@Test
	public void testAddIntObject() {
		list.add(5, 12);
		list.add(10, 11);
	}

	@Test
	public void testGet() {
		System.out.println(list.get(5));
	}

	@Test
	public void testRemove() {
		System.out.println("\nremoved 5："+list.remove(5)+".");
	}

	@Test
	public void testSize() {
		System.out.println("\nlist.size:"+list.size());
	}
	
	@After
	public void Print(){
		System.out.println("最终结果：List:");
		PrintList(list);
	}
	@Before
	public void createlist(){
		list = new MyArrayList();
		for(int j=0;j<12;j++){
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
