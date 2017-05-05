package com.rd.p2p.common.util.liuxin;

/**
 * 用Integer易于比较和插入
 * @author jhn
 * time:2017年2月24日
 */
public class BinaryTreeNode {
	
	private Integer data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public BinaryTreeNode(){
		
	}
	
	public BinaryTreeNode(Integer integer){
		this.data = integer;
	}
	
	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
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
	
	public BinaryTreeNode insert(Integer o){
		if(data == null){
			data = o;
			return  this;
		}
		BinaryTreeNode node = new BinaryTreeNode(o);
		BinaryTreeNode tempBinaryTreeNode = this;
		boolean begin = true;
		while(begin){
			if(o < data){
				tempBinaryTreeNode = tempBinaryTreeNode.getLeft();
				if(tempBinaryTreeNode.getLeft() == null){
					tempBinaryTreeNode.setLeft(node);
					begin = false;;
				}
			}else{
				tempBinaryTreeNode = tempBinaryTreeNode.getRight();
				if(tempBinaryTreeNode.getRight() == null){
					tempBinaryTreeNode.setRight(node);
					begin = false;;
				}
			}
			
		}
		return node;
	}
	
}
