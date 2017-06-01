package tree;

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
		preOrderVisit(root, result);
		return result;
	}
	
	private static <T> void preOrderVisit(BinaryTreeNode<T> node, List<T> result){
		if(node == null){
			return ;
		}
		result.add(node.getData());
		preOrderVisit(node.getLeft(), result);
		preOrderVisit(node.getRight(), result);
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		inOrderVisit(root, result);
		return result;
	}

	private static <T> void inOrderVisit(BinaryTreeNode<T> node, List<T> result){
		if(node == null){
			return ;
		}
		inOrderVisit(node.getLeft(), result);
		result.add(node.getData());
		inOrderVisit(node.getRight(), result);
	}
	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		postOrderVisit(root, result);
		return result;
	}
	
	private static <T> void postOrderVisit(BinaryTreeNode<T> node, List<T> result){
		if(node == null){
			return ;
		}
		postOrderVisit(node.getLeft(), result);
		postOrderVisit(node.getRight(), result);
		result.add(node.getData());
	}
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();	
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		while(root != null){
			result.add(root.getData());
			
			if(root.getRight() != null){
				stack.add(root.getRight());
			}
			
			root = root.getLeft();
			
			if(root == null && !stack.isEmpty()){
				root = stack.pop();
			}
		}
		
		
		return result;
	}
	
	// 非递归前序遍历，方法二
	public static <T> List<T> preOrderWithoutRecursion2(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();	
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		stack.push(root);
		while(!stack.isEmpty()){
			BinaryTreeNode<T> node = stack.pop();
			result.add(node.getData());
			if(node.getRight() != null){
				stack.add(node.getRight());
			}
			if(node.getLeft() != null){
				stack.add(node.getLeft());
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
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		BinaryTreeNode<T> point = root;
		stack.push(point);
		
		while(point.getLeft() != null && !stack.isEmpty()){
			while(point.getLeft() !=  null){//循环1
				point = point.getLeft();
				stack.push(point);
			}
			while(!stack.isEmpty()){//循环2
				BinaryTreeNode<T> node = stack.pop();
				result.add(node.getData());
				point = node;
				if(point.getRight() != null){
					point = node.getRight();
					stack.push(point);
					if(point.getLeft() != null){
						break;//如果左子树不为null,则跳出此循环，进入循环1
					}
				}
			}
		}
		
		return result;
	}
	// 非递归中序遍历：方法二
	public static <T> List<T> inOrderWithoutRecursion2(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		BinaryTreeNode<T> point = root;
		
		while(point != null || !stack.isEmpty()){
			while(point != null){
				stack.push(point);
				point = point.getLeft();
			}
			
			BinaryTreeNode<T> node = stack.pop();
			result.add(node.getData());
			point = node.getRight();
		}
		
		return result;
	}
	
}
