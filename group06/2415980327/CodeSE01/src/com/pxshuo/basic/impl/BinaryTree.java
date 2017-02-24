package com.pxshuo.basic.impl;

public class BinaryTree {
	BinaryTreeNode root = null;
	
	public void add(Comparable<Object> o){
		if (root == null) {
			root = new BinaryTreeNode();
			root.setData(o);
		}
		else {
			root.insert(o);
		}
	}
	
	public void display(){
		root.display(1);
	}
}
