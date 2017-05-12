package tree;

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

		if (root != null) {
			preOrder(result, root);
		}

		return result;
	}

	private static <T> void preOrder(List<T> result, BinaryTreeNode<T> root) {

		if (root != null) {
			result.add(root.getData());
			preOrder(result, root.getLeft());
			preOrder(result, root.getRight());
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

		if (root != null) {
			inOrder(result, root);
		}

		return result;
	}

	private static <T> void inOrder(List<T> result, BinaryTreeNode<T> root) {

		if (root != null) {

			inOrder(result, root.getLeft());
			result.add(root.getData());
			inOrder(result, root.getRight());
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

		if (root != null) {
			postOrder(result, root);
		}

		return result;

	}

	private static <T> void postOrder(List<T> result, BinaryTreeNode<T> root) {

		if (root != null) {

			postOrder(result, root.getLeft());
			postOrder(result, root.getRight());
			result.add(root.getData());

		}
	}

	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {

		List<T> result = new ArrayList<T>();

		BinaryTreeNode<T> node = root;

		if (node == null) {
			return result;
		}

		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();

		result.add(node.getData());
		stack.push(node);

		while (node.getLeft() != null) {
			node = node.getLeft();
			result.add(node.getData());
			stack.push(node);
		}

		while (!stack.isEmpty()) {
			node = stack.pop();
			if (node.getRight() != null) {
				node = node.getRight();
				result.add(node.getData());
				stack.push(node);
				while (node.getLeft() != null) {
					node = node.getLeft();
					result.add(node.getData());
					stack.push(node);
				}
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

		BinaryTreeNode<T> node = root;

		if (node == null) {
			return result;
		}

		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();

		stack.push(node);

		while (node.getLeft() != null) {
			node = node.getLeft();
			stack.push(node);
		}

		while (!stack.isEmpty()) {
			node = stack.pop();
			result.add(node.getData());
			if (node.getRight() != null) {
				node = node.getRight();
				stack.push(node);
				while (node.getLeft() != null) {
					node = node.getLeft();
					stack.push(node);
				}
			}
		}

		return result;
	}

}
