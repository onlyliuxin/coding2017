package com.dataStructure;

public class BinaryTreeNode {

	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	private BinaryTreeNode root;

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
		BinaryTreeNode newNode = null; // 新結點
		if (o == null)
			throw new NullPointerException("element is not null");

		if (root == null) {
			root = new BinaryTreeNode();
			root.setData(o);
		} else {
			newNode = new BinaryTreeNode(); 
			BinaryTreeNode nowNode = root;  //當前結點
			int val = (int) root.getData();
			nowNode.setData(o);
			while (true) {

				if ((int) newNode.getData() < val) { // 新結點的值 ＜ 當前結點
					if (nowNode.left == null) {
						nowNode.setLeft(newNode);
						break;
					} else {
						nowNode = nowNode.left;
					}
				} else if ((int) newNode.getData() > val) {
					if (nowNode.right == null) {
						nowNode.setRight(newNode);
						break;
					} else {
						nowNode = newNode.right;

					}
				} else {

					throw new IllegalArgumentException("element  exist");
				}
			}
		}
		return newNode;
	}


}
