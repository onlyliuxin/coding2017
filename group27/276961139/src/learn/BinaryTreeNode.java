package com.liam.learn.code2017;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode(Object data) {
		this.data = data;
	}

	public BinaryTreeNode() {
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

		if (left != null){
			return left = new BinaryTreeNode(o);
		}
		if (right !=null){
			return right = new BinaryTreeNode(o);
		}
		throw new RuntimeException("左右子树已经都有值了");
	}
	
}
