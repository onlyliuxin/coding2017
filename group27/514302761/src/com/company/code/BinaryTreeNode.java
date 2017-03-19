package com.company.code;

public class BinaryTreeNode {
	
	private Comparable data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = (Comparable) data;
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
	
	public BinaryTreeNode insert(Comparable  o){
		BinaryTreeNode insercode=new BinaryTreeNode();
		BinaryTreeNode compareNode=this;
			insercode.setData(o);
	while (null!=compareNode){
		if(null==compareNode.getData()){
			compareNode.setData(o);
			break;
		}else{
			Comparable com= (Comparable) compareNode.getData();
			int result=com.compareTo(o);
			if(result==0){
				break;
			}else if (result>0){
			if(null==compareNode.getLeft()){
				compareNode.setLeft(insercode);
				break;
			}
                compareNode=compareNode.getLeft();
			}else if(result<0){
			    if(null==compareNode.getRight()){
                    compareNode.setRight(insercode);
                    break;
                }
                compareNode=compareNode.getLeft();
            }
		}
	}
		return  insercode;
	}
	
}
