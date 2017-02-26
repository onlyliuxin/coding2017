package com.github.congcongcong250.coding2017.basicTest;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.congcongcong250.coding2017.basic.ArrayList;
import com.github.congcongcong250.coding2017.basic.LinkedList;
import com.github.congcongcong250.coding2017.basic.Iterator;

public class LinkedListTest implements testCase {
	
	LinkedList testlist = new LinkedList();

	@Override
	@Before
	public void setUp() {
		for(int i = 0; i < 20;i++){
			testlist.add(i);
		}
	}

	@Override
	@After
	public void tearDown() {
		testlist.clear();
	}

	@Override
	@Test
	public void testAdd() {
		assertEquals(20,testlist.size());
		assertEquals(0,testlist.get(0));
		assertEquals(19,testlist.get(19));
		
		testlist.add(11, 100);
		assertEquals(21,testlist.size());
		assertEquals(5,testlist.get(5));
		assertEquals(100,testlist.get(11));
		assertEquals(18,testlist.get(19));
		
		testlist.addFirst(200);
		assertEquals(22,testlist.size());
		assertEquals(200,testlist.get(0));
		assertEquals(4,testlist.get(5));
		assertEquals(100,testlist.get(12));
		assertEquals(17,testlist.get(19));
		
		testlist.addLast(300);
		assertEquals(23,testlist.size());
		assertEquals(200,testlist.get(0));
		assertEquals(4,testlist.get(5));
		assertEquals(100,testlist.get(12));
		assertEquals(17,testlist.get(19));
		assertEquals(300,testlist.get(22));

	}

	@Override
	@Test
	public void testRemove() {
		assertEquals(20,testlist.size());
		assertEquals(0,testlist.get(0));
		assertEquals(19,testlist.get(19));
		
		testlist.remove(10);
		assertEquals(19,testlist.size());
		assertEquals(4,testlist.get(4));
		assertEquals(9,testlist.get(9));
		assertEquals(11,testlist.get(10));
		assertEquals(19,testlist.get(18));
		
		testlist.removeFirst();
		assertEquals(18,testlist.size());
		assertEquals(1,testlist.get(0));
		assertEquals(12,testlist.get(10));
		assertEquals(19,testlist.get(17));
		
		testlist.removeLast();
		assertEquals(17,testlist.size());
		assertEquals(1,testlist.get(0));
		assertEquals(12,testlist.get(10));
		assertEquals(18,testlist.get(16));

	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testgetneg(){

	    LinkedList emptyList = new LinkedList();
	    Object o = emptyList.get(-2);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testgetout(){

	    Object o = testlist.get(31);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testremoveExp(){

	    Object o = testlist.remove(31);
	}
	
	@Override
	@Test
	public void testFunctional() {
		Iterator itr = testlist.iterator();
		
		assertTrue(itr.hasNext());
		for(int i = 0; i < 12; i++){
			assertEquals(i, itr.next());
		}
		
		//previous() function not yet defined in interface
		
		itr.remove();
		
		assertTrue(itr.hasNext());
		assertEquals(12, itr.next());
		assertEquals(19, testlist.size());
		
		for(int i = 13; i < 20; i++){
			assertEquals(i, itr.next());
		}
		assertFalse(itr.hasNext());
		
		boolean hasExp = false;
		try{
			itr.next();
		}catch (NoSuchElementException e){
			hasExp = true;
		}
		assertTrue(hasExp);

	}

}
