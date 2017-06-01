package assignment0507;

import java.util.*;

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

	private static <T> void preOrderVisit(BinaryTreeNode<T> node, List<T> result) {
		if (node != null) {
			result.add(node.getData());
			preOrderVisit(node.getLeft(), result);
			preOrderVisit(node.getRight(), result);
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
		inOrderVisit(root, result);
		return result;
	}

	private static <T> void inOrderVisit(BinaryTreeNode<T> node, List<T> result) {
		if (node != null) {
			inOrderVisit(node.getLeft(),result);
			result.add(node.getData());
			inOrderVisit(node.getRight(), result);
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
		postOrderVisit(root, result);
		return result;
	}

	private static <T> void postOrderVisit(BinaryTreeNode<T> node, List<T> result) {
		if (node != null) {
			postOrderVisit(node.getLeft(), result);
			postOrderVisit(node.getRight(), result);
			result.add(node.getData());
		}
	}

	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode> stack = new Stack<>();
		Map<BinaryTreeNode, Boolean> map = new HashMap<>();
		map.put(root, false);
		stack.push(root);
		while (!stack.empty()) {
			BinaryTreeNode<T> top = stack.peek();
			boolean visited = map.get(top);
			if (visited) {
				stack.pop();
				if(top.getRight()!=null) {
					stack.push(top.getRight());
					map.put(top.getRight(), false);
				}
			}else {
				result.add(top.getData());
				map.put(top, true);
				if(top.getLeft()!=null){
					stack.push(top.getLeft());
					map.put(top.getLeft(), false);
				}
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
		Stack<BinaryTreeNode> stack = new Stack<>();
		Map<BinaryTreeNode, Boolean> map = new HashMap<>();
		map.put(root, false);
		stack.push(root);
		while (!stack.empty()) {
			BinaryTreeNode<T> top = stack.peek();
			boolean visited = map.get(top);
			if(!visited){
				map.put(top, true);
				if (top.getLeft() != null) {
					stack.push(top.getLeft());
					map.put(top.getLeft(), false);
				}
			} else {
				result.add(top.getData());
				stack.pop();
				if (top.getRight() != null) {
					stack.push(top.getRight());
					map.put(top.getRight(), false);
				}
			}

		}
		return result;
	}
	
}
