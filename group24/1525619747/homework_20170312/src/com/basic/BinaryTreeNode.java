package com.basic;

public class BinaryTreeNode
{

	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode (Object data) {
		this.data = data;
		left = null;
		right = null;
	}

	public BinaryTreeNode (Object data, BinaryTreeNode left,
			BinaryTreeNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
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

	/*
	 * 排序二叉树的插入
	 */
	public BinaryTreeNode insert(Object o) {
		if (((Integer) data) > ((Integer) o)) {
			if (left == null) {
				setLeft(new BinaryTreeNode(o));
			} else {
				left.insert(o);
			}
		} else {
			if (right == null) {
				setRight(new BinaryTreeNode(o));
			} else {
				right.insert(o);
			}
		}
		return this;
	}

	/*
	 * 前序遍历
	 */
	public void preOrderInterator() {
		if (left != null) {
			left.preOrderInterator();
		}

		System.out.print(data.toString() + " ");

		if (right != null) {
			right.preOrderInterator();
		}
	}

}
