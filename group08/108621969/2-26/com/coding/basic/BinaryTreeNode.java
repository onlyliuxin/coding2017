package com.coding.basic;

/**
 * Created by zhangjiatao on 2017/2/25.
 */
public class BinaryTreeNode {
    private Object data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(Object o) {
        this.data = o;
        this.left = null;
        this.right = null;
    }

    public int getData() {
        return (int) data;
    }

    public void setData(Object data) {
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

    public BinaryTreeNode insert(Object o) {
        BinaryTreeNode newNode = new BinaryTreeNode(o);
        insertInto(this, newNode);
        return this;
    }

    private void insertInto(BinaryTreeNode tree, BinaryTreeNode o) {
        if (o.getData() <= tree.getData()) {
            if (tree.getLeft() != null) insertInto(tree.getLeft(), o);
            else tree.setLeft(o);
        } else {
            if (tree.getRight() != null) insertInto(tree.getRight(), o);
            else tree.setRight(o);
        }
    }
}
