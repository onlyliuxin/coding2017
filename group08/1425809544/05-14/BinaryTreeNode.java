package com.zhuoyue.scheduleplan.domain;

/**
 * 二叉树
 *
 * @author xyy
 * @create 2017-05-09 19:42
 **/
public class BinaryTreeNode<T> {


    private  T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode() {
    }

    public T getData() {
        return data;
    }

    public BinaryTreeNode setData(T data) {
        this.data = data;
        return this;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public BinaryTreeNode setLeft(BinaryTreeNode<T> left) {
        this.left = left;
        return this;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public BinaryTreeNode setRight(BinaryTreeNode<T> right) {
        this.right = right;
        return this;
    }


    public BinaryTreeNode(T data) {
        this.data = data;
    }

    public BinaryTreeNode<T> insert(Object o) {
        return null;
    }

}
