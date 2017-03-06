package com.coding.basic;

public class BinaryTreeNode {
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
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
		Comparable co = (Comparable)o;
		Comparable coData = (Comparable)data;
		BinaryTreeNode result = null;
		if(co.compareTo(data) > 0){
			if(null == right){
				right = new BinaryTreeNode();
				right.data = o;
				result = right;
				return right;
			}else{
				right.insert(o);
			}
		}else{
			if(null == left){
				left = new BinaryTreeNode();
				left.data = o;
				result = left;
				return left;
			}else{
				left.insert(o);
			}
		}
		return  result;
	}
}
