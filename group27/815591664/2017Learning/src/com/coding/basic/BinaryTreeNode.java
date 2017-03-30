package com.coding.basic;



/**
 * @author hugaoqing
 * created on 2017-3-11
 */
public class BinaryTreeNode {
	
	private Integer data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public BinaryTreeNode(Integer data) {
		super();
		this.data = data;
	}
	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
		this.data = data;
	}
	public BinaryTreeNode getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	public BinaryTreeNode getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
	/*public BinaryTreeNode insert(Object o){
		this.data = o;
		return this;
	}*/
	
}
