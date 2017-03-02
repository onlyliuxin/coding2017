package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 2017/2/24
 * @author 236995728
 *
 */
public class ArrayListTest {
	private static ArrayList list = new ArrayList();

	@Before
	public void setUp() throws Exception {
		for(int i=0;i<10;i++){
			list.add(i);
			System.out.println(list.get(i));
		}
	}

	@Test
	public void testAddObject() {
		list.add("www");
		assertEquals("www", list.get(10));
	}

	@Test
	public void testAddIntObject() {
		list.add(101, 101);
		assertEquals(101, list.get(101));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddIntObjectException1(){
		list.add(-1, -1);
	}

	@Test
	public void testGet() {
		assertEquals(1, list.get(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetException1(){
		list.get(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetException2(){
		list.get(11);
	}
	
	@Test
	public void testRemove() {
		list.remove(3);
		assertEquals(4, list.get(3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveException1(){
		list.remove(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveException2(){
		list.remove(1000000000);
	}
	
	@Test
	public void testSize() {
		assertEquals(10, list.size());
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

}
