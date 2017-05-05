package com.coding.basic;



/**
 * @author hugaoqing
 * created on 2017-3-11
 */
public class BinaryTreeNode {
	
	private Comparable data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public BinaryTreeNode(Comparable data2) {
		// TODO Auto-generated constructor stub
	}
	public BinaryTreeNode() {
		// TODO Auto-generated constructor stub
	}
	/*public BinaryTreeNode(Comparable data) {
		super();
		this.data = data;
	}*/
	public Comparable getData() {
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
	
	public BinaryTreeNode insert(Comparable data){
		/*if(this.data==null){
			return new BinaryTreeNode(o);
		}
		
		BinaryTreeNode curNode = this;
		while(curNode != null){
			if(curNode.data.compareTo(o) == 0){
				return curNode;
			}
			else if(o.compareTo(curNode.data) < 0){
				BinaryTreeNode node = curNode;
				curNode = curNode.left;
				if(curNode == null){
					curNode = new BinaryTreeNode(o);
					node.left = curNode;
					return curNode;
				}
				
				
			}else if(o.compareTo(curNode.data) > 0){
				BinaryTreeNode node = curNode;
				curNode = curNode.right;
				if(curNode == null){
					curNode = new BinaryTreeNode(o);
					node.right = curNode;
					return curNode;
				}
			}
		}
		return curNode;*/
		BinaryTreeNode curNode = this;
		BinaryTreeNode insertNode = new BinaryTreeNode();
		insertNode.setData(data);
		
		while(curNode != null){
			if(null == curNode.data){
				curNode.setData(data);
				break;
			}else{
				Comparable dataOfNode = curNode.getData();
				if(dataOfNode.compareTo(data) == 0){
					break;
				}else if(dataOfNode.compareTo(data) < 0){
					BinaryTreeNode leftNode = curNode.left;
					if(null == leftNode){
						curNode.setLeft(insertNode);
						break;					
					}
					curNode = leftNode;
				}else{
					BinaryTreeNode rightNode = curNode.right;
					if(null == rightNode){
						curNode.setRight(insertNode);
						break;					
					}
					curNode = rightNode;
				}
			}
		}
		return insertNode;
		
		
	}
	
}
