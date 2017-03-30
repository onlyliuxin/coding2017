package com.github.mrwengq.first;

import java.util.Comparator;

public class BinaryTreeNode {

	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	private Comparator cpt;
	public BinaryTreeNode() {
		cpt = new Comparator() {

			public int compare(Object o1, Object o2) {
				int ob1 = ((Integer) o1).intValue();
				int ob2 = ((Integer) o2).intValue();
				if (ob1 > ob2){
					return 1;			
				}
				if(ob1<ob2){
					return -1;
				}
				return 0;
			}

		};
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

	public BinaryTreeNode insert(Object o) {
		if (data == null) {
			data = o;
			return null;
		}
		BinaryTreeNode tree = new BinaryTreeNode();
		tree.setData(o);
		int comValue = cpt.compare(data, tree.data);
		if (comValue > 0) {
			if (getLeft() == null) {
				this.setLeft(tree);
				return null;
			}
			left.insert(o);
		} else if (comValue < 0) {
			if (getRight() == null) {
				this.setRight(tree);
				return null;
			}
			right.insert(o);
		}
		return null;
	}
	//新加入的方法用于获取节点，帮助测试insert
	public BinaryTreeNode findNode(Object o){
		
			
			if((int)this.data == (int)o){
				return this;
				
			}else if((int)this.data > (int)o){
				if(this.left!=null){
					return this.left.findNode(o);					
				}
				return null;
			}else if((int)this.data <(int)o){
				if(this.right!=null){			
					return this.right.findNode(o);
				}
				return null;
			}
			return null;
		
	}

}
