package com.github.chaoswang.learning.java.collection.myown;


/**
 * 二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，
 * 或者是具有下列性质的二叉树： 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 
 * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。
 */
public class MyBinarySearchTree {
	private TreeNode root;
	
	public MyBinarySearchTree(Comparable obj){
		this.root = new TreeNode(obj);
	}
	
	public boolean insert(Comparable obj){
		return insert(root, obj);
	}
	
	private boolean insert(TreeNode parent, Comparable obj){
		//如果已经存在该treenode，则返回false
		if((parent.getUserObject().equals(obj))){
			return false;
		}
		if(parent.getUserObject().compareTo(obj) > 0){
			//不存在该treenode，且插入成功，则返回true
			if(parent.getLeftChild() == null){
				TreeNode node = new TreeNode(obj);
				node.setParent(parent);
				parent.setLeftChild(node);
				return true;
			}
			return insert(parent.getLeftChild(), obj);
		}
		if(parent.getRightChild() == null){
			TreeNode node = new TreeNode(obj);
			node.setParent(parent);
			parent.setRightChild(node);
			return true;
		}
		return insert(parent.getRightChild(), obj);
	}
	
	@Override
	public String toString() {
		if(root == null){
			return "";
		}
		return root.toString();
	}
	
	private static class TreeNode {
		private TreeNode parent;
		private TreeNode leftChild;
		private TreeNode rightChild;
		private Comparable userObject;//节点都是用来存储数据的

		public TreeNode(Comparable userObject){
			this.userObject = userObject;
		}
		
		public Comparable getUserObject() {
			return userObject;
		}
		
		public TreeNode getParent() {
			return parent;
		}

		public void setParent(TreeNode parent) {
			this.parent = parent;
		}

		public TreeNode getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(TreeNode leftChild) {
			this.leftChild = leftChild;
		}

		public TreeNode getRightChild() {
			return rightChild;
		}

		public void setRightChild(TreeNode rightChild) {
			this.rightChild = rightChild;
		}

		@Override
		public String toString() {
			return "TreeNode [parent=" + (parent==null?null:parent.getUserObject()) + ", leftChild=" + (leftChild==null?null:leftChild)
					+ ", rightChild=" + (rightChild==null?null:rightChild) + ", userObject="
					+ userObject + "]";
		}

	}
}
