package com.coding.basic;

/**
 * BST ����������  ʵ�� ��14С�� 296933284
 * 
 * @author Tonnyson
 *
 */
public class BinarySearchTree implements Comparable {

	private Object data;
	private BinarySearchTree leftChild;
	private BinarySearchTree rightChild;

	public BinarySearchTree() {
		super();
		this.data = null;
		this.leftChild = null;
		this.rightChild = null;
	}

	public BinarySearchTree(Object data) {
		this();
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public BinarySearchTree getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinarySearchTree leftChild) {
		this.leftChild = leftChild;
	}

	public BinarySearchTree getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinarySearchTree rightChild) {
		this.rightChild = rightChild;
	}

	/**
	 * �����в���ڵ�
	 * 
	 * @param obj
	 *            �ڵ�ֵ
	 */
	public void insert(Object obj) {
		insert(obj, this);
	}

	private boolean insert(Object obj, BinarySearchTree node) {

		BinarySearchTree bstNode = new BinarySearchTree(obj);

		if (node == null) {
			node = bstNode;
			return true;
		} else if (node.compareTo(obj) == 0) {
			return true;
		} else if (node.compareTo(obj) > 0) {

			if (node.getLeftChild() != null) {
				return insert(obj, node.getLeftChild());
			}

			node.leftChild = bstNode;

		} else if (node.compareTo(obj) < 0) {

			if (node.getRightChild() != null) {
				return insert(obj, node.getRightChild());
			}

			node.rightChild = bstNode;
		}

		return false;

	}

	/**
	 * ������� BST �Ľڵ㣬ʹ֮�������
	 */
	public void inOrder(BinarySearchTree node) {

		if (node != null) {
			inOrder(node.getLeftChild());
			visit(node);
			inOrder(node.getRightChild());
		}

	}

	/**
	 * ������� BST �Ľڵ�ֵ
	 */
	public void levelOrder(BinarySearchTree node) {
		Queue queue = new Queue();
		BinarySearchTree bstNode = null;
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
	
	/**
	 * ����ָ���ڵ�ֵ
	 * 
	 * @param node
	 */
	public void visit(BinarySearchTree node) {
		System.out.println(node.getData());
	}

	/**
	 * �Ƚ� BST �ڵ�ֵ��С
	 */
	@Override
	public int compareTo(Object obj) {
		int result = 0;

		if (obj instanceof Integer) {
			Integer value = (Integer) obj;
			Integer thisValue = (Integer) this.data;
			result = thisValue.compareTo(value);
		} else {
			String value = obj.toString();
			result = this.data.toString().compareTo(value);
		}

		return result;
	}

}
