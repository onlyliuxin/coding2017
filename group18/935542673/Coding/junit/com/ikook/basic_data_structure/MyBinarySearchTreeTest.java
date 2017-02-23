package com.ikook.basic_data_structure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 此单元测试只测试了正常情况，一些异常情况没有测试。
 * @author ikook
 */
public class MyBinarySearchTreeTest {
	
	private MyBinarySearchTree tree;
	
	@Before
	public void setUp() {
		tree = new MyBinarySearchTree();
		
		tree.insert(3);
		tree.insert(8);
	}

	@SuppressWarnings("static-access")
	@Test
	public void testInsert() {
		tree.insert(1);
		tree.insert(4);
		tree.insert(6);
		tree.insert(2);
		tree.insert(10);
		tree.insert(9);
		
		assertEquals("1 2 3 4 6 8 9 10 ", tree.inorderTraverse(tree.root));
	}

	@Test
	public void testFind() {
		tree.insert(1);
		tree.insert(4);
		tree.insert(6);
		tree.insert(2);
		tree.insert(10);
		tree.insert(9);
		
		assertEquals(false, tree.find(5));
		assertEquals(true, tree.find(10));
	}

	@SuppressWarnings("static-access")
	@Test
	public void testDelete() {
		tree.insert(1);
		tree.insert(4);
		tree.insert(6);
		tree.insert(2);
		tree.insert(10);
		tree.insert(9);
		
		assertEquals(false, tree.delete(5));
		assertEquals(true, tree.delete(4));
		assertEquals("1 2 3 6 8 9 10 ", tree.inorderTraverse(tree.root));
	}
}
