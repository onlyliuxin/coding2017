package com.coding.basic;

public class BinaryTree<T extends Comparable> {
	private BinaryTreeNode<T> root;
	private BinaryTreeNode<T> compareNode=root;

	public BinaryTree() {
		root = null;
		System.out.println("success");
	}

	public void Insert(BinaryTreeNode<T> newNode) {
		if (compareNode== null) {
			compareNode= newNode;
			root=newNode;
			System.out.println("根节点插入"+newNode.data);
		} else if (compareNode.data.compareTo(newNode.data) > 0) {
			if (compareNode.left == null) {
				compareNode.left = newNode;
				System.out.println(compareNode.data+"左子节点插入"+newNode.data);
				compareNode=root;
			} else{
				compareNode=compareNode.left;
				Insert(newNode);
			}
		} else {
			if (compareNode.right == null) {
				compareNode.right = newNode;
				System.out.println(compareNode.data+"右子节点插入"+newNode.data);
				compareNode=root;
			} else{
				compareNode=compareNode.right;
				Insert(compareNode.left);
			}
			}
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}
	public void preOrder(BinaryTreeNode<T> treeNode) {//前序遍历
		if(treeNode!=null)
			{
			System.out.print(treeNode.data+"-");
			preOrder(treeNode.left);
			preOrder(treeNode.right);
			}
		
	}
	public void inOrder(BinaryTreeNode<T> treeNode) {
		if(treeNode!=null){
			inOrder(treeNode.left);
			System.out.print(treeNode.data+"-");
			inOrder(treeNode.right);
		}
	}
	public void postOrder(BinaryTreeNode<T> treeNode){
		if(treeNode!=null){
			postOrder(treeNode.left);
			postOrder(treeNode.right);
			System.out.print(treeNode.data+"-");
		}
	}

	public static class BinaryTreeNode<T extends Comparable> {
		private T data;
		private BinaryTreeNode<T> left = null;
		private BinaryTreeNode<T> right = null;

		public BinaryTreeNode() {
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public BinaryTreeNode<T> getLeft() {
			return left;
		}

		public void setLeft(BinaryTreeNode<T> left) {
			this.left = left;
		}

		public BinaryTreeNode<T> getRight() {
			return right;
		}

		public void setRight(BinaryTreeNode<T> right) {
			this.right = right;
		}

		public BinaryTreeNode(T data) {
			this.data = data;
		}

		public int compareTo(BinaryTreeNode<T> compareNode) {
			return this.data.compareTo(compareNode.data);
		}

	}
}
