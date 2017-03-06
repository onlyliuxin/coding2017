package com.coding.basic.binaryTree;

public class BinaryTreeNode implements Comparable<BinaryTreeNode> {

	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
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
	
	@Override
	public int compareTo(BinaryTreeNode o) {
		String str = String.valueOf(o.getData());
		int newData = Integer.parseInt(str);
		int data = Integer.parseInt(String.valueOf(getData()));
		return Integer.compare(data, newData);
	}

	@Override
	public String toString() {
		return "BinaryTreeNode [data=" + data + ", left=" + left + ", right=" + right + "]";
	}

}
