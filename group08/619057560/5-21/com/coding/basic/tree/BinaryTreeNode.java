package com.coding.basic.tree;

public class BinaryTreeNode<T> {
	
	public T data;
	public BinaryTreeNode<T> left;
	public BinaryTreeNode<T> right;
	
	public BinaryTreeNode(T data){
		this.data=data;
	}
	public T getData() {
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
	
	@SuppressWarnings("unchecked")
	public BinaryTreeNode<T> insert(Object o){
		if (!(o instanceof Comparable)) {
			return null;
		}
		
		Comparable<T> c = (Comparable<T>)o;
		if (data == null) {
			data = (T)c;
			return this;
		} 
		
		if (c.compareTo(data) < 0) {
			if (left == null) {
				left = new BinaryTreeNode<T>((T)c);
				return left;
			}
			return left.insert(c);
		}
		
		if (right == null) {
			right = new BinaryTreeNode<T>((T)c);
		}
		
		return right.insert(c);
	}
	
}
