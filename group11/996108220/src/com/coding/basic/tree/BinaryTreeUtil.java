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
		BinaryTreeUtil.preOrderVisit(root,result);
		return result;
	}
	private static <T> void preOrderVisit(BinaryTreeNode<T> node,List<T> result) {
		if (node==null) {
			return;
		}
		result.add(node.data);
		preOrderVisit(node.left, result);
		preOrderVisit(node.right, result);
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		BinaryTreeUtil.inOrderVisit(root,result);
		return result;
	}
	private static <T> void inOrderVisit(BinaryTreeNode<T> node,List<T> result) {
		if (node==null) {
			return;
		}
		inOrderVisit(node.left, result);
		result.add(node.data);
		inOrderVisit(node.right, result);
	}

	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		BinaryTreeUtil.postOrderVisit(root,result);
		return result;
	}
	private static <T> void postOrderVisit(BinaryTreeNode<T> node,List<T> result) {
		if (node==null) {
			return;
		}
		postOrderVisit(node.left, result);
		postOrderVisit(node.right, result);
		result.add(node.data);	
	}
	/**.0
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
				
		List<T> result = new ArrayList<T>();
		BinaryTreeNode<T> node=root;
		Stack<BinaryTreeNode<T>> stack=new Stack<>();
		do {
			result.add(node.data);
			if (node.right!=null) {
				stack.push(node.right);
			}
			if (!stack.isEmpty()) {
				node=node.left==null?stack.pop():node.left;
			}
			else {
				node=node.left;
			}
		} while (node!=null);
		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> stack=new Stack<>();
		BinaryTreeNode<T> node=root;
		
		while (node!=null) {
			stack.push(node);
			node=node.left;	
		}
		while (!stack.isEmpty()) {
			node=stack.pop();
			result.add(node.data);
			if(node.right!=null) {
				node=node.right;
				while (node!=null) {
					stack.push(node);
					node=node.left;	
				}	
			}
		}
		return result;
			
	
	}
	
}
