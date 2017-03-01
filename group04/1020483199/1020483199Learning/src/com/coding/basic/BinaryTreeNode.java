package com.coding.basic;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	//存放当前树
	private BinaryTreeNode primary;
	
	public BinaryTreeNode getPrimary() {
		return primary;
	}

	public void setPrimary(BinaryTreeNode primary) {
		this.primary = primary;
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
	/**
	 * 在二叉树中插入
	 * @param o
	 * @return
	 */
	public BinaryTreeNode insert(Object o){
		if(o==null){
			throw new IllegalArgumentException("二叉树的元素不能为空!");
		}
		//新建要插入的节点
		BinaryTreeNode bt = new BinaryTreeNode();
		bt.setData(o);
		int value = (int)o;
		//当原始二叉树为空时
		if(primary==null){
			primary = bt;
		}else{
			BinaryTreeNode bi = primary;
			while(true){
				if(value<(int)bi.data){
					if(bi.left==null){
						bi.setLeft(bt);
						break;
					}else{
						bi=bi.left;
					}
				}else if(value>(int)bi.data){
					if(bi.right==null){
						bi.setRight(bt);
						break;
					}else{
						bi=bi.right;
					}
					
				}else{
					System.out.println("当前元素在二叉树已存在");
					break;
				}
			}
		}
			
		return bt;
	}
	
}
