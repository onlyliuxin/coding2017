package com.dong.week1;

public class BinaryTreeNode {
	private TreeNode node;
	
	  private static class  TreeNode{  
	        private int key=0;  
	        private TreeNode leftChild=null;  
	        private TreeNode rightChild=null;  
	          
	        public TreeNode(){}  
	          
	        /** 
	         * @param key  ²ãĞò±àÂë 
	         * @param data Êı¾İÓò 
	         */  
	        public TreeNode(int key){  
	            this.key=key;  
	            this.leftChild=null;  
	            this.rightChild=null;  
	        }  
	  
	  
	    }  
	
	
	
	public TreeNode insert(TreeNode  o){
		if(node == null){
			return o;
		}		
		if(node.key > o.key){
			return insert(o.leftChild);
		}else{
			return insert(node.leftChild);
		}
	}
	
}
