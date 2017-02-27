package com.github.miniyk2012.coding2017.basic.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.miniyk2012.coding2017.basic.BinaryTreeNode;

public class BinaryTreeNodeTest {

	private BinaryTreeNode<Integer> binaryTreeNode;

	/**
		//	    4
		//	  1   5
		//  2 	3
	*/
	@Before
	public void setUpBinaryTreeNode() {
		binaryTreeNode = new BinaryTreeNode<Integer>(4);
		binaryTreeNode.insert(1);
		binaryTreeNode.insert(3);
		binaryTreeNode.insert(5);
		binaryTreeNode.insert(2);
	}

	@Test
	public void testBinaryTreeNodeFunctional1() {
		assertEquals(new Integer(4), binaryTreeNode.getData());
		assertEquals(new Integer(1), binaryTreeNode.getLeft().getData());
		assertEquals(new Integer(5), binaryTreeNode.getRight().getData());
		assertEquals(new Integer(3), binaryTreeNode.getLeft().getRight().getData());
		assertEquals(new Integer(2), binaryTreeNode.getLeft().getRight().getLeft().getData());
	}
   
	@Test
	public void testBinaryTreeFunctional2() {
		BinaryTreeNode<Integer> node1 = binaryTreeNode.getLeft();
		assertEquals(new Integer(1), node1.getData());
		assertEquals(new Integer(3), node1.getRight().getData());
		assertEquals(new Integer(5), binaryTreeNode.getRight().getData());
	}
	
	@Rule  
	public ExpectedException expectedEx = ExpectedException.none(); 
	
	@Test
	public void testBinaryTreeFunctional3()
	{
		BinaryTreeNode<Integer> treeNode = new BinaryTreeNode<Integer>(100);
		treeNode.insert(10);
		binaryTreeNode.setRight(treeNode);
		//	      4
		//	   1      100
		//  2 	  3  10
		assertEquals(new Integer(4), binaryTreeNode.getData());
		assertEquals(new Integer(1), binaryTreeNode.getLeft().getData());
		assertEquals(new Integer(3), binaryTreeNode.getLeft().getRight().getData());
		assertEquals(new Integer(2), binaryTreeNode.getLeft().getRight().getLeft().getData());	
		assertEquals(new Integer(100), binaryTreeNode.getRight().getData());
		assertEquals(new Integer(10), binaryTreeNode.getRight().getLeft().getData());
		
		expectedEx.expect(Exception.class);
		binaryTreeNode.getRight().getRight().getRight();  // null exception
		binaryTreeNode.getRight().getRight().getLeft();  // null exception
	}
}
