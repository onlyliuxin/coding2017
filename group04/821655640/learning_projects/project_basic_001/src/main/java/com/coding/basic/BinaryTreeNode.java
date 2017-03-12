package com.coding.basic;


public class BinaryTreeNode <T extends Comparable<T>> {
	
	private T data;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode <T> right;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryTreeNode<T> getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}
	public BinaryTreeNode<T> getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
	
	public    BinaryTreeNode<T> insert(T o){
		return  insert(this,o);
	}
	
	
	public    BinaryTreeNode<T> insert(BinaryTreeNode<T> bt, T o) {
		BinaryTreeNode<T> insertedNode = null;
		if(null == this.data) {
			this.data = o;
			return this;
		}
		
		if(-1 == o.compareTo(bt.data)) {
			if(null == bt.left) {
				insertedNode = new BinaryTreeNode<T>();
				insertedNode.data = o;
				bt.left = insertedNode;
				return insertedNode;
			}
			return insert(bt.left,o);
		}else {
			if(null == bt.right) {
				insertedNode = new BinaryTreeNode<T>();
				insertedNode.data = o;
				bt.right = insertedNode;
				return insertedNode;
			}
			
			return insert(bt.right,o);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder space = new StringBuilder("");
		return getString(this,space,0).toString();
	}
	
	private StringBuilder getString(BinaryTreeNode<T> head,StringBuilder space,int deepth) {
		StringBuilder spaceTemp = new StringBuilder(space.toString());
		if (null == head) {
			return new StringBuilder("   null  ,  "); 
		} else {
			StringBuilder tempStr = new StringBuilder(" {").append(spaceTemp.append(head.data.toString())
					.append(new StringBuilder(space)).append("} "));
			deepth++;
			return getString(head.left, space, deepth).append(deepth)
					.append(tempStr).append(deepth).append("\n")
					.append(getString(head.right, space, deepth));
		}
		
	}
	
	
	
}
