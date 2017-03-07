package com.github.eloiseSJTU.coding2017.basic;

public class BinaryTreeNode {

	private Integer data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode() {}

	public BinaryTreeNode(Integer data, BinaryTreeNode left, BinaryTreeNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
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

	public BinaryTreeNode insert(Integer o) {
		if (data == null) {
			data = o;
		} else {
			if (o < data) {
				if (left == null) {
					left = new BinaryTreeNode(o, null, null);
				} else {
					left = left.insert(o);
				}
			} else {
				if (right == null) {
					right = new BinaryTreeNode(o, null, null);
				} else {
					right = right.insert(o);
				}
			}
		}
		return this;
	}
	
	public void print() {
		if (left != null) {
			left.print();
		}
		System.out.println(data);
		if (right != null) {
			right.print();
		}
	}

}
