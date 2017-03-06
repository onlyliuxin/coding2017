package org.xukai.common;

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
	
	public BinaryTreeNode insert(Comparable o){
		if (data == null) {
			data = o;
			return this;
		} else {
			BinaryTreeNode node = new BinaryTreeNode();
			node.data = o;
			if (o.compareTo(data) < 0) {
				if (left == null) {
					left = node;
					return node;
				} else {
					return left.insert(o);
				}
			} else {
				if (right == null) {
					right = node;
					return node;
				} else {
					return right.insert(o);
				}
			}
		}
	}
	
}
