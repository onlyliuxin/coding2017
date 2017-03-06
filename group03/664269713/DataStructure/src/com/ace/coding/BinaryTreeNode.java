package com.ace.coding;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	private BinaryTreeNode rootNode;
	
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
		BinaryTreeNode newNode = new BinaryTreeNode();
		newNode.setData(o);
		
		if(rootNode == null){
			rootNode = newNode;
			rootNode.data = data;
			left = null;
			right = null;
		} else {
			BinaryTreeNode currentNode = rootNode;
			while(true){
				BinaryTreeNode pNode = currentNode;
				if((int)newNode.getData() > (int)currentNode.getData()){
					currentNode = currentNode.right;
					if(currentNode.right == null){
						pNode.right = newNode;
						return newNode;
					}
				} else {
					currentNode = currentNode.left;
					if(currentNode.left == null){
						pNode.left = newNode;
						return newNode;
					}
				}
			}
			
		}
		return newNode;
	}
	
}
