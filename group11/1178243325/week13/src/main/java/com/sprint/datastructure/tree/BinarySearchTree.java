package com.sprint.datastructure.tree;

import com.sprint.datastructure.Queue;
import java.util.ArrayList;
import java.util.List;
public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public T findMin() {
		if (root == null) {
			return null;
		}
		return findMin(root).data;
	}

	public T findMax() {
		if (root == null) {
			return null;
		}
		return findMax(root).data;
	}

	public int height() {
		return height(root);
	}

	public int size() {
		return size(root);
	}

	public void remove(T e) {
		remove(e, root);	
	} 


	public List<T> levelVisit() {
		List<T> result = new ArrayList<T>();		
		Queue<BinaryTreeNode<T>> queue = new Queue<BinaryTreeNode<T>>();
		BinaryTreeNode<T> node = root;
		if (node != null) {
			queue.enQueue(node);
			while (!queue.isEmpty()) {
				node = queue.deQueue();
				result.add(node.data);
				if (node.left != null) {
					queue.enQueue(node.left);
				}
				if (node.right != null) {
					queue.enQueue(node.right);
				}
			}
		}
		return result;
	}

	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> p) {
		if (p == null) {
			return null;
		} else if (p.left == null) {
			return p;
		} else {
			return findMin(p.left);
		}
	}

	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> p) {
		if (p == null) {
			return null;
		} else if (p.right == null) {
			return p;
		} else {
			return findMax(p.right);
		}
	}

	private int height(BinaryTreeNode<T> t) {
		if (t == null) {
			return 0;
		} else {
			int leftChildHeight = height(t.left);
			int rightChildHeight = height(t.right);
			if (leftChildHeight > rightChildHeight) {
				return leftChildHeight + 1;
			} else {
				return rightChildHeight + 1;
			}
		}
	}

	private int size(BinaryTreeNode<T> t) {
		if (t == null) {
			return 0;
		}
		return size(t.left) + 1 + size(t.right);
	}

	private BinaryTreeNode<T> remove(T x, BinaryTreeNode<T> t) {
		if (t == null) {
			return t;
		}	

		int compareResult = x.compareTo(t.data);
		
		if (compareResult < 0) {
			t.left = remove(x, t.left);
		} else if (compareResult < 0) {
			t.right = remove(x, t.right);
		} else {
			//todo	
			if (t.left != null && t.right != null) {
				t.data = findMin(t.right).data;
				t.right = remove(t.data, t.right);
			} else {
				t = (t.left != null) ? t.left : t.right;
			}
		}
		return t;
	}

}
