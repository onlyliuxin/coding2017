package com.coding.basic;

public class BinaryTreeNode {
	
	private int data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
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


	public BinaryTreeNode(int data) {
		this.data = data;
	}

	private BinaryTreeNode insertAt(BinaryTreeNode node, int o) {
		if (o < node.getData()) {
			if (node.getLeft() != null) {
				return insertAt(node.getLeft(), o);
			} else {
				BinaryTreeNode nowNode = new BinaryTreeNode(o);
				node.setLeft(nowNode);

				return nowNode;
			}
		} else {
			if (node.getRight() != null) {
				return insertAt(node.getRight(), o);
			} else {
				BinaryTreeNode nowNode = new BinaryTreeNode(o);
				node.setRight(nowNode);
				return nowNode;
			}
		}
	}

	public BinaryTreeNode insert(int o){
		return insertAt(this, o);
	}

	@Override
	public String toString() {
		return "data: " + data;
	}
}
