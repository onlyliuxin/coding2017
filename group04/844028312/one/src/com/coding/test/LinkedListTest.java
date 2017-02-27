package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;
import com.coding.basic.Stack;

public class LinkedListTest {
	private LinkedList linkedList;
	@Before
	public void setUp() throws Exception {
		linkedList =new LinkedList();
		for(int i=0;i<10;i++){
			linkedList.add(i);
		}
	}
	@Test
	public void addWithIndex() {
		linkedList.add(4, "a");
		Object o=linkedList.get(4);
		Assert.assertEquals("a",o);
	}
	@Test
	public void get() {
		Assert.assertEquals(1,linkedList.get(1));
	}
	@Test
	public void size() {
		Assert.assertEquals(10,linkedList.size());
	}
	@Test
	public void addFirst() {
		linkedList.addFirst("one");
		Object o=linkedList.get(0);
		Assert.assertEquals("one",o);
	}
	@Test
	public void addLast() {
		linkedList.addLast("last");
		Object o=linkedList.get(10);
		Assert.assertEquals("last",o);
	}
	@Test
	public void removeFirst() {
		Assert.assertEquals(0,linkedList.removeFirst());
	}
	@Test
	public void removeLast() {
		Assert.assertEquals(9,linkedList.removeLast());
	}
	@Test
	public void iterator() {
		Iterator it=linkedList.iterator();
		int i=0;
		while(it.hasNext()){
			Assert.assertEquals(it.next(),linkedList.get(i));
			i++;
		}
	}

}
