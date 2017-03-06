package com.coding.basic;
public class BinaryTreeNode {
	
	private Object data;
	//父节点
	private BinaryTreeNode parent;
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
	
	public BinaryTreeNode getParent() {
		return parent;
	}
	public void setParent(BinaryTreeNode parent) {
		this.parent = parent;
	}
}
