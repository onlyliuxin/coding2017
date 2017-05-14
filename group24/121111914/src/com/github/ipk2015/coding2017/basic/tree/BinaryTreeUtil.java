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
		preOrderNode(root,result);
		return result;
	}
	private static <T> void preOrderNode(BinaryTreeNode<T> node,List<T> list){
		if(null == node){
			return;
		}
		
		T data = node.getData();
		if(null != data){
			list.add(data);
		}
		
		BinaryTreeNode<T> leftNode = node.getLeft();
		preOrderNode(leftNode,list);
		
		BinaryTreeNode<T> rightNode = node.getRight();
		preOrderNode(rightNode,list);
	}
	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		inOrderNode(root,result);
		return result;
	}

	private static <T> void inOrderNode(BinaryTreeNode<T> node,List<T> list){
		if(null == node){
			return;
		}
		
		BinaryTreeNode<T> leftNode = node.getLeft();
		inOrderNode(leftNode,list);
		
		T data = node.getData();
		if(null != data){
			list.add(data);
		}
		
		BinaryTreeNode<T> rightNode = node.getRight();
		inOrderNode(rightNode,list);
	}
	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		postOrderNode(root,result);
		return result;
	}
	
	private static <T> void postOrderNode(BinaryTreeNode<T> node,List<T> list){
		if(null == node){
			return;
		}
		
		BinaryTreeNode<T> leftNode = node.getLeft();
		postOrderNode(leftNode,list);
		
		BinaryTreeNode<T> rightNode = node.getRight();
		postOrderNode(rightNode,list);
		
		T data = node.getData();
		if(null != data){
			list.add(data);
		}
		
	}
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		Stack stack = new Stack();
		stack.push(root);
		while(!stack.isEmpty()){
			BinaryTreeNode<T> node = (BinaryTreeNode<T>) stack.pop();
			if(null == node){
				break;
			}
			T data = node.getData();
			if(null != data){
				result.add(data);
			}
				
			BinaryTreeNode<T> rightNode = node.getRight();
			if(null != rightNode){
				stack.push(rightNode);
			}
			BinaryTreeNode<T> leftNode = node.getLeft();
			if(null != leftNode){
				stack.push(leftNode);
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
		Stack nodeStack = new Stack();
		Stack midStack = new Stack();
		nodeStack.push(root);
		while(!nodeStack.isEmpty()){
			BinaryTreeNode<T> node = (BinaryTreeNode<T>) nodeStack.pop();
			if(null == node){
				break;
			}
			
			BinaryTreeNode<T> rightNode = node.getRight();
			if(null != rightNode){
				nodeStack.push(rightNode);
			}
			
			BinaryTreeNode<T> leftNode = node.getLeft();
			if(null != leftNode){
				midStack.push(node.getData());
				nodeStack.push(leftNode);
			}else{
				T data = node.getData();
				if(null != data){
					result.add(data);
				}
				if(!midStack.isEmpty()){
					result.add((T) midStack.pop());
				}
			}
		} 
		return result;
	}
	
}
