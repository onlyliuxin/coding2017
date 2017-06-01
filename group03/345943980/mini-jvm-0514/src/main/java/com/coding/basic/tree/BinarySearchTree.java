package com.coding.basic.tree;

import java.util.NoSuchElementException;

/**
 * 实现二叉查找树的操作
 * 
 * @author chenming E-mail:cm_20094020@163.com
 * @version 创建时间：2017年5月15日 下午9:45:47
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public class BinarySearchTree<T extends Comparable> {
	BinaryTreeNode<T> root;

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> root) {
		BinaryTreeNode<T> p = root;
		while (p.left != null) {
			p = p.left;
		}
		return p;
	}
	
	public BinaryTreeNode<T> findMin() {
		if (root == null) {
			throw new NoSuchElementException("根节点为空");
		}
		return findMin(root);
	}

	public BinaryTreeNode<T> findMax() {
		if (root == null) {
			throw new NoSuchElementException("根节点为空");
		}
		return findMax(root);
	}

	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> root) {
		BinaryTreeNode<T> p = root;
		while (p.right != null) {
			p = p.right;
		}
		return p;
	}

	public int height() {
		if (root == null)
			return 0;
		return getHeight(root);
	}

	private int getHeight(BinaryTreeNode<T> node) {
		int leftHeight = getHeight(node.left);
		int rightHeight = getHeight(node.right);
		return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
	}
	

	public int size() {
		if(root==null) return 0;
		return getSize(root);
	}

	private int getSize(BinaryTreeNode<T> node) {
		int leftSize = getSize(root.left);
		int rightSize = getSize(root.right);
		return 1 + leftSize + rightSize;
	}

	public void remove(T e) {
		if (root == null) {
			throw new NoSuchElementException("根节点为空");
		}
		remove(e, root);
	}
	
	@SuppressWarnings("unchecked")
	private BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> node) {
		if (node.data.compareTo(e) == 0) {
			if (node.left == null && node.right == null) {
				return null;
			} else if (node.right == null && node.left != null) {
				return node.left;
			} else if (node.right != null && node.left == null) {
				return node.right;
			} else {
				BinaryTreeNode<T> minParent = findMinParent(node.right);
				node.data = minParent.left.data;
				minParent.left = null;
				return node;
			}
		} else if (node.data.compareTo(e) < 0) {
			node.right = remove(e, node.right);
			return node;
		} else {
			node.left = remove(e, node.left);
			return node;
		}
	}

	private BinaryTreeNode<T> findMinParent(BinaryTreeNode<T> node) {
		BinaryTreeNode<T> p = node;
		BinaryTreeNode<T> parent = null;
		while (p.left != null) {
			parent = p;
			p = p.left;
		}
		return parent;
	}
}
