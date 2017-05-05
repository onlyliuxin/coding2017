package com.coding.basic;

public class BinaryTreeNode<T extends Comparable<T>>{
	
	private T data;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	private BinaryTreeNode<T> root;
	private LinkedList tree = new LinkedList();
	
	public Object getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryTreeNode<T> getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}
	public BinaryTreeNode<T> getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
	
	public BinaryTreeNode<T> insert(T o){
		BinaryTreeNode<T> currNode = null;
		if(root==null){
			currNode = new BinaryTreeNode<T>();
			currNode.data = o;
			this.root = currNode;
			tree.addFirst(currNode);
		}else{
			currNode = findTheParentPosition(root,o);
			if(o.compareTo(currNode.data)>0){
				BinaryTreeNode<T> tNode = new BinaryTreeNode<T>();
				tNode.data = o;
				tree.add(tNode);
			}else{
				BinaryTreeNode<T> tNode = new BinaryTreeNode<T>();
				tNode.data = o;
				currNode.left  = tNode;
				tree.add(tNode);
			}
		}
		return  currNode;
	}
	
	private BinaryTreeNode<T> findTheParentPosition(BinaryTreeNode<T> node ,T o){
		if(o.compareTo(node.data) >0){
			if(node.getRight()!=null)
				return findTheParentPosition(node.getRight(),o);
			else
				return node;
		}else{
			if(node.getLeft()!=null)
				return findTheParentPosition(node.getLeft(),o);
			else
				return node;
		}
	}
	
	public void printTree(){
		if(this.tree!=null){
			Iterator i = tree.iterator();
			while(i.hasNext()){
				System.out.println(i.next());
			}
		}
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[Node data:"+this.data+"]");
		return sb.toString();
	}
	
	
	
}
