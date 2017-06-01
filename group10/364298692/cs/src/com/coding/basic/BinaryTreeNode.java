package com.coding.basic;

public class BinaryTreeNode implements Comparable{
	
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
		if(data==null){
			data = o;
			return this;
		}
		int compareResult = compareTo(o);
		BinaryTreeNode node = new BinaryTreeNode();
		node.data = o;
		if(compareResult>0){
			this.right = node;
		}else{
			this.left = node;
		}
		return  this;
	}
	@Override
	public int compareTo(Object o) {
		//两个object比较的规则
		if(data.hashCode() > o.hashCode()){
			return 1;
		}else if(data.hashCode() > o.hashCode()){
			return -1;
		}else{
			return 0;
		}		
	}
	
	
	
}
