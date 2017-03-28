package com.danny.hw1;

import java.util.Map;
import java.util.TreeMap;

public class BinaryTreeNode {
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public BinaryTreeNode insert(Object o){
		if ( this.data == null && left == null && right == null ) {
			this.data = o;
			return this;
		} else {
			BinaryTreeNode temp = findNode(this, o);

			BinaryTreeNode newNode = new BinaryTreeNode();
			newNode.data = o;
			//assert more than one null node in the temp(left,right,or both);
			if ( temp.compareTo(o) >= 0 ) {
				temp.left = newNode;
			} else {
				temp.right = newNode;
			}
			return newNode;
		}
	}
		
	public BinaryTreeNode() {
		// TODO Auto-generated constructor stub
		data=null;
		left =null;
		right = null;
		
	} 
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
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
	
	private int compareTo(Object o){
		// o1 > o2 == 1; o1 = o2 == 0 ; o1 < o2 == -1		
		//假设这个比较现在只用在数字上
		return Integer.parseInt(this.data.toString()) - Integer.parseInt(o.toString());
	}
	
	private static BinaryTreeNode findNode(BinaryTreeNode root,Object o){
		if ( root.left == null && root.right == null || 
		   ( root.compareTo(o) < 0 && root.right == null) ||
		   ( root.compareTo(o) >= 0 && root.left == null) ){
			return root;
		} else if ( root.compareTo(o) >= 0 ) {
			//root data is bigger than object
			return findNode(root.left, o);
		} else {
			return findNode(root.right, o);
		}
//			else if(root.compareTo(o) < 0 ){
//			return findNode(root.right, o);
//		}
//		
	}

	//For Test value
	private Map<Object, Integer> teMap = new TreeMap<>();
	public void printWholeTree(BinaryTreeNode root,int layer){
		if(root == null) {
			return ;
		}
		teMap.put(root.data,layer);

		layer++;
		printWholeTree(root.left,layer);
		printWholeTree(root.right,layer);
		
	}

	public Map<Object,Integer > getTeMap() {
		return teMap;
	}

	public void setTeMap(Map<Object, Integer> teMap) {
		this.teMap = teMap;
	}
}
