package com.coding.basic.tree;

import java.util.Comparator;

public class BinaryTreeNode<T> {

	public T data;
	// 父节点
	public BinaryTreeNode<T> parent;
	public BinaryTreeNode<T> left;
	public BinaryTreeNode<T> right;

	public BinaryTreeNode(T data) {
		this.data = data;
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

	public BinaryTreeNode<T> getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode<T> parent) {
		this.parent = parent;
	}

	public BinaryTreeNode<T> insert(T o,Comparator<? super T> comptor) {

		BinaryTreeNode<T> parent;
		BinaryTreeNode<T> currentNode = this;
		BinaryTreeNode<T> treeNode = new BinaryTreeNode<T>(o);

		while (true) {
			parent = currentNode;			
			if (comptor.compare(currentNode.data, o)>0) {// 向左放
				currentNode = currentNode.getLeft();
				if (currentNode == null) {
					parent.setLeft(treeNode);
					treeNode.setParent(parent);
					break;
				}
			} else if (comptor.compare(currentNode.data, o)<0) {// 向右放
				currentNode = currentNode.getRight();
				if (currentNode == null) {
					parent.setRight(treeNode);
					treeNode.setParent(parent);
					break;
				}
			} else {
				break;
			}
		}

		return treeNode;
	}
	
}
