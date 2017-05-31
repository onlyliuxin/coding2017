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
	public static <T> void preOrderVisit(BinaryTreeNode<T> root) {
		if(root==null)
		{
			return;
		}
		System.out.print(root.getData()+" ");
		preOrderVisit(root.getLeft());
		preOrderVisit(root.getRight());
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T>void inOrderVisit(BinaryTreeNode<T> root) {
		
		if(root==null)
		{
			return;
		}
		inOrderVisit(root.getLeft());
		System.out.print(root.getData()+" ");
		inOrderVisit(root.getRight());
	}

	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> void postOrderVisit(BinaryTreeNode<T> root) {
		if(root==null)
		{
			return;
		}
		postOrderVisit(root.getLeft());
		postOrderVisit(root.getRight());
		System.out.print(root.getData()+" ");
	}
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();		
		@SuppressWarnings("rawtypes")
		Stack<BinaryTreeNode> stack=new Stack<>();
        if(root!=null)
        {
        	stack.push(root);
        	while(!stack.isEmpty())
        	{
        		root=stack.pop();
        		result.add(root.getData());
        		if(root.getRight()!=null)
        		{
        			stack.push(root.getRight());
        		}
        		if(root.getLeft()!=null)
        		{
        			stack.push(root.getLeft());
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = null;
		if(root!=null)
		{
			result=new ArrayList<>();
			Stack<BinaryTreeNode> stack=new Stack<>();
			while(!stack.isEmpty()||root!=null)
			{
				if(root!=null)
				{
					stack.push(root);
					root=root.getLeft();
				}else{
					root=stack.pop();
					result.add(root.getData());
					root=root.getRight();
				}
			}
		}
		
		return result;
}
}
