package com.coding.week11;

import com.coding.weak1.Queue;
import com.coding.week10.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {

	BinaryTreeNode<T> root;

	public BinarySearchTree() {
	}

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}
	public T findMin(){
		BinaryTreeNode<T> min = findMin(root);
		return min == null ? null : min.getData();
	}

	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
		if (node == null) {
			return null;
		}
		BinaryTreeNode<T> left = node.getLeft();
		if (left == null) {
			return node;
		} else {
			return findMin(left);
		}
	}
	public T findMax(){
		if (root == null) {
			throw new RuntimeException("has no data");
		}
		BinaryTreeNode<T> t = root.getRight();
		T data = null;
		while (t != null) {
			data = t.getData();
			t = t.getRight();
		}

		return data;
	}
	public int height() {
		return height(root);
	}

	private int height(BinaryTreeNode<T> temp) {
		if (temp == null) {
			return 0;
		}
		return 1 + Math.max(height(temp.getRight()), height(temp.getLeft()));
	}

	public int size() {
		return size(root);
	}

	public int size(BinaryTreeNode<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + size(node.getLeft()) + size(node.getRight());
	}


	public void remove(T e){
		remove(root, e);
	}

	private BinaryTreeNode<T> remove(BinaryTreeNode<T> node, T e) {
		if (node == null) {
			return node;
		}
		int compareRes = node.getData().compareTo(e);
		if (compareRes < 0) {
			node.setRight(remove(node.getRight(), e));
		} else if (compareRes > 0) {
			node.setLeft(remove(node.getLeft(), e));
		} else {
			BinaryTreeNode<T> left = node.getLeft();
			BinaryTreeNode<T> right = node.getRight();
			if (left != null && right != null) {
				T data = findMin(right).getData();
				node.setData(data);
				node.setRight(remove(right, data));
			} else {
				node = left != null ? left : right;
			}
		}
		return node;
	}

	//按层次遍历： levelVisit
	@SuppressWarnings("unchecked")
	public List<T> levelVisit(){
		List<T> list = new ArrayList<>();
		if (root == null) {
			return list;
		}
		Queue q = new Queue();
		q.enQueue(root);
		while (!q.isEmpty()) {
			BinaryTreeNode<T> n = (BinaryTreeNode<T>)q.deQueue();
			list.add(n.getData());
			if (n.getLeft() != null) {
				q.enQueue(n.getLeft());
			}
			if (n.getRight() != null) {
				q.enQueue(n.getRight());
			}
		}
		return list;
	}

	public static <T extends Comparable<T>> List<T> levelVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<>();
		BinaryTreeNode<T> left = root.getLeft();
		BinaryTreeNode<T> right = root.getRight();

		if (right != null) {
			List<T> rightList = levelVisit(right);
			result.addAll(rightList);
		}
		if (left != null) {
			List<T> leftList = levelVisit(left);
			result.addAll(leftList);
		}
		result.add(root.getData());
		return result;
	}

	//判断一个二叉树是不是二叉查找树
	public boolean isValid(){

		return isValid(root);
	}

	private boolean isValid(BinaryTreeNode<T> node) {
		boolean valid = true;
		if (node != null) {
			T d = node.getData();

			BinaryTreeNode<T> l = node.getLeft();
			BinaryTreeNode<T> r = node.getRight();
			if (l != null) {
				if (d.compareTo(l.getData()) < 0) {
					return false;
				}
			}
			if (r != null) {
				if (d.compareTo(r.getData()) >= 0) {
					return false;
				}
			}
			valid = isValid(l) && isValid(r);
		}
		return valid;
	}


	//获取两个节点的最小公共祖先
	public T getLowestCommonAncestor(T n1, T n2){
		return betweenNode(n1, n2, root).getData();
	}

	private BinaryTreeNode<T> betweenNode(T n1, T n2, BinaryTreeNode<T> node) {
		if (node == null) {
			return null;
		} else {
			T max = n1.compareTo(n2) >= 0 ? n1 : n2;
			T min = max.compareTo(n1) == 0 ? n2 : n1;
			if (max.compareTo(node.getData()) < 0) {
				node = betweenNode(n1, n2, node.getLeft());
			} else if (min.compareTo(node.getData()) > 0) {
				node = betweenNode(n1, n2, node.getRight());
			}
			return node;
		}

	}



	public void insert(List<T> l) {
		for (T data : l) {
			if (data == null) {
				continue;
			}
			if (root == null) {
				root = new BinaryTreeNode<>(data);
			} else {
				root.insert(data);
			}
		}
	}

	//给定两个值， 获得处于这两个值中间的节点
	public List<T> getNodesBetween(T n1, T n2){
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()){
			BinaryTreeNode<T> node = stack.pop();
			if (isBetween(n1, n2, node.getData())) {
				result.add(node.getData());
			}
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}

		}

		return result;
	}

	private boolean isBetween(T n1, T n2, T data) {
		T max = n1.compareTo(n2) >= 0 ? n1 : n2;
		T min = max.compareTo(n1) == 0 ? n2 : n1;
		if (max.compareTo(data) < 0 || min.compareTo(data) > 0) {
			return false;
		} else {
			return true;
		}
	}


	public static void main(String[] args) {
		System.out.println(Math.max(0,0));
	}
}

