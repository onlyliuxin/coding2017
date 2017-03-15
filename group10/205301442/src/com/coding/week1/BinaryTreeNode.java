package com.coding.week1;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
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
	
	public BinaryTreeNode insert(Object o){
		BinaryTreeNode newBTN = new BinaryTreeNode(o);
		Integer insert = (Integer)o;
		
		BinaryTreeNode cursor = this;
		while(true){
			if(insert.compareTo((Integer)cursor.data)==-1){
				if(cursor.left==null){
					cursor.left = newBTN;
					break;
				}
				cursor = cursor.left;
			}else if(insert.compareTo((Integer)cursor.data)==1){
				if(cursor.right==null){
					cursor.right = newBTN;
					break;
				}
				cursor = cursor.right;
			}
		}
		return  newBTN;
	}
	
}
