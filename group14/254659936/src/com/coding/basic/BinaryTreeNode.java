package com.coding.basic;

public class BinaryTreeNode<T extends BinaryTreeNode.Compare> {

    private T data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

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
        BinaryTreeNode node = new BinaryTreeNode();
        node.setData(o);
        insert(this, node);
        return node;
    }

    private void insert(BinaryTreeNode parent, BinaryTreeNode child) {
        BinaryTreeNode node;
        if (child.getData().isLargeThanTarget(parent.getData())) {
            // 子节点比父节点大，需要向右插入
            node = getRight();
            if (null == node) {
                // 右节点为空则可以直接插入
                parent.setRight(node);
            } else {
                // 递归检查右边子树的插入位置
                insert(node, child);
            }
        } else {
            // 子节点比父节点小，或者等于父节点，需要向左插入
            node = getLeft();
            if (null == node) {
                // 左节点为空，则直接插入
                parent.setLeft(node);
            } else {
                // 递归检查左子树的插入位置
                insert(node, child);
            }
        }
    }

    public interface Compare {
        boolean isLargeThanTarget(Compare target);
    }

}
