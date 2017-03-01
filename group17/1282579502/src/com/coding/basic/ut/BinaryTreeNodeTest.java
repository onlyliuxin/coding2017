package com.coding.basic.ut;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.BinaryTreeNode;

public class BinaryTreeNodeTest {

	BinaryTreeNode bst;
	@Before
	public void setUp() throws Exception {
		bst = new BinaryTreeNode();
	}

	@Test
	public void test() {
		BinaryTreeNode rootNode = bst.insert(new Integer(10));
		assertEquals(rootNode.getData(), 10);
		bst.insert(5);
		bst.insert(15);
		assertEquals(rootNode.getLeft().getData(), 5);
		assertEquals(rootNode.getRight().getData(), 15);
		bst.insert(7);
		BinaryTreeNode testNode = rootNode.getLeft().getRight();
		assertEquals(testNode.getData(), 7);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalid()	{
		BinaryTreeNode rootNode = bst.insert(new Integer(10));
		rootNode.insert(10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInsertNonComparableItem(){
		BinaryTreeNode rootNode = bst.insert(new Object());
	}

}
