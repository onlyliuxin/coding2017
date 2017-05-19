package com.coding.basic.tree;

import java.util.List;

public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	
	public T findMin(){
		BinaryTreeNode<T> min = findMin(root);
		return min==null?null:min.data;
	}
	
	public T findMax(){
		BinaryTreeNode<T> max = findMax(root);
		return max==null?null:max.data;
	}
	
	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node){  
		if(node==null){
			return null;  
		}else if(!node.hasLeft()){
			return node;  
		}  
		return findMin(node.left);//递归查找  
	} 
	
	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> node){  
		if(node==null){
			return null;  
		}else if(!node.hasRight()){
			return node;  
		}  
		return findMax(node.right);//递归查找  
	} 
	
	public int height() {
		if(root==null){
			return 0;
		}
		BinarySearchTree<T> leftTree = new BinarySearchTree<T>(root.left);
		BinarySearchTree<T> rightTree = new BinarySearchTree<T>(root.right);
		int lh = leftTree.height();
		int rh = rightTree.height();
		if(lh>rh){
			return 1+lh;
		}else{
			return 1+rh;
		}
	}
	
	public int size() {
		if(root==null){
			return 0;
		}
		BinarySearchTree<T> leftTree = new BinarySearchTree<T>(root.left);
		BinarySearchTree<T> rightTree = new BinarySearchTree<T>(root.right);
		return leftTree.size()+1+rightTree.size();
	}
	
	//中序遍历中的前一个节点
	public T getFirstNode(T e){
		if(e==null||root==null){
			return null;
		}
		List<T> list = BinaryTreeUtil.inOrderVisit(root);
		for (int i=0;i<list.size();i++) {
			if(e.equals(list.get(i))){
				return list.get(i-1);
			}
		}
		return null;
	}
	
	public void remove(T e){
		if(root==null||e==null){
			return ;
		}
		remove(e,root);
	}
	
	//删除在节点node分支上的某节点,返回删除后的该分支的根节点
	public BinaryTreeNode<T> remove(T e,BinaryTreeNode<T> node){
		if(node==null){
			return null;
		}
		int res = e.compareTo(node.data);
		if(res>0){
			node.right = remove(e, node.right);
		}else if(res<0){
			node.left = remove(e, node.left);
		}else{
			if(node.hasLeft()&&node.hasRight()){
				node.data = findMin(node.right).data;
				node.right = remove(node.data,node.right);
			}else{
				node = node.hasLeft()?node.left:node.right;
			}
		}
		return node;		
	}
	
}

