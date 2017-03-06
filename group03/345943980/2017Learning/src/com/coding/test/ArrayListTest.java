package com.coding.test;

import org.junit.Test;

import com.coding.basic.ArrayList;

public class ArrayListTest {

	@Test
	public void test01(){
		ArrayList arrayList = new ArrayList();
		arrayList.add(1);
		arrayList.add(100);
		arrayList.add(0, 1000);
		System.out.println(arrayList.size());
		for(int i =0;i<arrayList.size();i++){
			System.out.println(arrayList.get(i));
		}
		arrayList.remove(0);
		System.out.println(arrayList.size());
		for(int i =0;i<arrayList.size();i++){
			System.out.println(arrayList.get(i));
		}
	}
	
	@Test
	public void test02(){
		Object[] arrays = new Object[1];
		arrays[0] = "helloObj";
		//arrays[1] = "howAreYou";
		System.out.println(arrays[0]);
		System.out.println(arrays.length);
	}
	
	@Test
	public void test03(){
		java.util.ArrayList<Object> array = new java.util.ArrayList<Object>();
		array.add(1);
		array.add(1, 20);
		System.out.println(array.size());
		for(Object o:array){
			System.out.println(o.toString());
		}
		//System.out.println(array.get(100));
	}
	

}
