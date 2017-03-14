package com.louisly.java;
import com.louisly.java.LYObject;

public class LYBinaryTree {

	private LYBinaryTreeNode headerNode;
	
	public void addObject(LYObject obj) {
		
		if (headerNode == null) {
			LYBinaryTreeNode node = new LYBinaryTreeNode();
			node.data = obj;
			headerNode = node;
			return;
		}
		
		this.appendObject(headerNode, obj);
	}
	
	private void appendObject(LYBinaryTreeNode toNode, LYObject obj) {
		if (obj.i > toNode.data.i) {
			if (toNode.right != null) {
				this.appendObject(toNode.right, obj);
			} else {
				LYBinaryTreeNode node = new LYBinaryTreeNode();
				node.data = obj;
				toNode.right = node;
			}
		} else {
			if (toNode.left != null) {
				this.appendObject(toNode.left, obj);
			} else {
				LYBinaryTreeNode node = new LYBinaryTreeNode();
				node.data = obj;
				toNode.left = node;
			}
		}
	}
	
	public static class LYBinaryTreeNode {
		private LYObject data;
		private LYBinaryTreeNode left;
		private LYBinaryTreeNode right;
	}
}
