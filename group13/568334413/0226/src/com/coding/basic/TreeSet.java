package com.coding.basic;

/**
 * Created by laibin on 2017/2/25.
 */
public class TreeSet {
    BinaryTreeNode root = null;
    public int size = 0;

    public void add(Integer integer) {

        BinaryTreeNode binaryTreeNode = new BinaryTreeNode();
        binaryTreeNode.setData(integer);
        if (root == null) {
            root = binaryTreeNode;
            size++;
            return;
        }
        insert(root, binaryTreeNode);
    }

    void insert(BinaryTreeNode node, BinaryTreeNode tempNode) {
        if (tempNode.getData() < node.getData()) {
            if (node.getLeft() == null) {
                node.setLeft(tempNode);
                size++;
                return;
            }
            insert(node.getLeft(), tempNode);
        } else if (tempNode.getData() > node.getData()) {
            if (node.getRight() == null) {
                node.setRight(tempNode);
                size++;
                return;
            }
            insert(node.getRight(), tempNode);
        }
    }

}
