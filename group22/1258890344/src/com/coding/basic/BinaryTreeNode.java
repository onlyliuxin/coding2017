package com.coding.basic;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	public BinaryTreeNode(){
		
	}
	public BinaryTreeNode(Object o){
		this.data=o;
		this.left=null;
		this.right=null;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
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
	
	public BinaryTreeNode insert(Object o){
		BinaryTreeNode treeNode=new BinaryTreeNode(o);
		
		return  null;
	}
	
}
