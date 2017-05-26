package com.coding.basic.tree;

import java.util.List;

public class BinarySearchTree<T extends Comparable<? super T>> {

	BinaryTreeNode<T> root;

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public T findMin() {

		BinaryTreeNode<T> temp = root;
		if (temp == null) {
			return null;
		}
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp.data;
	}

	public T findMax() {

		BinaryTreeNode<T> temp = root;
		if (temp == null) {
			return null;
		}
		while (temp.right != null) {
			temp = temp.right;
		}
		return temp.data;
	}

	public int height() {

		return maxDepth(this.root);
	}

	private int maxDepth(BinaryTreeNode<T> root) {

		if (root == null)
			return 0;
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);

		return leftDepth > rightDepth ? (leftDepth + 1) : (rightDepth + 1);
	}

	public int size() {

		List<T> dataList = BinaryTreeUtil.preOrderVisit(root);

		return dataList.size();
	}

	public void remove(T e) {
		
		remove(this.root,e);
	}
	
	private void remove(BinaryTreeNode<T> root,T value){
		
		// 获取将要删除的节点
		BinaryTreeNode<T> node = BinaryTreeUtil.findNode(root, value);
		if (node == null) {
			return;
		}

		// 获取当前节点的父节点以及在父节点中的位置
		BinaryTreeNode<T> parent = BinaryTreeUtil.findParentNode(root, null, value);
		if (parent == null) {
			return;
		}
		// position记录当前节点在父节点中的位置，如果为true为左节点，false又节点
		boolean position = true;
		if (node.equals(parent.right)) {
			position = false;
		}

		// 当前节点为叶子节点直接删除
		if (node.left == null && node.right == null) {
			assignmentValue(null,parent,position);
		}

		// 当前节点只有一个叶子节点
		if ((node.left != null && node.right == null)) {
			assignmentValue(node.left, parent, position);
		}

		if ((node.right != null && node.left == null)) {
			assignmentValue(node.right, parent, position);
		}

		if (node.left != null && node.right != null) {

			// 当前节点有两个子节点
			// 获取右节点中值最小的节点
			T minValue = new BinarySearchTree<T>(node.right).findMin();
			if (position) {
				parent.left.data = minValue;
			} else {
				parent.right.data = minValue;
			}
			//删除该节点值为minValue的节点
			remove(node.left,minValue);
		}
	}

	/**
	 * 为删除元素所在的节点赋值
	 * @param node
	 * @param parent
	 * @param position
	 */
	private void assignmentValue(BinaryTreeNode<T> node,
			BinaryTreeNode<T> parent, boolean position) {
		if (position) {
			parent.left = node;
		} else {
			parent.right = node;
		}
	}
}
