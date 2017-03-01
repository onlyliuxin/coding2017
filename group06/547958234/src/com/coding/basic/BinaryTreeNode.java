package com.coding.basic;

public class BinaryTreeNode {

    private Object data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public Object getData() {
        return data;
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
        if (o < this.data) {
            if (this.left != null) {
                this.left.insert(o);
            } else {
                BinaryTreeNode node = new BinaryTreeNode();
                node.data = o;
                this.left = node;
            }
        }
        if (o > this.data) {
            if (this.right != null) {
                this.right.insert(o);
            } else {
                BinaryTreeNode node = new BinaryTreeNode();
                node.data = o;
                this.right = node;
            }
        }
        return this;
    }

}
