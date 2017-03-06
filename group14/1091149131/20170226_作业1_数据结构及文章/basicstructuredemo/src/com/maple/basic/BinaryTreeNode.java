package com.maple.basic;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

public class BinaryTreeNode<T extends Comparable<T>>{
	
	private T data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	/**
	 * 如果待插入的值等于节点的值，则抛出异常：duplicate value
	 * 如果小于节点的值，则往左边遍历
	 * 如果大于节点的值，则往右边遍历
	 * @param o
	 * @return 
	 * 2017年2月23日 下午4:22:50
	 * @Author Joy
	 */
	public BinaryTreeNode insert(T o){
		//assume that no duplicate key
		
		if(o.compareTo(data)==0){
			try {
				throw new DuplicateName("duplicate value: "+o);
			} catch (DuplicateName e) {
				e.printStackTrace();
			}
		}
		BinaryTreeNode<T> newB=new BinaryTreeNode<T>();
		newB.setData(o);
		newB.setLeft(null);
		newB.setRight(null);
		//o更大，在右边
		if(o.compareTo(data)>0){
			if(this.getRight()!=null){
				this.getRight().insert(o);
			}else{
				this.setRight(newB);
			}
		}else if(o.compareTo(data)<0){
			if(this.getLeft()!=null){
				this.getLeft().insert(o);
			}else{
				this.setLeft(newB);
			}
		}
		return  newB;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
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
}
