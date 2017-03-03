package com.coding.basic;


public class BinaryTree {

	private BinaryTreeNode root;
	
	public void insert(Integer o){
		BinaryTreeNode node = new BinaryTreeNode(o);
		if(root == null){
			root = node;
		}else{
			BinaryTreeNode current = root;
			BinaryTreeNode parent;
			
			while(true){
				parent = current;
				if(o<parent.getData()){
					current = parent.getLeft();
					if(current == null){
						parent.setLeft(node);
						return;
					}
				}else{
					current = parent.getRight();
					if(current == null){
						parent.setRight(node);
						return;
					}
				}
			}
		}
	}
	
	public BinaryTreeNode find(Integer i){
		
		BinaryTreeNode node = root;
		
		while(node != null){
			if(i<node.getData()){
				node = node.getLeft();
			}else if(i>node.getData()){
				node = node.getRight();
			}else{
				return node;
			}
		}
		
		return null;
	}
	
	
}












