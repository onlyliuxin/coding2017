package com.coding.basic;

public class BinaryTreeTest {
	private BinaryTreeNode rootNode;
	
	class BinaryTreeNode{
		private int data;
		private BinaryTreeNode left;
		private BinaryTreeNode right;
		public BinaryTreeNode(int data) {
			super();
			this.data = data;
		}	
	}
	
	public BinaryTreeNode insert(int o){
		BinaryTreeNode newNode=new BinaryTreeNode(o);
		if(rootNode==null){
			rootNode=newNode;
		}else{
			compareNode(rootNode,newNode);
		}
		return  newNode;
	}
	
	private void compareNode(BinaryTreeNode node1,BinaryTreeNode node2){
		if(node1.data>node2.data){
			if(node1.left==null){
				node1.left=node2;
			}else{
				compareNode(node1.left,node2);
			}
		}else{
			if(node1.right==null){
				node1.right=node2;
			}else{
				compareNode(node1.right,node2);
			}
		}
	}
	
}
