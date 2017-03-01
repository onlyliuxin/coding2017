package com.coding.basic;

import java.util.Comparator;

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
		BinaryTreeNode treeNode = new BinaryTreeNode();
		treeNode.data = o;
		int intO = Integer.parseInt(o.toString());
		int intData = Integer.parseInt(this.data.toString());
		if(intO > intData){
			if(this.right == null){
				this.right = treeNode;
			}
			else {
				this.right.insert(o);
			}
		}
		else {
			if(this.left == null) {
				this.left = treeNode;
			}
			else {
				this.left.insert(o);
			}
		}
		return treeNode;
	}
	
	private class MyComparator implements Comparator<BinaryTreeNode> {

		@Override
		public int compare(BinaryTreeNode arg0, BinaryTreeNode arg1) {
			// TODO Auto-generated method stub
			int int0 = Integer.parseInt(arg0.data.toString());
			int int1 = Integer.parseInt(arg1.data.toString());
			if(int0 > int1) {
				return 1;
			}
			else if(int0 < int1){
				return -1;
			}
		
			return 0;
			
		}
		
		
	}
	
}
