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
		if (root == null)
			return result;
		result.add(root.getData());
		if (root.getLeft() != null) {
			List<T> lefts = preOrderVisit(root.getLeft());
			for (T t : lefts) {
				result.add(t);
			}
		}
		if (root.getRight() != null) {
			List<T> rights = preOrderVisit(root.getRight());
			for (T t : rights) {
				result.add(t);
			}
		}
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
		if (root == null)
			return result;

		if (root.getLeft() != null) {
			List<T> lefts = inOrderVisit(root.getLeft());
			for (T t : lefts) {
				result.add(t);
			}
		}
		result.add(root.getData());
		if (root.getRight() != null) {
			List<T> rights = inOrderVisit(root.getRight());
			for (T t : rights) {
				result.add(t);
			}
		}
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
		if (root == null)
			return result;

		if (root.getLeft() != null) {
			List<T> lefts = postOrderVisit(root.getLeft());
			for (T t : lefts) {
				result.add(t);
			}
		}

		if (root.getRight() != null) {
			List<T> rights = postOrderVisit(root.getRight());
			for (T t : rights) {
				result.add(t);
			}
		}
		result.add(root.getData());
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
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		if (root == null)
			return result;
		while (!stack.isEmpty()||root!=null) {
			while (root != null) {
				stack.push(root);
				result.add(root.getData());
				root = root.getLeft();
			}
			root = stack.pop();
			root = root.getRight();
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
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		if (root == null)
			return result;
		while (!stack.isEmpty()||root!=null) {
			while (root != null) {
				stack.push(root);
				root = root.getLeft();
			}
			root = stack.pop();
			result.add(root.getData());
			root = root.getRight();
		}

		return result;
	}

}
