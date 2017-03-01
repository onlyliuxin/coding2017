package com.coding.basic.container;

public class BinaryTreeNode<T extends Comparable> {
    private T data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private BinaryTreeNode root;

    public BinaryTreeNode(T data, BinaryTreeNode left, BinaryTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public BinaryTreeNode insert(T o) {
        if (null == root) {
            root = new BinaryTreeNode(o, null, null);
            return root;
        }

        return insert(o, root);
    }
    public BinaryTreeNode insert(T o, BinaryTreeNode node) {
        BinaryTreeNode nodeNew = new BinaryTreeNode<Comparable>(o, null, null);
        BinaryTreeNode nodeCurrent = node;
        if (o.compareTo(nodeCurrent.data) < 0) {
            if (nodeCurrent.left == null) {
                nodeCurrent.left = nodeNew;
                return nodeNew;
            } else {
                return insert(o, nodeCurrent.left);
            }
        } else if (o.compareTo(nodeCurrent.data) > 0) {
            if (nodeCurrent.right == null) {
                nodeCurrent.right = nodeNew;
                return nodeNew;
            } else {
                return insert(o, nodeCurrent.right);
            }
        } else {
            return nodeCurrent;
        }
    }
}
