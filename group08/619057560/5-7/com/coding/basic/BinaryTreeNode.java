package com.coding.basic;

import java.util.ArrayList;

public class BinaryTreeNode<E extends Comparable<E>> {
	
	private E data;
	private BinaryTreeNode<E> left;
	private BinaryTreeNode<E> right;
	
	private BinaryTreeNode<E> parent = null;
	
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public BinaryTreeNode<E> getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode<E> left) {
		this.left = left;
	}
	public BinaryTreeNode<E> getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode<E> right) {
		this.right = right;
	}
	
	public BinaryTreeNode<E> insert(E e){
		if (data == null) {
			data = e;
			return this;
		} 
		
		if (e.compareTo(data) < 0) {
			if (left == null) {
				left = new BinaryTreeNode<E>();
				left.parent = this;
			}
			return left.insert(e);
		}
		
		if (right == null) {
			right = new BinaryTreeNode<E>();
			right.parent = this;
		}
		
		return right.insert(e);
	}
	
	public boolean remove(E e) {
		if (data == null || e == null) {
			return false;
		}
		
		if (e.compareTo(data) < 0) {
			return left.remove(e);
		} else if (e.compareTo(data) > 0) {
			return right.remove(e);
		}
		
		return removeNode();
	}
	
	public ArrayList<E> sortedList(ArrayList<E> list) {
		if (list == null) {
			list = new ArrayList<>();
		}
		if (left != null) {
			left.sortedList(list);
		}
		list.add(data);
		if (right != null) {
			right.sortedList(list);
		}
		return list;
	}
	
	public BinaryTreeNode<E> findMinNode() {
		BinaryTreeNode<E> node = this;
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	public BinaryTreeNode<E> findMaxNode() {
		BinaryTreeNode<E> node = this;
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}
	
	public boolean removeNode() {
		// CASE 1: current node is root node without children
		if (parent == null && left == null && right == null) {
			this.data = null;
			return true;
		}
		
		// CASE 2: current node is not root node, either left or right node is empty
		if (parent != null && (left == null || right == null)) {
			if (data.compareTo(parent.data) < 0) {
				parent.left = left==null?right:left;
			} else {
				parent.right = left==null?right:left;
			}
			return true;
		}
		
		// CASE 3: current node is root node, and it has at least one child node
		// CASE 4: current node is not root node, and it has two children
		BinaryTreeNode<E> sub = right==null?left.findMaxNode():right.findMinNode();
		this.data = sub.data;
		return sub.removeNode();
	}
	
}
