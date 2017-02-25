package com.pxshuo.basic.impl;

import com.pxshuo.basic.Iterator;

/**
 * 排序二叉树
 * @author Pxshuo
 *
 */

public class BinaryTree {
	BinaryTreeNode root = null;
	
	/**
	 * 添加一个二叉树的节点
	 * @param o
	 */
	public void add(Comparable<Object> o){
		if (root == null) {
			root = new BinaryTreeNode();
			root.setData(o);
		}
		else {
			root.insert(o);
		}
	}
	
	public Object get(int index){
		Stack findChild = childPath(index);
		BinaryTreeNode child = null;
		int childNum = 0;
		for(;!findChild.isEmpty();){
			childNum = (int)findChild.pop();
			if (childNum != -1) {
				child = child.getChild(childNum);
			}
			else {
				child = root;
			}
		}
		return child == null ? null : child.getData();
	}
	
	public void display(){
		root.display(1);
	}
	
	private Stack childPath(int index) {
		Stack findChild = new Stack();
		
		while(true){
			if (index == 1 || index <= 0) {
				findChild.push(-1);
				return findChild;
			}
			if (index%2 == 1) {
				findChild.push(1);
			}
			else {
				findChild.push(0);
			}
			index = index/2;
		}
	}
}
