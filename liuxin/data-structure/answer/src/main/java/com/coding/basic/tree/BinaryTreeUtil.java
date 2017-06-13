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

	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();		
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		
		BinaryTreeNode<T> node = root;
		
		if(node != null){
			stack.push(node);
		}
		
		while(!stack.isEmpty()){
			node = stack.pop();
			result.add(node.data);
			
			if(node.right != null){
				stack.push(node.right);
			}
			
			if(node.left != null){
				stack.push(node.right);
			}			
		}
		return result;
	}

	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		BinaryTreeNode<T> node = root;
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();

		while (node != null || !stack.isEmpty()) {

			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			BinaryTreeNode<T> currentNode = stack.pop();
			result.add(currentNode.data);			
			node = currentNode.right;
		}
		return result;
	}
	
	private static <T> void preOrderVisit(BinaryTreeNode<T> node, List<T> result) {
		if (node == null) {
			return;
		}
		result.add(node.getData());
		preOrderVisit(node.getLeft(), result);
		preOrderVisit(node.getRight(), result);
	}

	private static <T> void inOrderVisit(BinaryTreeNode<T> node, List<T> result) {
		if (node == null) {
			return;
		}
		inOrderVisit(node.getLeft(), result);
		result.add(node.getData());
		inOrderVisit(node.getRight(), result);
	}

	private static <T> void postOrderVisit(BinaryTreeNode<T> node, List<T> result) {
		if (node == null) {
			return;
		}
		postOrderVisit(node.getLeft(), result);
		postOrderVisit(node.getRight(), result);
		result.add(node.getData());
	}

}
