package com.coding.basic;

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
		if(this.data == null){//根节点为空
			this.data = o;
		}else{//根节点非空
			BinaryTreeNode pres = this;
			BinaryTreeNode insertNode = new BinaryTreeNode();
			insertNode.setData(o);
			while(pres != null){
				if((int)o < (int)pres.data){//插入值<当前值，pres向左移动，或者将插入节点挂在当前节点左边
					if(pres.left == null){
						pres.left = insertNode;
						break;
					}
					pres = pres.left;
				}else{//插入值>=当前值，pres向右移动，或者将插入节点挂在当前节点右边
					if(pres.right == null){
						pres.right = insertNode;
						break;
					}
					pres = pres.right;
				}
			}
		}
		return  null;
	}
	
	public void print(){
		if(this.data == null){
			return;
		}else{
			if(this.left !=null){
				this.left.print();
			}
			System.out.print(this.data);
			System.out.print(" ");
			if(this.right != null){
				this.right.print();
			}
		}
	}
}
