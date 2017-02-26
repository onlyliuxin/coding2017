package com.coding2017.basic;

public class BinaryTreeNode {

    private Integer data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

	public BinaryTreeNode insert(Integer o) {
        if (o <= data) {
            if (left == null) {
                left = new BinaryTreeNode(o);
                return left;
            }
            return left.insert(o);
        } else {
            if (right == null) {
                right = new BinaryTreeNode(o);
                return right;
            }
            return right.insert(o);
        }
	}

    public BinaryTreeNode(Integer data) {
	    this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
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

    @Override
    public String toString() {
        return data + " " + left + " " + right;
    }
}
