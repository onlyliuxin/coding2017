package com.github.fei9009.coding2017.basic;

public class BinaryTreeNode <Object extends Comparable<Object>> {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public BinaryTreeNode(Object o) {
		data = o;
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
	
	public BinaryTreeNode insert(Object o){
		BinaryTreeNode node = new BinaryTreeNode(o);
		boolean left = true;
		BinaryTreeNode cur = this;
		BinaryTreeNode pre = null;
		while (cur != null) {
			pre = cur;
			if (node.getData().compareTo(cur.getData()) <= 0) {
				cur = cur.left;
				left = true;
			} else {
				cur = cur.right;
				left = false;
			}
		}
		if(left) {
			pre.left = node;
		} else {
			pre.right = node;
		}
		return node;
	}
	
}
