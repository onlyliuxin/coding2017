package com.coding.basic;


public class BinaryTreeNode implements Comparable<BinaryTreeNode> {
    public BinaryTreeNode(Object data) {
        this.data = data;
    }

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
        //左子节点的值永远比父节点的值小
        //右子节点的值永远比父节点的值大
        BinaryTreeNode node = new BinaryTreeNode(o);
        insertNode(node);
        return node;
    }
    private void insertNode(BinaryTreeNode node){
        insertNode(this,node);
    }
    private void insertNode(BinaryTreeNode parentNode, BinaryTreeNode node) {
        if (parentNode.compareTo(node) <= 0) {//数字大于父节点
            if (parentNode.right == null) {
                parentNode.right = node;
                return;
            }
            insertNode(parentNode.right, node);
        } else {
            if (parentNode.left == null) {
                parentNode.left = node;
                return;
            }
            insertNode(parentNode.left, node);
        }
    }

    @Override
    public int compareTo(BinaryTreeNode o) {
        Integer i = (Integer) this.data;
        return i.compareTo((Integer) o.data);
    }
}
