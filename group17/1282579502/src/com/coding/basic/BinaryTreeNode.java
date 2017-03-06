package com.coding.basic;

public class BinaryTreeNode {
	
	private Object data = null;
	private BinaryTreeNode left = null;
	private BinaryTreeNode right = null;
	
	public BinaryTreeNode(){};
	public BinaryTreeNode(Object data){
		this.data = data;
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
	/*
	 * this.data > o, return 1
	 * this.data < o, return -1
	 * this.data == o, return 0;
	 */
	public BinaryTreeNode insert(Object o){
		if(!( o instanceof Comparable)) throw new IllegalArgumentException(o + " is NOT comparable. ");
		if(data == null) {
			data = o;
			return this;
		}

		Comparable cdata = (Comparable) data;
		if(cdata.compareTo(o)>0){
			if(left == null) {
				left = new BinaryTreeNode(o);
				return left;
			}
			else{
				return left.insert(o);
				}
		}
		else if(cdata.compareTo(o)<0){
			if(right == null){
				right = new BinaryTreeNode(o);
				return right;
			}else{
				return right.insert(o);
			}
		}
		else{
			throw new IllegalArgumentException(o + " encountered a duplication.");
		}
	}
	
}
