package com.maple.basic;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

public class BinaryTree<T extends Comparable<T>> {
	private BinaryTreeNode<T> root;
	
	public void traversal(BinaryTreeNode<T> node){
		if(node.getLeft()!=null){
			traversal(node.getLeft());
		}
		System.out.println("--"+node.getData()+"--");
		if(node.getRight()!=null){
			traversal(node.getRight());
		}
	}
	/**
	 * 如果根节点为null，则作为根节点，否则遍历下去插值
	 * @param o
	 * @return 
	 * 2017年2月23日 下午4:21:51
	 * @Author Joy
	 */
	public BinaryTreeNode insert(T o){
		if(root==null){
			BinaryTreeNode<T> newB=new BinaryTreeNode<T>();
			newB.setData(o);
			newB.setLeft(null);
			newB.setRight(null);
			root=newB;
			return root;
		}
		
		return  root.insert(o);
	}
	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}
	
	
}
