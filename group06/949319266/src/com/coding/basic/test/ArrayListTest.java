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
		
		System.out.println("�±�Ϊ3��Ԫ��Ϊ��"+list.get(3));
		System.out.println("���鳤��"+list.size());
		list.remove(2);
		System.out.println("remove������鳤��"+list.size());
		
		for(int i = 0; i < list.size() ; i++) {
			System.out.print(list.get(i)+",");
		}
		list.add(3, "g");
		System.out.println("");
		System.out.println("����������Ϊ��");
		
		for(int i = 0; i < list.size() ; i++) {
			System.out.print(list.get(i)+",");
		}
		
		
	}

}
