package com.coding.basic;

public class BinaryTreeNode implements Comparable {

	private Object data;
	private BinaryTreeNode leftChild;
	private BinaryTreeNode rightChild;
	
	public BinaryTreeNode() {
		super();
		this.data = null;
		this.leftChild = null;
		this.rightChild = null;
	}

	public BinaryTreeNode(Object data) {
		super();
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public BinaryTreeNode getLeftChild() {
		return leftChild;
	}
 
	public void setLeftChild(BinaryTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public BinaryTreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryTreeNode rightChild) {
		this.rightChild = rightChild;
	}
	
	/**
	 * 向树中插入节点
	 * 
	 * @param i
	 */
	public void insert(int i) {
		
	}

	// ...
	@Override
	public int compareTo(Object obj) {	
		
		return 0;
	}
	

}















