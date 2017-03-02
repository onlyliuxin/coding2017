package com.github.chaoswang.learning.java.collection.myown;


/**
 * �����������Binary Search Tree�������֣���������������������������������һ�ÿ�����
 * �����Ǿ����������ʵĶ������� ���������������գ��������������н���ֵ��С�����ĸ�����ֵ�� 
 * ���������������գ��������������н���ֵ���������ĸ�����ֵ�� ������������Ҳ�ֱ�Ϊ������������
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
		//����Ѿ����ڸ�treenode���򷵻�false
		if((parent.getUserObject().equals(obj))){
			return false;
		}
		if(parent.getUserObject().compareTo(obj) > 0){
			//�����ڸ�treenode���Ҳ���ɹ����򷵻�true
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
		private Comparable userObject;//�ڵ㶼�������洢���ݵ�

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
