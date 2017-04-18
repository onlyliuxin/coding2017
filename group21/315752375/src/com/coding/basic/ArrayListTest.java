package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayListTest {

	@Test
	public void testArrayList() {
		fail("Not yet implemented");
		ArrayList arrayList=new ArrayList();
	}

	@Test
	public void testArrayListInt() {
		fail("Not yet implemented");
		ArrayList arrayList=new ArrayList(10);
	}

	@Test
	public void testAddObject() {
		fail("Not yet implemented");
		ArrayList arrayList=new ArrayList();
		arrayList.add("1");
		System.out.println(arrayList.get(0));
	}

	@Test
	public void testAddIntObject() {
		fail("Not yet implemented");
		ArrayList arrayList=new ArrayList();
		arrayList.add(0,"1");
		arrayList.add(1,"2");
		arrayList.add(2,"3");
		arrayList.add(3,"4");
		arrayList.add(4,"5");
		System.out.println(arrayList.get(4));
		arrayList.add(4,"6");
		System.out.println(arrayList.get(4));
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
		ArrayList arrayList=new ArrayList();
		arrayList.add(0,"1");
		arrayList.add(1,"2");
		arrayList.add(2,"3");
		arrayList.add(3,"4");
		arrayList.add(4,"5");
		System.out.println(arrayList.get(4));
		arrayList.add(4,"6");
		System.out.println(arrayList.get(4));
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
		ArrayList arrayList=new ArrayList();
		arrayList.add(0,"1");
		arrayList.add(1,"2");
		arrayList.add(2,"3");
		arrayList.add(3,"4");
		arrayList.add(4,"5");
		System.out.println(arrayList.get(4));
		arrayList.remove(4);
		//System.out.println(arrayList.get(4));
		
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
		ArrayList arrayList=new ArrayList();
		System.out.println(arrayList.size());
		arrayList.add("sdfasdf");
		System.out.println(arrayList.size());
	}

	@Test
	public void testGrow() {
		fail("Not yet implemented");
		ArrayList arrayList=new ArrayList(1);
		arrayList.add("1");
		arrayList.add("2");
		arrayList.add("2");
		arrayList.add("2");
		arrayList.add("2");
	}

	@Test
	public void testIterator() {
		ArrayList arrayList=new ArrayList();
		arrayList.add("1");
		arrayList.add("2");
		arrayList.add("3");
		arrayList.add("4");
		arrayList.add("5");
		Iterator iterator=arrayList.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}
