package com.github.xiaozi123.coding2017.basic;

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
		if (data==null) {
			this.setData(0);
		}
		if ((Integer)o<=(Integer)data) {
			if (left==null) {
				left=new BinaryTreeNode();
				left.setData(o);
				return left;
			}
			return left.insert(o);
		}else{
			if (right==null) {
				right=new BinaryTreeNode();
				right.setData(o);
				return right;
			}
			return right.insert(o);
			
		}		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return data+" "+left+" "+right;
	}
	
	public static void main(String[] args) {
		BinaryTreeNode binaryTreeNode=new BinaryTreeNode();
		for (int i = 0; i < 5; i++) {
			binaryTreeNode.insert(i);
		}
		System.out.println(binaryTreeNode);
	}
	
//			 0
//		0		 1
//	null null null	2	
//				null   3
//				  null 	 4
//					  null	null
	
	
	
}
