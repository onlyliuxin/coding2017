package com.github.miniyk2012.coding2017.basic;

public class BinaryTreeNode <E extends Comparable<E>> {
	private E data;
	private BinaryTreeNode<E> left;
	private BinaryTreeNode<E> right;
	
	public BinaryTreeNode(E x) {
		data = x;
	}
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
	
	public BinaryTreeNode<E> insert(E o){
		BinaryTreeNode<E> node = new BinaryTreeNode<E>(o);
		boolean left = true; 
		BinaryTreeNode<E> currentNode = this;
		BinaryTreeNode<E> previousNode = null;
		
		while (currentNode != null) {
			previousNode = currentNode;
			int compareTo = node.getData().compareTo(currentNode.getData());
			if (compareTo <= 0) { // 小于，往左插入
				currentNode = currentNode.left;
				left = true;
			} else {
				currentNode = currentNode.right;
				left = false;
			}
		}
		if (left) {
			previousNode.left = node;
		} else {
			previousNode.right = node;
		}
		return  node;
	}
	
}
