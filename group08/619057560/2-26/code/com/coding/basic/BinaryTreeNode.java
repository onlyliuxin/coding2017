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
	
	public BinaryTreeNode insert(Object o){
		if (data == null || ((Integer)data).intValue() == ((Integer)o).intValue()) {
			data = o;
			return this;
		}
		else if (((Integer)o).intValue() < ((Integer)data).intValue()) {
			if (left == null) {
				left = new BinaryTreeNode();
			}
			return left.insert(o);
		}
		else {
			if (right == null) {
				right = new BinaryTreeNode();
			}
			return right.insert(o);
		}
	}
	
}
