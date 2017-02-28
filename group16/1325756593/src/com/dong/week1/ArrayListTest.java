package com.dong.week1;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayListTest {

	//@Test
	public void testAddObject() {
		ArrayList arrayList = new ArrayList();
		for(int i=0;i<=200;i++){
			arrayList.add(i);
		}
		System.out.println(arrayList);
	}

	//@Test
	public void testAddIntObject() {
		ArrayList arrayList = new ArrayList();
		for(int i=0;i<=2;i++){
			arrayList.add(i);
		}
		arrayList.add(1,100);
		arrayList.add(1, 1000);
		System.out.println(arrayList);
	}

//	@Test
	public void testGet() {
		ArrayList arrayList = new ArrayList();
		for(int i=0;i<=200;i++){
			arrayList.add(i);
		}
		//System.out.println(arrayList.get(-1));
		//System.out.println(arrayList.get(50));
		System.out.println(arrayList.get(200));
		//System.out.println(arrayList.get(300));
		
		
	}

	@Test
	public void testRemove() {
		ArrayList arrayList = new ArrayList();
		for(int i=0;i<=10;i++){
			arrayList.add(i);
		}
		arrayList.remove(1);
		arrayList.remove(1);
		System.out.println(arrayList);
	}

//	@Test
	public void testSize() {
		ArrayList arrayList = new ArrayList();
		for(int i=0;i<=10;i++){
			arrayList.add(i);
		}
		System.out.println(arrayList.size());
	}

}
