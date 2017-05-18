package com.coding.week10;

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
		BinaryTreeNode<T> left = root.getLeft();
		BinaryTreeNode<T> right = root.getRight();
		result.add(root.getData());
		if (left != null) {
			List<T> leftList = preOrderVisit(left);
			result.addAll(leftList);
		}
		if (right != null) {
			List<T> rightList = preOrderVisit(right);
			result.addAll(rightList);
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
		BinaryTreeNode<T> left = root.getLeft();
		BinaryTreeNode<T> right = root.getRight();

		if (left != null) {
			List<T> leftList = inOrderVisit(left);
			result.addAll(leftList);
		}
		result.add(root.getData());
		if (right != null) {
			List<T> rightList = inOrderVisit(right);
			result.addAll(rightList);
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
		BinaryTreeNode<T> left = root.getLeft();
		BinaryTreeNode<T> right = root.getRight();

		if (left != null) {
			List<T> leftList = postOrderVisit(left);
			result.addAll(leftList);
		}

		if (right != null) {
			List<T> rightList = postOrderVisit(right);
			result.addAll(rightList);
		}
		result.add(root.getData());
		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()){
			BinaryTreeNode<T> node = stack.pop();
			result.add(node.getData());
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}

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
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		BinaryTreeNode<T> p = root;
		while (p != null) {
			while (p != null) {
				if (p.getRight() != null)
					stack.push(p.getRight());// 当前节点右子入栈
				stack.push(p);// 当前节点入栈
				p = p.getLeft();
			}
			p = stack.pop();
			while (!stack.empty() && p.getRight() == null) {
				result.add(p.getData());
				p = stack.pop();
			}
			result.add(p.getData());
			if (!stack.empty())
				p = stack.pop();
			else
				p = null;
		}
		return result;
	}
	
}
