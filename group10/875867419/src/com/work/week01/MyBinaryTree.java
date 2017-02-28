package com.work.week01;

public class MyBinaryTree<E> {

	private MyBinaryTreeNode<E> parent;
	
	public MyBinaryTree(){
		this.parent = new MyBinaryTreeNode<E>(null, null, null);
	}
	
	public void insertNode(E element){
		MyBinaryTreeNode<E> node = new MyBinaryTreeNode<E>(element, null, null);
		if(parent.element == null){
			parent = node;
			return;
		}
		insertNode(parent, node);
	}
	
	private void insertNode(MyBinaryTreeNode<E> parentNode, MyBinaryTreeNode<E> newNode){
		if(parentNode.compareTo(newNode) <= 0){//
			if(parentNode.right == null){
				parentNode.right = newNode;
			}else{
				insertNode(parentNode.right, newNode);
			}
		}else{
			if(parentNode.left == null){
				parentNode.left = newNode;
			}else{
				insertNode(parentNode.left, newNode);
			}
		}
	}

	private void printNode(MyBinaryTreeNode<E> node, int count){
		if(node.left != null){
			printNode(node.left, count++);
		}
		if(node.right != null){
			printNode(node.right, count++);
		}
		for(int i=0;i<count;i++){
			System.out.println();
		}
		System.out.print(node.element);
	}
	
	public void printTree(){
		printNode(this.parent, 0);
	}
	
	private class MyBinaryTreeNode<T> implements Comparable<MyBinaryTreeNode<T>> {

		private T element;
		private MyBinaryTreeNode<T> left;
		private MyBinaryTreeNode<T> right;
		
		public MyBinaryTreeNode(T element, MyBinaryTreeNode<T> left, MyBinaryTreeNode<T> right){
			this.element = element;
			this.left = left;
			this.right = right;
		}
		
		@Override
		public int compareTo(MyBinaryTreeNode<T> o) {
			Integer src = (Integer) this.element;
			Integer dest = (Integer) o.element;
			return src.compareTo(dest);
		}
	}
	
	public static void main(String[] args) {
		MyBinaryTree<Integer> tree = new MyBinaryTree<Integer>();
		tree.insertNode(5);
		tree.insertNode(7);
		tree.insertNode(3);
		tree.insertNode(9);
		tree.insertNode(4);
		tree.printTree();
	}
}
