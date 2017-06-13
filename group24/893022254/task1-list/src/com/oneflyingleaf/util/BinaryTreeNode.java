package com.oneflyingleaf.util;

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
		if(! (o instanceof Comparable)){
			throw new RuntimeException("未实现Comparable接口");
		}
		Comparable<Object> temp = (Comparable<Object>)o;
		
		if(temp.compareTo(data) > 0){
			if(right == null){
				right= new BinaryTreeNode();
				right.data = o;
			}else{
				right.insert(o);
			}
		}
		
		if(temp.compareTo(data) <= 0){
			if(left == null){
				
				left = new BinaryTreeNode();
				left.data = o;
			}else{
				left.insert(o);
			}
		}
		
		
		return this;
	}
	
}
