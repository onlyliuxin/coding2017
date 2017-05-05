package com.github.zhanglifeng.coding2017.basic;

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
		return this.data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public BinaryTreeNode getLeft() {
		return this.left;
	}
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	public BinaryTreeNode getRight() {
		return this.right;
	}
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
	public BinaryTreeNode insert(Object o){
		return  null;
	}
	
}
