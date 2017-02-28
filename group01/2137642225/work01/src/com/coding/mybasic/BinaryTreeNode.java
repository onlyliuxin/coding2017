package com.coding.mybasic;

public class BinaryTreeNode {
	
	private Integer data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public Object getData() {
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
		if(o == null){
			throw new RuntimeException("不能插入空值");
		}
		BinaryTreeNode searchNode = search(this,o);
		if(isExistData(searchNode,o)){
			throw new RuntimeException("该值已存在 无法插入");
		}
		if(searchNode != null){
			BinaryTreeNode binaryTreeNode = new BinaryTreeNode();
			binaryTreeNode.setData(o);
			if(searchNode.data.intValue() > o.intValue()){
				searchNode.setLeft(binaryTreeNode);
			}else{
				searchNode.setRight(binaryTreeNode);
			}
		} else {
			throw new RuntimeException("根节点未赋值,无法插入");
		}
		return this;
	}
	
	private boolean isExistData(BinaryTreeNode searchNode,Integer data) {
		return  searchNode != null && searchNode.data.intValue() == data.intValue();
		
	}
	
	private BinaryTreeNode search(BinaryTreeNode binaryTreeNode, Integer data) {
		if(binaryTreeNode == null || binaryTreeNode.data == null){
			return null;
		}
		Integer curNodeData = binaryTreeNode.data;
		if(curNodeData.intValue() > data.intValue()){// 左 curNodeData > data
			if(binaryTreeNode.left != null){
				return search(binaryTreeNode.left,data);
			} 
		}else if(curNodeData.intValue() < data.intValue()){
			if(binaryTreeNode.right != null){
				return search(binaryTreeNode.right,data);
			} 
			
		}
		return binaryTreeNode;
	}
	
}
