package com.github.HarryHook.coding2017.tree;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

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
	BinaryTreeNode<Integer> root = tree.getRoot();
	Assert.assertEquals(3, root.left.right.data.intValue());

    }

    @Test
    public void testRemoveMiddleNode() {
	tree.remove(2);
	BinaryTreeNode<Integer> root = tree.getRoot();
	Assert.assertEquals(3, root.left.data.intValue());
	Assert.assertEquals(4, root.left.right.data.intValue());
    }
    @Test
    public void TestLevelVisit(){
	List<Integer> list = tree.levelVisit();
	Assert.assertEquals("[6, 2, 8, 1, 4, 3]", list.toString());
    }
    
    @Test
    public void TestIsValid(){
	Assert.assertTrue(tree.isValid());
    }
    
    @Test
    public void TestGetBetweenNodes(){
	List<Integer> list = tree.getNodesBetween(9, 11);
	Assert.assertTrue(list.isEmpty());
	list = tree.getNodesBetween(1, 6);
	Assert.assertEquals("[1, 2, 3, 4, 6]", list.toString());
    }
    
    @Test
    public void TestGetLowestCommonAncestor(){
	Assert.assertEquals(2, tree.getLowestCommonAncestor(2, 3).intValue());
	Assert.assertEquals(6, tree.getLowestCommonAncestor(1, 8).intValue());
    }
    
}
