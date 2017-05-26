package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉查找树
 * @author 小摩托
 *
 * @param <T>
 */
public class BinarySearchTree <T extends Comparable> {


	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	
	public T findMin(){
		return findMin(root);
	}
	private T findMin(BinaryTreeNode<T> node){
		if(null==node.getLeft()){
			return node.getData();
		}
		return findMin(node.getLeft());
		
	}
	
	public T findMax(){
		return findMax(root);
	}
	private T findMax(BinaryTreeNode<T> node){
		if(null==node.getRight()){
			return node.getData();
		}
		return findMax(node.getRight());
		
	}
	/*
	 * 查找树的高度
	 */
	public int height() {
	    return height(root);
	}
	private int height(BinaryTreeNode<T>node){
		if (node == null) {
            return 0;
        }
		int right=height(node.right);
		int left=height(node.left);
		
		return (right>left?right+1:left+1);
		
	}
	/*
	 * 树的大小
	 */
	public int size() {
		return size(root);
	}
	private int size(BinaryTreeNode<T>node){
		if(node==null){
			return 0;
		}
		return size(node.left)+size(node.right)+1;
		
	}
	
	public void remove(T e){
		remove(e,root);
	}
	private BinaryTreeNode<T> remove(T e,BinaryTreeNode<T>node){
		if (node == null) {
            return node;
        }
        int compareResult = e.compareTo(node.data);
        if (compareResult < 0) {
            node.left = remove(e, node.left);
        } else if (compareResult > 0) {
            node.right = remove(e, node.right);
        } else {
            if (node.left != null && node.right != null) {
                node.data = findMin(node.right);
                node.right = remove(node.data, node.right);
            } else {
                node = (node.left != null) ? node.left : node.right;
            }
        }
        return node;
		
	}
	/*
	 * 逐层遍历
	 */
	public List<T> levelVisit(){
		
		Queue<BinaryTreeNode<T>>queue=new LinkedList<>();
		List<T>resultList=new ArrayList<T>();
		if(root==null){
			return null;
		}
		queue.add(root);
		while(!queue.isEmpty()){
			BinaryTreeNode<T> temp = queue.poll();
        	resultList.add(temp.data);
            if(temp.left != null){
            	queue.add(temp.left);
            }
            if(temp.right != null){
            	queue.add(temp.right);
            }
		}
		return resultList;
	}
	
	/*
	 * 判断是否属于二叉查找树
	 */
	public boolean isValid(){
		
		List<T>list=BinaryTreeUtil.inOrderVisit(root);
		for(int i=0;i<list.size()-1;i++){
			if(list.get(i).compareTo(list.get(i+1))>0){
				return false;
			}
		}
		return true;
	}
	
	/*
	 * 获取两个节点的最小公共祖先
	 */
	public T getLowestCommonAncestor(T d1, T d2){
		return getLowestCommonAncestor(root,d1,d2);
        
	}
	public  T getLowestCommonAncestor(BinaryTreeNode<T> node,T data1,T data2){  
        if(node == null || data1 == null || data2 == null){  
            return null;  
        }  
        if((data1.compareTo(node.data))*(data2.compareTo(node.data)) < 0){  
            return node.data;  
        }else if((data1.compareTo(node.data))*(data2.compareTo(node.data)) > 0){  
            BinaryTreeNode<T> newRoot = (data1.compareTo(node.data)<0) && (data2.compareTo(node.data)<0) ? node.left : node.right;  
            return getLowestCommonAncestor(newRoot, data1, data2);  
        }
        return null;
    }
	/*
	 * 给定两个值， 获得处于这两个值中间的节点
	 */
	public List<T> getNodesBetween(T n1, T n2){
		return null;
	}
}
