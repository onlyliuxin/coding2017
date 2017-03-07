package com.ikook.basic_data_structure;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * 此单元测试只测试了正常情况，一些异常情况没有测试。
 * @author ikook
 */
public class MyArrayListTest {
	
	private MyArrayList list;
	
	@Before
	public void setUp() {
		list = new MyArrayList();
		list.add("111");
		list.add("222");
		list.add(33);
		list.add("444");
		list.add(new Date());
		list.add("666");
		list.add("777");
		list.add("888");
		list.add("999");
	}

	@Test
	public void testAdd() {
		//测试add(Object obj)方法
		list.add(100);
		assertEquals(10, list.size());
		
		//测试add(int index, Object obj)方法
		list.add(3, 444);
		assertEquals(444, list.get(3));
		
		assertEquals("444", list.get(4));
		assertEquals(11, list.size());
	}
	
	@Test
	public void testIsEmpty() {
		
		assertEquals(false, list.isEmpty());
	}
	
	@Test
	public void testGet() {
		assertEquals("111", list.get(0));
		assertEquals(new Date(), list.get(4));
	}
	
	@Test
	public void testRemove() {
	
		// 测试remove(int index)方法
		assertEquals(33, list.remove(2));
		assertEquals("444", list.get(2));
		
		// 测试remove(Object obj)方法
		assertEquals(true, list.remove("222"));
		assertEquals("444", list.get(1));
	}
	
	@Test
	public void testSet() {
		assertEquals(33, list.set(2, "333"));
		assertEquals("333", list.get(2));
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
