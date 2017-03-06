package com.github.congcongcong250.coding2017.basic;

public class BinaryTreeNode <Object extends Comparable<Object>>{
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public BinaryTreeNode(){
		data = null;
		left = null;
		right = null;
	}
	
	public BinaryTreeNode(Object obj){
		data = obj;
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
	
	public void destroy(){
		this.data = null;
		this.left = null;
		this.right = null;
	}
	
	public BinaryTreeNode insert(Object o){
		//If is empty root
		if(data == null){
			data = o;
			return this;
		}

		//If it is a normal root
		BinaryTreeNode in;
		
		if(o.compareTo(data) <= 0){
			if(left == null){
				in = new BinaryTreeNode(o);
				left = in;
			}else{
				in = left.insert(o);
			}
		}else{
			if(right == null){
				in = new BinaryTreeNode(o);
				right = in;
			}else{
				in = right.insert(o);
			}
		}
		
		assert (in == null):"Insert error";
		return in;
	}
	
	
	
}
