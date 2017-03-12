package com.coding.basic;

/**
 * Created by yrs on 2017/2/25.
 */
public class BinaryTreeNode<T extends Comparable> {

    private T data;

    private BinaryTreeNode left;

    private BinaryTreeNode right;

    public BinaryTreeNode(BinaryTreeNode left, T o, BinaryTreeNode right) {
        setData(o);
        setLeft(left);
        setRight(right);
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

    public BinaryTreeNode insert(Object o) {
        return null;
    }


    public BinaryTreeNode insert(T data) {
        if(this.data == null) {
            this.data = data;
            return this;
        }
        int compareResult = this.data.compareTo(data);
        if(compareResult > 0) {
            if(this.left == null) {
                this.left = new BinaryTreeNode(null, data, null);
                return this.left;
            }else {
                return this.left.insert(data);
            }
        }else if (compareResult < 0) {
            if(this.right == null) {
                this.right = new BinaryTreeNode(null, data, null);
                return this.right;
            }else {
                return this.right.insert(data);
            }
        }else {
            return this;
        }
    }

}
                