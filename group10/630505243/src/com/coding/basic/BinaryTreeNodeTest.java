package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeNodeTest {
	BinaryTreeNode<Integer> root  = new BinaryTreeNode<Integer>();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInsert() {
		root.insert(5);
		root.insert(3);
		root.insert(8);

		root.printTree();
	}

}
