package com.coding;

public class BinaryTreeNode {

	private int data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
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
		if (data >= left.data) {
			this.left = left;
		}		
	}
	public BinaryTreeNode getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode right) {
		if (data < right.data) {
			this.right = right;
		}		
	}
	
	public BinaryTreeNode insert(int o) {
		return insert(this, o);
	}
	
	private BinaryTreeNode insert(BinaryTreeNode tree,int o){
		if (null == tree) {
			tree = new BinaryTreeNode(o, null, null);
		} else if (o > tree.data) {
			tree.right =insert(tree.right,o);
		} else {
			tree.left = insert(tree.left,o);
		}
		return tree;
	}	
}
