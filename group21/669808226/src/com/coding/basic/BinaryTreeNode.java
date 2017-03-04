package com.coding.basic;

/*
 *  不平衡的二叉树
 *  不支持查找和遍历
 *  支持插入数据
 */
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

	public BinaryTreeNode insert(Object o)
	{
		if(o == null)
			return null;
		
		if(o instanceof Comparable<?>)
		{
			return insert((Comparable<?>)o);
		}	
		
		return null;
	}
	
	private BinaryTreeNode insert(Comparable<?> object)
	{
		if(object == null)
			return null;
		
		//be the root
		if(this.left == null && this.right == null)
		{
			
		}
		
		return null;
	}
	
}
