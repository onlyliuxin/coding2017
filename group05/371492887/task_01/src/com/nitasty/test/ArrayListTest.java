package com.nitasty.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.nitasty.util.ArrayList;
import com.nitasty.util.Iterator;

public class ArrayListTest {
	
	private ArrayList list;
	
	@Before
	public void init(){
		list=new ArrayList();
		for (int i = 0; i < 100; i++) {
			list.add(i); 
		}
	}
	
	@Test
	public void testAddObject() {
		list.add(100);
		Assert.assertEquals(101, list.size());
	}

	@Test
	public void testAddIntObject() {
		list.add(3,"test");
		Assert.assertEquals("test", list.get(3));
	}

	@Test
	public void testRemoveInt() {
		list.add(3,"test");
		list.remove(3);
		Assert.assertEquals(3, list.get(3));
	}

	@Test
	public void testRemoveObject() {
		list.add(0,"test");
		list.remove("test");
		Assert.assertEquals(0, list.get(0));
	}


	@Test
	public void testIsEmpty() {
		list.clear();
		Assert.assertEquals(true, list.isEmpty());
	}

	@Test
	public void testContains() {
		Assert.assertEquals(false, list.contains("test"));
		list.add("test");
		Assert.assertEquals(true, list.contains("test"));
	}

	

	@Test
	public void testSet() {
		Assert.assertEquals(true, list.contains(3));
		list.set(3, "test");
		Assert.assertEquals(true, list.contains("test"));
		Assert.assertEquals(false, list.contains(3));
	}

	@Test
	public void testIndexOf() {
		list.set(3, "test");
		Assert.assertEquals(3, list.indexOf("test"));
	}

	@Test
	public void testLastIndexOf() {
		list.set(3, "test");
		list.set(33, "test");
		Assert.assertEquals(33, list.lastIndexOf("test"));
	}
	
	@Test
	public void testHasNext(){
		int i=0;
		for(Iterator it=list.iterator();it.hasNext();i++){
			Assert.assertEquals(i, it.next());
//			System.out.println(it.next());
		}
	}
}
