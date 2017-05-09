package com.coding.basic.tree;

public class BinaryTreeNode<T> {
	
	private T data;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	
	private BinaryTreeNode<T> parent = null;
	private boolean flag = false;
	
	public boolean isRead() {
		return flag;
	}	
	public BinaryTreeNode<T> getParent() {
		return parent;
	}
	public void setParent(BinaryTreeNode<T> parent) {
		this.parent = parent;
	}
	public BinaryTreeNode(T data){
		this.data=data;
	}
	public T getData() {
		this.flag = true;
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
		left.setParent(this);
	}
	public BinaryTreeNode<T> getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
		right.setParent(this);
	}
	
	public boolean hasLeft(){
		return this.left!=null;
	}
	
	public boolean hasRight(){
		return this.right!=null;
	}
	
	public BinaryTreeNode<T> insert(Object o){
		return  null;
	}
	
}
