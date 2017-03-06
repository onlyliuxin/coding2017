package com.guodong.datastructure.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.guodong.datastructure.ArrayList;
import com.guodong.datastructure.Iterator;

public class ArrayListTest {

	ArrayList arrayList;

	@Before
	public void setUp() throws Exception {
		arrayList = new ArrayList();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		// 测试新增
		arrayList.add(0);
		assertEquals(0, arrayList.get(0));
		assertEquals(1, arrayList.size());

		// 测试扩充
		for (int i = 1; i < 101; i++) {
			arrayList.add(i);
		}
		assertEquals(101, arrayList.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionForAdd1() {
		// 测试新增下标异常时，是否可以正确抛出异常
		arrayList.add(-1, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionForAdd2() {
		// 测试新增下标异常时，是否可以正确抛出异常
		arrayList.add(1, 3);
	}

	@Test
	public void testAddIntObject() {
		// 测试下标新增
		arrayList.add(0, 1);
		arrayList.add(1, 2);
		arrayList.add(2, 3);
		arrayList.add(3, 4);
		assertEquals(4, arrayList.size());

		// 测试中间插入
		arrayList.add(2, 5);
		assertEquals(5, arrayList.size()); // 测试插入之后长度
		assertEquals(5, arrayList.get(2));
		assertEquals(4, arrayList.get(4)); // 测试插入之后原来数据是否后移
		assertEquals(3, arrayList.get(3)); // 测试插入之后原来数据是否后移

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionForGet1() {
		// 测试Get时，下标异常，是否可以正确抛出异常
		arrayList.get(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionForGet2() {
		// 测试Get时，下标异常，是否可以正确抛出异常
		arrayList.get(0);
	}

	@Test
	public void testGet() {
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		assertEquals(1, arrayList.get(0));
		assertEquals(2, arrayList.get(1));
		assertEquals(3, arrayList.get(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionForRemove1() {
		arrayList.remove(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionForRemove2() {
		arrayList.remove(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionForRemove3() {
		arrayList.remove(1);
	}

	@Test
	public void testRemove() {
		arrayList.add(1);
		arrayList.remove(0);
		assertEquals(0, arrayList.size());

		arrayList.add(1);
		arrayList.add(2);
		arrayList.remove(0);
		assertEquals(1, arrayList.size());
		assertEquals(2, arrayList.get(0));
	}

	@Test
	public void testSize() {
		arrayList.add(1);
		assertEquals(1, arrayList.size());
	}

	@Test
	public void testIterator() {
		Iterator iterator = arrayList.iterator();
		assertFalse(iterator.hasNext());
		
		arrayList.add(1);
		assertTrue(iterator.hasNext());
		assertEquals(1, iterator.next());
		assertFalse(iterator.hasNext());
	}
}
