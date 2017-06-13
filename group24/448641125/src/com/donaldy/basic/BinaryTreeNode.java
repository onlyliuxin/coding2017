package com.donaldy.basic;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode(Object data) {
		this.data = data;
		left = null;
		right = null;
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
	
	public BinaryTreeNode insert(Object o) {
		BinaryTreeNode bNode = new BinaryTreeNode(o);
		insertNewNode(bNode);
		return  bNode;
	}
	private void insertNewNode(BinaryTreeNode node) {
		if ((int)data <= (int)node.getData()) {
			if (this.right != null)
				this.right.insertNewNode(node);
			else
				this.right = node;
		} else {
			if (this.left != null)
				this.left.insertNewNode(node);
			else
				this.left.insertNewNode(node);
		}
	}
	
}
