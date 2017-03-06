package com.coding.basic;

public class BinaryTree {
	private BinaryTreeNode root; // 根节点
	public BinaryTree( ){
		BinaryTreeNode node = new BinaryTreeNode();
		this.root = node;
	}
	
	public boolean isEmpty(){
		return this.root == null;
	}
	
	public void insert(Object o){
		// 如果是第一次添加节点，就是root节点
		if(root.data == null){
			BinaryTreeNode bnode = new BinaryTreeNode(o, null, null);
			root = bnode;
		}else{
			insert(o, root);
		}
	}

	// 递归添加非root节点
	private BinaryTreeNode insert(Object o, BinaryTreeNode node) {
		if(node == null){
			BinaryTreeNode bnode = new BinaryTreeNode(o, null, null);
			return bnode;
		}
		if((int)o <= (int)node.data){
			node.left = insert(o, node.left);
		}else{
			node.right = insert(o, node.right);
		}
		
		return node;
	}
	
	// 中序遍历
	public void middlePrint(){
		middleOrder(this.root);
	}

	private void middleOrder(BinaryTreeNode node) {
		if(node != null){
			middleOrder(node.left);
			System.out.print(node.data + " ");
			middleOrder(node.right);
		}
	}
	
	// 前序遍历
	public void prePrint(){
		preOrder(this.root);
	}

	private void preOrder(BinaryTreeNode node) {
		if(node != null){
			System.out.print(node.data + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	// 后序遍历
	public void postPrint(){
		postOrder(this.root);
	}

	private void postOrder(BinaryTreeNode node) {
		if(node != null){
			postOrder(node.right);
			System.out.print(node.data + " ");
			postOrder(node.left);
		}
	}
	
}
