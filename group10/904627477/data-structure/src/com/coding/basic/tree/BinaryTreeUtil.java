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
		preOrderVisit(result,root);
		return result;
	}
	
	public static <T> void preOrderVisit(List<T> result,BinaryTreeNode<T> node) {
		if(node==null){
			return;
		}
		result.add(node.getData());
		if(node.hasLeft()){
			preOrderVisit(result,node.getLeft());
		}
		if(node.hasRight()){
			preOrderVisit(result,node.getRight());
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
		inOrderVisit(result,root);
		return result;
	}
	
	public static <T> void inOrderVisit(List<T> result,BinaryTreeNode<T> node) {
		if(node==null){
			return;
		}
		if(node.hasLeft()){
			inOrderVisit(result,node.getLeft());
		}
		result.add(node.getData());
		if(node.hasRight()){
			inOrderVisit(result,node.getRight());
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
		postOrderVisit(result,root);
		return result;
	}
	
	public static <T> void postOrderVisit(List<T> result,BinaryTreeNode<T> node) {
		if(node==null){
			return;
		}
		if(node.hasLeft()){
			postOrderVisit(result,node.getLeft());
		}
		if(node.hasRight()){
			postOrderVisit(result,node.getRight());
		}
		result.add(node.getData());
	}
	
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	/*public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		if(root==null){
			return null;
		}
		List<T> result = new ArrayList<T>();
		BinaryTreeNode<T> p = root;
		while(p!=null){
			if(p.isRead()){
				if(p.hasRight()&&!p.getRight().isRead()){
					p = p.getRight();
				}else{
					p = p.getParent();
				}
			}else{
				result.add(p.getData());
				if(p.hasLeft()){
					p = p.getLeft();
				}else if(p.hasRight()){
					p = p.getRight();
				}else{
					p = p.getParent();
				}
			}
		}
		return result;
	}*/
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		if(root==null){
			return null;
		}
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		//stack.push(root);
		BinaryTreeNode<T> p = root;
		while(p!=null){
			if(!p.isRead()){
				result.add(p.getData());
			}
			if(p.hasLeft()&&!p.left.isRead()){
				stack.push(p);
				p = p.left;
			}else{
				if(p.hasRight()){
					p = p.right;
				}else{
					if(stack.isEmpty()){
						break;
					}else{
						p = stack.pop();
					}
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
/*	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		if(root==null){
			return null;
		}
		List<T> result = new ArrayList<T>();
		BinaryTreeNode<T> p = root;
		while(p!=null){	
			if(p.hasLeft()&&!p.getLeft().isRead()){
				p = p.getLeft();
			}else{
				if(!p.isRead()){
					result.add(p.getData());
				}
				if(p.hasRight()&&!p.getRight().isRead()){
					p = p.getRight();
				}else{
					p = p.getParent();
				}
			}			
		}
		return result;
	}*/
	
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		if(root==null){
			return null;
		}
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		//stack.push(root);
		BinaryTreeNode<T> p = root;
		while(p!=null){
			if(p.hasLeft()&&!p.left.isRead()){
				stack.push(p);
				p = p.left;
			}else{
				result.add(p.getData());
				if(p.hasRight()){
					p = p.right;
				}else{
					if(stack.isEmpty()){
						break;
					}else{
						p = stack.pop();
					}
				}
			}
		}
		return result;
	}
}
