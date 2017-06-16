package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.coding.basic.array.ArrayList;
import com.coding.basic.array.ArrayUtil;
/**
 * junit 单元测试 -- list接口
 * @author mazan
 *
 */
public class ListTest {
	
	private List list;
	
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	@Before
	public void setUp() throws Exception {
		this.list = new ArrayList();
		this.list.add("a");
		this.list.add("b");
		this.list.add("c");
		System.out.println("init list");
		printList(this.list);
	}
	
	@Test
	public void test() {
		System.out.println("test");
	}
	
	/**
	 * 输出list
	 * @param list
	 */
	private static void printList(List list) {
		System.out.println("-------------------------------------");
		System.out.println("list.size = " + list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println("list[" + i + "] = " + list.get(i));
			
		}
		System.out.println("-------------------------------------");
	}
	
	/**
	 * size
	 */
	@Test
	public void testSize() {
		
		System.out.println("testSize");
		System.out.println(this.list.size());
		Assert.assertTrue(this.list.size() == 3);
	}
	
	/**
	 * add(Object o)
	 */
	@Test
	public void testAdd() {
		System.out.println("testAdd");
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		
		list.add("d");
		printList(list);
		
		list.add("e");
		printList(list);
		
	}

	/**
	 * add(int index, Object o)
	 */
	@Test
	public void testAddByIndex() {
		System.out.println("testAddByIndex");
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		
		
		list.add(4, "d");
		printList(list);
		
	}
	
	/**
	 * add(int index, Object o)
	 */
	@Test
	public void testAddByIndexLessZero() {
		System.out.println("testAddByIndexLessZero");
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		
		
		list.add(0, "d");
		printList(list);
		
		thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("index can not less than 0 ");
		list.add(-1, "d");
		fail("cannot catch the exception!");
		
	}
	/**
	 * add(int index, Object o)
	 */
	@Test
	public void testAddByIndexMoreSize() {
		System.out.println("testAddByIndexMoreSize");
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		
		
		list.add(0, "d");
		printList(list);
		
		thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("index can not more than the size");
		list.add(1000, "d");
		fail("cannot catch the exception!");
		
	}
	
	/**
	 * 测试删除
	 */
	@Test
	public void testRemove() {
		System.out.println("testRemove");
		List list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		
		list.remove(3);
		printList(list);
		
	}
	
	
	@Test
	public void testIter() {
		ArrayList list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
				
		Iterator iter = list.iterator();
		
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		
	}
	
	
}
