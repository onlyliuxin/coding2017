package com.coding.basic;

/**
 * ʵ������һ������������BinarySearchTree
 */
public class BinaryTreeNode {
	
	//���ڵ�this//
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
	
	//ǰ�������������//
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
	
	//��������������//
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
	
	//������������Ҹ�//
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
		//�ȿ������ڵ��ǲ��ǿյ�//
		if (this.data==null){
			this.data=o;
			return this;
		}else{
			//С��ȥ��ߣ����ȥ�ұ�//
			if ((int) o < (int) this.data){
				/*������ǿյ��Ǿ������ˣ�����һ���µĽڵ�
				 *����߲��ǿյ�����������ҿյ�
				 */
				if (this.left==null){
					this.left = new BinaryTreeNode(o);
					return this.left;
				}else{
					
					return this.left.insert(o);
				}
			}else{
				/*���ұ��ǿյ��Ǿ������ˣ�����һ���µĽڵ�
				 *���ұ߲��ǿյ�����������ҿյ�
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