package com.coding.basic.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.coding.basic.queue.Queue;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class BinarySearchTree<T extends Comparable> {

	BinaryTreeNode<T> root;
	private int height;
	private int size = 0;

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
		this.height = trverse(root);
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public T findMin() {
		if (root == null)
			return null;
		while (root.getLeft() != null) {
			root = root.getLeft();
		}
		return root.getData();
	}

	public T findMax() {
		if (root == null)
			return null;
		while (root.getRight() != null) {
			root = root.getRight();
		}
		return root.getData();
	}

	public int height() {
		return height;
	}

	public int size() {
		return size;
	}

	public void remove(T e) {
		BinaryTreeNode<T> preNode = findPreNode(e);
		if (preNode == null)
			return;
		try {
			if (preNode.left == root)
				root = null;
			else {
				BinaryTreeNode<T> curNode = null;
				boolean isLeftChild = true;
				if (preNode.left.data.equals(e))
					curNode = preNode.left;
				else {
					curNode = preNode.right;
					isLeftChild = false;
				}
				if (curNode.left == null && curNode.right == null) {
					if (isLeftChild)
						preNode.left = null;
					else
						preNode.right = null;
					return;
				} else if (curNode.left == null || curNode.right == null) {
					if (curNode.left != null) {
						if (isLeftChild)
							preNode.left = curNode.left;
						else
							preNode.right = curNode.left;
					} else {
						if (isLeftChild)
							preNode.left = curNode.right;
						else
							preNode.right = curNode.right;
					}
					return;
				} else {
					curNode = curNode.right;
					while (curNode.left != null) {
						curNode = curNode.left;
					}
					remove(curNode.data);
					if (isLeftChild) {
						preNode.left.data = curNode.data;
					} else
						preNode.right.data = curNode.data;

				}
			}
		} finally {
			trverse(root);
		}

	}

	public List<T> levelVisit() {
		Queue<BinaryTreeNode<T>> queue = new Queue<>();
		List<T> ans = new LinkedList<>();
		queue.enQueue(root);
		BinaryTreeNode<T> curNode = null;
		while (!queue.isEmpty()) {
			curNode = queue.deQueue();
			ans.add(curNode.data);
			if (curNode.left != null)
				queue.enQueue(curNode.left);
			if (curNode.right != null)
				queue.enQueue(curNode.right);
		}
		return ans;
	}

	public boolean isValid() {
		return isValid(root);
	}

	private boolean isValid(BinaryTreeNode<T> root) {
		if (root == null)
			return true;
		boolean isSubValid = false;
		if (root.left != null) {
			BinarySearchTree<T> leftSubTree = new BinarySearchTree<>(root.left);
			if (root.data.compareTo(leftSubTree.findMax()) < 0)
				return false;
			isSubValid = isValid(root.left);
			if (!isSubValid)
				return false;
		}
		if (root.right != null) {
			BinarySearchTree<T> rightSubTree = new BinarySearchTree<>(
					root.right);
			T min = rightSubTree.findMin();
			if (root.data.compareTo(rightSubTree.findMin()) > 0)
				return false;
			isSubValid = isValid(root.right);
			if (!isSubValid)
				return false;
		}
		return true;
	}

	public T getLowestCommonAncestor(T n1, T n2) {
		List<T> n1Trace = findTrace(n1);
		List<T> n2Trace = findTrace(n2);
		T ans = null;
		Iterator<T> n1Iter = n1Trace.iterator();
		Iterator<T> n2Iter = n2Trace.iterator();
		while (n1Iter.hasNext() && n2Iter.hasNext()) {
			T cur1 = n1Iter.next();
			T cur2 = n2Iter.next();
			if (!cur1.equals(cur2))
				return ans;
			ans = cur1;
		}
		return ans;

	}

	private List<T> findTrace(T t) {
		List<T> ans = new LinkedList<>();
		BinaryTreeNode<T> cur = root;
		while (!cur.data.equals(t)) {
			ans.add(cur.data);
			if (t.compareTo(cur.data) > 0)
				cur = cur.right;
			else
				cur = cur.left;
		}
		ans.add(t);
		return ans;
	}

	private List<T> listBetweenL = null;

	public List<T> getNodesBetween(T n1, T n2) {
		listBetweenL = new LinkedList<>();
		if(n1.compareTo(n2)>0){
			T tmp=n1;
			n1=n2;
			n2=tmp;
		}
		preOrder(root, n1, n2);
		return listBetweenL;
	}

	private void preOrder(BinaryTreeNode<T> root, T min, T max) {
		if (root == null)
			return;
		if (root.data.compareTo(min) > 0 && root.data.compareTo(max) < 0)
			listBetweenL.add(root.data);
		if (root.left != null)
			preOrder(root.left, min, max);
		if (root.right != null)
			preOrder(root.right, min, max);
	}

	private int trverse(BinaryTreeNode<T> root) {
		int left = 0;
		int right = 0;
		if (root == null) {
			this.size = 0;
			return 0;
		}
		if (root.getLeft() != null) {
			left = trverse(root.getLeft());
		}
		if (root.getRight() != null) {
			right = trverse(root.getRight());
		}
		int height = 1 + (left > right ? left : right);
		this.size++;
		return height;
	}

	private BinaryTreeNode<T> findPreNode(T e) {
		if (root == null)
			return null;
		BinaryTreeNode<T> pre = null;
		if (root.data.equals(e)) {
			pre = new BinaryTreeNode<T>(null);
			pre.left = root;
			return pre;
		}
		pre = root;
		while (pre != null) {
			if (pre.data.compareTo(e) > 0) {
				if (pre.left == null)
					return null;
				if (pre.left.data.equals(e))
					return pre;
				pre = pre.left;
				continue;
			} else {
				if (pre.right == null)
					return null;
				if (pre.right.data.equals(e))
					return pre;
				pre = pre.right;
				continue;
			}
		}
		return null;
	}
}
