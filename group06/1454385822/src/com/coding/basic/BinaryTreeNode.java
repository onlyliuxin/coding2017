package com.coding.basic;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
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
	
	public BinaryTreeNode insert(Object o){
		BinaryTreeNode node = null;
		return  this.insert(node, o);
	}
	public BinaryTreeNode insert(BinaryTreeNode node, Object o){
		if(node == null){
			node = new BinaryTreeNode();
			node.data = o;
			node.left = node.left = null;
			return node;
		}
		BinaryTreeNode tem = new BinaryTreeNode();
		tem.setData(o);
		int iroot = (Integer) node.data;
		int io = (Integer) o;
		if(io < iroot){
			insert(node.left, o);
		}else{
			insert(node.right, o);
		}
		return node;
	}
	
}
//class TreeComparator implements Comparator{
//
//	@Override
//	public int compare(Object o1, Object o2) {
//		BinaryTreeNode b1 = (BinaryTreeNode)o1;
//		BinaryTreeNode b2 = (BinaryTreeNode)o2;
//		Integer i1 = (Integer)b1.getData();
//		Integer i2 = (Integer)b2.getData();
//		
//		int result = i1 > i2 ? 1 : (i1 == i2 ? 0 : -1);
//		return result;
//	}
//	
//}
