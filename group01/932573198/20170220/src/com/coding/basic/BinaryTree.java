package com.coding.basic;

public class BinaryTree {

	private BinaryTreeNode tNode;

	@Override
	public String toString() {
		return tNode + "";
	}

	public void insert(Object o) {
		tNode = insert(o, tNode);
	}

	public BinaryTreeNode insert(Object o, BinaryTreeNode node) {
		if (node == null) {
			node = new BinaryTreeNode(o);
		} else {
			int result = o.toString().compareTo(node.getData().toString());
			if (result < 0)
				node.setLeft(insert(o, node.getLeft()));
			if (result > 0)
				node.setRight(insert(o, node.getRight()));
		}
		return node;
	}

	private static class BinaryTreeNode {

		private BinaryTreeNode left;

		private Object data;

		private BinaryTreeNode right;

		public BinaryTreeNode() {
		}

		public BinaryTreeNode(Object data) {
			this.left = null;
			this.data = data;
			this.right = null;
		}

		public BinaryTreeNode getLeft() {
			return left;
		}

		public void setLeft(BinaryTreeNode left) {
			this.left = left;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public BinaryTreeNode getRight() {
			return right;
		}

		public void setRight(BinaryTreeNode right) {
			this.right = right;
		}

		@Override
		public String toString() {
			return "[" + left + ", " + data + ", " + right + "]";
		}

	}

}
