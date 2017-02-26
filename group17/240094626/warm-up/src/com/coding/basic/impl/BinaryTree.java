package com.coding.basic.impl;


/**
 * 二叉树简单实现(key为int类型)
 * @author 240094626
 *
 */
public class BinaryTree {
	private Node rootNode = null;
	
	public Node insert(int key){
		return insert(key,null);
	}
	
	public Node insert(int key ,Object o){
		Node newNode = new Node(key, o);
		if(rootNode == null){
			rootNode = newNode;
			return rootNode;
		}
		Node fatherNode = rootNode;
		Node currentNode = rootNode;
		while(currentNode != null){
			fatherNode = currentNode;
			if(key < currentNode.key){
				currentNode = currentNode.left;
			}else{
				currentNode = currentNode.right;
			}
		}
		if(key < fatherNode.key){
			fatherNode.left = newNode;
		}else{
			fatherNode.right = newNode;
		}
		return newNode;
	}
	
	public Node getNode(int key){
		return get(rootNode, key);
	}
	
	private Node get(Node n,int key){
		if(n == null){
			return null;
		}
		if(key < n.key){
			return get(n.left, key);
		}else if(key > n.key){
			return get(n.left, key);
		}
		return null;
	}
	
	
	

	private static class Node{
		
		int key;
		Object data;
		Node left;
		Node right;
		
		public Node(int key, Object data) {
			this.key = key;
			this.data = data;
			this.left = null;
			this.right = null;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", data=" + data + "]";
		}
		
		
	}

	
}
