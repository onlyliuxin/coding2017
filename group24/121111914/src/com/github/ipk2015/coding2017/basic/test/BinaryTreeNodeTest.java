package com.github.ipk2015.coding2017.basic.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.ipk2015.coding2017.basic.BinaryTreeNode;

public class BinaryTreeNodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInsert() {
		BinaryTreeNode node=new BinaryTreeNode();
		
		node.setData(5);
		node.insert(2);
		node.insert(7);
		node.insert(1);
		node.insert(4);
		node.insert(3);
		assertEquals(3,node.getLeft().getRight().getLeft().getData());
	}

}
