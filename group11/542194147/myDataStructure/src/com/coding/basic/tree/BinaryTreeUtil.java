package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeUtil {

	/**
	 * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
	 * 前序遍历： 根节点->左节点->右节点
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		preOrderVisit(root,result);
		return result;
	}
	private static<T> void preOrderVisit(BinaryTreeNode<T> root,List<T> result){
		if(null==root){
			return;
		}
		result.add(root.getData());
		if(null!=root.getLeft()){
			preOrderVisit(root.getLeft(), result);
		}
		if(null!=root.getRight()){
			preOrderVisit(root.getRight(), result);
		}
	}
	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 中序遍历:  左节点->根节点->右节点
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		inOrderVisit(root,result);
		return result;
	}
	private static<T> void inOrderVisit(BinaryTreeNode<T> root,List<T> result){
		if(null==root){
			return;
		}
		if(null!=root.getLeft()){
			inOrderVisit(root.getLeft(), result);
		}
		result.add(root.getData());
		if(null!=root.getRight()){
			inOrderVisit(root.getRight(), result);
		}
	}
	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 后序遍历:  左节点->右节点->根节点
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		postOrderVisit(root,result);
		return result;
	}
	private static<T> void postOrderVisit(BinaryTreeNode<T> root,List<T> result){
		if(null==root){
			return;
		}
		if(null!=root.getLeft()){
			postOrderVisit(root.getLeft(), result);
		}
		if(null!=root.getRight()){
			postOrderVisit(root.getRight(), result);
		}
		result.add(root.getData());
	}
	
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();		
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){  
            while(root != null){  
            	result.add(root.getData());  
                stack.push(root);  
                root = root.getLeft();  
            }  
            root = stack.pop();  
            root = root.getRight();  
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
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){  
            while(root != null){  
                stack.push(root);  
                root = root.getLeft();
            }  
            root = stack.pop();
            result.add(root.getData());  
            root = root.getRight();  
        }  
		return result;
	}
}
