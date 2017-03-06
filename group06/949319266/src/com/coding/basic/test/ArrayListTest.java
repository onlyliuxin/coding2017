package com.coding.basic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.ArrayList;

public class ArrayListTest {

	@Test
	public void test() {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		System.out.println("下标为3的元素为："+list.get(3));
		System.out.println("数组长度"+list.size());
		list.remove(2);
		System.out.println("remove后的数组长度"+list.size());
		
		for(int i = 0; i < list.size() ; i++) {
			System.out.print(list.get(i)+",");
		}
		list.add(3, "g");
		System.out.println("");
		System.out.println("插入后的数组为：");
		
		for(int i = 0; i < list.size() ; i++) {
			System.out.print(list.get(i)+",");
		}
		
		
	}

}
