package com.zzl.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class LinkedListTest {

	private List list;
	private LinkedList aList;
	
    @Before
    public void init() {
        list = new LinkedList();

		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		aList = new LinkedList();
		
		aList.add("1");
		aList.add("2");
		aList.add("3");
		aList.add("4");
		aList.add("5");
    }
	
	@Test
	public void testAddObject() {
		assertEquals(list.size(), 5);
		String[] str = {"1","2","3","4","5"};
		Common.loop(list, str);
	}

	@Test
	public void testAddIntObject() {
		
		list.add(3, "6");
		
		assertEquals(list.get(3), "6");
		assertEquals(list.get(5), "5");
		
		String[] str = {"1","2","3","6","4","5"};
		Common.loop(list, str);
	}

	@Test
	public void testRemove() {
		
		String[] str = {"1","2","4","5"};
		String result = Common.removeTest(aList, 2, str);
		aList.add(2 ,result);
		
		String[] str1 = {"2","3","4","5"};
		result = Common.removeTest(aList, 0, str1);
		aList.addFirst(result);
		
		String[] str2 = {"2","3","4","5"};
		aList.removeFirst();
		Common.loop(aList,str2);
		aList.addFirst(result);
		
		String[] str3 = {"1","2","3","4"};
		result = Common.removeTest(aList, 4, str3);
		aList.add(4 ,result);

		String[] str4 = {"1","2","3","4"};
		aList.removeLast();
		Common.loop(aList,str4);
		aList.add(4 ,result);
		
		String[] str5 = {"1","2","3","4","5"};
		Common.loop(aList,str5);
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
