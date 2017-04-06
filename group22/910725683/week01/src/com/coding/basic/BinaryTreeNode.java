package com.coding.basic;

/**
 * 实际上是一个二叉搜索树BinarySearchTree
 */
public class BinaryTreeNode {
	
	//根节点this//
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public BinaryTreeNode () {}
	
	public BinaryTreeNode(Object o) {
		this.data=o;
		this.left=null;
		this.right=null;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public BinaryTreeNode getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	public BinaryTreeNode getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
	//前序遍历，根左右//
	public void preOrder(BinaryTreeNode node) {
		if (node.data!=null) {
			System.out.print(node.data + " ");
			if (node.left!=null) {
				preOrder(node.left);
			}
			if (node.right!=null) {
				preOrder(node.right);
			}
		}
	}
	
	//中序遍历，左根右//
	public void inOrder(BinaryTreeNode node) {
		if (node.data!=null) {
			if (node.left!=null) {
				inOrder(node.left);
			}
			System.out.print(node.data + " ");
			if (node.right!=null) {
				inOrder(node.right);
			}
		}
	}
	
	//后序遍历，左右根//
	public void postOrder(BinaryTreeNode node) {
		if (node.data!=null) {
			if (node.left!=null) {
				postOrder(node.left);
			}
			if (node.right!=null) {
				postOrder (node.right);
			}
			System.out.print(node.data + " ");
		}
	}
	
	public BinaryTreeNode insert(Object o){
		//先看看根节点是不是空的//
		if (this.data==null){
			this.data=o;
			return this;
		}else{
			//小的去左边，大的去右边//
			if ((int) o < (int) this.data){
				/*若左边是空的那就是你了，链上一个新的节点
				 *若左边不是空的则继续往下找空的
				 */
				if (this.left==null){
					this.left = new BinaryTreeNode(o);
					return this.left;
				}else{
					
					return this.left.insert(o);
				}
			}else{
				/*若右边是空的那就是你了，链上一个新的节点
				 *若右边不是空的则继续往下找空的
				 */
				if (this.right==null){
					this.right = new BinaryTreeNode(o);
					return this.right;
				}else{
					return this.right.insert(o);
				}				
			}
		}
	}
}