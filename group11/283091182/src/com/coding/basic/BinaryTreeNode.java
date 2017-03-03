package com.coding.basic;

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
	public BinaryTreeNode(Object o,BinaryTreeNode leftChild,BinaryTreeNode rightChild){
		this.data = o;
		this.left = leftChild;
		this.right = rightChild;
	}
	public BinaryTreeNode insert(Object o){
		if(!(o instanceof Comparable)){
			throw new RuntimeException("Incompareable Oject:"+o);
		}
		System.out.println("CurrentNode Value="+data);
		if(((Comparable)o).compareTo(data)>0){
			System.out.print(o+" is greater than "+data+",insert to right;");
			if(this.right==null){
				System.out.println("Creating new rightChild;");
				this.right = new BinaryTreeNode(o,null,null);
			}else{
				System.out.println("rightChild exists,Conitnue to insert to rightChild");
				this.right.insert(o);
			}
		}
		if(((Comparable)o).compareTo(data)<0){
			System.out.print(o+" is less than "+data+",insert to left;");
			if(this.left==null){
				System.out.println("Creating new leftChild;");
				this.left = new BinaryTreeNode(o,null,null);
			}else{
				System.out.println("leftChild exists,Conitnue to insert to leftChild");
				this.left.insert(o);
			}
		}
		return this;
	}
	
	public static void main(String[] args){
		Integer one = new Integer(1);
		Integer two = new Integer(2);
		System.out.println(one.compareTo(two));
		System.out.println(two.compareTo(one));
		System.out.println(one.compareTo(one));
		BinaryTreeNode btn = new BinaryTreeNode(new Integer(5),null,null);
		btn.insert(new Integer(2));
		btn.insert(new Integer(7));
		btn.insert(new Integer(1));
		btn.insert(new Integer(6));
		inOrderTraversal(btn);
		btn.insert(new Integer(4));
		btn.insert(new Integer(8));
		inOrderTraversal(btn);
	}
	//in-order traversal to print all nodes in sorted order
	private static void inOrderTraversal(BinaryTreeNode btn){
		if(btn!=null){
			if(btn.left!=null){
				inOrderTraversal(btn.left);
			}
			System.out.println(btn.data);
			if(btn.right!=null){
				inOrderTraversal(btn.right);
			}
		}
	}
	
}
