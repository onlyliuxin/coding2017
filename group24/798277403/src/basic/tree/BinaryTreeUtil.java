package basic.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeUtil {
	/**
	 * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		prevAdd(root,result);
		return result;
	}
	private static <T> void prevAdd(BinaryTreeNode<T> node,List<T> result){
		if(node!=null){
			result.add(node.getData());
			if(node.getLeft()!=null){
				prevAdd(node.getLeft(),result);
			}
			if(node.getRight()!=null){
				prevAdd(node.getRight(),result);
			}
		}
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		inOrderAdd(root,result);
		return result;
	}
	private static <T> void inOrderAdd(BinaryTreeNode<T> node,List<T> result){
		if(node!=null){
			if(node.getLeft()!=null){
				inOrderAdd(node.getLeft(),result);
			}
			result.add(node.getData());
			if(node.getRight()!=null){
				inOrderAdd(node.getRight(),result);
			}
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
		postOrderAdd(root,result);
		return result;
	}
	private static <T> void postOrderAdd(BinaryTreeNode<T> node,List<T> result){
		if(node!=null){
			if(node.getLeft()!=null){
				postOrderAdd(node.getLeft(),result);
			}
			if(node.getRight()!=null){
				postOrderAdd(node.getRight(),result);
			}
			result.add(node.getData());
		}
	}
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		while(!stack.isEmpty() || root!=null){
			//先压入所有左节点，在压入前访问
			while(root!=null){
				result.add(root.getData());
				stack.push(root);
				root = root.getLeft();
			}
			//左节点压入完后压入右节点
			if(!stack.isEmpty()){
				root = stack.pop();
				root = root.getRight();
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
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		while(!stack.isEmpty() || root!=null){
			while(root!=null){
				stack.push(root);
				root = root.getLeft();
			}
			if(!stack.isEmpty()){
				root = stack.pop();
				result.add(root.getData());
				root = root.getRight();
			}
		}
		return result;
	}
	
}
