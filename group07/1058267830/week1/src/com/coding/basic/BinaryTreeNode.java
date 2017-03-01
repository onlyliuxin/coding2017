package com.coding.basic;

public class BinaryTreeNode {

	protected Object data;
	protected BinaryTreeNode left;
	protected BinaryTreeNode right;
	
	public BinaryTreeNode(){}
	public BinaryTreeNode(Object data, BinaryTreeNode left, BinaryTreeNode right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
}
