/**
 * 
 */
package com.coding.basic;

/**
 * @author patchouli
 * @param <T>
 *
 */
public class BinaryTreeNode {

	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public BinaryTreeNode(){}
	
	public BinaryTreeNode(Object data){
		this.data=data;
		left=null;
		right=null;
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

	public BinaryTreeNode insert(Object o) {
		if (o.toString().compareTo(data.toString())<0) {
			if (left==null) {
				left=new BinaryTreeNode(o);
				return left;
			}
			insert(o);
		}
		else {
			if (right==null) {
				right=new BinaryTreeNode(o);
				return right;
			}
			insert(o);
		}
		return null;
	}

}
