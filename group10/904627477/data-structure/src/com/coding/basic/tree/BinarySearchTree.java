package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BinarySearchTree<T extends Comparable> {

	BinaryTreeNode<T> root;
	
	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public T findMin() {
		BinaryTreeNode<T> min = findMin(root);
		return min == null ? null : min.data;
	}

	public T findMax() {
		BinaryTreeNode<T> max = findMax(root);
		return max == null ? null : max.data;
	}

	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
		if (node == null) {
			return null;
		} else if (!node.hasLeft()) {
			return node;
		}
		return findMin(node.left);// 递归查找
	}

	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> node) {
		if (node == null) {
			return null;
		} else if (!node.hasRight()) {
			return node;
		}
		return findMax(node.right);// 递归查找
	}

	public int height() {
		if (root == null) {
			return 0;
		}
		BinarySearchTree<T> leftTree = new BinarySearchTree<T>(root.left);
		BinarySearchTree<T> rightTree = new BinarySearchTree<T>(root.right);
		int lh = leftTree.height();
		int rh = rightTree.height();
		if (lh > rh) {
			return 1 + lh;
		} else {
			return 1 + rh;
		}
	}

	public int size() {
		if (root == null) {
			return 0;
		}
		BinarySearchTree<T> leftTree = new BinarySearchTree<T>(root.left);
		BinarySearchTree<T> rightTree = new BinarySearchTree<T>(root.right);
		return leftTree.size() + 1 + rightTree.size();
	}

	// 中序遍历中的前一个节点
	public T getFirstNode(T e) {
		if (e == null || root == null) {
			return null;
		}
		List<T> list = BinaryTreeUtil.inOrderVisit(root);
		for (int i = 0; i < list.size(); i++) {
			if (e.equals(list.get(i))) {
				return list.get(i - 1);
			}
		}
		return null;
	}

	public void remove(T e) {
		if (root == null || e == null) {
			return;
		}
		remove(e, root);
	}

	// 删除在节点node分支上的某节点,返回删除后的该分支的根节点
	public BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> node) {
		if (node == null) {
			return null;
		}
		int res = e.compareTo(node.data);
		if (res > 0) {
			node.right = remove(e, node.right);
		} else if (res < 0) {
			node.left = remove(e, node.left);
		} else {
			if (node.hasLeft() && node.hasRight()) {
				node.data = findMin(node.right).data;
				node.right = remove(node.data, node.right);
			} else {
				node = node.hasLeft() ? node.left : node.right;
			}
		}
		return node;
	}

	public List<T> levelVisit() {
		if(root == null){
			return null;
		}
		List<T> result = new ArrayList<T>();
		Queue<BinaryTreeNode<T>> queue = new LinkedList<BinaryTreeNode<T>>();
		BinaryTreeNode<T> temp = root;
		while(temp!=null){
			result.add(temp.data);
			if(temp.hasLeft()){
				queue.add(temp.left);
			}
			if(temp.hasRight()){
				queue.add(temp.right);
			}
			temp = queue.poll();
		}
		return result;
	}

	public boolean isValid() {
		boolean flag = true;
		if(root!=null){
			if(root.hasLeft()){
				if(root.left.data.compareTo(root.data)<0){
					flag = new BinarySearchTree(root.left).isValid();
					if(!flag){
						return false;
					}
				}else{
					return false;
				}
			}
			if(root.hasRight()){
				if(root.right.data.compareTo(root.data)>0){
					flag = new BinarySearchTree(root.right).isValid();
					if(!flag){
						return false;
					}
				}else{
					return false;
				}
			}
		}
		return flag;
	}

	public T getLowestCommonAncestor(T n1, T n2) {
		if(root==null){
			return null;
		}
		int r = n1.compareTo(n2);
		if(r==0){
			return n1;
		}
		if(r>0){
			T t = n1;
			n1 = n2;
			n2 = t;
		}
		Stack<BinaryTreeNode<T>> parents = new Stack<BinaryTreeNode<T>>();
		BinaryTreeNode<T> temp = root;
		while(temp!=null){
			parents.push(temp);
			int c = temp.data.compareTo(n1);
			if(c>0){
				temp = temp.left;
			}else if(c==0){
				break;
			}else{
				temp = temp.right;
			}
		}
		if(temp==null){
			return null; 
		}
		if(parents.isEmpty()){
			return root.data;
		}
		temp = parents.pop();
		while(!parents.isEmpty()){
			if(parents.peek().data.compareTo(n2)==0){
				return n2;
			}
			
			if(parents.peek().data.compareTo(n2)>0){
				return temp.data;
			}
			temp = parents.pop();
		}
		if(parents.isEmpty()){
			return root.data;
		}
		return null;
	}

	public List<T> getNodesBetween(T n1, T n2) {
		if(root==null){
			return null;
		}
		List<T> result = new ArrayList<T>();
		int r = n1.compareTo(n2);
		if(r==0){
			result.add(n1);
			return result;
		}
		if(r>0){
			T t = n1;
			n1 = n2;
			n2 = t;
		}
		List<T> l = BinaryTreeUtil.inOrderVisit(root);
		for (T t : l) {
			if(t.compareTo(n1)>0&&t.compareTo(n2)<0){
				result.add(t);
			}
			if(t.compareTo(n2)>=0){
				break;
			}
		}
		return result;
	}
}
