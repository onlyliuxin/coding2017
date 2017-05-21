package dataStructure_10_BinaryTree;

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
		result.add(root.getData());
		if( root.getLeft() != null){
			//result.add( root.getLeft().getData());
			List list = preOrderVisit(root.getLeft());
			for (int i = 0; i < list.size(); i++) {
				result.add((T) list.get(i));
			}
			
			System.out.println(result.get(0));
		}
		
		if( root.getRight() != null){
			//result.add( root.getRight().getData());
			List list = preOrderVisit(root.getRight());
			for (int i = 0; i < list.size(); i++) {
				result.add((T) list.get(i));
			}
			System.out.println(result.get(0));
		}
		return result;
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
		List<T> result = new ArrayList<T>();
		
		if(root.getLeft() != null){
			List list = new ArrayList();
			list = inOrderVisit(root.getLeft());
			for(int i =0; i < list.size(); i++){
				result.add((T) list.get(i));
			}
		}
		result.add(root.getData());
		if(root.getRight()!=null){
			List list = new ArrayList();
			list = inOrderVisit(root.getRight());
			for(int i =0; i < list.size(); i++){
				result.add((T) list.get(i));
			}
		}
		
		
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
		
		if(root.getLeft() != null){
			List list = new ArrayList();
			list = postOrderVisit(root.getLeft());
			for(int i =0; i < list.size(); i++){
				result.add((T) list.get(i));
			}
		}
		
		if(root.getRight()!=null){
			List list = new ArrayList();
			list = postOrderVisit(root.getRight());
			for(int i =0; i < list.size(); i++){
				result.add((T) list.get(i));
			}
		}
		
		result.add(root.getData());
		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
		
		List<T> result = new ArrayList<T>();		
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();  
		BinaryTreeNode node = root;  
	    while (node != null || stack.size() > 0) {  
            while (node != null) {//压入所有的左节点，压入前访问它  
            	result.add((T) node.getData());
                stack.push(node);  
                node = node.getLeft();  
            }  
            if (stack.size() > 0) {//  
                node = stack.pop();  
                node = node.getRight();  
            }  
	     }  
	    
	    while(!stack.isEmpty()){
	    	result.add((T) stack.pop());
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
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();  
        while (root != null) {  
            while (root != null) {  
                if (root.getRight() != null)  
                    stack.push(root.getRight());// 当前节点右子入栈  
                stack.push(root);// 当前节点入栈  
                root = root.getLeft();  
            }  
            root = stack.pop();  
            while (!stack.empty() && root.getRight() == null) {  
            	result.add((T) root.getData());
                root = stack.pop();  
            }  
            result.add((T) root.getData());
            if (!stack.empty())  
            	root = stack.pop();  
            else  
            	root = null;  
        }  
		
		return result;
	}
	
}
