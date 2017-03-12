package com.coding.basic;

/**
 * 
 * @ClassName: BinaryTreeNode
 * @Description:TODO(BinaryTreeNode二叉树)
 * @author: QQ：878169060
 * @date: 2017年3月11日 上午12:33:19
 */
public class BinaryTreeNode implements Comparable<BinaryTreeNode> {

	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode() {
	}

	public BinaryTreeNode(Object o) {
		data = o;
		left = null;
		right = null;
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
		Integer to = (Integer) this.data;
		Integer co = (Integer) o.data;
		if (to < co)
			return -1;
		if (to > co)
			return 1;
		return 0;
	}
}
