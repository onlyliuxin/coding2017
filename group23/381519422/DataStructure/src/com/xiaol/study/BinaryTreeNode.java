package com.xiaol.study;

/**
 * @Description TODO
 * @date 创建时间：2017年3月5日 上午12:57:18
 */
public class BinaryTreeNode {
	// 要求，左节点比父节点小，右节点比父节点大
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode insert(Object o) {
		// 当前没有数据就放入当前节点
		if (data == null) {
			this.data = o;
		} else if ((int) data >= (int) o) {// 放入左节点
			if (left == null) {
				left = new BinaryTreeNode();
				left.setData(o);
			} else {
				left.insert(o);
			}
		} else if ((int) data < (int) o) {// 放入右节点
			if (right == null) {
				right = new BinaryTreeNode();
				right.setData(o);
			} else {
				right.insert(o);
			}
		}
		return this;
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

}
