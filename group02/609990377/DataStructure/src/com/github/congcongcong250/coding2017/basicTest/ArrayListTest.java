package com.github.congcongcong250.coding2017.basicTest;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.congcongcong250.coding2017.basic.ArrayList;
import com.github.congcongcong250.coding2017.basic.Iterator;

public class ArrayListTest implements testCase {
	
	ArrayList testlist = new ArrayList();
	
	@Override
	@Before
	public void setUp() {
		
		for(int i = 0; i < 30; i++){
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

		assertEquals(0,testlist.get(0));
		assertEquals(11,testlist.get(11));
		assertEquals(20,testlist.get(20));
		assertEquals(29,testlist.get(29));
		assertEquals(30,testlist.size());
		
		testlist.add(20, 100);
		assertEquals(100,testlist.get(20));
		assertEquals(20,testlist.get(21));
		assertEquals(29,testlist.get(30));
		assertEquals(31,testlist.size());
		
	}

	@Override
	@Test
	public void testRemove() {

		
		assertEquals(6,testlist.get(6));
		assertEquals(30,testlist.size());
		testlist.remove(6);
		assertEquals(7,testlist.get(6));
		assertEquals(29,testlist.size());
		assertEquals(21,testlist.get(20));
		assertEquals(5,testlist.get(5));
	}


	@Test(expected=IndexOutOfBoundsException.class)
	public void testgetneg(){

	    ArrayList emptyList = new ArrayList();
	    Object o = emptyList.get(-1);
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
		for(int i = 0; i < 20; i++){
			assertEquals(i, itr.next());
		}
		itr.remove();
		
		assertTrue(itr.hasNext());
		assertEquals(20, itr.next());
		assertEquals(29, testlist.size());
		
		for(int i = 21; i < 30; i++){
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
