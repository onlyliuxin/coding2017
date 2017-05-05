package com.github.fei9009.coding2017.basic;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ListTest {
	
	protected static List aList;
	
	@Test
	public void testFunctional() {
		aList.add(1);
		aList.add(2);
		assertEquals(1, aList.get(0));
		assertEquals(2, aList.get(1));
		
		aList.add(3);
		aList.add(0, 5);
		aList.add(2, 11);
		assertEquals(5, aList.get(0));
		assertEquals(11, aList.get(2));
		
		aList.add("hi");
		assertEquals("hi", aList.get(5));
		assertEquals(6, aList.size());
		
		aList.remove(1);
		assertEquals(11, aList.get(1));
		assertEquals(2, aList.get(2));
		
		assertEquals(5, aList.size());
	}
	
	@Test
	public void testAdd() {
		for (int i = 0; i < 1000; i++)
			aList.add(i);
		assertEquals(0, aList.get(0));
		assertEquals(100, aList.get(100));
		assertEquals(999, aList.get(999));
	}
	
	@Test
	public void testRemove() {
		aList.add(1);
		aList.add(2);
		aList.add(3);
		int u = (Integer)aList.remove(2);
		assertEquals(3, u);
		assertEquals(2, aList.size());
		
		aList.add(1, 5);
		u = (Integer)aList.remove(0);
		assertEquals(1, u);
		assertEquals(5, aList.get(0));
		assertEquals(2, aList.get(1));
		assertEquals(2, aList.size());
		
		aList.remove(0);
		aList.remove(0);
		assertEquals(0, aList.size());
		

	}
	
	@Test
	public void testSize() {
		for (int i = 0; i < 10; i++)
			aList.add(i * 2);
		assertEquals(10, aList.size());
	}
	
	@Rule  
	public ExpectedException expectedEx = ExpectedException.none(); 
	
	@Test
	public void testException() {
		expectedEx.expect(Exception.class);
		aList.remove(1);
		
		aList.add(3);
		
		expectedEx.expect(Exception.class);
		aList.add(2, 5);
	}
	
	

}
