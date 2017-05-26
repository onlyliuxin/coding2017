package com.coding.basic.tree;

import java.util.List;

public class BinarySearchTree<T extends Comparable<? super T>> {
	
	BinaryTreeNode<T> root;
	
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	
	public T findMin(){
		
		BinaryTreeNode<T> temp = root;
		if(temp == null){
			return null;
		}
		while(temp.left!=null){
			temp = temp.left;
		}
		return temp.data;
	}
	
	public T findMax(){
		
		BinaryTreeNode<T> temp = root;
		if(temp == null){
			return null;
		}
		while(temp.right!=null){
			temp = temp.right;
		}
		return temp.data;
	}
	
	public int height() {
		
		return maxDepth(this.root);
	}
	
	private int maxDepth(BinaryTreeNode<T> root) {
        
        if(root == null)
            return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return leftDepth > rightDepth ? (leftDepth + 1) : (rightDepth + 1);
    }
	
	public int size() {
		
		List<T> dataList = BinaryTreeUtil.preOrderVisit(root);
		
		return dataList.size();
	}
	
	public void remove(T e){
		
		//获取将要删除的节点
		BinaryTreeNode<T> node = BinaryTreeUtil.findNode(root, e);
		if(node == null){
			return;
		}
				
		//当前节点为叶子节点直接删除
		if(node.left==null&&node.right==null){
			//获取该节点的父节点
			
		}
	}
	
}
