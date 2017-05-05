package com.coding;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeNodeTest {
	
	public BinaryTreeNode node;

	@Before
	public void setUp() throws Exception {
		node = new BinaryTreeNode(12, null, null);
		node = node.insert(10);
	}

	@Test
	public void testGetData() {
		Assert.assertEquals(12, node.getData());
	}

	@Test
	public void testSetData() {
		node.setData(15);
		Assert.assertEquals(15, node.getData());
	}

	@Test
	public void testGetLeft() {
		Assert.assertEquals(10, node.getLeft().getData());
	}

	@Test
	public void testSetLeft() {
		node.setLeft(new BinaryTreeNode(8, null, null));
		Assert.assertEquals(8, node.getLeft().getData());
	}

	@Test
	public void testGetRight() {
		Assert.assertEquals(null, node.getRight());
	}

	@Test
	public void testSetRight() {
		node.setRight(new BinaryTreeNode(16, null, null));
		Assert.assertEquals(16, node.getRight().getData());
	}

	@Test
	public void testInsert() {
		node = node.insert(11);
		Assert.assertEquals(11, node.getLeft().getRight().getData());
	}

}
