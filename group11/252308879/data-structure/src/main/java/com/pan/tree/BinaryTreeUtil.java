package com.pan.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeUtil {
	/**
	 * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		
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
		if (root != null){
			List<T> leftResult = inOrderVisit(root.getLeft());
			result.add(root.getData());
//			result = leftResult;
			List<T> rightResult = inOrderVisit(root.getRight());
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

		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();		
		
		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		if (root == null){
			return result;
		}
		// 没有子节点
		if (root.getLeft() == null && root.getRight() == null){
			result.add(root.getData());
			return result;
		}

		BinaryTreeNode<T> leftDepth1 = root.getLeft();
		BinaryTreeNode<T> leftDepthMax = leftDepth1.getLeft();
//		int leftDe
		while (leftDepthMax != null){
			leftDepthMax = leftDepthMax.getLeft();
		}



		BinaryTreeNode<T> rightDepth1 = root.getRight();


		return result;
	}
	
}
