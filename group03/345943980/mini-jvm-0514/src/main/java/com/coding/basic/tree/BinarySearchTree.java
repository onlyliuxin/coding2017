package com.coding.basic.tree;

/**
 * 实现二叉查找树的操作
 * 
 * @author chenming E-mail:cm_20094020@163.com
 * @version 创建时间：2017年5月15日 下午9:45:47
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<?>> {
	BinaryTreeNode<T> root;

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public T findMin() {
		return null;
	}

	public T findMax() {
		return null;
	}

	public int height() {
		return -1;
	}

	public int size() {
		return -1;
	}

	public void remove(T e) {

	}
}
