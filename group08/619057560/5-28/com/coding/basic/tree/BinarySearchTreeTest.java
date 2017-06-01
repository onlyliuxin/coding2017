package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class BinarySearchTreeTest {
	
	BinarySearchTree<Integer> tree = null;
	
	@Before
	public void setUp() throws Exception {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
		root.left = new BinaryTreeNode<Integer>(2);
		root.right = new BinaryTreeNode<Integer>(8);
		root.left.left = new BinaryTreeNode<Integer>(1);
		root.left.right = new BinaryTreeNode<Integer>(4);
		root.left.right.left = new BinaryTreeNode<Integer>(3);
		tree = new BinarySearchTree<Integer>(root);
	}

	@After
	public void tearDown() throws Exception {
		tree = null;
	}

	@Test
	public void testFindMin() {
		Assert.assertEquals(1, tree.findMin().intValue());
		
	}

	@Test
	public void testFindMax() {
		Assert.assertEquals(8, tree.findMax().intValue());
	}

	@Test
	public void testHeight() {
		Assert.assertEquals(4, tree.height());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(6, tree.size());
	}

	@Test
	public void testRemoveLeaf() {
		tree.remove(4);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(3, root.left.right.data.intValue());
		
	}
	@Test
	public void testRemoveMiddleNode() {
		tree.remove(2);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(3, root.left.data.intValue());
		Assert.assertEquals(4, root.left.right.data.intValue());
	}
	
	@Test
	public void testLevelVisit() {
		List<Integer> expected = new ArrayList<>();
		expected.add(6);
		expected.add(2);
		expected.add(8);
		expected.add(1);
		expected.add(4);
		expected.add(3);
		Assert.assertEquals(expected, tree.levelVisit());
	}
	
	@Test
	public void testIsValid() {
		Assert.assertEquals(true, tree.isValid());
		tree.getRoot().left.right.left.left = new BinaryTreeNode<Integer>(1);
		Assert.assertEquals(true, tree.isValid());
		tree.getRoot().left.right.left.left = new BinaryTreeNode<Integer>(4);
		Assert.assertEquals(false, tree.isValid());
	}
	
	@Test
	public void testGetLowestCommonAncestor() {
		Assert.assertEquals(new Integer(2), tree.getLowestCommonAncestor(1, 3));
		Assert.assertEquals(null, tree.getLowestCommonAncestor(1, 5));
	}
	
	@Test
	public void testGetNodesBetween() {
		List<Integer> expected = new ArrayList<>();
		expected.add(2);
		expected.add(3);
		Assert.assertEquals(expected, tree.getNodesBetween(1, 4));
		expected = new ArrayList<>();
		expected.add(4);
		expected.add(6);
		Assert.assertEquals(expected, tree.getNodesBetween(3, 8));
	}
}
