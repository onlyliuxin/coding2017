package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeUtil {
	/**
	 * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		preOrderTraversal(root, result);
		return result;
	}

	private static <T> List<T> preOrderTraversal(BinaryTreeNode<T> node,
			List<T> datas) {

		if (node != null) {
			datas.add(node.getData());
			preOrderTraversal(node.getLeft(), datas);
			preOrderTraversal(node.getRight(), datas);
		}
		return datas;
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		inOrderTraversal(root, result);
		return result;
	}

	private static <T> List<T> inOrderTraversal(BinaryTreeNode<T> node,
			List<T> datas) {

		if (node != null) {
			inOrderTraversal(node.getLeft(), datas);
			datas.add(node.getData());
			inOrderTraversal(node.getRight(), datas);
		}
		return datas;
	}

	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		postOrderTraversal(root, result);
		return result;
	}

	private static <T> List<T> postOrderTraversal(BinaryTreeNode<T> node,
			List<T> datas) {

		if (node != null) {
			postOrderTraversal(node.getLeft(), datas);
			postOrderTraversal(node.getRight(), datas);
			datas.add(node.getData());
		}
		return datas;
	}

	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {

		List<T> result = new ArrayList<T>();

		if (root == null) {
			return result;
		}

		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		stack.push(root);

		while (!stack.isEmpty()) {
			BinaryTreeNode<T> node = stack.pop();
			result.add(node.getData());
			if (node.getRight() != null)
				stack.push(node.getRight());
			if (node.getLeft() != null)
				stack.push(node.getLeft());
		}

		return result;
	}

	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {

		List<T> result = new ArrayList<T>();

		if (root == null) {
			return result;
		}

		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();

		do {
			// 依次将左节点均加入栈中
			while (root != null) {
				stack.push(root);
				root = root.getLeft();
			}
			if (!stack.isEmpty()) {
				root = stack.pop();
				result.add(root.getData());
				root = root.getRight();
			}
		} while (!stack.isEmpty() || root != null);

		return result;
	}

	/**
	 * 获取指定内容的节点
	 * 
	 * @param root
	 * @param data
	 * @return
	 */
	public static <T> BinaryTreeNode<T> findNode(BinaryTreeNode<T> root, T data) {

		if (root == null || data == null) {
			return null;
		}
		if (data.equals(root.data)) {
			return root;
		}

		BinaryTreeNode<T> parent = findParent(root, data);
		if (parent == null) {
			return parent;
		}
		if (parent.right != null && data.equals(parent.right.data)) {
			return parent.right;
		} else {
			return parent.left;
		}
	}

	/**
	 * 获取指定内容节点的最近父节点
	 * 
	 * @param root
	 * @param data
	 * @return
	 */
	public static <T> BinaryTreeNode<T> findParentNode(BinaryTreeNode<T> root,
			T data) {
		return findParent(root, data);
	}

	/**
	 * @Title: findParentNodes
	 * @Description: 获取指定内容节点的所有父节点
	 * @param @param root
	 * @param @param data
	 * @param @return
	 * @return List<BinaryTreeNode<T>>
	 * @throws
	 */
	public static <T> List<BinaryTreeNode<T>> findParentNodes(
			BinaryTreeNode<T> root, T data) {

		BinaryTreeNode<T> temp = findParent(root, data);
		List<BinaryTreeNode<T>> parents = new ArrayList<BinaryTreeNode<T>>();

		while (temp != null) {
			parents.add(temp);
			temp = findParent(root, temp.data);
		}
		return parents;
	}

	private static <T> BinaryTreeNode<T> findParent(BinaryTreeNode<T> root,
			T data) {

		if (root == null || data == null || data.equals(root.data)) {
			return null;
		}

		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		stack.push(root);

		while (!stack.isEmpty()) {
			BinaryTreeNode<T> node = stack.pop();
			if (node.getRight() != null) {
				if (data.equals(node.getRight().data)) {
					return node;
				}
				stack.push(node.getRight());
			}
			if (node.getLeft() != null) {
				if (data.equals(node.left.data)) {
					return node;
				}
				stack.push(node.getLeft());
			}

		}

		return null;
	}

}
