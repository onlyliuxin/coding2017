package com.github.qq809203042.coding2017.basic.structures;


/*
 * 实现二叉树,
 * 只存储int类型
 * 内部类代表每个节点：每个节点含有一个键，一个值，一条左链接，一条右链接
 * 
 * 实现插入值操作：当root为空时插在root上：确定某一节点上是否为空
 * 			 
 */
public class MyBinaryTree {
	private BinaryTreeNode root ;
	private int size;

//	构造函数
	public MyBinaryTree(){
		
	}
	
	public boolean getValue(Integer key){
		return getValue(root,key);
	}
	
	private boolean getValue(BinaryTreeNode node,Integer key){
		if(node == null){//如果为空，则为空，没有该元素
			return false;
		}
		Integer value = node.getValue();
		if(value == key){//找到
			return true;
		}
		else if(value.compareTo(key) > 0){//如果小于该节点对象，比较左节点
			return getValue(node.left,key);
		}else{
			return getValue(node.right,key);//如果小于该节点对象，比较右节点
		}
		
	}

	public void add(Integer key){
		root = add(root, key);
		
	}

	private BinaryTreeNode add(BinaryTreeNode node, Integer key) {
		if(node == null){//若没有该节点，则创建并添加数据至节点中
			node = new BinaryTreeNode(key);
			size++;
			return node;
		}
		Integer value = node.getValue();
		if(value.compareTo(key) > 0){
			node.setLeft(add(node.left,key));
			
		}else if(value.compareTo(key) < 0){
			node.setRight(add(node.right,key));
			
		}
		return node;
	}
	
	public int size(){
		return size;
	}
	

//	前序遍历
	public void preOrder(){
		preOrder(root);
		System.out.println();
	}
//	中序遍历
	public void midOrder(){
		midOrder(root);
		System.out.println();
	}
//	后序遍历
	public void aftOrder(){
		aftOrder(root);
		System.out.println();
	}
	
//	前序遍历
	private void preOrder(BinaryTreeNode node){
		if(node == null){
			return;
		}
		System.out.print(node.value + " ");
		preOrder(node.left);
		preOrder(node.right);
		
	}
//	中序遍历
	private void midOrder(BinaryTreeNode node){
		if(node == null){
			return;
		}
		midOrder(node.left);
		System.out.print(node.value + " ");
		midOrder(node.right);
		
	}
//	后序遍历
	private void aftOrder(BinaryTreeNode node){
		if(node == null){
			return;
		}
		aftOrder(node.left);
		aftOrder(node.right);
		System.out.print(node.value + " ");
		
	}
	


	// 二叉树的节点对象：每个节点含有一个键，一个值，一条左链接，一条右链接和一个节点计数器
	private static class BinaryTreeNode  {
		private Integer value;

		private BinaryTreeNode left;
		private BinaryTreeNode right;
//		构造函数
		BinaryTreeNode(Integer value){
			this.value = value;
		}

		

		public Integer getValue() {
			return value;
		}
		public void setValue(Integer value) {
			this.value = value;
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
		
		public BinaryTreeNode insert(Integer value) {
			this.value = value;
			return null;
		}

	}
}
