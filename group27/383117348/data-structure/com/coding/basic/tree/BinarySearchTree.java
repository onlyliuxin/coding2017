package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

import com.coding.basic.stack.Stack;

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
			throw new NoSuchElementException("根节点为空");
		}

		return findMin(root);
	}

	private T findMin(BinaryTreeNode<T> root) {
		BinaryTreeNode<T> p = root;
		while (p.left != null) {
			p = p.left;
		}
		return p.getData();
	}

	public T findMax() {
		if (root == null) {
			throw new NoSuchElementException("根节点为空");
		}
		return findMax(root);
	}

	private T findMax(BinaryTreeNode<T> root) {
		BinaryTreeNode<T> p = root;
		while (p.right != null) {
			p = p.right;
		}
		return p.getData();
	}

	public int height() {
		if (root == null) {
			return 0;
		}
		return getHeight(root);
	}

	private int getHeight(BinaryTreeNode<T> root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);
		return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
	}

	public int size() {
		if (root == null) {
			return 0;
		}
		return getSize(root);
	}

	private int getSize(BinaryTreeNode<T> root) {
		if (root == null) {
			return 0;
		}
		int leftSize = getSize(root.left);
		int rightSize = getSize(root.right);
		return 1 + leftSize + rightSize;
	}

	public void remove(T e) {
		if (root == null) {
			throw new NoSuchElementException("根节点为空");
		}
		remove(e, root);
	}

	private BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> root) {
		if (root == null) {
			throw new NoSuchElementException("不存在该元素，删除失败");
		}
		if (root.data.compareTo(e) == 0) {
			if (root.left == null && root.right == null) {
				return null;
			} else if (root.right == null && root.left != null) {
				return root.left;
			} else if (root.right != null && root.left == null) {
				return root.right;
			} else {
				BinaryTreeNode<T> minParent = findMinParent(root.right);
				root.data = minParent.left.data;
				minParent.left = null;
				return root;
			}
		} else if (root.data.compareTo(e) < 0) {
			root.right = remove(e, root.right);
			return root;
		} else {
			root.left = remove(e, root.left);
			return root;
		}
	}

	private BinaryTreeNode<T> findMinParent(BinaryTreeNode<T> root) {
		BinaryTreeNode<T> p = root;
		BinaryTreeNode<T> parent = null;
		while (p.left != null) {
			parent = p;
			p = p.left;
		}
		return parent;
	}

	public List<T> levelVisit() {
		Queue q = new LinkedList();
		List<T> result = new ArrayList<T>();
		result.add(root.data);
		q.add(root.getLeft());
		q.add(root.getRight());
		while (q.size() != 0) {
			BinaryTreeNode<T> node = (BinaryTreeNode<T>) q.poll();
			result.add(node.data);
			if (node != null && node.left != null) {
				q.add(node.getLeft());
			}
			if (node != null && node.right != null) {
				q.add(node.getRight());
			}
		}
		return result;
	}

	public boolean isValid() {
		return isValid(root, Integer.parseInt(root.data.toString()));
	}

	public boolean isValid(BinaryTreeNode<T> node, int d) {
		boolean f = false;
		if (node == null) {
			return false;
		}
		if (Integer.parseInt(node.data.toString()) <= d) {
			f = true;
			d = Integer.parseInt(node.data.toString());
			isValid(node.left, d);
		} else {
			f = false;
		}

		if (Integer.parseInt(node.data.toString()) >= d) {
			f = true;
			d = Integer.parseInt(node.data.toString());
			isValid(node.right, d);
		} else {
			f = false;
		}

		return f;
	}

	public T getLowestCommonAncestor(T n1, T n2) {
		return getLowestCommonAncestor(root, n1, n2);

	}

	public T getLowestCommonAncestor(BinaryTreeNode<T> n, T n1, T n2) {
		if (root == null)
			return null;
		if ((Integer.parseInt(root.data.toString()) >= (Integer) n1
				&& Integer.parseInt(root.data.toString()) <= (Integer) n2)
				|| (Integer.parseInt(root.data.toString()) >= (Integer) n1
						&& Integer.parseInt(root.data.toString()) <= (Integer) n2))
			return (T) root.data.toString();
		else if (Integer.parseInt(root.data.toString()) > (Integer) n1
				&& Integer.parseInt(root.data.toString()) > (Integer) n2)
			return getLowestCommonAncestor(root.left, n1, n2);
		else if (Integer.parseInt(root.data.toString()) < (Integer) n1
				&& Integer.parseInt(root.data.toString()) < (Integer) n2)
			return getLowestCommonAncestor(root.right, n1, n2);
		return null;

	}

	public List<T> getNodesBetween(T n1, T n2) {
		List<T> result = new ArrayList<T>();
		List<T> seq = new ArrayList<T>();
		result.add((T) root.getData());
		BinaryTreeNode<T> node = root;
		getNodesBetweenProcess(node, result, seq, n1, n2);
		node.setLeft(root.getRight());
		getNodesBetweenProcess(node, result, seq, n1, n2);
		return seq;
	}

	public List<T> getNodesBetweenProcess(BinaryTreeNode<T> node, List<T> result, List<T> seq, T n1, T n2) {
		Stack s = new Stack();
		while (true) {
			if (node.getLeft() != null) {
				if (Integer.parseInt(node.getLeft().getData().toString()) >= Integer.parseInt(n1.toString())
						&& Integer.parseInt(node.getLeft().getData().toString()) <= Integer.parseInt(n2.toString())) {
					seq.add((T) node.getLeft().getData());
				}
				s.push(node.getLeft());
				node = node.getLeft();
			} else {
				node = (BinaryTreeNode<T>) s.pop();
				node.setLeft(node.getRight());
			}
			if (node.getLeft() == null && s.size() == 0)
				break;
		}
		return seq;
	}
}