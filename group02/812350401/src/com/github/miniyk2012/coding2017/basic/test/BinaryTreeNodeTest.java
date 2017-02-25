package com.github.miniyk2012.coding2017.basic.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import com.github.miniyk2012.coding2017.basic.BinaryTreeNode;

public class BinaryTreeNodeTest {

	private BinaryTreeNode<Integer> binaryTreeNode;

	@Before
	public void setUpBinaryTreeNode() {
		binaryTreeNode = new BinaryTreeNode<Integer>(4);
	}

	@Test
	public void testBinaryTreeNodeFunctional() {
		binaryTreeNode.insert(1);
		binaryTreeNode.insert(3);
		binaryTreeNode.insert(5);
		binaryTreeNode.insert(2);
		assertEquals(new Integer(4), binaryTreeNode.getData());
		assertEquals(new Integer(1), binaryTreeNode.getLeft().getData());
		assertEquals(new Integer(5), binaryTreeNode.getRight().getData());
		assertEquals(new Integer(3), binaryTreeNode.getLeft().getRight().getData());
		assertEquals(new Integer(2), binaryTreeNode.getLeft().getRight().getLeft().getData());
	}

}
