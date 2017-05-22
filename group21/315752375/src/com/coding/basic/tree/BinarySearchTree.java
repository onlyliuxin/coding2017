package com.coding.basic.tree;

public class BinarySearchTree<T extends Comparable> {

	BinaryTreeNode<T> root;
	private int height;
	private int size = 0;

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
		this.height = trverse(root);
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public T findMin() {
		if (root == null)
			return null;
		while (root.getLeft() != null) {
			root = root.getLeft();
		}
		return root.getData();
	}

	public T findMax() {
		if (root == null)
			return null;
		while (root.getRight() != null) {
			root = root.getRight();
		}
		return root.getData();
	}

	public int height() {
		return height;
	}

	public int size() {
		return size;
	}

	public void remove(T e) {
		BinaryTreeNode<T> preNode = findPreNode(e);
		if (preNode == null)
			return;
		if (preNode.left == root)
			root = null;
		else {
			BinaryTreeNode<T> curNode = null;
			boolean isLeftChild = true;
			if (preNode.left.data.equals(e))
				curNode = preNode.left;
			else {
				curNode = preNode.right;
				isLeftChild = false;
			}
			if (curNode.left == null && curNode.right == null) {
				if (isLeftChild)
					preNode.left = null;
				else
					preNode.right = null;
				return;
			} else if (curNode.left == null || curNode.right == null) {
				if (curNode.left != null) {
					if (isLeftChild)
						preNode.left = curNode.left;
					else
						preNode.right = curNode.left;
				} else {
					if (isLeftChild)
						preNode.left = curNode.right;
					else
						preNode.right = curNode.right;
				}
				return;
			}else{
				curNode=curNode.right;
				while (curNode.left!=null) {
					curNode=curNode.left;
				}
				remove(curNode.data);
				if (isLeftChild) {
					preNode.left.data=curNode.data;
				}else preNode.right.data=curNode.data;
				
			}
		}

	}

	public int trverse(BinaryTreeNode<T> root) {
		int left = 0;
		int right = 0;
		if (root == null) {
			this.size = 0;
			return 0;
		}
		if (root.getLeft() != null) {
			left = trverse(root.getLeft());
		}
		if (root.getRight() != null) {
			right = trverse(root.getRight());
		}
		int height = 1 + (left > right ? left : right);
		this.size++;
		return height;
	}

	public BinaryTreeNode<T> findPreNode(T e) {
		if (root == null)
			return null;
		BinaryTreeNode<T> pre = null;
		if (root.data.equals(e)) {
			pre = new BinaryTreeNode<T>(null);
			pre.left = root;
			return pre;
		}
		pre=root;
		while (pre != null) {
			if (pre.data.compareTo(e) > 0) {
				if (pre.left == null)
					return null;
				if (pre.left.data.equals(e))
					return pre;
				pre = pre.left;
				continue;
			} else {
				if (pre.right == null)
					return null;
				if (pre.right.data.equals(e))
					return pre;
				pre =pre.right;
				continue;
			}
		}
		return null;
	}
}
