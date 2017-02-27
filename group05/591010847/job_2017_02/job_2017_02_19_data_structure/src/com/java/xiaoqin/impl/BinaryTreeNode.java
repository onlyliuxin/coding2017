package com.java.xiaoqin.impl;

/**
 * Created by xiaoqin on 17-2-26.
 */
public class BinaryTreeNode<T> {
    private T data;
    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public BinaryTreeNode insert(T o) {
        if (data == null) {
            data = o;
            return this;
        } else {
            return insert(this, o);
        }
    }

    private BinaryTreeNode insert(BinaryTreeNode node, T o) {
        if (o instanceof Integer) {
            if ((Integer) node.data > (Integer) o) {
                if (null == node.leftChild) {
                    node.leftChild = new BinaryTreeNode();
                    node.leftChild.data = o;
                    return node.leftChild;
                } else {
                    return insert(node.leftChild, o);
                }
            } else if ((Integer) node.data < (Integer) o) {
                if (null == node.rightChild) {
                    node.rightChild = new BinaryTreeNode();
                    node.rightChild.data = o;
                    return node.rightChild;
                } else {
                    return insert(node.rightChild, o);
                }
            } else {
                return node;
            }
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sbToString = new StringBuilder();
        sbToString.append("data:").append(data);
        if (null != leftChild) {
            sbToString.append("\t").append(data).append("left:").append(leftChild.toString());
        }
        if (null != rightChild) {
            sbToString.append("\t").append(data).append("right:").append(rightChild.toString());
        }
        return sbToString.toString();
    }
}
