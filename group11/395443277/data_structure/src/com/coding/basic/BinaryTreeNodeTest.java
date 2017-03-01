package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryTreeNodeTest {

	@Test
	public void testInsert() {
		BinaryTreeNode root = new BinaryTreeNode();
		root.setData(3);
		
		root.insert(2);
		BinaryTreeNode left = root.getLeft();
		assertEquals(2,left.getData());
		
		root.insert(5);
		BinaryTreeNode right = root.getRight();
		assertEquals(5, right.getData());
		
		root.insert(7);
		BinaryTreeNode rr = right.getRight();
		assertEquals(7, rr.getData());
	}

	@Test
	public void testCompareTo() {
		BinaryTreeNode n1 = new BinaryTreeNode();
		n1.setData("abc");
		
		assertEquals(true, n1.compareTo("cde")<0);
		
		BinaryTreeNode n3 = new BinaryTreeNode();
		n3.setData(1);
		
		assertEquals(true, n3.compareTo(2)<0);
	}

}
