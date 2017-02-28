package com.sanmubird.basicDataStructure;

public class BinaryTreeNode {
	/**	二叉树同时具有数组和链表各自的特点：它可以像数组一样迅速查找；也可以像链表一样快速添加；
	 * 	但 删除操作复杂；
	 * 	二叉树是每个节点最多有两个子树的有序树；
	 * 	一个节点的左子点的关键值必须小于此节点，右节点的关键值必须大于或者等于此节点，
	 * */
	
	
	private Integer data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public BinaryTreeNode(Integer i){
		this.data = i ;
	}
	
	
	public Object getData() {
		return data;
	}
	public void setData(Integer i) {
		this.data = i;
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
	
	public BinaryTreeNode insert(Integer i){
		BinaryTreeNode node = new BinaryTreeNode(i);
		if(i > this.data){
			if(this.getRight() == null ){
				this.setRight(node);
				return node;
			}else{
				return this.getRight().insert(i);
			}
		}else{
			if(this.getLeft() == null ){
                this.setLeft(node);
                return node ;
			}else{
				return this.getLeft().insert(i);
			}
		}
	}
	
}