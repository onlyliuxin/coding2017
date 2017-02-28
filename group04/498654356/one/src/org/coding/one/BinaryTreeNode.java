package org.coding.one;

@SuppressWarnings( {"unchecked", "rawtypes"})
public class BinaryTreeNode {

	private Comparable data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	public BinaryTreeNode(Comparable data, BinaryTreeNode left, BinaryTreeNode right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}
	public Object getData() {
		return data;
	}
	public void setData(Comparable data) {
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
	
	public BinaryTreeNode insert(Comparable val){
		return doInsert(this, val);
	}
	
	private BinaryTreeNode doInsert(BinaryTreeNode node, Comparable val) {
		if(node == null) {
			return null;
		}
		if(val.compareTo(node.data) == 0) {
			return node;
		}
		if(val.compareTo(node.data) > 0) {
			if(node.right == null) {
				BinaryTreeNode rightNode = new BinaryTreeNode(val, null, null);
				node.right = rightNode;
				return rightNode;
			}
			return doInsert(node.right, val);
		} else {
			if(node.left == null) {
				BinaryTreeNode leftNode = new BinaryTreeNode(val, null, null);
				node.left = leftNode;
				return leftNode;
			}
			return doInsert(node.left, val);
		}
	}
	
}
