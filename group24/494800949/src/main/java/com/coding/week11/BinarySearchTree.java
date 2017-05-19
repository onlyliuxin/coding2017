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
		return null;
	}
	public T findMax(){
		return null;
	}
	public int height() {
	    return -1;
	}
	public int size() {
		return -1;
	}
	public void remove(T e){
		
	}
	
}

