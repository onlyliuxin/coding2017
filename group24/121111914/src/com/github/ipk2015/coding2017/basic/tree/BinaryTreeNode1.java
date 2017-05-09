package com.github.ipk2015.coding2017.basic.tree;

public class BinaryTreeNode1 {
	
	private Comparable data;
	private BinaryTreeNode1 left;
	private BinaryTreeNode1 right;
	
	public Object getData() {
		return data;
	}
	public void setData(Comparable data) {
		this.data = data;
	}
	public BinaryTreeNode1 getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode1 left) {
		this.left = left;
	}
	public BinaryTreeNode1 getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode1 right) {
		this.right = right;
	}
	
	public BinaryTreeNode1 insert(Comparable o){
		BinaryTreeNode1 insertNode=new BinaryTreeNode1();
		BinaryTreeNode1 compareNode=this;
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
