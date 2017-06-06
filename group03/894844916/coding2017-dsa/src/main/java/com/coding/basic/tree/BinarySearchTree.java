package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> {

	BinaryTreeNode<T> root;

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public T findMin() {
		BinaryTreeNode<T> currentNode = root;
		while (currentNode.getLeft() != null) {
			currentNode = currentNode.getLeft();
		}
		return currentNode.getData();
	}

	public T findMin(BinaryTreeNode<T> node) {
		if (node==null) {
			return null;
		}
		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		return node.getData();
	}
	
	public T findMax() {
		BinaryTreeNode<T> currentNode = root;
		while (currentNode.getRight() != null) {
			currentNode = currentNode.getRight();
		}
		return currentNode.getData();
	}

	public int height() {
		if (root == null) {
			return 0;
		}
		return height(root);
	}

	private int height(BinaryTreeNode<T> node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = height(node.getLeft());
		int rightHeight = height(node.getRight());
		if (leftHeight >= rightHeight) {
			return leftHeight + 1;
		}
		return rightHeight + 1;
	}

	public int size() {
		if (root == null) {
			return 0;
		}
		return size(root);
	}

	private int size(BinaryTreeNode<T> node) {
		if (root == null) {
			return 0;
		}
		int leftSize = size(node.getLeft());
		int rightSize = size(node.getRight());
		return leftSize + rightSize + 1;
	}

	public void remove(T e) {
		remove(e, root);
	}
	
	private void remove(T e,BinaryTreeNode<T> node){
		if (node==null) {
			return;
		}
		if (node.getData().compareTo(e)>0) {
			remove(e, node.getLeft());
		}else if (node.getData().compareTo(e)<0) {
			remove(e, node.getRight());
		}else if (node.getLeft()!=null&&node.getRight()!=null) {
			node.setData(findMin(node.getRight()));
			remove(findMin(node.getRight()),node.getRight());
		}else {
			if (node.getLeft()==null) {
				node.setData(node.getRight().getData());
				node.setRight(null);
			}
			if (node.getRight()==null) {
				node.setData(node.getLeft().getData());
				node.setLeft(null);
			}
		}
	}

	public List<T> levelVisit() {
		if (root == null) {
			return null;
		}
		Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
		List<T> list = new ArrayList<>();
		queue.add(root);
		list.add(root.getData());
		while (!queue.isEmpty()) {
			int n = queue.size();
			for (int i = 0; i < n; i++) {
				BinaryTreeNode<T> node = queue.poll();
				list.add(node.getData());
				if (node.getLeft() != null) {
					queue.add(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.add(node.getRight());
				}
			}
		}
		return list;
	}

	public boolean isValid() {
		if (root == null) {
			return true;
		}
		return isValid(root);
	}

	private boolean isValid(BinaryTreeNode<T> node) {
		if (node == null) {
			return true;
		}
		if (node.getLeft().getData().compareTo(node.getData()) > 0) {
			return false;
		} else {
			isValid(node.getLeft());
		}
		if (node.getRight().getData().compareTo(node.getData()) < 0) {
			return false;
		} else {
			isValid(node.getRight());
		}
		return true;
	}

	public T getLowestCommonAncestor(T n1, T n2) {
		if (root == null) {
			return null;
		}
		return getLowestCommonAncestor(root, n1, n2);
	}

	private T getLowestCommonAncestor(BinaryTreeNode<T> node, T n1, T n2) {
		if (root == null) {
			return null;
		}
		if (node.getData().compareTo(n1) > 0 && node.getData().compareTo(n2) > 0) {
			return getLowestCommonAncestor(node.getLeft(), n1, n2);
		}
		if (node.getData().compareTo(n1) < 0 && node.getData().compareTo(n2) < 0) {
			return getLowestCommonAncestor(node.getRight(), n1, n2);
		}
		return node.getData();
	}

	/**
	 * 返回所有满足下列条件的节点的值： n1 <= n <= n2 , n 为 该二叉查找树中的某一节点
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 */
	public List<T> getNodesBetween(T n1, T n2) {
		List<T> list = new ArrayList<>();
		getNodesBetween(list, root, n1, n2);
		return list;
	}

	private List<T> getNodesBetween(List<T> list, BinaryTreeNode<T> node, T n1, T n2) {
		if (node == null) {
			return null;
		}
		if (node.getData().compareTo(n1) > 0) {
			getNodesBetween(list, node.getLeft(), n1, n2);
		}
		if (node.getData().compareTo(n2) < 0) {
			getNodesBetween(list, node.getRight(), n1, n2);
		}
		list.add(node.getData());
		return list;
	}
}
