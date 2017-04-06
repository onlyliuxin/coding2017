package com.coding.basic;

public class BinarySearchTree<T extends Comparable> {

	private T data;
	private BinarySearchTree<T> leftChild;
	private BinarySearchTree<T> rightChild;

	public BinarySearchTree() {
		super();
		this.data = null;
		this.leftChild = null;
		this.rightChild = null;
	}

	public BinarySearchTree(T data) {
		this();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public BinarySearchTree<T> getLeftChild() {
		return leftChild;
	}

	public BinarySearchTree<T> getRightChild() {
		return rightChild;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setLeftChild(BinarySearchTree<T> leftChild) {
		this.leftChild = leftChild;
	}

	public void setRightChild(BinarySearchTree<T> rightChild) {
		this.rightChild = rightChild;
	}

	public void insert(T element) {
		insert(element, this);
	}

	private boolean insert(T element, BinarySearchTree<T> node) {

		BinarySearchTree<T> bstNode = new BinarySearchTree(element);



		return false;

	}

	public void inOrder(BinarySearchTree<T> node) {

		if (node != null) {
			inOrder(node.getLeftChild());
			visit(node);
			inOrder(node.getRightChild());
		}

	}

	public void levelOrder(BinarySearchTree<T> node) {
		Queue queue = new Queue();
		BinarySearchTree<T> bstNode = null;
		queue.enQueue(node);

		while (!queue.isEmpty()) {
			bstNode = (BinarySearchTree) queue.deQueue();
			visit(bstNode);

			if (bstNode.getLeftChild() != null) {
				queue.enQueue(bstNode.getLeftChild());
			}

			if (bstNode.getRightChild() != null) {
				queue.enQueue(bstNode.getRightChild());
			}
		}
	}
	
	public void visit(BinarySearchTree<T> node) {
		System.out.println(node.getData());
	}

}
