package com.coding.basic.impl;


/**
 * 二叉树简单实现(key为int类型)
 * @author 240094626
 *
 */
public class BinaryTree {
	/**根结点，初始化为空*/
	private Node rootNode = null;
	
	
	/**
	 * 根据key值插入数据data为空的新节点
	 * @param key
	 * @return
	 */
	public Node insert(int key){
		return insert(key,null);
	}
	
	/**
	 * 根据key值插入数据data为o的新节点
	 * @param key
	 * @param o
	 * @return
	 */
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
		size++;
		return newNode;
	}
	
	/**
	 * 根据key值查找结点
	 * @param key
	 * @return
	 */
	public Node getNode(int key){
		return get(rootNode, key);
	}
	
	/**
	 * 递归算法： 根据开始结点位置和key值查找节点
	 * @param n
	 * @param key
	 * @return
	 */
	private Node get(Node n,int key){
		if(n == null){
			return null;
		}
		if(key < n.key){
			return get(n.left, key);
		}else if(key > n.key){
			return get(n.left, key);
		}
		return n;
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
