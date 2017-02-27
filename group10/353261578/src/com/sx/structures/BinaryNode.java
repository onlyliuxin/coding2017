package com.sx.structures;

public class BinaryNode implements Comparable<BinaryNode>{
	private Object data;
	private BinaryNode left;
	private BinaryNode right;
	
	public BinaryNode() {
	}
	public BinaryNode(Object o){
		data = o;
		left = null;
		right = null;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public BinaryNode getLeft() {
		return left;
	}
	public void setLeft(BinaryNode left) {
		this.left = left;
	}
	public BinaryNode getRight() {
		return right;
	}
	public void setRight(BinaryNode right) {
		this.right = right;
	}
	@Override
	public int compareTo(BinaryNode o) {
		Integer to = (Integer)this.data;
		Integer co = (Integer)o.data;
		if(to<co)
			return -1;
		if(to>co)
			return 1;
		return 0;
	}
}
