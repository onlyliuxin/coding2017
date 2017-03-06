package com.coding.basic;

public class BinaryTree {
	private BinaryTreeNode root;
	
	public BinaryTreeNode getRoot(){
		return root;
	}
	
	public BinaryTreeNode insert(Object o){
		BinaryTreeNode node = new BinaryTreeNode(o);
		if(root == null){
			root = node;
			root.setLeft(null);
			root.setRight(null);
			return root;
		}else{
			BinaryTreeNode currentNode = root;
			BinaryTreeNode parentNode;
			while(true){
				parentNode = currentNode;
				
				if(((Integer)node.getData()) > ((Integer)currentNode.getData())){
					currentNode = currentNode.getRight();
					if(currentNode == null){
						parentNode.setRight(node);
						return node;
					}
				}else{
					currentNode = currentNode.getLeft();
					if(currentNode == null){
						parentNode.setLeft(node);
						return node;
					}
				}
			}
		}
	}
	
}
