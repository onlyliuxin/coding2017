package com.coding.basic;

public class BinaryTreeNode {

	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	int i = 0;

	public BinaryTreeNode(Object data){
		this.data = data;
	}
	
	public BinaryTreeNode(){}
	
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
		
		if(data == null){
			data=o;
			return this;
		}
		
		if (left == null) {
			left = new BinaryTreeNode(o);
		} else if(right == null){
			right = new BinaryTreeNode(o);
		} else {
			left.insert(o);
		}
		return new BinaryTreeNode(o);
	}

}
