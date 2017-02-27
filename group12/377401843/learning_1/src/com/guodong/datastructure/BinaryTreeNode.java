package com.guodong.datastructure;

public class BinaryTreeNode {

	private int data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode(int data) {
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
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

	public BinaryTreeNode insert(int o) {

		if (o < data) {
			if (left != null) {
				left.insert(o);
			} else {
				left = new BinaryTreeNode(o);
				return left;
			}
		} else {
			if (right != null) {
				right.insert(o);
			} else {
				right = new BinaryTreeNode(o);
				return right;
			}
		}

		return null;
	}

}
