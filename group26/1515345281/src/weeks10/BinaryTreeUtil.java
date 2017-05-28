package weeks10;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class BinaryTreeUtil {
	/**
	 * 用递归的方式实现对二叉树的前序遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		
		preOrderWithRecursion(root,result);
		
		return result;
	}

	private static <T> void preOrderWithRecursion(BinaryTreeNode<T> root, List<T> result) {
		if(null == root){
			return ;
		}
		
		result.add(root.getData());
		
		preOrderWithRecursion(root.getLeft(),result); 
		preOrderWithRecursion(root.getRight(),result); 
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		
		inOrderWithRecursion(root,result);
		return result;
	}
	
	private static <T> void inOrderWithRecursion(BinaryTreeNode<T> root,List<T> result){
		if(null == root){
			return ;
		}
		
		inOrderWithRecursion(root.getLeft(),result);
		
		result.add(root.getData());	
		
		inOrderWithRecursion(root.getRight(),result);
	}

	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		
		postOrderWithRecursion(root,result);
		
		return result;
	}
	private static <T> void postOrderWithRecursion(BinaryTreeNode<T> root, List<T> result) {
		if(null == root){
			return ;
		}
		
		postOrderWithRecursion(root.getLeft(),result);
		postOrderWithRecursion(root.getRight(),result);
		
		result.add(root.getData());
	}

	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * 思路:
	 * 将根结点入栈，
	 * 每次从栈中弹出一结点，访问该结点。
	 * 将该结点的右孩子入栈
	 * 将该结点的左孩子入栈
	 * 按照以上顺序入栈，这样出栈顺序就与先序遍历一样：先根结点，再左子树，最后右子树。
	 * @param root
	 * @returns
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();		
		
		Stack<BinaryTreeNode<T>> stack=new Stack<BinaryTreeNode<T>>();
		
		stack.push(root);
		
		while(!stack.isEmpty()){
			
			BinaryTreeNode<T> node=stack.pop();
			result.add(node.getData());
			
			if(node.getRight() != null){
				stack.push(node.getRight());
			}
			
			if(node.getLeft() != null){
				stack.push(node.getLeft());
			}
		}
		
		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * 将当前结点设为根节点
	 * 若当前结点非空，则入栈，把当前结点置为其左孩子,重复知道当前结点为空。
	 * 若栈非空，弹出当前结点并加入结果集，置当前结点为其右孩子。
	 * 直到当前结点为空并且栈为空结束
	 * @param root
	 * @return 
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();
		Stack<BinaryTreeNode<T>> stack=new Stack<BinaryTreeNode<T>>();
		
		BinaryTreeNode<T> currNode=root;
		
		while(currNode != null || !stack.isEmpty()){
			while(currNode != null){
				stack.push(currNode);
				currNode=currNode.getLeft();
			}
			
			if(!stack.isEmpty()){
				currNode=stack.pop();
				result.add(currNode.getData());
				currNode=currNode.getRight();
			}
			
		}
		
		return result;
	}
	
}