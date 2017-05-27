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
		preOrderVisit(root, result);
		return result;
	}

	public static <T> List<T> preOrderVisit(BinaryTreeNode<T> node, List<T> result) {
		if (node == null) {
			return null;
		}
		result.add(node.getData());
		preOrderVisit(node.getLeft(), result);
		preOrderVisit(node.getRight(), result);
		return result;
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();

		inOrderVisit(root, result);
		return result;
	}

	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> node, List<T> result) {
		if (node == null) {
			return null;
		}
		inOrderVisit(node.getLeft(), result);
		result.add(node.getData());
		inOrderVisit(node.getRight(), result);
		return result;
	}

	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();

		postOrderVisit(root, result);
		return result;
	}

	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> node, List<T> result) {
		if (node == null) {
			return null;
		}
		postOrderVisit(node.getLeft(), result);
		inOrderVisit(node.getRight(), result);
		result.add(node.getData());
		return result;
	}

	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {

		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		while (root != null || stack.size() > 0) {  //将所有左孩子压栈
			if (root != null) {   //压栈之前先访问
				//printNode(node);
				result.add(root.getData());
				stack.push(root);
				root = root.getLeft();
			} else {
				root = stack.pop();
				root = root.getRight();
			}
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
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		while (root != null || stack.size() > 0) {
			if (root != null) {
				stack.push(root);   //直接压栈
				root = root.getLeft();
			} else {
				root = stack.pop(); //出栈并访问
				result.add(root.getData());
				root = root.getRight();
			}
		}
		return result;
	}

}
