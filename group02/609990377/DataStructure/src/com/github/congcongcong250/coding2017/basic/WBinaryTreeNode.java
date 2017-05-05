package com.github.congcongcong250.coding2017.basic;

public class WBinaryTreeNode <Object extends Comparable<Object>>{
	
	private Object data;
	private WBinaryTreeNode left;
	private WBinaryTreeNode right;
	
	public WBinaryTreeNode(){
		data = null;
		left = null;
		right = null;
	}
	
	public WBinaryTreeNode(Object obj){
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
	public WBinaryTreeNode getLeft() {
		return left;
	}
	public void setLeft(WBinaryTreeNode left) {
		this.left = left;
	}
	public WBinaryTreeNode getRight() {
		return right;
	}
	public void setRight(WBinaryTreeNode right) {
		this.right = right;
	}
	
	public void destroy(){
		this.data = null;
		this.left = null;
		this.right = null;
	}
	
	public WBinaryTreeNode insert(Object o){
		//If is empty root
		if(data == null){
			data = o;
			return this;
		}

		//If it is a normal root
		WBinaryTreeNode in;
		
		if(o.compareTo(data) <= 0){
			if(left == null){
				in = new WBinaryTreeNode(o);
				left = in;
			}else{
				in = left.insert(o);
			}
		}else{
			if(right == null){
				in = new WBinaryTreeNode(o);
				right = in;
			}else{
				in = right.insert(o);
			}
		}
		
		assert (in == null):"Insert error";
		return in;
	}
	
	
	
}
