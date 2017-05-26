package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeUtil {
	/**
	 * 用 递归 的方式实现对二叉树的 前序 遍历， 需要通过BinaryTreeUtilTest测试
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		
		if(root == null || root.getData() == null){
			return result;
		}
		
		result.add(root.getData());
		result.addAll(preOrderVisit(root.getLeft()));
		result.addAll(preOrderVisit(root.getRight()));
		
		return result;
	}

	/**
	 * 用 递归 的方式实现对二叉树的 中序 遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		
		if(root == null || root.getData() == null){
			return result;
		}
		
		result.addAll(inOrderVisit(root.getLeft()));
		result.add(root.getData());
		result.addAll(inOrderVisit(root.getRight()));
		
		return result;
	}

	/**
	 * 用 递归 的方式实现对二叉树的 后序 遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		
		if(root == null || root.getData() == null){
			return result;
		}
		
		result.addAll(postOrderVisit(root.getLeft()));
		result.addAll(postOrderVisit(root.getRight()));
		result.add(root.getData());
		
		return result;
	}
	
	/**
	 * 用 非递归 的方式实现对二叉树的 前序 遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();		
		
		if(root == null || root.getData() == null){
			return result;
		}
		
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		stack.push(root);
		while(!stack.isEmpty()){
			BinaryTreeNode<T> temp = stack.pop();
			result.add(temp.getData());
			
			if(temp.getRight() != null){
				stack.push(temp.getRight());
			}
			
			if(temp.getLeft() != null){
				stack.push(temp.getLeft());
			}
		}
		
		return result;
	}
	
	/**
	 * 用 非递归 的方式实现对二叉树的 中序 遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		
		if(root == null || root.getData() == null){
			return result;
		}
		
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		while(root != null){
			stack.push(root);
			root = root.getLeft();
		}
		
		while(!stack.isEmpty()){
			BinaryTreeNode<T> temp = stack.pop();
			result.add(temp.getData());
			
			temp = temp.getRight();
			while(temp != null){
				stack.push(temp);
				temp = temp.getLeft();
			}
		}
		
		return result;
	}
	
}
