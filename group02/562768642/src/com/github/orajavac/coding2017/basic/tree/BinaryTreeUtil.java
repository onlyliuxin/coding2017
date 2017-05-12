package com.github.orajavac.coding2017.basic.tree;

import java.util.ArrayList;
import java.util.List;
import com.github.orajavac.coding2017.basic.BinaryTreeNode;
import com.github.orajavac.coding2017.basic.stack.Stack;

public class BinaryTreeUtil {
	
	private static Stack s = new Stack();
	
	/**
	 * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
	 * 
	 * @param root
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
		return null;
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
	@SuppressWarnings("unchecked")
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		result.add((T) root.getData());
		BinaryTreeNode<T> node = root;
		preOrderWithoutRecursionProcess(node,result);
		node.setLeft(root.getRight());
		preOrderWithoutRecursionProcess(node,result);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> preOrderWithoutRecursionProcess(BinaryTreeNode<T> node,List<T> result) {
		while(true){
			if (node.getLeft()!=null){
				result.add((T) node.getLeft().getData());
				s.push(node.getLeft());
				node = node.getLeft();
			}else{
				node = (BinaryTreeNode<T>)s.pop();
				node.setLeft(node.getRight());
			}
			if (node.getLeft()==null&&s.length()==0)
				break;
		}
		return result;
	}
	
	
	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * @param root
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		BinaryTreeNode<T> node = root;
		while (node.getLeft()!=null){
			s.push(node.getLeft());
			node = node.getLeft();
		}
		
		inOrderVisitProcess(node,s,result);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> inOrderVisitProcess(BinaryTreeNode<T> node,Stack s,List<T> result) {
		if (node.getRight()!=null){
			while (true){
				if (node.getLeft()!=null){
					s.push(node.getLeft());
					node = node.getLeft();
				}else{
					node = (BinaryTreeNode<T>)s.pop();
					result.add((T) node.getData());
					break;
				}
			}
		}else{
			node = (BinaryTreeNode<T>)s.pop();
			result.add((T) node.getData());
		}
		return result;
	}
	
}
