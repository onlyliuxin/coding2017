package com.coding.basic.binaryTree;

public class BinaryTreeOperationImpl implements BinaryTreeOperation<BinaryTreeNode> {

	private BinaryTreeNode root;

	private BinaryTreeNode insertNextNode(BinaryTreeNode newNode, BinaryTreeNode compareNode) {
		int result = compareNode.compareTo(newNode);
		if (result == 0) return compareNode;
		BinaryTreeNode nextNode = null;
		if (result > 0) { 
			if (null == compareNode.getLeft()) {
				compareNode.setLeft(newNode);
			} else {
				nextNode = compareNode.getLeft();
			}
		} else { 
			if (null == compareNode.getRight()) {
				compareNode.setRight(newNode);
			} else {
				nextNode = compareNode.getRight();
			}
		}
		if (null == nextNode) {
			return compareNode;
		}
		return insertNextNode(nextNode, compareNode);
	}

	@Override
	public BinaryTreeNode insert(BinaryTreeNode node) {
		if (null == root) {
			root = node;
		} else {
			insertNextNode(node, root);
		}
		return node;
	}

}
