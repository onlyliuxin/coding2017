package com.coding.basic.tree;

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
	public void testIsValid() {
		Assert.assertEquals(true, tree.isValid());
		tree.root.right.left = new BinaryTreeNode<Integer>(9);
		Assert.assertEquals(false, tree.isValid());
	}
	
	@Test
	public void testLevelVisit() {
		Assert.assertEquals("6,2,8,1,4,3", toString(tree.levelVisit()));
	}
	
	@Test
	public void testGetLowestCommonAncestor() {
		Assert.assertEquals(2, (int)tree.getLowestCommonAncestor(1, 3));
		Assert.assertEquals(2, (int)tree.getLowestCommonAncestor(1, 4));
		Assert.assertEquals(4, (int)tree.getLowestCommonAncestor(4, 3));
		Assert.assertEquals(6, (int)tree.getLowestCommonAncestor(4, 8));
		Assert.assertEquals(6, (int)tree.getLowestCommonAncestor(1, 8));
	}
	
	@Test
	public void testGetNodesBetween() {
		Assert.assertEquals("2,3", toString(tree.getNodesBetween(1, 4)));
		Assert.assertEquals("4,6", toString(tree.getNodesBetween(8, 3)));
	}
	
	private <T> String toString(List<T> list){
		StringBuffer buff = new StringBuffer();
		for (T t : list) {
			if(buff.length()!=0){
				buff.append(",");
			}
			buff.append(t);
		}
		return buff.toString();
	}
}
