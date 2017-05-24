package com.donaldy.basic.tree;

import com.donaldy.basic.queue.Queue;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;

	public BinarySearchTree(BinaryTreeNode<T> root){

		this.root = root;

	}

	public BinaryTreeNode<T> getRoot(){

		return root;

	}

	public T findMin(){

		if (this.root == null) {
			throw new NullPointerException();
		}

		T minx = this.root.getData();

		List<T> list = BinaryTreeUtil.inOrderVisit(this.root);

		for (T data : list) {
			if (data.compareTo(minx) < 0) {
				minx = data;
			}
		}

		return minx;
	}

	public T findMax(){
		if (this.root == null) {
			throw new NullPointerException();
		}

		T maxn = this.root.getData();

		List<T> list = BinaryTreeUtil.inOrderVisit(this.root);

		for (T data : list) {
			if (data.compareTo(maxn) > 0) {
				maxn = data;
			}
		}

		return maxn;

	}

	public int height() {
		if (this.root == null) {
			throw new NullPointerException();
		}

		int height = treeHeight(this.root);

	    return height;
	}

	private int treeHeight(BinaryTreeNode<T> node) {

		if (node == null)
			return 0;

		return Math.max(treeHeight(node.getLeft()), treeHeight(node.getRight())) + 1;

	}

	public int size() {

		List<T> list = BinaryTreeUtil.inOrderVisit(this.root);

		return list.size();

	}

	public void remove(T e){

		removeNode(e, this.root);
		
	}

	private BinaryTreeNode removeNode(T e, BinaryTreeNode node) {
		if (this.root == null) {
			throw new NullPointerException();
		}

		//找子树
		if (e.compareTo(node.getData()) < 0) {
			node.setLeft(removeNode(e, node.getLeft()));
		}
		else if (e.compareTo(node.getData()) > 0) {
			node.setRight(removeNode(e, node.getRight()));
		}
		else {
			//删除
			if (node.getLeft() != null && node.getRight() != null) {
				BinaryTreeNode n = findMinNode(node.getRight());
				node.setData(n.getData());
				node.setRight(removeNode((T) node.getData(), node.getRight()));
			}
			else {
				BinaryTreeNode n = node;
				if (node.getLeft() == null) {
					node = node.getRight();
				}
				else if (node.getRight() == null) {
					node = node.getLeft();
				}
			}
		}
		return node;

	}

	private BinaryTreeNode findMinNode(BinaryTreeNode node) {

		if (node == null) {
			return null;
		}

		if (node.getLeft() == null) {
			return node;
		}

		return findMinNode(node.getLeft());
	}

	public List<T> levelVisit(){

		List<T> list = new ArrayList<T>();

		Queue<BinaryTreeNode> queue = new Queue<BinaryTreeNode>();

		queue.enQueue(this.root);

		while (!queue.isEmpty()) {

			BinaryTreeNode node = queue.deQueue();

			list.add((T)node.getData());

			if (node.getLeft() != null) {
				queue.enQueue(node.getLeft());
			}

			if (node.getRight() != null) {
				queue.enQueue(node.getRight());
			}

		}

		return list;
	}

	/**
	 * 是否为二叉树
	 * @return
	 */
	public boolean isValid(){

		Queue<BinaryTreeNode> queue = new Queue<BinaryTreeNode>();

		queue.enQueue(this.root);

		while (!queue.isEmpty()) {

			BinaryTreeNode node = queue.deQueue();

			T t = (T)node.getData();

			if (node.getLeft() != null) {

				if (t.compareTo(node.getLeft().getData()) <= 0) {
					return false;
				}

				queue.enQueue(node.getLeft());
			}

			if (node.getRight() != null) {

				if (t.compareTo(node.getRight().getData()) >= 0) {
					return false;
				}

				queue.enQueue(node.getRight());
			}

		}

		return true;

	}

	/**
	 * 获取两个节点的最小公共祖先
	 * 同层算同一级别？
	 * @param n1
	 * @param n2
	 * @return
	 */
	public T getLowestCommonAncestor(T n1, T n2){

		if (n1.compareTo(n2) > 0) {
			T t = n1;
			n1 = n2;
			n2 = t;
		}

		BinaryTreeNode ancestor = null;

		Queue<BinaryTreeNode> queue = new Queue<BinaryTreeNode>();

		queue.enQueue(this.root);

		while (!queue.isEmpty()) {

			BinaryTreeNode node = queue.deQueue();

			T t = (T)node.getData();

			if (t.compareTo(n1) > 0 && t.compareTo(n2) < 0) {
				ancestor = node;
			}

			if (node.getLeft() != null) {

				queue.enQueue(node.getLeft());
			}

			if (node.getRight() != null) {

				queue.enQueue(node.getRight());
			}

		}


		return (T)ancestor.getData();

	}

	/**
	 * 给定两个值， 获得处于这两个值中间的节点
	 * @param n1
	 * @param n2
	 * @return
	 */
	public List<T> getNodesBetween(T n1, T n2) {

		if (n1.compareTo(n2) > 0) {
			T t = n1;
			n1 = n2;
			n2 = t;
		}

		List<T> list = new ArrayList<T>();

		Queue<BinaryTreeNode> queue = new Queue<BinaryTreeNode>();

		queue.enQueue(this.root);

		while (!queue.isEmpty()) {

			BinaryTreeNode node = queue.deQueue();

			T t = (T)node.getData();

			if (t.compareTo(n1) > 0 && t.compareTo(n2) < 0) {
				list.add(t);
			}

			if (node.getLeft() != null) {
				queue.enQueue(node.getLeft());
			}

			if (node.getRight() != null) {
				queue.enQueue(node.getRight());
			}

		}

		return list;

	}

}

