package com.louisly.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LYArrayLinkTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverse() {
		LYArrayLink arrayLink = new LYArrayLink();
		arrayLink.addObject(new Integer(3));
		arrayLink.addObject(new Integer(7));
		arrayLink.addObject(new Integer(10));
		arrayLink.addObject(new Integer(43));
		arrayLink.reverse();
		Assert.assertEquals(new Integer(43), arrayLink.get(0));
		Assert.assertEquals(new Integer(10), arrayLink.get(1));
		Assert.assertEquals(new Integer(7), arrayLink.get(2));
		Assert.assertEquals(new Integer(3), arrayLink.get(3));
	}
	
	// 单数
	@Test
	public void testRemoveFirstHalf1() {
		LYArrayLink arrayLink = new LYArrayLink();
		arrayLink.addObject(new Integer(2));
		arrayLink.addObject(new Integer(5));
		arrayLink.addObject(new Integer(7));
		arrayLink.addObject(new Integer(8));
		arrayLink.addObject(new Integer(10));
		arrayLink.removeFirstHalf();
		Assert.assertEquals(3, arrayLink.size());
		Assert.assertEquals(new Integer(7), arrayLink.get(0));
		Assert.assertEquals(new Integer(8), arrayLink.get(1));
		Assert.assertEquals(new Integer(10), arrayLink.get(2));
	}
	
	// 偶数
	@Test
	public void testRemoveFirstHalf2() {
		LYArrayLink arrayLink = new LYArrayLink();
		arrayLink.addObject(new Integer(2));
		arrayLink.addObject(new Integer(5));
		arrayLink.addObject(new Integer(7));
		arrayLink.addObject(new Integer(8));
		arrayLink.removeFirstHalf();
		Assert.assertEquals(2, arrayLink.size());
		Assert.assertEquals(new Integer(7), arrayLink.get(0));
		Assert.assertEquals(new Integer(8), arrayLink.get(1));
	}

	@Test
	public void testRemove() {
		// 删除中间
		LYArrayLink arrayLink = new LYArrayLink();
		arrayLink.addObject(new Integer(2));
		arrayLink.addObject(new Integer(5));
		arrayLink.addObject(new Integer(7));
		arrayLink.addObject(new Integer(8));
		arrayLink.addObject(new Integer(9));
		arrayLink.addObject(new Integer(19));
		arrayLink.remove(2, 3);
		Assert.assertEquals(3, arrayLink.size());
		Assert.assertEquals(new Integer(2), arrayLink.get(0));
		Assert.assertEquals(new Integer(5), arrayLink.get(1));
		Assert.assertEquals(new Integer(19), arrayLink.get(2));
		
		// 删除头部
		arrayLink = new LYArrayLink();
		arrayLink.addObject(new Integer(2));
		arrayLink.addObject(new Integer(5));
		arrayLink.addObject(new Integer(7));
		arrayLink.addObject(new Integer(8));
		arrayLink.addObject(new Integer(9));
		arrayLink.addObject(new Integer(19));
		arrayLink.remove(0, 3);
		Assert.assertEquals(3, arrayLink.size());
		Assert.assertEquals(new Integer(8), arrayLink.get(0));
		Assert.assertEquals(new Integer(9), arrayLink.get(1));
		Assert.assertEquals(new Integer(19), arrayLink.get(2));
		
		// 删除尾部
		arrayLink = new LYArrayLink();
		arrayLink.addObject(new Integer(2));
		arrayLink.addObject(new Integer(5));
		arrayLink.addObject(new Integer(7));
		arrayLink.addObject(new Integer(8));
		arrayLink.addObject(new Integer(9));
		arrayLink.addObject(new Integer(19));
		arrayLink.remove(3, 3);
		Assert.assertEquals(3, arrayLink.size());
		Assert.assertEquals(new Integer(2), arrayLink.get(0));
		Assert.assertEquals(new Integer(5), arrayLink.get(1));
		Assert.assertEquals(new Integer(7), arrayLink.get(2));
	}
	
	@Test
	public void testGetElements(){
		LYArrayLink arrayLink = new LYArrayLink();
		arrayLink.addObject(new Integer(11));
		arrayLink.addObject(new Integer(101));
		arrayLink.addObject(new Integer(201));
		arrayLink.addObject(new Integer(301));
		arrayLink.addObject(new Integer(401));
		arrayLink.addObject(new Integer(501));
		arrayLink.addObject(new Integer(601));
		arrayLink.addObject(new Integer(701));
		
		LYArrayLink list = new LYArrayLink();
		list.addObject(new Integer(1));
		list.addObject(new Integer(3));
		list.addObject(new Integer(4));
		list.addObject(new Integer(6));
		int[] intList = arrayLink.getElements(list);
		Assert.assertEquals(101, intList[0]);
		Assert.assertEquals(301, intList[1]);
		Assert.assertEquals(401, intList[2]);
		Assert.assertEquals(601, intList[3]);
	}
	
	@Test
	public void testSubtract(){
		LYArrayLink arrayLink = new LYArrayLink();
		arrayLink.addObject(new Integer(11));
		arrayLink.addObject(new Integer(101));
		arrayLink.addObject(new Integer(201));
		arrayLink.addObject(new Integer(301));
		arrayLink.addObject(new Integer(401));
		arrayLink.addObject(new Integer(501));
		arrayLink.addObject(new Integer(601));
		arrayLink.addObject(new Integer(701));
		
		LYArrayLink list = new LYArrayLink();
		list.addObject(new Integer(11));
		list.addObject(new Integer(101));
		list.addObject(new Integer(201));
		list.addObject(new Integer(301));
		list.addObject(new Integer(401));
		
		arrayLink.subtract(list);
		
		Assert.assertEquals(3, arrayLink.size());
		Assert.assertEquals(new Integer(501), arrayLink.get(0));
		Assert.assertEquals(new Integer(601), arrayLink.get(1));
		Assert.assertEquals(new Integer(701), arrayLink.get(2));
	}
}