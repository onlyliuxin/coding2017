package xqfGit.dataStructure;

import com.sun.swing.internal.plaf.basic.resources.basic;

public class BinaryTreeNode {
	
	private Integer data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	
	
	
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
	
	public BinaryTreeNode insert(Integer i){
		
		return  null;
	}
	
	public BinaryTreeNode (){
		
	}
	

	
	public BinaryTreeNode(BinaryTreeNode b1,BinaryTreeNode b2){
		this.left = b1;
		this.right = b2;
	}
	
}
