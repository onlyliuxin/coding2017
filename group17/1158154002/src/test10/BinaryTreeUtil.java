package test10;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.sun.org.apache.bcel.internal.util.BCELifier;

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
		if (root.getLeft()!=null) {
			addPreResult(root, result);
		}else if (root.getRight()!=null) {
			addPreResult(root, result);
		}
		return result;
	}

	private static <T> void addPreResult(BinaryTreeNode<T> root, List<T> result) {
		for (T t : preOrderVisit(root.getLeft())) {
			result.add(t);
		}
		for (T t : preOrderVisit(root.getRight())) {
			result.add(t);
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
		if (root.getLeft()!=null) {
			addInResult(root.getLeft(), result);
		}
		result.add(root.getData());
		if (root.getRight()!=null) {
			addInResult(root.getRight(), result);
		}
		
		return result;
	}

	private static <T> void addInResult(BinaryTreeNode<T> root, List<T> result) {
		for (T t : inOrderVisit(root)) {
			result.add(t);
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
		if (root.getLeft()!=null) {
			addPostResult(root.getLeft(), result);
		}
		if (root.getRight()!=null) {
			addPostResult(root.getRight(), result);
		}
		result.add(root.getData());	
		return result;
	}
	
	private static <T> void addPostResult(BinaryTreeNode<T> root, List<T> result) {
		for (T t : postOrderVisit(root)) {
			result.add(t);
		}
	}
	
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();		
		Stack<T> s=new Stack<>();
		s.push((T) root);
		while (!s.isEmpty()) {
			BinaryTreeNode<T> bt=(BinaryTreeNode<T>) s.pop();
			result.add((T) bt.getData());
			if (bt.getRight()!=null) {
				s.push((T) bt.getRight());
			}
			if (bt.getLeft()!=null) {
				s.push((T) bt.getLeft());
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
		Stack<T> s=new Stack<>();
		BinaryTreeNode<T> p=root;
		while (p!=null||!s.isEmpty()) {
			if (p!=null) {
				s.push((T) p);
				p=p.getLeft();
			}else{
				p=(BinaryTreeNode<T>) s.pop();
				result.add((T) p.getData());
				p=p.getRight();
			}
		}
		return result;
	}
}
