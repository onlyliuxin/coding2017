package com.danny.hw1.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.danny.hw1.ArrayList;
import com.danny.hw1.Iterator;
import com.danny.hw1.LinkedList;

public class LinkedListTest {

	static Object[] Data = new Object[]{1,2,3,4,5,6,7,8};
	LinkedList test;
	@Before
	public void setUp() throws Exception{
		test = new LinkedList();
		for(Object data: Data){
			test.add(data);
		}
	}

	@Test
	public void testAddObject() {
		int len = test.size();
		test.add(10);
		assertEquals(len, test.size()-1);
	}

	@Test
	public void testAddIntObject() {
		int len = test.size();
		test.add(len, 10);
		
		assertEquals(len, test.size()-1);
	}

	@Test
	public void testGet() {
		assertEquals(Data[3], test.get(3));
	}

	@Test
	public void testRemove() {
		System.out.println(Data[4]);
		assertEquals(Data[4], test.remove(4));
		assertEquals(Data.length -1, test.size());
	}

	@Test
	public void testSize() {
		assertEquals(Data.length, test.size());
	}

	@Test
	public void testIterator() {
		Iterator iterator =test.iterator();
		for(Object i:Data){
			if(iterator.hasNext()){
				assertEquals(i,iterator.next());
			}
		}
	}

	@Test
	public void testAddFirst() {
		test.addFirst(10);
		assertEquals(10, test.get(0));
	}

	@Test
	public void testRemoveFirst() {
		Object ans=test.get(0);
		assertEquals(ans,test.removeFirst());
		assertEquals(Data.length-1,test.size());
	}

	@Test
	public void testRemoveLast() {
		Object ans=test.get(Data.length - 1);
		assertEquals(ans,test.removeLast());
		assertEquals(Data.length-1,test.size());
	}

}
