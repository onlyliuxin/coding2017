package com.pxshuo.basic.impl;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	private int index = 0;
	
	public BinaryTreeNode() {
		data = null;
		left = null;
		right = null;
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
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public BinaryTreeNode insert(Comparable<Object> o){
		if(data == null){
			data = o;
			return this;
		}
		//本节点已经存过数据
		BinaryTreeNode child = new BinaryTreeNode();
		child.setData(o);
		if (o.compareTo(data) > 0) {
			if (right == null) {
				right = child;
			}
			else {
				right.insert(o);
			}
		}
		else {//小于等于的数据放在左子树中
			if (left == null) {
				left = child;
			}
			else {
				left.insert(o);
			}
		}
		return child;
	}
	
	public void display(int myIndex){

		System.out.println(myIndex + ":" + data.toString());
		if (left != null) {
			left.display(2 * myIndex);
		}
		if (right != null) {
			right.display(2 * myIndex + 1);
		}
	}
	
}
