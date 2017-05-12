package com.github.orajavac.coding2017.basic;

public class BinaryTreeNode<T> {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode(Integer i){
		data = i;
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
		return  null;
	}
	
}
