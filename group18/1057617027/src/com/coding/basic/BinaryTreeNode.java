package com.coding.basic;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	private BinaryTreeNode head;
	private BinaryTreeNode node;
	BinaryTreeNode(Object data,BinaryTreeNode left,BinaryTreeNode right){
		this.data = data;
		this.left = left;
		this.right = right;
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
	
	public BinaryTreeNode insert(Object o){
		if(node==null){
			node = new BinaryTreeNode(o, null, null);
		}else{
			if(Integer.parseInt(String.valueOf(o))<=Integer.parseInt(String.valueOf(node.data))){
				node.left = insert(node.left,o );
				node = node.left;      
			}else{
				node.right = insert(node.right,o);
				node = node.right;
			}
		}
		return  node;
	}
	public BinaryTreeNode insert(BinaryTreeNode node,Object o){
		if(node==null){
			node = new BinaryTreeNode(o, null, null);
		}else{
			if(Integer.parseInt(String.valueOf(o))<=Integer.parseInt(String.valueOf(node.data))){
				node.left = insert(node.left,o );
				node.left =node;
			}else{
				node.right = insert(node.right,o );
				node.right =node;
			}
		}
		return  node;
	}
public static void main(String[] args){
	BinaryTreeNode node = new BinaryTreeNode(null, null, null);
	
	System.out.println(node.insert(6).data);
	System.out.println(node.insert(5).data);
	System.out.println(node.insert(11).data);
	System.out.println(node.insert(7).data);
	System.out.println(node.insert(2).data);
	System.out.println(node);

}	
}