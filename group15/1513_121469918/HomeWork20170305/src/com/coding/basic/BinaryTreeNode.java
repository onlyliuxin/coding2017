package com.coding.basic;

public class BinaryTreeNode {
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

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

	public BinaryTreeNode insert(Object o) {
		// 判断当前节点有无元素
		if (data == null) {
			setData(o);
		} else {
			Integer i = (Integer) o;
			// 当前节点有数据则判断左右节点
			if (i.compareTo((Integer) data) == -1) {
				if(right == null)
					right = new BinaryTreeNode();
					return right.insert(i);
			} else if (i.compareTo((Integer) data) == 1) {
				if(left == null)
					left = new BinaryTreeNode();
					return left.insert(i);
			}
			return null;
		}
		return null;
	}

}
