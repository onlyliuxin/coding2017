package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.mybasic.BinaryTreeNode;

public class TestBinaryTreeNode {

	private BinaryTreeNode node;
	@Before
	public void before(){
		node = new BinaryTreeNode();
	}
	
	@Test
	public void testInsert() {
		node.insert(1);
		node.insert(0);
		node.insert(3);
		node.insert(-2);
		node.insert(-1);
		assertEquals(1, node.getData());
		assertEquals(0, node.getLeft().getData());
		assertEquals(3, node.getRight().getData());
		assertEquals(-2, node.getLeft().getLeft().getData());
		assertEquals(-1, node.getLeft().getLeft().getRight().getData());
	}

}
