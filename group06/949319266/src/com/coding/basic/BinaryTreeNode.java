package com.coding.basic;

public class BinaryTreeNode {
	
	private int data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	private BinaryTreeNode root;
	
	public BinaryTreeNode getRoot() {
		return root;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(int data) {
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
	
	public void insert(int value){
		BinaryTreeNode newNode = new BinaryTreeNode();
		if(root == null) {
			root=newNode;
			root.left = null;
			root.right = null;
		} else {
			BinaryTreeNode currentNode = root;
			BinaryTreeNode parentNode;
			while(true) {
				parentNode = currentNode;
				if(newNode.data > currentNode.data) {
					currentNode = currentNode.getRight();
					if(currentNode == null) {
						parentNode.setRight(newNode);
						return;
					}
				} else {
					currentNode = currentNode.getLeft();
					if(currentNode == null) {
						parentNode.setLeft(newNode);
						return;
					}
				}
			}
			
		}
	}
	public boolean find(int key) {
		BinaryTreeNode cNode = root;
		if(cNode != null) {
			while(cNode.data != key) {
				if(cNode.data > key) {
					cNode = cNode.getLeft();
				} else {
					cNode = cNode.getRight();
				}				
				return true;
			}
			return true;
		}
		return false;		
	}
	//中序遍历
	public void inOrder(BinaryTreeNode treeNode) {
		if(treeNode != null) {
			inOrder(treeNode.getLeft());
			System.out.println(treeNode.data);
			inOrder(treeNode.getRight());
		}
	}
	//先序遍历
	public void leftOrder(BinaryTreeNode treeNode) {
		if(treeNode != null) {
			leftOrder(treeNode.getLeft());
			System.out.println(treeNode.data);
			leftOrder(treeNode.getRight());
		}
	}
	//后序遍历
	public void rightOrder(BinaryTreeNode treeNode) {
		if(treeNode != null) {
			rightOrder(treeNode.getLeft());
			System.out.println(treeNode.data);
			rightOrder(treeNode.getRight());
		}
	}
}
