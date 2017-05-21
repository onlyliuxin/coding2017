package com.bjsxd.test;

public class BinaryTreeNode {
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	private BinaryTreeNode root;
	
	public BinaryTreeNode(BinaryTreeNode root){
		this.root = root;
		
	}
	
	public BinaryTreeNode(BinaryTreeNode left,BinaryTreeNode right,Object data){
		this.left = left;
		this.right = right;
		this.data = data;
	}
	
	public void buildTree(){
		
	}
	
	public BinaryTreeNode(Object data){
		this(null,null,data);
	}
	 public Object getData(){
		 return data;
	 }
	 public void setData(Object data){
		 this.data = data;
	 }
	 public BinaryTreeNode getLeft(){
		 return left;
	 }
	 public void setLeft(BinaryTreeNode left){
		 this.left = left;
		
	 }
	 public BinaryTreeNode getRight(){
		 return right;
	 }
	 public void setRight(BinaryTreeNode right){
		 this.right = right;
	 }
	 public BinaryTreeNode insert(Object o){
		 return null;
	 }

}
