package com.coding.test;

//
import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.BinaryTreeNode;
import com.coding.basic.LinkedList;

public class BinaryTreeNodeTest {

	@Test
	public void testAddObject() {
		BinaryTreeNode bt = new BinaryTreeNode(null);
		bt.insert(new Integer(1));
		assertNotNull(bt);
	}
}
