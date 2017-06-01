package com.coding.week11;

import com.coding.week10.BinaryTreeNode;
import com.coding.week10.BinaryTreeUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class BinarySearchTreeTest {
	
	BinarySearchTree<Integer> tree = null;
	
	@Before
	public void setUp() throws Exception {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
		root.setLeft(new BinaryTreeNode<Integer>(2));
		root.setRight( new BinaryTreeNode<Integer>(8));
		root.getLeft().setLeft(new BinaryTreeNode<Integer>(1));
		root.getLeft().setRight(new BinaryTreeNode<Integer>(4));
		root.getLeft().getRight().setLeft(new BinaryTreeNode<Integer>(3));
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
		Assert.assertEquals(3, root.getLeft().getRight().getData().intValue());
		
	}
	@Test
	public void testRemoveMiddleNode() {
		tree.remove(2);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(3, root.getLeft().getData().intValue());
		Assert.assertEquals(4, root.getLeft().getRight().getData().intValue());
	}

	@Test
	public void testLevelVisit() {
		List<Integer> li = tree.levelVisit();
		Assert.assertEquals("[6, 2, 8, 1, 4, 3]", li.toString());
	}

	@Test
	public void testIsValid() throws Exception {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
		root.setLeft(new BinaryTreeNode<Integer>(3));
		root.setRight( new BinaryTreeNode<Integer>(8));
		root.getLeft().setLeft(new BinaryTreeNode<Integer>(1));
		root.getLeft().setRight(new BinaryTreeNode<Integer>(4));
		root.getLeft().getRight().setLeft(new BinaryTreeNode<Integer>(3));
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>(root);
		boolean valid = t.isValid();
		Assert.assertTrue(valid);
	}

	@Test
	public void testInsert() {
		BinarySearchTree<Integer> tree1 = new BinarySearchTree<>();
		tree1.insert(Arrays.asList(6, 8, 8, 1, 4, 9, 2));
		List t = BinaryTreeUtil.inOrderVisit(tree1.root);
		Assert.assertEquals("[1, 2, 4, 6, 8, 8, 9]", t.toString());
	}

	@Test
	public void getLowestCommonAncestor() {

		Assert.assertTrue(tree.getLowestCommonAncestor(1,5)==2);
		Assert.assertTrue(tree.getLowestCommonAncestor(1,9)==6);
		Assert.assertTrue(tree.getLowestCommonAncestor(4,5)==4);
		Assert.assertTrue(tree.getLowestCommonAncestor(4,9)==6);
	}


	@Test
	public void getNodesBetween(){
		List<Integer> ret = tree.getNodesBetween(3, 10);
		Assert.assertTrue(ret.contains(4));
		Assert.assertTrue(ret.contains(6));
		Assert.assertTrue(ret.contains(8));
		Assert.assertTrue(ret.contains(3));
	}


}
