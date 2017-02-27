package com.nitasty.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.nitasty.util.ArrayList;
import com.nitasty.util.Iterator;
import com.nitasty.util.LinkedList;

public class LinkedListTest {
	
	private LinkedList list;
	
	@Before
	public void init(){
		list=new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
	}
	
	@Test
	public void testGet() {
		IndexOutOfBoundsException tx=null;
		for (int i = 0; i < 100; i++) {
			Assert.assertEquals(i, list.get(i));
		}

		try {
			list.get(100);
		} catch (IndexOutOfBoundsException e) {
			tx=e;
		}
		Assert.assertEquals(IndexOutOfBoundsException.class,tx.getClass());
	}

	@Test
	public void testRemoveInt() {
		for (int i = 99; i >= 0; i--) {
			Assert.assertEquals(i, list.remove(i));
		}
	}

	@Test
	public void testSize() {
			Assert.assertEquals(100, list.size());
	}

	@Test
	public void testAddFirst() {
		list.addFirst(-1);
		for (int i = 0; i < 101; i++) {
			Assert.assertEquals(i-1, list.get(i));
		}
	}

	@Test
	public void testAddLast() {
		
		for (int i = 100; i < 1000; i++) {
			list.addLast(i);
		}

		for (int i = 0; i < 1000; i++) {
			Assert.assertEquals(i, list.get(i));
		}
	}

	@Test
	public void testAddBefore() {
			list.addBefore(66,list.node(3));
			Assert.assertEquals(66, list.get(3));
	}

	@Test
	public void testAddAfter() {
		list.addAfter(66,list.node(3));
		Assert.assertEquals(66, list.get(4));
	}

	@Test
	public void testIsEmpty() {
		list.clear();
		Assert.assertEquals(true, list.isEmpty());
	}

	@Test
	public void testContains() {
		for (int i = 0; i < 100; i++) {
			Assert.assertEquals(true, list.contains(i));
			Assert.assertEquals(false, list.contains(i+100));
		}
	}


	@Test
	public void testAddIntObject() {
		list.add(20,"test");
		Assert.assertEquals("test", list.get(20));
	}

	@Test
	public void testRemoveObject() {
		list.remove(30);
		Assert.assertEquals(31, list.get(30));
	}
	
	@Test
	public void testSet() {
		for (int i = 0; i < 100; i++) {
			list.set(i, i+100);
			Assert.assertEquals(i+100, list.get(i));
		}
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
