package com.coding.basic;

public class BinaryTreeNode {

	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

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

	public BinaryTreeNode insert(Object o) {
		BinaryTreeNode node = new BinaryTreeNode();
		if (left == null && right != null) {
			right = node;
		} else if (right == null & left != null) {
			left = node;
		} else {
			return null;
		}
		return node;
	}

}
