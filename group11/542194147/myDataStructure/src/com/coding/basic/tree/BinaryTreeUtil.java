package com.coding.basic.tree;

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
		result.add(root.getData());
		if(root.getLeft()!=null){
			preOrderVisit(root.getLeft());
		}
		if(root.getRight()!=null){
			preOrderVisit(root.getRight());
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
		
		return result;
	}
}
