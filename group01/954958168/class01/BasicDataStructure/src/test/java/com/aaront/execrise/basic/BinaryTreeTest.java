package com.aaront.execrise.basic;

import com.aaront.exercise.basic.BinaryTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tonyhui
 * @since 17/2/20
 */
public class BinaryTreeTest {

    private BinaryTree binaryTree = null;

    @Before
    public void init() {
        int[] datas = new int[]{9, 4, 5, 7, 1, 2, 3, 10, 17, 9};
        binaryTree = new BinaryTree();
        for (int data : datas) {
            binaryTree.add(data);
        }
    }


    @Test
    public void testAdd() {
        int[] preorderDatas = binaryTree.traversal(BinaryTree.PREORDER);
        Assert.assertArrayEquals(preorderDatas, new int[]{9, 4, 1, 2, 3, 5, 7, 10, 9, 17});
        int[] inorderDatas = binaryTree.traversal(BinaryTree.INORDER);
        Assert.assertArrayEquals(inorderDatas, new int[]{1, 2, 3, 4, 5, 7, 9, 9, 10, 17});
        int[] postorderDatas = binaryTree.traversal(BinaryTree.POSTORDER);
        Assert.assertArrayEquals(postorderDatas, new int[]{3, 2, 1, 7, 5, 4, 9, 17, 10, 9});
        int[] hierarchicalDatas = binaryTree.traversal(BinaryTree.HIERARCHICAL);
        Assert.assertArrayEquals(hierarchicalDatas, new int[]{9, 4, 10, 1, 5, 9, 17, 2, 7, 3});
    }

    @Test
    public void testSearch() {
        BinaryTree.BinaryTreeNode node1 = binaryTree.search(5, BinaryTree.RECURSION);
        Assert.assertTrue(node1.getData() == 5);
        BinaryTree.BinaryTreeNode node2 = binaryTree.search(17, BinaryTree.RECURSION);
        Assert.assertTrue(node2.getData() == 17);
        BinaryTree.BinaryTreeNode node3 = binaryTree.search(100, BinaryTree.RECURSION);
        Assert.assertTrue(node3 == null);
    }

    @Test
    public void testMin() {
        BinaryTree.BinaryTreeNode min = binaryTree.min();
        Assert.assertTrue(min.getData() == 1);
    }

    @Test
    public void testMax() {
        BinaryTree.BinaryTreeNode max = binaryTree.max();
        Assert.assertTrue(max.getData() == 17);
    }

    @Test
    public void testDelete() {
        buildTree(new int[]{50, 25, 12, 11, 40, 14, 35, 45, 44, 43, 42, 75, 55, 70, 60, 65, 63, 61, 90, 80, 85, 82, 88});
        // 删除叶子节点
        binaryTree.delete(11);
        int[] preOrderDatas = binaryTree.traversal();
        Assert.assertArrayEquals(preOrderDatas, new int[]{50, 25, 12, 14, 40, 35, 45, 44, 43, 42, 75, 55, 70, 60, 65, 63, 61, 90, 80, 85, 82, 88});
        binaryTree.delete(88);
        preOrderDatas = binaryTree.traversal();
        Assert.assertArrayEquals(preOrderDatas, new int[]{50, 25, 12, 14, 40, 35, 45, 44, 43, 42, 75, 55, 70, 60, 65, 63, 61, 90, 80, 85, 82});

        // 删除一个子节点的节点
        binaryTree.delete(70);
        preOrderDatas = binaryTree.traversal();
        Assert.assertArrayEquals(preOrderDatas, new int[]{50, 25, 12, 14, 40, 35, 45, 44, 43, 42, 75, 55, 60, 65, 63, 61, 90, 80, 85, 82});
        binaryTree.delete(80);
        preOrderDatas = binaryTree.traversal();
        Assert.assertArrayEquals(preOrderDatas, new int[]{50, 25, 12, 14, 40, 35, 45, 44, 43, 42, 75, 55, 60, 65, 63, 61, 90, 85, 82});

        // 删除两个子节点的节点
        binaryTree.delete(40);
        preOrderDatas = binaryTree.traversal();
        Assert.assertArrayEquals(preOrderDatas, new int[]{50, 25, 12, 14, 42, 35, 45, 44, 43, 75, 55, 60, 65, 63, 61, 90, 85, 82});
        binaryTree.delete(50);
        preOrderDatas = binaryTree.traversal();
        Assert.assertArrayEquals(preOrderDatas, new int[]{55, 25, 12, 14, 42, 35, 45, 44, 43, 75, 60, 65, 63, 61, 90, 85, 82});
    }

    private void buildTree(int[] datas) {
        binaryTree = new BinaryTree();
        for (int data : datas) {
            binaryTree.add(data);
        }
    }
}