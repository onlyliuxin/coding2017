package com.bruce.homework0226;

public class BinaryTreeNode<T extends Comparable> {
	
	private T data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode(){}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryTreeNode<T> getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	public BinaryTreeNode<T> getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}

	@SuppressWarnings("unchecked")
	public BinaryTreeNode<T> insert(T data){
		if(this.data == null){
			this.data = data;
			return this;
		}
		if(this.data.compareTo(data) > 0) {
			if(this.left == null) {
				this.left = new BinaryTreeNode();
				this.left.data = data;
				return this.left;
			} else {
				return this.left.insert(data);
			}
		} else if(this.data.compareTo(data) < 0) {
			if(this.right == null) {
				this.right = new BinaryTreeNode();
				this.right.data = data;
				return this.right;
			} else {
				return this.right.insert(data);
			}
		} else {
			return this;
		}
	}

	@SuppressWarnings("unchecked")
	public BinaryTreeNode search(T data){
		if(data == null || this.data == null) {
			return null;
		}
		if(this.data.compareTo(data) > 0) {
			if(this.left == null) {
				return null;
			} else {
				return this.left.search(data);
			}
		} else if(this.data.compareTo(data) < 0) {
			if(this.right == null) {
				return null;
			} else {
				return this.right.search(data);
			}
		} else {
			return this;
		}
	}

	//TODO 未确定
	@SuppressWarnings("unchecked")
	public BinaryTreeNode delete(T data){
        BinaryTreeNode treeNode = search(data);
        if(treeNode == null) {
			return null;
		}
		if(this.data.compareTo(data) > 0) {
			return this.left.delete(data);
		} else if(this.data.compareTo(data) < 0) {
			return this.right.delete(data);
		} else {
		    if(this.left == null) {
		        if(this.right == null) {
		            this.data = null;
                } else {
                    this.right = this;
                }
            } else {
                if(this.right == null) {
                    this.left = this;
                } else {
                    this.left = this;
                    this.left.right = this.right;
                }
            }
		}
		return this;
	}
}
