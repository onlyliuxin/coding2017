package com.coding.basic;

public class BinaryTreeNode {
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public void insert(BinaryTreeNode node) {
		if (this.data == null) {
			// empty binary tree
			this.data = node.data;
			this.left = node.left;
			this.right = node.right;
		} else if (((Integer) this.data).intValue() >= ((Integer) node.data).intValue()) {
			this.left.insert(node);
		}else{
			this.right.insert(node);
		}
	}

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
}
