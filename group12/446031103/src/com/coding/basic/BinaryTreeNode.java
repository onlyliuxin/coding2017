package com.coding.basic;


public class BinaryTreeNode {
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	private BinaryTreeNode( Object data,BinaryTreeNode left,BinaryTreeNode right){
		this.data = data;
		this.left = left;
		this.right = right;
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
	
	public BinaryTreeNode insert(BinaryTreeNode tree,Object o){
		if(null== tree){
			tree = new BinaryTreeNode(o, null, null);
		}
		if(Integer.valueOf(o.toString())>Integer.valueOf(tree.data.toString())){
			tree.right =insert(tree.right,o);
		}else{
			tree.left = insert(tree.left,o);
		}
		return  tree;
	}
	
}
