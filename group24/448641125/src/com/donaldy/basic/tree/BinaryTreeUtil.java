package com.donaldy.basic.tree;

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

		preOrder(root, result);
		
		return result;
	}

	private static void preOrder(BinaryTreeNode root, List result) {

		if (root == null) {
			return ;
		}

		result.add(root.getData());

		preOrder(root.getLeft(), result);

		preOrder(root.getRight(), result);

	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();

		inOrder(root, result);

		return result;
	}

	private static void inOrder(BinaryTreeNode root, List result) {

		if (root == null) {
			return ;
		}

		inOrder(root.getLeft(), result);

		result.add(root.getData());

		inOrder(root.getRight(), result);

	}

	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();

		postOrder(root, result);
		
		return result;
	}


	private static void postOrder(BinaryTreeNode root, List result) {

		if (root == null) {
			return ;
		}

		postOrder(root.getLeft(), result);

		postOrder(root.getRight(), result);

		result.add(root.getData());

	}

	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();

		Stack<BinaryTreeNode> treeStack = new Stack<>();

		while (root != null || !treeStack.isEmpty()) {

			while (root != null) {

				result.add(root.getData());

				treeStack.push(root);

				root = root.getLeft();
			}

			root = treeStack.pop();

			root = root.getRight();


		}
		
		return result;
	}


	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();

		Stack<BinaryTreeNode> treeStack = new Stack<>();

		while (root != null || !treeStack.isEmpty()) {

			while (root != null) {

				treeStack.push(root);

				root = root.getLeft();

			}

			root = treeStack.pop();

			result.add(root.getData());

			root = root.getRight();

		}

		return result;
	}
	
}
