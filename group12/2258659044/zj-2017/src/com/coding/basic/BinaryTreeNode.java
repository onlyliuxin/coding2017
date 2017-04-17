package com.coding.basic;
public class BinaryTreeNode<E> {
	
	private E data;
	//父节点
	private BinaryTreeNode<E> parent;
	private BinaryTreeNode<E> left;
	private BinaryTreeNode<E> right;
	
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	
	public BinaryTreeNode<E> getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode<E> left) {
		this.left = left;
	}
	
	public BinaryTreeNode<E> getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode<E> right) {
		this.right = right;
	}
	
	public BinaryTreeNode<E> getParent() {
		return parent;
	}
	public void setParent(BinaryTreeNode<E> parent) {
		this.parent = parent;
	}
}
