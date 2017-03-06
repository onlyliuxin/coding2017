package com.github.Ven13.coding2017.basic.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.*;

import com.github.Ven13.coding2017.basic.ArrayList;
import com.github.Ven13.coding2017.basic.Iterator;
import com.github.Ven13.coding2017.basic.List;

public class ListTest {

	//protected static List aList;
	
	@Test
	public void testFunctional() {
		
		List aList = new ArrayList();
		
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
		
		List aList = new ArrayList();
		
		for (int i = 0; i < 100; i++) {
			aList.add(i);
		}
			
		assertEquals(0, aList.get(0));
		assertEquals(99, aList.get(99));
		assertEquals(44, aList.get(44));
	}
	
	@Test
	public void testRemove() {
		
		List aList = new ArrayList();
		
		aList.add(1);
		aList.add(2);
		aList.add(3);
		aList.remove(3);
		assertEquals(2, aList.size());
		
	}
	
	@Test
	public void testSize() {
		
		List aList = new ArrayList();
		
		for (int i = 0; i < 10; i++) {
			aList.add(i * 2);
		}
			
		assertEquals(10, aList.size());
	}
	
	@Rule  
	public ExpectedException expectedEx = ExpectedException.none(); 
	
	@Test
	public void testException() {
		
		List aList = new ArrayList();
		
		expectedEx.expect(Exception.class);
		
		aList.remove(1);
		aList.add(3);
		aList.add(2, 5);
	}
	
	@Test
	public void testIterator() {
		
		List aList = new ArrayList();
		
		Iterator it = aList.iterator();
		
		assertEquals(false, it.hasNext());
		
		aList.add(1);
		aList.add(2);
		aList.add(3);
		
		it = aList.iterator();
		assertEquals(true, it.hasNext());
		assertEquals(1, it.next());
		assertEquals(2, it.next());
		assertEquals(3, it.next());
		assertEquals(false, it.hasNext());
		
		aList.remove(1);
		it = aList.iterator();
		assertEquals(true, it.hasNext());
		assertEquals(1, it.next());
		assertEquals(3, it.next());
		assertEquals(false, it.hasNext());	
		
		expectedEx.expect(Exception.class);
		it.next();
	}

}
