package com.ikook.basic_data_structure;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * 此单元测试只测试了正常情况，一些异常情况没有测试。
 * @author ikook
 */
public class MyLinkedListTest {

	private MyLinkedList list;

	@Before
	public void setUp() {
		list = new MyLinkedList();
		list.add("111");
		list.add(222);
		list.add("333");
	}

	@Test
	public void testAddFirst() {
		list.addFirst(444);
		assertEquals(4, list.size());
		assertEquals(444, list.get(0));
		assertEquals(444, list.getFirst());

	}

	@Test
	public void testAddLast() {
		list.addLast("444");
		assertEquals(4, list.size());
		assertEquals("444", list.getLast());
		assertEquals("444", list.get(3));
	}

	@Test
	public void testAddObject() {
		list.add(new Date());
		assertEquals(new Date(), list.get(3));
	}

	@Test
	public void testAddIntObject() {
		list.add(1, "222");
		assertEquals("222", list.get(1));
		assertEquals(4, list.size());
	}

	@Test
	public void testSize() {
		assertEquals(3, list.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false, list.isEmpty());

		MyLinkedList list = new MyLinkedList();
		assertEquals(true, list.isEmpty());
	}

	@Test
	public void testGetFirst() {
		assertEquals("111", list.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals("333", list.getLast());
	}

	@Test
	public void testGet() {
		assertEquals(222, list.get(1));
	}

	@Test
	public void testSet() {
		assertEquals(222, list.set(1, new Date()));
		assertEquals(new Date(), list.get(1));
	}

	@Test
	public void testRemoveFirst() {
		assertEquals("111", list.removeFirst());
		assertEquals(222, list.getFirst());
	}

	@Test
	public void testRemoveLast() {
		assertEquals("333", list.removeLast());
		assertEquals(222, list.getLast());
	}

	@Test
	public void testRemoveObject() {
		assertEquals(true, list.remove((Integer) 222));
		assertEquals("333", list.get(1));
	}

	@Test
	public void testRemoveInt() {
		assertEquals(222, list.remove(1));
		assertEquals("333", list.get(1));

	}
	
	@Test
	public void testIterator() {
		int i = 0;
		for(MyIterator iter = list.iterator(); iter.hasNext();) {
			Object str = (Object) iter.next();
			assertEquals(list.get(i++), str);
		}
		
		int j = list.size();
		for(MyIterator iter = list.iterator(); iter.hasNext();) {
		        iter.next();
			iter.remove();
			assertEquals( --j , list.size());
		}
	}

}
