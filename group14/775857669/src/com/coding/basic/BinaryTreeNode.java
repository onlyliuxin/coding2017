package com.coding.basic;

public class BinaryTreeNode<T extends Comparable<T>> {
	private T data;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	
	public Object getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryTreeNode<T> getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}
	public BinaryTreeNode<T> getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
	
	public void insert(T t) {
		BinaryTreeNode<T> node = new BinaryTreeNode<>();
		node.setData(t);
		compare(this, node);
	}
	
	private void compare(BinaryTreeNode<T> targetNode, BinaryTreeNode<T> insertNode) {
		
		if (targetNode.data.compareTo(insertNode.data) < 0) {
			if (targetNode.left != null){
				compare(targetNode.getLeft(), insertNode);
			} else {
				targetNode.left = insertNode;
			}
				
		} else {
			if (targetNode.right != null) {
				compare(targetNode.getRight(), insertNode);
			} else {
				targetNode.right = insertNode;
			}
			
		}
	}
	
}
