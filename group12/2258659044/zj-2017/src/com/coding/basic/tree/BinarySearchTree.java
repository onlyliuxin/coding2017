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
		//删除本节点		
		if(node.equals(node.parent.left)){
			node.parent.left = null;
		}else{
			node.parent.right = null;
		}
		//存储所删除节点下所有子节点的值(该集合包含节点本身数据)
		List<T> dataList = BinaryTreeUtil.preOrderVisit(node);
		//重构二叉树
		for (T t : dataList) {
			if(t != node.data){
				add(t);
			}			
		}
	}
	
	private void add(T o) {

		BinaryTreeNode<T> parent;
		BinaryTreeNode<T> currentNode = root;
		BinaryTreeNode<T> treeNode = new BinaryTreeNode<T>(o);

		while (true) {
			parent = currentNode;	
						
			if (currentNode.data.compareTo(o)>0) {// 向左放
				currentNode = currentNode.getLeft();
				if (currentNode == null) {
					parent.setLeft(treeNode);
					treeNode.setParent(parent);
					break;
				}
			} else if (currentNode.data.compareTo(o)<0) {// 向右放
				currentNode = currentNode.getRight();
				if (currentNode == null) {
					parent.setRight(treeNode);
					treeNode.setParent(parent);
					break;
				}
			} else {
				break;
			}
		}
	}
}
