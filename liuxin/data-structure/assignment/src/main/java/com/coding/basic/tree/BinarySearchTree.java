package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;

import com.coding.basic.queue.Queue;

public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	public T findMin(){
		return null;
	}
	public T findMax(){
		return null;
	}
	public int height() {
	    return -1;
	}
	public int size() {
		return -1;
	}
	public void remove(T e){
		
	}
	public List<T> levelVisit(){
		
		return null;
	}
	public boolean isValid(){
		return false;
	}
	public T getLowestCommonAncestor(T n1, T n2){
		return null;
        
	}
	/**
	 * 返回所有满足下列条件的节点的值：  n1 <= n <= n2 , n 为
	 * 该二叉查找树中的某一节点
	 * @param n1
	 * @param n2
	 * @return
	 */
	public List<T> getNodesBetween(T n1, T n2){
		return null;
	}
	
}

