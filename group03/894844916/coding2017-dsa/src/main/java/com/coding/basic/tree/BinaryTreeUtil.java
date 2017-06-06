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
		if (root == null) {
			return null;
		}
		List<T> result = new ArrayList<T>();
		result.add(root.getData());
		if (root.getLeft() != null) {
			result.addAll(preOrderVisit(root.getLeft()));
		}
		if (root.getRight() != null) {
			result.addAll(preOrderVisit(root.getRight()));
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
		if (root == null) {
			return null;
		}
		List<T> result = new ArrayList<T>();
		if (root.getLeft() != null) {
			result.addAll(preOrderVisit(root.getLeft()));
		}
		result.add(root.getData());
		if (root.getRight() != null) {
			result.addAll(preOrderVisit(root.getRight()));
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
		if (root == null) {
			return null;
		}
		List<T> result = new ArrayList<T>();
		if (root.getLeft() != null) {
			result.addAll(preOrderVisit(root.getLeft()));
		}
		if (root.getRight() != null) {
			result.addAll(preOrderVisit(root.getRight()));
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
		if (root == null) {
			return null;
		}
		BinaryTreeNode<T> currentNode=root;
		Stack<BinaryTreeNode<T>> stack=new Stack<>();
		List<T> result = new ArrayList<T>();
		while (currentNode!=null) {
			while (currentNode!=null) {
				result.add(currentNode.getData());
				stack.push(currentNode);
				currentNode=currentNode.getLeft();
			}
			if (!stack.isEmpty()) {
				currentNode=stack.pop().getRight();
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
		if (root == null) {
			return null;
		}
		BinaryTreeNode<T> currentNode=root;
		Stack<BinaryTreeNode<T>> stack=new Stack<>();
		List<T> result = new ArrayList<T>();
		while (currentNode!=null) {
			while (currentNode!=null) {
				stack.push(currentNode);
				currentNode=currentNode.getLeft();
			}
			if (!stack.isEmpty()) {
				currentNode=stack.pop();
				result.add(currentNode.getData());
				currentNode=currentNode.getRight();
			}
		}
		return result;
	}

}
