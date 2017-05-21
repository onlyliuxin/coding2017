package com.github.miniyk2012.coding2017.basic.tree;


import java.util.*;

public class BinaryTreeUtil {
	/**
	 * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
	 * 先访问root，再访问左子树，再访问右子树
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<>();
		if (root == null) {
		    return result;
        }
        result.add(root.getData());
        BinaryTreeNode<T> leftTreeNode = root.getLeft();
        result.addAll(preOrderVisit(leftTreeNode));
        BinaryTreeNode<T> rightTreeNode = root.getRight();
        result.addAll(preOrderVisit(rightTreeNode));
		return result;
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 先左后根再右
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        BinaryTreeNode<T> leftTreeNode = root.getLeft();
        result.addAll(inOrderVisit(leftTreeNode));
        result.add(root.getData());
        BinaryTreeNode<T> rightTreeNode = root.getRight();
        result.addAll(inOrderVisit(rightTreeNode));
		return result;
	}

	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 左右根
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        BinaryTreeNode<T> leftTreeNode = root.getLeft();
        result.addAll(postOrderVisit(leftTreeNode));
        BinaryTreeNode<T> rightTreeNode = root.getRight();
        result.addAll(postOrderVisit(rightTreeNode));
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
        Stack<BinaryTreeNode<T>> stack = new Stack<>();  // 存放接下来要访问的节点
        if (root == null) {
            return result;
        }
        stack.push(root);
        while(!stack.isEmpty()) {
            BinaryTreeNode<T> top = stack.pop();
            result.add(top.getData());
            if (top.getRight() != null) {
                stack.add(top.getRight());
            }
            if (top.getLeft() != null) {
                stack.add(top.getLeft());
            }
        }
 		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的中序遍历
     * 先左后根再右
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
        if (root == null) {
            return result;
        }
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		stack.push(root);
        BinaryTreeNode<T> top = stack.peek();
		while (!stack.isEmpty()) {
            if (top.getLeft() != null) {
                stack.push(top.getLeft());
                top = top.getLeft();
            } else {
                top = stack.pop();
                result.add(top.getData());
                if (top.getRight() != null) {
                    stack.push(top.getRight());
                    top = top.getRight();
                }
            }
        }
		return result;
	}
	
}
