package com.zzl.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {
	
	private List list;
	private ArrayList aList;
	
    @Before
    public void init() {
        list = new ArrayList();

		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		aList = new ArrayList();
		
		aList.add("1");
		aList.add("2");
		aList.add("3");
		aList.add("4");
		aList.add("5");
    }
	
	@Test
	public void testAddObject() {
		
		assertEquals(list.size(), 5);
	}

	@Test
	public void testAddIntObject() {
		
		list.add(2, "5");
		
		assertEquals(list.get(2), "5");
		assertEquals(list.get(4), "4");
	}

	@Test
	public void testGet() {
		
		String[] str = {"1","2","3","4","5"};
		Common.loop(list, str);
		
		list.add(4, "6");
		
		String[] str1 = {"1","2","3","4","6","5"};
		Common.loop(list, str1);
	}

	@Test
	public void testRemove() {

		String[] str = {"1","2","4","5"};
		String result = Common.removeTest(list, 2, str);
		list.add(2 ,result);
		
		String[] str1 = {"2","3","4","5"};
		result = Common.removeTest(list, 0, str1);
		list.add(0 ,result);
		
		String[] str2 = {"1","2","3","4"};
		result = Common.removeTest(list, 4, str2);
		list.add(4 ,result);
		
		String[] str3 = {"1","2","3","4","5"};
		Common.loop(list, str3);
	}
	
	@Test
	public void testIterator() {
		
		Iterator it = aList.iterator();
		
		assertTrue(it.hasNext());
		assertEquals(it.next(), "1");
		
		assertTrue(it.hasNext());
		assertEquals(it.next(), "2");

		assertTrue(it.hasNext());
		assertEquals(it.next(), "3");

		assertTrue(it.hasNext());
		assertEquals(it.next(), "4");

		assertTrue(it.hasNext());
		assertEquals(it.next(), "5");

		assertFalse(it.hasNext());
	}
}
