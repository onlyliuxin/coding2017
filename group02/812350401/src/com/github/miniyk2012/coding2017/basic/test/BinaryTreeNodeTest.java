package com.github.miniyk2012.coding2017.basic.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import com.github.miniyk2012.coding2017.basic.BinaryTreeNode;

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
		assertEquals(4, binaryTreeNode.getData());
		assertEquals(1, binaryTreeNode.getLeft().getData());
		assertEquals(5, binaryTreeNode.getRight().getData());
		assertEquals(3, binaryTreeNode.getLeft().getRight().getData());
		assertEquals(2, binaryTreeNode.getLeft().getRight().getLeft().getData());
		// binaryTreeNode.print();
	}

}
