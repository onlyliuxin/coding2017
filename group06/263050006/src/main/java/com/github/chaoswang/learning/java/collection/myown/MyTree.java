package com.github.chaoswang.learning.java.collection.myown;

import java.util.List;

public class MyTree {

	public MyTree(MyTreeNode treeNode){
		
	}
	
	
	private class MyTreeNode {
		private MyTreeNode parent;
		private List<MyTreeNode> children;//二叉树时，限制为left和right
		private boolean allowsChildren;
		private Object userObject;//节点都是用来存储数据的
		
		public MyTreeNode(Object userObject){
			this.userObject = userObject;
		}
		
		public List<MyTreeNode> children(){
			return children;
		}
		
		public MyTreeNode getChildAt(int childIndex){
			return children.get(childIndex);
		}
		
		public int getIndex(MyTreeNode node){
			return children.indexOf(node);
		}
		
		public MyTreeNode getParent(){
			return parent;
		}
		
		public boolean isLeaf(){
			return children.size() > 0 ? false : true; 
		}
		
		public boolean getAllowsChildren(){
			return allowsChildren;
		}
		
		public void insert(MyTreeNode node, int index){
			children.remove(node);
			children.add(index, node);
		}
		
		public void remove(int index){
			children.remove(index);
		}
		
		public void remove(MyTreeNode node){
			children.remove(node);
		}
	}
}
