package task0514.coding.basic.tree;

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
		if (null != root) {
			preOrderVisit(result, root);
		}
		return result;
	}

	static <T> void preOrderVisit(List<T> result, BinaryTreeNode<T> node) {
		result.add(node.getData());

		if (null != node.getLeft()) {
			preOrderVisit(result, node.getLeft());
		}
		if (null != node.getRight()) {
			preOrderVisit(result, node.getRight());
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
		if (null != root) {
			inOrderVisit(result, root);
		}
		return result;
	}

	static <T> void inOrderVisit(List<T> result, BinaryTreeNode<T> node) {
		if (null != node.getLeft()) {
			inOrderVisit(result, node.getLeft());
		}
		result.add(node.getData());

		if (null != node.getRight()) {
			inOrderVisit(result, node.getRight());

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
		if (null != root) {
			postOrderVisit(result, root);
		}

		return result;
	}

	static <T> void postOrderVisit(List<T> result, BinaryTreeNode<T> node) {
		if (null != node.getLeft()) {
			postOrderVisit(result, node.getLeft());
		}

		if (null != node.getRight()) {
			postOrderVisit(result, node.getRight());
		}

		result.add(node.getData());

	}

	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {

		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> nodeStack = new Stack<>();

		BinaryTreeNode<T> node = root;
		wc: while (true) {
			while (true) {
				result.add(node.getData());

				if (null == node.getLeft()) {
					break;
				} else {
					nodeStack.push(node);//将中间节点放到栈
					node = node.getLeft();//将节点的左节点设为相对的中间节点
				}

			}
			while (true) {
				if (null != node.getRight()) {//将右节点设为相对的中间节点
					node = node.getRight();
					break;
				}
				if (null == node.getRight()) {
					if (nodeStack.isEmpty()) {//存放节点的栈为空则表示所有节点遍历完成
						break wc;
					}
					node = nodeStack.pop();//节点没有子节点，将栈顶的节点设为相对的中间节点
					continue;
				}
			}
		}
		return result;
	}

	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {

		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> nodeStack = new Stack<>();

		BinaryTreeNode<T> node = root;
		wc: while (true) {
			while (true) {
				if (null == node.getLeft()) {
					break;
				} else {
					nodeStack.push(node);//将中间节点放到栈
					node = node.getLeft();//将节点的左节点设为相对的中间节点
				}

			}

			while (true) {
				result.add(node.getData());
				if (null != node.getRight()) {//将右节点设为相对的中间节点
					node = node.getRight();
					break;
				}
				if (null == node.getRight()) {
					if (nodeStack.isEmpty()) {//存放节点的栈为空则表示所有节点遍历完成
						break wc;
					}
					node = nodeStack.pop();//节点没有子节点，将栈顶的节点设为相对的中间节点
					continue;
				}
			}
		}
		return result;
	}

}