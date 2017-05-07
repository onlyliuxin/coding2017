package com.coding.basic;

public class BinaryTree {
	
	private BinaryTreeNode root;
	
	public BinaryTreeNode insert(Comparable data){
		BinaryTreeNode node = new BinaryTreeNode(data);
		
		
		if(this.root==null){
			root = node;
			root.setLeft(null);
			root.setRight(null);
		}else{
			BinaryTreeNode curNode = this.root;
			
			if(data.compareTo(root.getData())>0){
				while(curNode.getRight()!=null){
					curNode = curNode.getRight();
				}
				curNode = node;
				
			}else{
				while(curNode.getLeft()!=null){
					curNode = curNode.getLeft();
				}
				curNode = node;
			}
			
		} 
		return null;
	}

}
