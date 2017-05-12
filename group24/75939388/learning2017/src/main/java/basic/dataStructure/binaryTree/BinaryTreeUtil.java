package basic.dataStructure.binaryTree;

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

		preOrder(root, result);

		return result;
	}

	private static <T> void preOrder(BinaryTreeNode<T> tree, List<T> result){
		if(tree == null) return;
		result.add(tree.getData());

		preOrder(tree.getLeft(), result);
		preOrder(tree.getRight(), result);
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		inOrder(root, result);
		return result;
	}

	private static <T> void inOrder(BinaryTreeNode<T> tree, List<T> result){
		if(tree == null) return;

		inOrder(tree.getLeft(), result);
		result.add(tree.getData());
		inOrder(tree.getRight(), result);
	}

	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		postOrder(root, result);
		return result;
	}

	private static <T> void postOrder(BinaryTreeNode<T> tree, List<T> result){
		if(tree == null) return;

		postOrder(tree.getLeft(), result);
		postOrder(tree.getRight(), result);
		result.add(tree.getData());
	}

	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode> buffer = new Stack();
		BinaryTreeNode<T> tmp = root;
		while(tmp != null){
			//遍历这一排的左边节点，右边节点入栈缓存
			BinaryTreeNode<T> node = tmp;
			while(node != null){
				result.add(node.getData());
				if(node.getRight() != null) buffer.add(node.getRight());
				node = node.getLeft();
			}

			//遍历右边
			//栈中没有节点则表示遍历结束
			if (buffer.isEmpty()) break;

			tmp = buffer.pop();
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
		Stack<BinaryTreeNode> buffer = new Stack<BinaryTreeNode>();

		BinaryTreeNode<T> tmp = root;
		while (tmp != null || !buffer.isEmpty()){
			while(tmp != null){
				buffer.push(tmp);
				tmp = tmp.getLeft();
			}

			tmp = buffer.pop();
			result.add(tmp.getData());
			tmp = tmp.getRight();
		}
		return result;
	}
	
}
