package com.coding.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.BinaryTree;

public class BinaryTreeTest {

	private BinaryTree node;
	
	@Before
	public void setUp() throws Exception {
		node = new BinaryTree();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println(node);
	}

	@Test
	public void testInsertObject() {
		node.insert(1);
		node.insert(5);
	}

}
