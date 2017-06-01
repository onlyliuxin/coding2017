package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;

import com.coding.basic.queue.Queue;

public class BinarySearchTree<T extends Comparable<? super T>> {

	BinaryTreeNode<T> root;

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public T findMin() {

		BinaryTreeNode<T> temp = root;
		if (temp == null) {
			return null;
		}
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp.data;
	}

	public T findMax() {

		BinaryTreeNode<T> temp = root;
		if (temp == null) {
			return null;
		}
		while (temp.right != null) {
			temp = temp.right;
		}
		return temp.data;
	}

	public int height() {

		return maxDepth(this.root);
	}

	private int maxDepth(BinaryTreeNode<T> root) {

		if (root == null)
			return 0;
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);

		return leftDepth > rightDepth ? (leftDepth + 1) : (rightDepth + 1);
	}

	public int size() {

		List<T> dataList = BinaryTreeUtil.preOrderVisit(root);

		return dataList.size();
	}

	public void remove(T e) {

		remove(this.root, e);
	}

	private void remove(BinaryTreeNode<T> root, T value) {

		// 获取将要删除的节点
		BinaryTreeNode<T> node = BinaryTreeUtil.findNode(root, value);
		if (node == null) {
			return;
		}

		// 获取当前节点的父节点以及在父节点中的位置
		BinaryTreeNode<T> parent = BinaryTreeUtil.findParentNode(root, value);
		if (parent == null) {
			return;
		}
		// position记录当前节点在父节点中的位置，如果为true为左节点，false又节点
		boolean position = true;
		if (node.equals(parent.right)) {
			position = false;
		}

		// 当前节点为叶子节点直接删除
		if (node.left == null && node.right == null) {
			assignmentValue(null, parent, position);
		}

		// 当前节点只有一个叶子节点
		if ((node.left != null && node.right == null)) {
			assignmentValue(node.left, parent, position);
		}

		if ((node.right != null && node.left == null)) {
			assignmentValue(node.right, parent, position);
		}

		// 当前节点有两个子节点
		if (node.left != null && node.right != null) {

			// 获取右节点中值最小的节点
			T minValue = new BinarySearchTree<T>(node.right).findMin();
			if (position) {
				parent.left.data = minValue;
			} else {
				parent.right.data = minValue;
			}
			// 删除该节点值为minValue的节点
			remove(node.left, minValue);
		}
	}

	/**
	 * 为删除元素所在的节点赋值
	 * 
	 * @param node
	 * @param parent
	 * @param position
	 */
	private void assignmentValue(BinaryTreeNode<T> node,
			BinaryTreeNode<T> parent, boolean position) {
		if (position) {
			parent.left = node;
		} else {
			parent.right = node;
		}
	}

	/**
	 * @Title: levelVisit
	 * @Description: 按层次便利
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	public List<T> levelVisit() {

		List<T> datas = new ArrayList<T>();
		Queue<BinaryTreeNode<T>> nodeQueue = new Queue<>();
		nodeQueue.enQueue(root);
		do {

			BinaryTreeNode<T> node = nodeQueue.deQueue();
			datas.add(node.data);
			if (node.left != null) {
				nodeQueue.enQueue(node.left);
			}
			if (node.right != null) {
				nodeQueue.enQueue(node.right);
			}
		} while (!nodeQueue.isEmpty());

		return datas;
	}

	public boolean isValid() {

		// 中序遍历二叉树
		List<T> datas = BinaryTreeUtil.inOrderVisit(root);
		if (datas.isEmpty()) {
			return false;
		}
		// 判断中序遍历结果是否为递增数列
		for (int i = 0; i < datas.size() - 1; i++) {
			if (datas.get(i).compareTo(datas.get(i + 1)) >= 0) {
				return false;
			}
		}
		return true;
	}

	public T getLowestCommonAncestor(T n1, T n2) {
		
		// 获取值为n1的节点的所有父节点
		List<BinaryTreeNode<T>> n1List = BinaryTreeUtil.findParentNodes(root,
				n1);
		// 获取值为n2的节点的所有父节点
		List<BinaryTreeNode<T>> n2List = BinaryTreeUtil.findParentNodes(root,
				n2);
		if(n1List==null||n2List==null){
			return null;
		}
		// 取交集
		n1List.retainAll(n2List);
		if (n1List == null || n1List.isEmpty()) {
			return null;
		}
		return n1List.get(0).data;

	}

	public List<T> getNodesBetween(T n1, T n2) {

		// 获取值为n1的节点的所有父节点
		List<BinaryTreeNode<T>> n1List = BinaryTreeUtil.findParentNodes(root,
				n1);
		List<BinaryTreeNode<T>> n1Temp= new ArrayList<BinaryTreeNode<T>>();
		n1Temp.addAll(n1List);
		
		// 获取值为n2的节点的所有父节点
		List<BinaryTreeNode<T>> n2List = BinaryTreeUtil.findParentNodes(root,
				n2);
		List<BinaryTreeNode<T>> n2Temp = new ArrayList<BinaryTreeNode<T>>();
		n2Temp.addAll(n2List);
		
		if(n1List==null||n2List==null){
			return null;
		}
		// 取交集
		n1List.retainAll(n2List);
		//取并集
		n2Temp.removeAll(n1Temp);
		n1Temp.addAll(n2Temp);
		
		for (int i = n1List.size()-1; i >0; i--) {
			n1Temp.remove(n1List.get(i));
		}

		List<T> datas = new ArrayList<T>();
		for (BinaryTreeNode<T> binaryTreeNode : n1Temp) {
			datas.add(binaryTreeNode.data);
		}
		return datas;
	}

}
