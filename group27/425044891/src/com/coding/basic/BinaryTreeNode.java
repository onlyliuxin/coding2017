package com.coding.basic;

public class BinaryTreeNode<T extends Comparable<T>> {

	private T data;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;

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

	public BinaryTreeNode<T> insert(T o) {
		if (o.compareTo(data) <= 0) {
			return getLeft().insert(o);
		} else {
			return getRight().insert(o);
		}
	}

}