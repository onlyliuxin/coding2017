package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.coding.basic.queue.Queue;


/**
 * 实现二叉查找树的操作
 * 
 * @author chenming E-mail:cm_20094020@163.com
 * @version 创建时间：2017年5月15日 下午9:45:47
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> root) {
		BinaryTreeNode<T> p = root;
		while (p.left != null) {
			p = p.left;
		}
		return p;
	}
	
	public BinaryTreeNode<T> findMin() {
		if (root == null) {
			throw new NoSuchElementException("根节点为空");
		}
		return findMin(root);
	}

	public BinaryTreeNode<T> findMax() {
		if (root == null) {
			throw new NoSuchElementException("根节点为空");
		}
		return findMax(root);
	}

	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> root) {
		BinaryTreeNode<T> p = root;
		while (p.right != null) {
			p = p.right;
		}
		return p;
	}

	public int height() {
		if (root == null)
			return 0;
		return getHeight(root);
	}

	private int getHeight(BinaryTreeNode<T> node) {
		int leftHeight = getHeight(node.left);
		int rightHeight = getHeight(node.right);
		return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
	}
	

	public int size() {
		if(root==null) return 0;
		return getSize(root);
	}

	private int getSize(BinaryTreeNode<T> node) {
		int leftSize = getSize(root.left);
		int rightSize = getSize(root.right);
		return 1 + leftSize + rightSize;
	}

	public void remove(T e) {
		if (root == null) {
			throw new NoSuchElementException("根节点为空");
		}
		remove(e, root);
	}
	
	@SuppressWarnings("unchecked")
	private BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> node) {
		if (node.data.compareTo(e) == 0) {
			if (node.left == null && node.right == null) {
				return null;
			} else if (node.right == null && node.left != null) {
				return node.left;
			} else if (node.right != null && node.left == null) {
				return node.right;
			} else {
				BinaryTreeNode<T> minParent = findMinParent(node.right);
				node.data = minParent.left.data;
				minParent.left = null;
				return node;
			}
		} else if (node.data.compareTo(e) < 0) {
			node.right = remove(e, node.right);
			return node;
		} else {
			node.left = remove(e, node.left);
			return node;
		}
	}

	private BinaryTreeNode<T> findMinParent(BinaryTreeNode<T> node) {
		BinaryTreeNode<T> p = node;
		BinaryTreeNode<T> parent = null;
		while (p.left != null) {
			parent = p;
			p = p.left;
		}
		return parent;
	}

	/**
	 * 按层次遍历： levelVisit
	 * @return
	 */
	public List<T> levelVisit(){
		List<T> result = new ArrayList<>();
		if(root ==null){
			return result;
		}
		Queue<BinaryTreeNode<T>> queue = new Queue<BinaryTreeNode<T>>();
		BinaryTreeNode<T> node = root;
		queue.enQueue(node);
		while(!queue.isEmpty()){
			node = queue.deQueue();
			result.add(node.data);
			if(node.left!=null){
				queue.enQueue(node.left);
			}
			if(node.right!=null){
				queue.enQueue(node.right);
			}
		}
		return result;
	}
	
	/**
	 * 判断一个二叉树是不是二叉查找树
	 * @return
	 */
	public boolean isValid(){
		return isValid(root);
	}
	
	/**
	 * 获取两个节点的最小公共祖先
	 * @param n1
	 * @param n2
	 * @return
	 */
	public T getLowestCommonAncestor(T n1, T n2) {
		if (root == null) {
			return null;
		}
		return lowestCommonAncestor(root, n1, n2);
	}
	
	
	/**
	 * 给定两个值， 获得处于这两个值中间的节点
	 * @param n1
	 * @param n2
	 * @return
	 */
	public List<T> getNodesBetween(T n1,T n2){
		List<T> elements = new ArrayList<T>();
		getNodesBetween(elements,root,n1,n2);
		return elements;
	}
	
	
	@SuppressWarnings("unchecked")
	private void getNodesBetween(List<T> elements, BinaryTreeNode<T> node, T n1, T n2) {
		if(node==null){
			return ;
		}
		if(n1.compareTo(node.data) < 0){
			getNodesBetween(elements,node.left,n1, n2);
		}
		if((n1.compareTo(node.data) <= 0) && (n2.compareTo(node.data)>=0)){
			elements.add(node.data);
		}
		if(n2.compareTo(node.data) < 0){
			getNodesBetween(elements,node.right,n1, n2);
		}
	}
	
	@SuppressWarnings("unchecked")
	private T lowestCommonAncestor(BinaryTreeNode<T> node, T n1, T n2) {
		if (node == null) {
			return null;
		}
		if (node.data.compareTo(n1) > 0 && node.data.compareTo(n2) > 0) {
			return lowestCommonAncestor(node.left, n1, n2);
		}
		if (node.data.compareTo(n1) < 0 && node.data.compareTo(n2) < 0) {
			return lowestCommonAncestor(node.right, n1, n2);
		}
		return node.data;
	}

	@SuppressWarnings("unchecked")
	private boolean isValid(BinaryTreeNode<T> btn) {
		if(root==null){
			return true;
		}
		if(btn.left!=null&&findMax(btn.left).data.compareTo(btn.data)>0){
			return false;
		}
		if(btn.right!=null&&findMin(btn.right).data.compareTo(btn.data)<0){
			return false;
		}
		if(!isValid(btn.left)||!isValid(btn.right)){
			return false;
		}
		return true;
	}
	
	
}
