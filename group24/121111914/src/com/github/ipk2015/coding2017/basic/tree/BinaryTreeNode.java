package com.github.ipk2015.coding2017.basic.tree;



public class BinaryTreeNode<T> {
	
	private T data;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	
	public BinaryTreeNode(T data){
		this.data=data;
	}
	public BinaryTreeNode(){
		
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
	
	public BinaryTreeNode<T> insert(T o){
		BinaryTreeNode insertNode=new BinaryTreeNode(o);
		BinaryTreeNode compareNode=this;
		
		while(null!=compareNode){
			if(null==compareNode.getData()){
				compareNode.setData(o);
				break;
			}else{
				Comparable com=(Comparable) compareNode.getData();
				
				int result = com.compareTo(o);
				
				if(result==0){
					break;
				}else if(result>0){
					if(null==compareNode.getLeft()){
						compareNode.setLeft(insertNode);
						break;
					}
					compareNode=compareNode.getLeft();
				}else if(result<0){
					if(null==compareNode.getRight()){
						compareNode.setRight(insertNode);
						break;
					}
					compareNode=compareNode.getRight();
				}
			}
		}
		
		return  insertNode;
	}
	
}
