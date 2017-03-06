package com.aaront.execrise.generic;

import com.aaront.exercise.generic.GenericBinaryTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tonyhui
 * @since 17/2/20
 */
public class GenericBinaryTreeTest {

    @Before
    public void init() {
        String[] datas = new String[]{"9", "4", "5", "7", "1", "2", "3", "10", "17", "9"};
        GenericBinaryTree<String> binaryTree = new GenericBinaryTree<>();
        for (String data : datas) {
            binaryTree.add(data);
        }
    }

    @Test
    public void testAdd() {
        String[] datas = new String[]{"9", "4", "5", "7", "1", "2", "3", "10", "17", "9"};
        GenericBinaryTree<String> binaryTree = new GenericBinaryTree<>();
        for (String data : datas) {
            binaryTree.add(data);
        }
        String[] preorderDatas = binaryTree.traversal(GenericBinaryTree.PREORDER, new String[0]);
        Assert.assertArrayEquals(preorderDatas, new String[]{"9", "4", "1", "2", "10", "17", "3", "5", "7", "9" });
        String[] inorderDatas = binaryTree.traversal(GenericBinaryTree.INORDER, new String[0]);
        Assert.assertArrayEquals(inorderDatas, new String[]{"1", "10", "17", "2", "3", "4", "5", "7", "9", "9" });
        String[] postorderDatas = binaryTree.traversal(GenericBinaryTree.POSTORDER, new String[0]);
        Assert.assertArrayEquals(postorderDatas, new String[]{"17", "10", "3", "2", "1", "7", "5", "4", "9", "9" });
        String[] hierarchicalDatas = binaryTree.traversal(GenericBinaryTree.HIERARCHICAL, new String[0]);
        Assert.assertArrayEquals(hierarchicalDatas, new String[]{"9", "4", "9", "1", "5", "2", "7", "10", "3", "17" });
    }

    @Test
    public void testDelete() {
        GenericBinaryTree<Integer> binaryTree = buildTree(new int[]{50, 25, 12, 11, 40, 14, 35, 45, 44, 43, 42, 75, 55, 70, 60, 65, 63, 61, 90, 80, 85, 82, 88});
        // 删除叶子节点
        binaryTree.delete(11);
        Object[] preOrderDatas = binaryTree.traversal();
        Assert.assertArrayEquals(preOrderDatas, new Object[]{50, 25, 12, 14, 40, 35, 45, 44, 43, 42, 75, 55, 70, 60, 65, 63, 61, 90, 80, 85, 82, 88});
        binaryTree.delete(88);
        preOrderDatas = binaryTree.traversal();
        Assert.assertArrayEquals(preOrderDatas, new Object[]{50, 25, 12, 14, 40, 35, 45, 44, 43, 42, 75, 55, 70, 60, 65, 63, 61, 90, 80, 85, 82});

        // 删除一个子节点的节点
        binaryTree.delete(70);
        preOrderDatas = binaryTree.traversal();
        Assert.assertArrayEquals(preOrderDatas, new Object[]{50, 25, 12, 14, 40, 35, 45, 44, 43, 42, 75, 55, 60, 65, 63, 61, 90, 80, 85, 82});
        binaryTree.delete(80);
        preOrderDatas = binaryTree.traversal();
        Assert.assertArrayEquals(preOrderDatas, new Object[]{50, 25, 12, 14, 40, 35, 45, 44, 43, 42, 75, 55, 60, 65, 63, 61, 90, 85, 82});

        // 删除两个子节点的节点
        binaryTree.delete(40);
        preOrderDatas = binaryTree.traversal();
        Assert.assertArrayEquals(preOrderDatas, new Object[]{50, 25, 12, 14, 42, 35, 45, 44, 43, 75, 55, 60, 65, 63, 61, 90, 85, 82});
        binaryTree.delete(50);
        preOrderDatas = binaryTree.traversal();
        Assert.assertArrayEquals(preOrderDatas, new Object[]{55, 25, 12, 14, 42, 35, 45, 44, 43, 75, 60, 65, 63, 61, 90, 85, 82});
    }

    private GenericBinaryTree<Integer> buildTree(int[] datas) {
        GenericBinaryTree<Integer> binaryTree = new GenericBinaryTree<>();
        for (int data : datas) {
            binaryTree.add(data);
        }
        return binaryTree;
    }
}