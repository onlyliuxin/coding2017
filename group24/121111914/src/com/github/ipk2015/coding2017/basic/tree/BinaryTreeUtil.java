package com.github.ipk2015.coding2017.basic.tree;



import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*
 * 二叉树的遍历
                    对于二叉树的遍历方式一般分为三种先序、中序、后序三种方式
                 先序遍历
                  若二叉树为空，则不进行任何操作：否则
                       1、访问根结点。
                       2、先序方式遍历左子树。
                       3、先序遍历右子树。
                中序遍历
                  若二叉树为空，则不进行任何操作：否则
                       1、中序遍历左子树。
                       2、访问根结点。
                       3、中序遍历右子树。
               后序遍历
                  若二叉树为空，则不进行任何操作：否则
                       1、后序遍历左子树。
                       2、后序遍历右子树。
                       3、访问根结点。
 * */
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
