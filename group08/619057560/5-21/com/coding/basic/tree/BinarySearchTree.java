package com.coding.basic.tree;

public class BinarySearchTree<T extends Comparable<T>> {
	
	BinaryTreeNode<T> root;
	
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	
	public T findMin(){
		@SuppressWarnings("unchecked")
		BinaryTreeNode<T> n = (BinaryTreeNode<T>)findMinNode(root)[0];
		return n==null?null:n.getData();
	}

	// return: an array containing the node with minimum data and its parent
	private Object[] findMinNode(BinaryTreeNode<T> n) {
		if (n == null) {
			return new Object[] {null, null};
		}
		
		BinaryTreeNode<T> p = null;
		while (n.getLeft() != null) {
			p = n;
			n = n.getLeft();
		}
		return new Object[] {n, p};
	}
	
	public T findMax(){
		@SuppressWarnings("unchecked")
		BinaryTreeNode<T> n = (BinaryTreeNode<T>)findMaxNode(root)[0];
		return n==null?null:n.getData();
	}

	// return: an array containing the node with maximum data and its parent
	private Object[] findMaxNode(BinaryTreeNode<T> n) {
		if (n == null) {
			return new Object[] {null, null};
		}
		
		BinaryTreeNode<T> p = null;
		while (n.getRight() != null) {
			p = n;
			n = n.getRight();
		}
		return new Object[] {n, p};
	}
	
	public int height() {
	    return getHeightOfNode(root);
	}
	
	private int getHeightOfNode(BinaryTreeNode<T> n) {
		if (n == null) {
			return 0;
		}
		
		return Math.max(getHeightOfNode(n.getLeft()), getHeightOfNode(n.getRight())) + 1;
	}

	public int size() {
		return getSizeOfNode(root);
	}
	
	private int getSizeOfNode(BinaryTreeNode<T> n) {
		if (n == null) {
			return 0;
		}
		return getSizeOfNode(n.getLeft()) + getSizeOfNode(n.getRight()) + 1;
	}

	public void remove(T e){
		removeDataFromNode(root, e, null);
	}

	private boolean removeDataFromNode(BinaryTreeNode<T> n, T e, BinaryTreeNode<T> parent) {
		if (n == null || n.getData() == null) {
			return false;
		}
		
		if (e.compareTo(n.getData()) < 0) {
			return removeDataFromNode(n.getLeft(), e, n);
		} 
		
		if (e.compareTo(n.getData()) > 0) {
			return removeDataFromNode(n.getRight(), e, n);
		} 
		
		return removeNode(n, parent);
	}

	private boolean removeNode(BinaryTreeNode<T> n, BinaryTreeNode<T> parent) {
		// CASE 1: node n has at least one empty child node
		if (n.getLeft() == null || n.getRight() == null) {
			// CASE 1.1 current node is root
			if (parent == null) {
				root = n.getLeft() == null?n.getRight():n.getLeft();
				return true;
			} 
			
			// CASE 1.2 node n is not root
			BinaryTreeNode<T> sub = n.getLeft()==null?n.getRight():n.getLeft();
			if (n.getData().compareTo(parent.getData()) < 0) {
				parent.setLeft(sub);
			} else {
				parent.setRight(sub);
			}
			return true;
		}
		
		// CASE 2: node n has two children
		Object[] minNodesArray = findMinNode(n.getRight());
		@SuppressWarnings("unchecked")
		BinaryTreeNode<T> next = (BinaryTreeNode<T>)minNodesArray[0];
		@SuppressWarnings("unchecked")
		BinaryTreeNode<T> parentOfNext = (BinaryTreeNode<T>)minNodesArray[1];
		n.setData(next.getData());
		return removeNode(next, parentOfNext);
	}
	
}

