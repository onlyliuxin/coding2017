package com.xusheng.tree;

/**
 * 实现二叉查找树
 * @author xusheng
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

	private BinaryNode<T> root;
	
	public BinarySearchTree() {
		this.root = null;
	}

	public boolean isEmpty(){
		return this.root == null;
	}
	
	public void insert(T element){
		root = insert(element,root);
	}
	
	private BinaryNode<T> insert(T element,BinaryNode<T> node){
		if(node == null){
			return new BinaryNode<T>(element,null,null); 
		}
		
		int intCompare = element.compareTo(node.data);
		
		if(intCompare < 0){
			node.leftNode = insert(element,node.leftNode);
		}else if(intCompare > 0){
			node.rightNode = insert(element,node.rightNode);
		}
		return node;
	}
	
	
	public boolean contains(T element){
		return contains(element,root);
	}
	
	private boolean contains(T element,BinaryNode<T> node){
		
		if(node == null){
			return false;
		}
		
		int intCompare = element.compareTo(node.data);
		
		if(intCompare < 0){
			return contains(element,node.leftNode);
		}else if(intCompare > 0){
			return contains(element,node.rightNode);
		}else{
			return true;
		}
	}
	
	public void remove(T element){
		remove(element,root);
	}
	
	private BinaryNode<T> remove(T element,BinaryNode<T> node){
		if(node == null){
			return node;
		}
		
		int intCompare = element.compareTo(node.data);
		
		if(intCompare < 0){
			node.leftNode = remove(element,node.leftNode);
		}else if(intCompare > 0){
			node.rightNode = remove(element,node.rightNode);
		}
		else if(node.leftNode != null && node.rightNode != null){
			node.data = findMin(node).data;
			node.rightNode = remove(node.data,node.rightNode);
		}else{
			node = (node.leftNode != null) ? node.leftNode : node.rightNode;
		}
		return node;
	}
	

	private BinaryNode<T> findMin(BinaryNode<T> node){
		if(node == null){
			return null;
		}else if(node.leftNode == null){
			return node;
		}else{
			return findMin(node.leftNode);
		}
	}
	
	private BinaryNode<T> findMax(BinaryNode<T> node){
		if(node == null){
			return null;
		}
		
		while(node.rightNode != null){
			node = node.rightNode;
		}
		
		return node;
	}

	private class BinaryNode<T>{
		
		private T data;
		private BinaryNode<T> leftNode;
		private BinaryNode<T> rightNode;
		
		public BinaryNode(T data, BinaryNode<T> leftNode,BinaryNode<T> rightNode) {
			this.data = data;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
		}
		
		public BinaryNode(T element) {
			this(element,null,null);
		}
		
		
	}
}
