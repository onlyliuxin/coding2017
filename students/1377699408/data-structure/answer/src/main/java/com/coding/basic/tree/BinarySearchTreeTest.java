package com.coding.basic.tree;

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
		root.left.right.right = new BinaryTreeNode<Integer>(5);
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
		Assert.assertEquals(7, tree.size());
	}

	@Test
	public void testRemoveLeaf() {
		tree.remove(3);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(4, root.left.right.data.intValue());
		
	}
	@Test
	public void testRemoveMiddleNode1() {
		tree.remove(4);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(5, root.left.right.data.intValue());
		Assert.assertEquals(3, root.left.right.left.data.intValue());
	}
	@Test
	public void testRemoveMiddleNode2() {
		tree.remove(2);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(3, root.left.data.intValue());
		Assert.assertEquals(4, root.left.right.data.intValue());
	}
	
	@Test
	public void testLevelVisit() {
		List<Integer> values = tree.levelVisit();
		Assert.assertEquals("[6, 2, 8, 1, 4, 3, 5]", values.toString());
		
	}
	@Test
	public void testLCA(){
		Assert.assertEquals(2,tree.getLowestCommonAncestor(1, 5).intValue());
		Assert.assertEquals(2,tree.getLowestCommonAncestor(1, 4).intValue());
		Assert.assertEquals(6,tree.getLowestCommonAncestor(3, 8).intValue());
	}
	@Test
	public void testIsValid() {
		
		Assert.assertTrue(tree.isValid());
		
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
		root.left = new BinaryTreeNode<Integer>(2);
		root.right = new BinaryTreeNode<Integer>(8);
		root.left.left = new BinaryTreeNode<Integer>(4);
		root.left.right = new BinaryTreeNode<Integer>(1);
		root.left.right.left = new BinaryTreeNode<Integer>(3);
		tree = new BinarySearchTree<Integer>(root);
		
		Assert.assertFalse(tree.isValid());
	}
	@Test
	public void testGetNodesBetween(){
		List<Integer> numbers = this.tree.getNodesBetween(3,  8);
		Assert.assertEquals("[3, 4, 5, 6, 8]",numbers.toString());
	}
}
