package com.coding.basic;

import java.util.Properties;

public class BinaryTreeNode {

	private TreeData treeData;
	//private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public TreeData getData() {
		return treeData;
	}
	public void setData(TreeData data) {
		this.treeData = data;
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

	public void PriOder(BinaryTreeNode Node)
	{
		if (Node.treeData != null)
		{
				System.out.println(Node.treeData.getT());
				if (Node.left != null)
				{
					PriOder(Node.left);
				}
				if (Node.right != null)
				{
					PriOder(Node.right);
				}
		}

	}

	
	public BinaryTreeNode insert(TreeData o){
		if (this.treeData == null && this.left == null && this.right == null)
		{
			this.treeData = o;
			this.left = null;
			this.right = null;
			return null;
		}

		//遍历寻找元素应该插入的位置
		if (o.compareTo(this.treeData) <= 0)
		{
			if (this.left != null)
			{
				this.left.insert(o);
			}
			else
			{
				BinaryTreeNode NewNode = new BinaryTreeNode();
				NewNode.setData(o);
				NewNode.setLeft(null);
				NewNode.setRight(null);
				this.left = NewNode;
			}
		}
		else
		{
			if (this.right != null)
			{
				this.right.insert(o);
			}
			else
			{
				BinaryTreeNode NewNode = new BinaryTreeNode();
				NewNode.setData(o);
				NewNode.setLeft(null);
				NewNode.setRight(null);
				this.right = NewNode;
			}
		}


		return  null;
	}
	
}
