package com.dudy.learn01.base;

public class MyBinaryTree {

	private Node root;

	public Node getRoot() {
		return root;
	}

	static class Node {
		private int data;
		private Node left;
		private Node right;

		public Node(int data) {
			this.data = data;
		}

	}

	public Node insert(int o) {
		Node newNode = new Node(o);

		if (root == null) {
			root = newNode;
			return newNode;
		}
		Node currentNode = root;

		while (true) {
			// System.out.println("currentNode: " + currentNode.data );
			if (o <= currentNode.data) { // left

				if (currentNode.left != null) {
					currentNode = currentNode.left;
				} else {
					currentNode.left = newNode;
					// System.out.println("left return ...");
					// System.out.println("");
					return newNode;
				}
			} else { // right
				if (currentNode.right != null) {
					currentNode = currentNode.right;
				} else {
					currentNode.right = newNode;
					// System.out.println("right return ...");
					// System.out.println("");
					return newNode;
				}
			}

		}

	}

	public  void display(Node root) {
		
		System.out.print(root.data + " ");
		if (root.left != null) {
			display(root.left);
		}
		if (root.right != null) {
			display(root.right);
		}
	}



}