package com.github.eloiseSJTU.coding2017.basic.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.github.eloiseSJTU.coding2017.basic.BinaryTreeNode;

public class BinaryTreeNodeTest {

	private BinaryTreeNode binaryTreeNode;

	@Before
	public void setUpBinaryTreeNode() {
		binaryTreeNode = new BinaryTreeNode();
	}

	@Test
	public void testBinaryTreeNodeFunctional() {
		binaryTreeNode.insert(4);
		binaryTreeNode.insert(1);
		binaryTreeNode.insert(3);
		binaryTreeNode.insert(5);
		binaryTreeNode.insert(2);
		assertEquals(true, 4 == binaryTreeNode.getData());
		assertEquals(true, 1 == binaryTreeNode.getLeft().getData());
		assertEquals(true, 5 == binaryTreeNode.getRight().getData());
		assertEquals(true, 3 == binaryTreeNode.getLeft().getRight().getData());
		assertEquals(true, 2 == binaryTreeNode.getLeft().getRight().getLeft().getData());
		binaryTreeNode.print();
	}

}
