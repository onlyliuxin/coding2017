package com.github.HarryHook.coding2017.basic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.github.HarryHook.coding2017.basic.BinaryTreeNode;

public class BinaryTreeNodeTest 
{

	BinaryTreeNode binaryTreeNode;

	@Before
	public void setUpBinaryTreeNode() 
	{
		binaryTreeNode = new BinaryTreeNode();
	}

	@Test
	public void testBinaryTreeNodeFunctional() 
	{
		binaryTreeNode = binaryTreeNode.insert(4);
		binaryTreeNode.insert(1);
		binaryTreeNode.insert(3);
		binaryTreeNode.insert(5);
		binaryTreeNode.insert(2);
		
		assertEquals(true, 4 == binaryTreeNode.getData());
		assertEquals(true, 1 == binaryTreeNode.getLeft().getData());
		assertEquals(true, 5 == binaryTreeNode.getRight().getData());
		assertEquals(true, 3 == binaryTreeNode.getLeft().getRight().getData());
		assertEquals(true, 2 == binaryTreeNode.getLeft().getRight().getLeft().getData());

		//节点为空  说明值没有插进去
		binaryTreeNode.inOrder(binaryTreeNode);
	}

}
