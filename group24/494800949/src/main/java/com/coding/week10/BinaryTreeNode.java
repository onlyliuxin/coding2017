package com.coding.week10;


public class BinaryTreeNode<T extends Comparable<T>>{
	
	private T data;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	
	public BinaryTreeNode(T data){
		this.data=data;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryTreeNode<T> getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}
	public BinaryTreeNode<T> getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
	
	public BinaryTreeNode<T> insert(Object o){
		@SuppressWarnings("unchecked")
		T o1 = (T)o;
		BinaryTreeNode<T> newNode = new BinaryTreeNode<>(o1);
		if (data.compareTo(o1) > 0 ) {
			if (left == null) {
				left = newNode;
			} else {
				left.insert(o);
			}
		} else {
			if (right == null) {
				right = newNode;
			} else {
				right.insert(o);
			}
		}
		return  newNode;
	}

	@Override
	public String toString() {
		return "{" +
				"data:" + data +
				", left:" + left +
				", right:" + right +
				'}';
	}

	public static void main(String[] args) {
		BinaryTreeNode<Integer> b = new BinaryTreeNode<>(2);
		b.insert(3).insert(5).insert(1);
		b.insert(6);
		b.insert(8);
		b.insert(1);
		System.out.println(BinaryTreeUtil.preOrderVisit(b));

//		BinaryTreeNode<AtomicInteger> b1 = new BinaryTreeNode<>(new AtomicInteger(3));
//		b1.insert(new AtomicInteger(4));
	}
}
