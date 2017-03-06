package com.guodong.datastructure.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.guodong.datastructure.BinaryTreeNode;

public class BinaryTreeNodeTest {
	
	private BinaryTreeNode binaryTreeNode;

	@Before
	public void setUp() throws Exception {
		binaryTreeNode = new BinaryTreeNode(50);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() {
		binaryTreeNode.insert(20);
		binaryTreeNode.insert(30);
		binaryTreeNode.insert(60);
		binaryTreeNode.insert(80);
		
		assertEquals(20, binaryTreeNode.getLeft().getData());
		assertEquals(30, binaryTreeNode.getLeft().getRight().getData());
		assertEquals(60, binaryTreeNode.getRight().getData());
		assertEquals(80, binaryTreeNode.getRight().getRight().getData());
	}

}
