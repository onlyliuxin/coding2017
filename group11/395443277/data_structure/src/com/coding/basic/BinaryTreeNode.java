package com.coding.basic;

public class BinaryTreeNode implements Comparable<Object>{
	
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
	
	public BinaryTreeNode insert(Object o){
		if (this.compareTo(o)==0) {
			return null;
		} else {
			// current value less than inserted value
			// go right
			if (this.compareTo(o)<0) { 
				if (this.right == null) {
					BinaryTreeNode nd = new BinaryTreeNode();
					nd.setData(o);
					this.setRight(nd);
				} else {
					this.getRight().insert(o);
				}
			} 
			// greater than
			// go left
			else if(this.compareTo(o)>0) {
				if (this.left == null) {
					BinaryTreeNode nd = new BinaryTreeNode();
					nd.setData(o);
					this.setLeft(nd);
				} else {
					this.getLeft().insert(o);
				}
			}
		}
		
		return  null;
	}
	
	/**
	 * oversimplified implementation: only allows int and string
	 */
	@Override
	public int compareTo(Object nd) throws ClassCastException{
		if (!(nd instanceof Object)) {
			throw new ClassCastException("An object expected.");
		}
		
		if (nd instanceof String) {
			return ((String)this.data).compareTo((String) nd);
		} else {
			return ((int) this.data) -((int) nd);
		}
	}
	
}
