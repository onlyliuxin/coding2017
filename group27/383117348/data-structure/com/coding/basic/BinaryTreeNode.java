package com.coding.basic;

public class BinaryTreeNode {

	private Comparable data;
	private BinaryTreeNode root;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public Comparable getData() {
		return data;
	}

	public void setData(Comparable data) {
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

	public BinaryTreeNode getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}

	/**
	 * 插入节点方法
	 * 
	 * @param o
	 * @return
	 */
	public BinaryTreeNode insert(Comparable o) {
		if (o != null) {
			root = insert(root, o);
			//System.out.println("@@@@@@"+root.getData());
		}
		return root;
	}

	/**
	 * 递归比较插入平衡树
	 * 
	 * @param node
	 * @param o
	 * @return
	 */
	private BinaryTreeNode insert(BinaryTreeNode node, Comparable o) {
		if (node == null) {
			node = new BinaryTreeNode();
			node.setData(o);
		} else if (compare(node, o) >= 0) {
			node.left = insert(node.left, o);
			 //System.out.println(o+":insert into left");
			 //System.out.println("left:"+node.getData()+":"+o);
		} else if (compare(node, o) < 0) {
			node.right = insert(node.right, o);
			 //System.out.println(o+":insert into right");
			 //System.out.println("right:"+node.getData()+":"+o);
		}
		return node;
	}

	/**
	 * 比较内容大小
	 * 
	 * @param node
	 * @param o
	 * @return
	 */
	private int compare(BinaryTreeNode node, Comparable o) {
		// TODO Auto-generated method stub
		return node.data.compareTo(o);
	}

	/*-----------------------------------------------------单元测试-----------------------------------------------------*/

	private void printTree() {
		printTree(root);
	}

	private void printTree(BinaryTreeNode node) {
		if (node == null)
			return;
		printTree(node.left);
		System.out.println("---"+node.data + "");
		printTree(node.right);
	}

	public static void main(String[] args) {
		BinaryTreeNode tree = new BinaryTreeNode();
		for (int x = 0; x < 100; x++) {
			Double i = new Double(Math.random() * 100);
			//System.out.println(i);
			tree.insert(i);
		}
		System.out.println("@@@@@@" + tree.getRoot().getData());
		tree.printTree();
	}
}
