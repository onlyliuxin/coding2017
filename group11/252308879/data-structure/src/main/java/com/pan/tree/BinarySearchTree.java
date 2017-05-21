package com.pan.tree;

/**
 * 二叉查找树：目前使用Integer 实现
 *
 * @param <T>
 * @author Pan
 */
public class BinarySearchTree<T extends Comparable> {

    /**
     * 定义根节点
     */
    private BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    /**
     * 查询二叉树中最小的节点
     *
     * @return 节点
     */
    public T findMin() {
        if (root == null) {
            return null;
        }
        return findMin(root);
    }

    private T findMin(BinaryTreeNode<T> root) {
        if (root.getLeft() == null) {
            return root.getData();
        }
        return findMin(root.getLeft());
    }

    /**
     * 查询二叉树中最大的节点
     *
     * @return 节点
     */
    public T findMax() {
        if (root == null){
            return null;
        }
        return findMax(root);
    }

    private T findMax(BinaryTreeNode<T> node){
        if (node.getRight() == null){
            return node.getData();
        }
        return findMax(node.getRight());
    }

    /**
     * 二叉树的最大高度和深度
     *
     * @return 节点
     */
    public int height() {
        return -1;
    }

    private int height(BinaryTreeNode<T> node){
        return 0;
    }

    /**
     * 二叉数的大小：节点的个数
     *
     * @return 节点
     */
    public int size() {
        return -1;
    }

    /**
     * 移除指定的节点
     *
     * @param e 指定的节点
     */
    public void remove(T e) {

    }

}

