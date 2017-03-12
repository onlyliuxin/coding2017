package cn.cs.week1.basic;

public class BinaryTreeNode {
	
	private Integer data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode(){

	}

	public BinaryTreeNode(Integer data,BinaryTreeNode left, BinaryTreeNode right){
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public Object getData() {
		return data;
	}

	public void setData(Integer data) {
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
	
	public BinaryTreeNode insert(Integer o){
		if(this.data == null){
			this.data = o;
			return this;
		}
		if(this.data > o){
			if(this.left == null){
				this.left = new BinaryTreeNode(o,null,null);
				return this.left;
			}
			return this.left.insert(o);
		}else if(this.data < o){
			if(this.right == null){
				this.right = new BinaryTreeNode(o,null,null);
				return this.right;
			}
			return this.right.insert(o);
		}
		return this;
	}
	
}
