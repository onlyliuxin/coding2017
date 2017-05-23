package com.coding.week11;

import com.coding.week10.BinaryTreeNode;

public class BinarySearchTree<T extends Comparable<T>> {

	BinaryTreeNode<T> root;

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}
	public T findMin(){
		BinaryTreeNode<T> min = findMin(root);
		return min == null ? null : min.getData();
	}

	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
		if (node == null) {
			return null;
		}
		BinaryTreeNode<T> left = node.getLeft();
		if (left == null) {
			return node;
		} else {
			return findMin(left);
		}
	}
	public T findMax(){
		if (root == null) {
			throw new RuntimeException("has no data");
		}
		BinaryTreeNode<T> t = root.getRight();
		T data = null;
		while (t != null) {
			data = t.getData();
			t = t.getRight();
		}

		return data;
	}
	public int height() {
		return height(root);
	}

	private int height(BinaryTreeNode<T> temp) {
		if (temp == null) {
			return 0;
		}
		return 1 + Math.max(height(temp.getRight()), height(temp.getLeft()));
	}

	public int size() {
		return size(root);
	}

	public int size(BinaryTreeNode<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + size(node.getLeft()) + size(node.getRight());
	}


	public void remove(T e){
		remove(root, e);
	}

	private BinaryTreeNode<T> remove(BinaryTreeNode<T> node, T e) {
		if (node == null) {
			return node;
		}
		int compareRes = node.getData().compareTo(e);
		if (compareRes < 0) {
			node.setRight(remove(node.getRight(), e));
		} else if (compareRes > 0) {
			node.setLeft(remove(node.getLeft(), e));
		} else {
			BinaryTreeNode<T> left = node.getLeft();
			BinaryTreeNode<T> right = node.getRight();
			if (left != null && right != null) {
				T data = findMin(right).getData();
				node.setData(data);
				node.setRight(remove(right, data));
			} else {
				node = left != null ? left : right;
			}
		}
		return node;
	}

	public static void main(String[] args) {
		System.out.println(Math.max(0,0));
	}
}

