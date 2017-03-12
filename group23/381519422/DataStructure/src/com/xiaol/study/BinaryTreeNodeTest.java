package com.xiaol.study;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeNodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() {
		BinaryTreeNode btn = new BinaryTreeNode();
		btn.insert(1);
		btn.insert(2);
		btn.insert(0);
		btn.insert(3);
		assertEquals(0, btn.getLeft().getData());
		assertEquals(1, btn.getData());
		assertEquals(2, btn.getRight().getData());
		assertEquals(3, btn.getRight().getRight().getData());
	}

	@Test
	public void testGetData() {
		BinaryTreeNode btn = new BinaryTreeNode();
		btn.insert(1);
		assertEquals(1, btn.getData());
	}

	@Test
	public void testSetData() {
		BinaryTreeNode btn = new BinaryTreeNode();
		btn.setData(1);
		assertEquals(1, btn.getData());
	}

	@Test
	public void testGetLeft() {
		BinaryTreeNode btn = new BinaryTreeNode();
		btn.insert(1);
		btn.insert(0);
		assertEquals(0, btn.getLeft().getData());
	}

	@Test
	public void testSetLeft() {
		BinaryTreeNode btn = new BinaryTreeNode();
		btn.insert(1);
		BinaryTreeNode left = new BinaryTreeNode();
		left.setData(2);
		btn.setLeft(left);
		assertEquals(2, btn.getLeft().getData());
	}

	@Test
	public void testGetRight() {
		BinaryTreeNode btn = new BinaryTreeNode();
		btn.insert(1);
		btn.insert(2);
		assertEquals(2, btn.getRight().getData());
	}

	@Test
	public void testSetRight() {
		BinaryTreeNode btn = new BinaryTreeNode();
		btn.insert(1);
		BinaryTreeNode right = new BinaryTreeNode();
		right.setData(2);
		btn.setRight(right);
		assertEquals(2, btn.getRight().getData());
	}

}
