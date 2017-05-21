package com.sprint.datastructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class BinaryTreeUtil {
	/**
	 * 用递归的方式实现对二叉树的前序遍历，需要通过BinaryTreeUtil测试用例
	 * @param root
	 * @return 
	 */		
	public static <T extends Comparable> List<T> preOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		preOrderVisit(root, result);
		return result;
	}

	private static <T extends Comparable> void preOrderVisit(BinaryTreeNode<T> root, List<T> values) {
		if (root != null) {
			values.add(root.getData());
			preOrderVisit(root.getLeft(), values);
			preOrderVisit(root.getRight(), values);
		}
	}

	/**
	 * 用递归的方式实现对二叉树的中序遍历，需要通过BinaryTreeUtil测试用例
	 * @param root
	 * @return 
	 */		
	public static <T extends Comparable> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		inOrderVisit(root, result);
		return result;
	}

	private static <T extends Comparable> void inOrderVisit(BinaryTreeNode<T> root, List<T> values) {
		if (root != null) {
			inOrderVisit(root.getLeft(), values);
			values.add(root.getData());
			inOrderVisit(root.getRight(), values);
		}
	}
	/**
	 * 用递归的方式实现对二叉树的后序遍历，需要通过BinaryTreeUtil测试用例
	 * @param root
	 * @return 
	 */		
	public static <T extends Comparable> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		postOrderVisit(root, result);
		return result;
	}

	private static <T extends Comparable> void postOrderVisit(BinaryTreeNode<T> root, List<T> values) {
		if (root != null) {
			postOrderVisit(root.getLeft(), values);
			postOrderVisit(root.getRight(), values);
			values.add(root.getData());
		}
	}

	/**
	 * 用非递归的方式实现二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T extends Comparable> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		if (root != null) {
			stack.push(root);
			while (!stack.isEmpty()) {
				BinaryTreeNode<T> node = (BinaryTreeNode<T>)stack.peek();
				while (node != null) {
					result.add(node.getData());
					stack.push(node.getLeft());
					node = stack.peek();
				}
				node = stack.pop();
				if (!stack.isEmpty()) {
					node = stack.pop();
					stack.push(node.getRight());
				}
			}

		}
		return result;
	}

	/**
	 * 用非递归的方式实现二叉树的中序遍历
	 * @param root
	 * @return
	 */
	public static <T extends Comparable> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		BinaryTreeNode<T> node = root;
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				stack.push(node);
				node = node.getLeft();
			} else {
				node = stack.pop();
				result.add(node.getData());
				node = node.getRight();
			}
		} 
		return result;
	}
}
