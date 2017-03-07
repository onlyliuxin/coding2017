package com.coding2017.basic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kaitao.li on 17/2/24.
 */
public class BinaryTreeNodeTest {

    @Test
    public void insert() throws Exception {
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode(5);
        binaryTreeNode.insert(4);
        binaryTreeNode.insert(6);
        binaryTreeNode.insert(5);
        assertTrue(binaryTreeNode.getLeft().getData() == 4);
        assertTrue(binaryTreeNode.getRight().getData() == 6);
        assertTrue(binaryTreeNode.getLeft().getRight().getData() == 5);
        System.out.println(binaryTreeNode);
    }

}