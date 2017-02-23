package com.coding.basic;

/**
 * Binary Tree
 */
public class BinaryTreeNode {

    /**
     * 节点数据
     */
    private Object data;
    /**
     * 左节点
     */
    private BinaryTreeNode left;
    /**
     * 右节点
     */
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
        return null;
    }

    public BinaryTreeNode(Object o){
        this.setData(o);
    }

}