package com.coding.basic.tree;

import org.junit.Assert;

/**
 * Korben's BinaryTreeNode Test
 *
 * Created by Korben on 21/02/2017.
 */
public class BinaryTreeNodeTest {

    private BinaryTreeNode<Integer> treeNode;

    @org.junit.Before
    public void setUp() throws Exception {
        treeNode = new BinaryTreeNode<>();
        treeNode.insert(5);
        treeNode.insert(3);
        treeNode.insert(7);
        treeNode.insert(1);
        treeNode.insert(4);
        treeNode.insert(2);
        treeNode.insert(8);
        treeNode.insert(6);
    }

    @org.junit.Test
    public void insert() {
        Assert.assertEquals(treeNode.getData().intValue(), 5);
        Assert.assertEquals(treeNode.getLeft().getData(), 3);
        Assert.assertEquals(treeNode.getRight().getData(), 7);
        Assert.assertEquals(treeNode.getLeft().getLeft().getData(), 1);
        Assert.assertEquals(treeNode.getLeft().getRight().getData(), 4);
        Assert.assertEquals(treeNode.getLeft().getLeft().getRight().getData(), 2);
        Assert.assertEquals(treeNode.getRight().getRight().getData(), 8);
        Assert.assertEquals(treeNode.getRight().getLeft().getData(), 6);
    }

    @org.junit.Test
    public void delete() throws Exception {
        treeNode.delete(3);
        for (int i = 1; i < 9; i++) {
            if (i != 3) {
                Assert.assertNotNull(treeNode.search(i));
            } else {
                Assert.assertNull(treeNode.search(i));
            }
        }
    }

    @org.junit.Test
    public void search() throws Exception {
        for (int i = 1; i < 9; i++) {
            Assert.assertNotNull(treeNode.search(i));
        }
        Assert.assertNull(treeNode.search(0));
        Assert.assertNull(treeNode.search(9));
    }
}