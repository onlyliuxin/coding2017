package com.github.ipk2015.coding2017.basic;

public class BinaryTreeNode {
	
	private Comparable data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public Object getData() {
		return data;
	}
	public void setData(Comparable data) {
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
	
	public BinaryTreeNode insert(Comparable o){
		BinaryTreeNode insertNode=new BinaryTreeNode();
		BinaryTreeNode compareNode=this;
		insertNode.setData(o);
		
		while(null!=compareNode){
			if(null==compareNode.getData()){
				compareNode.setData(o);
				break;
			}else{
				Comparable com=(Comparable) compareNode.getData();
				int result=com.compareTo(o);
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
