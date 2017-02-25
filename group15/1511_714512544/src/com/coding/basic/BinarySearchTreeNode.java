package com.coding.basic;

/**
 * 二叉树BST结点
 */
public class BinarySearchTreeNode<T>{
	private T data;
	private BinarySearchTreeNode<T> left;
	private BinarySearchTreeNode<T> right;
	private int state;  //递归状态(非递归遍历表示一个节点运行到的状态)

	public BinarySearchTreeNode(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public BinarySearchTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinarySearchTreeNode<T> left) {
		this.left = left;
	}

	public BinarySearchTreeNode<T> getRight() {
		return right;
	}

	public void setRight(BinarySearchTreeNode<T> right) {
		this.right = right;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
