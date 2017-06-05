package com.github.HarryHook.coding2017.tree;

public class BinaryTreeNode<T extends Comparable> {

    public  T data;
    public  BinaryTreeNode<T> left;
    public  BinaryTreeNode<T> right;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(T data) {
	this.data = data;
    }

    public BinaryTreeNode(T data, BinaryTreeNode<T> right, BinaryTreeNode<T> left) {
	this.data = data;
	this.left = left;
	this.right = right;
    }

    public T getData() {
	return data;
    }

    public void setData(T data) {
	this.data = data;
    }

    public BinaryTreeNode<T> getLeft() {
	return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
	this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
	return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
	this.right = right;
    }

    public BinaryTreeNode<T> insert(Object o) {

	if (data == null) {
	    data = (T) o;
	} else {
	    int comparedValue = data.compareTo((T) o);
	    if (comparedValue > 0) {
		if (left == null) {
		    left = new BinaryTreeNode((T) o, null, null);
		} else {
		    left = left.insert(o);
		}
	    } else {
		if (right == null) {
		    right = new BinaryTreeNode((T) o, null, null);
		} else {
		    right = right.insert(o);
		}
	    }
	}
	return this;
    }

    public static void main(String[] args) {
	BinaryTreeNode<Integer> binaryTreeNode = new BinaryTreeNode<>();
	binaryTreeNode.insert(4);
	binaryTreeNode.insert(1);
	binaryTreeNode.insert(3);
	binaryTreeNode.insert(5);
	binaryTreeNode.insert(2);
	System.out.println(binaryTreeNode.getData());
	System.out.println(binaryTreeNode.getLeft().getData());
	System.out.println(binaryTreeNode.getRight().getData());
	System.out.println(binaryTreeNode.getLeft().getRight().getData());
	System.out.println(binaryTreeNode.getLeft().getRight().getLeft().getData());
    }
}
