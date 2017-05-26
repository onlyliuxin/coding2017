package com.sprint.datastructure.tree;

public class BinaryTreeNode<T extends Comparable> {
	private T data;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;

	public BinaryTreeNode(T data) {
		this.data = data;
	} 
	//setter && getter
	public void setData(T data) {
		this.data = data;
	}
	public T getData() {
		return data;
	}

	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}
	public BinaryTreeNode<T> getLeft() {
		return left;
	}

	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
	public BinaryTreeNode<T> getRight() {
		return right;
	}

	public BinaryTreeNode<T> insert(T data) {
		//二叉树的基本原则:
		//	left.data < this.data < right.data
		if (this.data == null) {
			this.data = data;
			return this;
		}

		int compareResult = this.data.compareTo(data);
		if (compareResult > 0) {
			//插入的data 小于 this.data
			if (this.left == null) {
				this.left = new BinaryTreeNode<T>(data);
				return this.left;
			} else {
				return this.left.insert(data);
			}
		} else if (compareResult < 0) {
			//插入的data 大于 this.data
			if (this.right == null) {
				this.right = new BinaryTreeNode<T>(data);
				this.right.data = data;
				return this.right;
			} else {
				return this.right.insert(data);
			}
		} else {
			//二叉树中不存在重复的元素
			return this;	
		}
	}
}
