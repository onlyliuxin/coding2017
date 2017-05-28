package me.lzb.basic.tree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author LZB
 */
public class BinarySearchTreeTest {
    BinarySearchTree<Integer> tree = null;

    @Before
    public void setUp() throws Exception {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(6);
        root.insert(2);
        root.insert(8);
        root.insert(1);
        root.insert(4);
        root.insert(3);
        tree = new BinarySearchTree<>(root);
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
        Assert.assertEquals(3, root.getLeft().getRight().getData().intValue());

    }

    @Test
    public void testRemoveMiddleNode() {
        tree.remove(2);
        BinaryTreeNode<Integer> root = tree.getRoot();
        Assert.assertEquals(3, root.getLeft().getData().intValue());
        Assert.assertEquals(4, root.getLeft().getRight().getData().intValue());
    }

    @Test
    public void testLevelVisit() {
        Assert.assertEquals("[6, 2, 8, 1, 4, 3]", tree.levelVisit().toString());
    }

    @Test
    public void testIsValid() {
        Assert.assertTrue(tree.isValid());
        tree.getRoot().getRight().setData(5);
        Assert.assertFalse(tree.isValid());
        tree.getRoot().getRight().setData(8);
        tree.getRoot().getLeft().getLeft().setData(5);
        Assert.assertFalse(tree.isValid());
    }

    @Test
    public void testGetLowestCommonAncestor() {
        Assert.assertEquals(2l, tree.getLowestCommonAncestor(1, 4).longValue());
        Assert.assertEquals(6l, tree.getLowestCommonAncestor(1, 8).longValue());
    }


    @Test
    public void testGetNodesBetween() {
        Assert.assertEquals("[6, 2, 4, 3]", tree.getNodesBetween(1, 8).toString());

        Assert.assertEquals("[6]", tree.getNodesBetween(7, 4).toString());

        Assert.assertEquals("[6, 2, 1, 4, 3, 8]", tree.getNodesBetween(20, 0).toString());
    }


}
