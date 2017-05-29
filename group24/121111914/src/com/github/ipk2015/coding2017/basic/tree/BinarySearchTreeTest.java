package com.github.ipk2015.coding2017.basic.tree;



import static org.junit.Assert.fail;

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
	public void testRemoveRootNode() {
		tree.remove(6);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(8, root.data.intValue());
		Assert.assertEquals(2, root.left.data.intValue());
		
	}
	
	@Test
	public void testLevelVisit() {
		List<Integer> list = tree.levelVisit();
		System.out.println(list.toString());
		Assert.assertEquals("[6, 2, 8, 1, 4, 3]", list.toString());
	}
	
	@Test
	public void testIsValid() {
		Assert.assertEquals(true, tree.isValid());
		BinarySearchTree<Integer> tree2;
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(6);
		root2.left = new BinaryTreeNode<Integer>(2);
		root2.right = new BinaryTreeNode<Integer>(8);
		root2.left.left = new BinaryTreeNode<Integer>(1);
		root2.left.right = new BinaryTreeNode<Integer>(7);
		root2.left.right.left = new BinaryTreeNode<Integer>(3);
		tree2 = new BinarySearchTree<Integer>(root2);
		Assert.assertEquals(false, tree2.isValid());
	}
	@Test
	public void testGetLowestCommonAncestor() {
		Assert.assertEquals(2, tree.getLowestCommonAncestor(1, 3).intValue());
		Assert.assertEquals(6, tree.getLowestCommonAncestor(3, 8).intValue());
	}
	@Test
	public void testGetNodesBetween() {
		List<Integer> list = tree.getNodesBetween(0, 9);
		Assert.assertEquals("[6, 2, 1, 4, 3, 8]", list.toString());
		list = tree.getNodesBetween(1, 7);
		Assert.assertEquals("[6, 2, 4, 3]", list.toString());
	}
}
