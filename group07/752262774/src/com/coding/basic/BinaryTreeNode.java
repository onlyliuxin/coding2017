package com.coding.basic;

/**
 * Created by yrs on 2017/2/25.
 */
public class BinaryTreeNode {

    private Object data;

    private BinaryTreeNode left;

    private BinaryTreeNode right;

    public BinaryTreeNode(BinaryTreeNode left, Object o, BinaryTreeNode right) {
        setData(o);
        setLeft(left);
        setRight(right);
    }

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
        return null;
    }

}
                