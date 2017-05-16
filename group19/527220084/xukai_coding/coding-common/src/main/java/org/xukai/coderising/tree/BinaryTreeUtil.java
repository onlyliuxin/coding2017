package org.xukai.coderising.tree;

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
		if (root == null) {
			return result;
		}
		preVisitNode(root, result);
		return result;
	}

	private static <T> void preVisitNode(BinaryTreeNode<T> node, List<T> result) {
		result.add(node.getData());
		if (node.getLeft() != null) {
			preVisitNode(node.getLeft(), result);
		}
		if (node.getRight() != null) {
			preVisitNode(node.getRight(), result);
		}
	}


	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		if (root == null) {
			return result;
		}
		inVisitNode(root, result);
		return result;
	}

	private static <T> void inVisitNode(BinaryTreeNode<T> node, List<T> result) {
		if (node.getLeft() != null) {
			inVisitNode(node.getLeft(), result);
		}
		result.add(node.getData());
		if (node.getRight() != null) {
			inVisitNode(node.getRight(), result);
		}
	}


	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		if (root == null) {
			return result;
		}
		postVisitNode(root, result);
		return result;
	}

	private static <T> void postVisitNode(BinaryTreeNode<T> node, List<T> result) {
		if (node.getLeft() != null) {
			postVisitNode(node.getLeft(), result);
		}
		if (node.getRight() != null) {
			postVisitNode(node.getRight(), result);
		}
		result.add(node.getData());
	}
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		if (root == null) {
			return result;
		}

		Stack<BinaryTreeNode> stack = new Stack<>();
		stack.push(root);
		do {

			result.add(root.getData());
			if (root.getLeft() != null) {
				stack.push(root);
				root = root.getLeft();
				continue;
			}
			root = stack.pop();
			if (root.getRight() != null) {
				root = root.getRight();
			}

		} while (!stack.empty());

		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		if (root == null) {
			return result;
		}

		Stack<BinaryTreeNode> stack = new Stack<>();
		stack.push(root);
		do {


			if (root.getLeft() != null) {
				stack.push(root);
				root = root.getLeft();
				continue;
			}
			result.add(root.getData());
			root = stack.pop();
			if (stack.isEmpty()) {
				break;
			}
			result.add(root.getData());
			if (root.getRight() != null) {
				root = root.getRight();
			}

		} while (!stack.isEmpty());

		return result;
	}
	
}
