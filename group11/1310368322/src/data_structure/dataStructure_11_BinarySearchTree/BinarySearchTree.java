package dataStructure_11_BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import DataStructure_1.LinkedList;

public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	
	public T findMin(){
		while(root.left != null){
			root = root.left;
		}
		return root.data;
	}
	/*
	 *  找到指定树的最小值
	 */
	public BinaryTreeNode<T> findMin(BinaryTreeNode<T> t){
		if( t==null ){
			return null;
		} else if ( t.left == null ){
			return t;
		} else {
			return findMin(t.left);
		}
		
	}
	
	public T findMax(){
		while(root.right != null){
			root = root.right;
		}
		
		return root.data;
	}
	int height = 0;
	
	public int height(BinaryTreeNode<T> root) {
		
		int leftHeight;
		int rightHeight;
		
		if( null == root){
			return 0;
		}else{
			leftHeight = height(root.getLeft());// 算出当前左子树的高度
			rightHeight = height(root.getRight());
			
			if( leftHeight > rightHeight){
				return leftHeight+1;
			}else{
				return rightHeight+1;
			}
		}
	}  
	
	public int size(BinaryTreeNode<T> root) {
		

		int leftSize = 0;
		int rightSize = 0;

		if( null == root){
			return 0;
		}else{
			
			leftSize = size(root.getLeft());
			rightSize = size(root.getRight());
			
			return leftSize + rightSize + 1;
		}
	}
	
	public void remove(T e){
		System.out.println(remove(e, root).left.data);
	}

	private BinaryTreeNode<T> remove(T x, BinaryTreeNode<T> t) {
		if( t == null){
			return t;
		}
		int compareResult = x.compareTo(t.data);
		 
		if(compareResult < 0) {
			t.left = remove(x, t.left);
		} else if(compareResult > 0){
			t.right = remove(x, t.right);
		} else {
			if( t.left != null && t.right != null){
				
				t.data = findMin(t.right).data;// 找到右子数的最小值,并把其值赋给了当前节点的data
				
				t.right = remove(t.data, t.right);// 将问题转化为删除叶子节点
			} else {
				t = (t.left != null) ? t.left : t.right;// 让 t 本身为 null， 也就让 t 的父节点的左或右孩子为null ------ 删除叶子节点
			}
		} 
		return t;
	}

	private BinaryTreeNode<T> findRemoveNode(BinaryTreeNode<T> root, T e) {
		
		System.out.println("root : " + root.getData());
		//System.out.println("要找的值：" + e);
		BinaryTreeNode<T> node;
		
		if(root.left == null && root.right == null){
			return null;
		}else{
			if(root.left != null && root.left.getData() != e){
				node = findRemoveNode(root.left, e);
			}else{
				return root;
			}
			
			if(root.right != null && root.right.getData() != e){
				node = findRemoveNode(root.right, e);
			}else{
				return root;
			}
		}
		return node;
	
	}
	
	
	public List<T> levelVisit(){
		
		List<T> result = new ArrayList<T>();
		BinaryTreeNode<T> node;
		Queue<BinaryTreeNode<T>> queue = new java.util.LinkedList<BinaryTreeNode<T>>();
		
		if( root == null ) {  return null;  }
		
		queue.offer(root);
		
		while( !queue.isEmpty()){
			
			node = queue.poll();
			if( node.getLeft() != null ){
				
				queue.offer(node.left);
				
			}
			if( node.getRight() != null ){
				queue.offer(node.right);
			}
			
			result.add(node.getData());
		}
		
		return result;
	}
	
	public boolean isValid(){
		
		return isValid(root);
		
	}
	

	
	private boolean isValid(BinaryTreeNode<T> node) {
		
		if(node == null){
			return true;
		}
		
		int compareResultLeft = 0;
		int compareResultRight = 0;
		
		if( node.left != null){
			compareResultLeft = node.getData().compareTo(node.left.getData());
		}
		if( node.right != null){
			compareResultRight = node.getData().compareTo(node.left.getData());
		}
	
		if( compareResultLeft >= 0 || compareResultRight <= 0){
			return false;
		}
		isValid(node.left);
		isValid(node.right);
		return true;
	}

	public T getLowestCommonAncestor(T n1, T n2){
		
		return getLowestCommonAncestor(n1, n2,root).data;
        
	}
	
	private BinaryTreeNode<T> getLowestCommonAncestor(T n1, T n2, BinaryTreeNode<T> node) {
		
		BinaryTreeNode result = null;
		if( node == null){
			return null;
		}
		int compareResult1 = node.getData().compareTo(n1);
		int compareRusult2 = node.getData().compareTo(n2);
		if( n1.compareTo(n2) < 0){
			
			if((compareResult1 > 0 && compareRusult2 < 0)){
				return node;
			}
			
			if(compareResult1 > 0){
				result = getLowestCommonAncestor(n1, n2, node.left);
			}else if(compareResult1 < 0){
				result = getLowestCommonAncestor(n1, n2, node.right);
			}
		}else if( n1.compareTo(n2) > 0 ){
			if((compareResult1 < 0 && compareRusult2 > 0)){
				return node;
			}
			
			if(compareResult1 < 0){
				result = getLowestCommonAncestor(n1, n2, node.left);
			}else if(compareResult1 > 0){
				result = getLowestCommonAncestor(n1, n2, node.right);
			}
		}
		

		return result;
		
	}

	public List<T> getNodesBetween(T n1, T n2){
		
		List<T> result = new ArrayList<T>();
		getNodesBetween(n1, n2,root,result);
		
		return result;
	}

	private void getNodesBetween(T n1, T n2, BinaryTreeNode<T> node, List<T> result) {
		
		if( node == null){
			return;
		}
		if( (node.getData().compareTo(n1)) > 0 && (node.getData().compareTo(n2) < 0) ){
			result.add(node.getData());
		}
		
		getNodesBetween(n1, n2, node.left, result);
		getNodesBetween(n1, n2, node.right, result);
		
	}
	
	
}
