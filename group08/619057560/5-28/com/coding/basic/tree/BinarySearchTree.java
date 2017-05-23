package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySearchTree<T extends Comparable<T>> {
	
	BinaryTreeNode<T> root;
	
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	
	public T findMin(){
		@SuppressWarnings("unchecked")
		BinaryTreeNode<T> n = (BinaryTreeNode<T>)findMinNode(root)[0];
		return n==null?null:n.getData();
	}

	// return: an array containing the node with minimum data and its parent
	private Object[] findMinNode(BinaryTreeNode<T> n) {
		if (n == null) {
			return new Object[] {null, null};
		}
		
		BinaryTreeNode<T> p = null;
		while (n.getLeft() != null) {
			p = n;
			n = n.getLeft();
		}
		return new Object[] {n, p};
	}
	
	public T findMax(){
		@SuppressWarnings("unchecked")
		BinaryTreeNode<T> n = (BinaryTreeNode<T>)findMaxNode(root)[0];
		return n==null?null:n.getData();
	}

	// return: an array containing the node with maximum data and its parent
	private Object[] findMaxNode(BinaryTreeNode<T> n) {
		if (n == null) {
			return new Object[] {null, null};
		}
		
		BinaryTreeNode<T> p = null;
		while (n.getRight() != null) {
			p = n;
			n = n.getRight();
		}
		return new Object[] {n, p};
	}
	
	public int height() {
	    return getHeightOfNode(root);
	}
	
	private int getHeightOfNode(BinaryTreeNode<T> n) {
		if (n == null) {
			return 0;
		}
		
		return Math.max(getHeightOfNode(n.getLeft()), getHeightOfNode(n.getRight())) + 1;
	}

	public int size() {
		return getSizeOfNode(root);
	}
	
	private int getSizeOfNode(BinaryTreeNode<T> n) {
		if (n == null) {
			return 0;
		}
		return getSizeOfNode(n.getLeft()) + getSizeOfNode(n.getRight()) + 1;
	}

	public void remove(T e){
		removeDataFromNode(root, e, null);
	}

	private boolean removeDataFromNode(BinaryTreeNode<T> n, T e, BinaryTreeNode<T> parent) {
		if (n == null || n.getData() == null) {
			return false;
		}
		
		if (e.compareTo(n.getData()) < 0) {
			return removeDataFromNode(n.getLeft(), e, n);
		} 
		
		if (e.compareTo(n.getData()) > 0) {
			return removeDataFromNode(n.getRight(), e, n);
		} 
		
		return removeNode(n, parent);
	}

	private boolean removeNode(BinaryTreeNode<T> n, BinaryTreeNode<T> parent) {
		// CASE 1: node n has at least one empty child node
		if (n.getLeft() == null || n.getRight() == null) {
			// CASE 1.1 current node is root
			if (parent == null) {
				root = n.getLeft() == null?n.getRight():n.getLeft();
				return true;
			} 
			
			// CASE 1.2 node n is not root
			BinaryTreeNode<T> sub = n.getLeft()==null?n.getRight():n.getLeft();
			if (n.getData().compareTo(parent.getData()) < 0) {
				parent.setLeft(sub);
			} else {
				parent.setRight(sub);
			}
			return true;
		}
		
		// CASE 2: node n has two children
		Object[] minNodesArray = findMinNode(n.getRight());
		@SuppressWarnings("unchecked")
		BinaryTreeNode<T> next = (BinaryTreeNode<T>)minNodesArray[0];
		@SuppressWarnings("unchecked")
		BinaryTreeNode<T> parentOfNext = (BinaryTreeNode<T>)minNodesArray[1];
		n.setData(next.getData());
		return removeNode(next, parentOfNext);
	}
	
	public List<T> levelVisit(){
		List<T> resultList = new ArrayList<>();
		if (root == null) {
			return resultList;
		}
		Queue<BinaryTreeNode<T>> btNodeQueue = new LinkedBlockingQueue<>();
		btNodeQueue.add(root);
		while (!btNodeQueue.isEmpty()) {
			BinaryTreeNode<T> n = btNodeQueue.poll();
			resultList.add(n.getData());
			if (n.getLeft() != null) {
				btNodeQueue.add(n.getLeft());
			}
			if (n.getRight() != null) {
				btNodeQueue.add(n.getRight());
			}
		}
		return resultList;
	}
	
	private boolean isNodeValid(BinaryTreeNode<T> node) {
		if (node == null) {
			return true;
		}
		
		if (node.getLeft() != null && node.getLeft().getData().compareTo(node.getData()) > 0) {
			return false;
		}
		
		if (node.getRight() != null && node.getRight().getData().compareTo(node.getData()) < 0) {
			return false;
		}
		
		return isNodeValid(node.getLeft()) && isNodeValid(node.getRight());
	}
	
	public boolean isValid(){
		return isNodeValid(root);
	}
	
	private boolean nodeHasOffspring(BinaryTreeNode<T> node, T n) {
		int comp;
		while (node != null && (comp = node.getData().compareTo(n)) != 0) {
			if (comp < 0) {
				node = node.getRight();
			} else {
				node = node.getLeft();
			}
		}
		if (node == null) {
			return false;
		}
		return true;
	}
	
	public T getLowestCommonAncestor(T n1, T n2){
		if (n1 == null || n2 == null) {
			throw new RuntimeException("n1 and n2 cannot be null!");
		}
		if (root == null) {
			throw new RuntimeException("This tree is empty");
		}
		
		BinaryTreeNode<T> node = root;
		boolean n1SmallerThanNode = n1.compareTo(node.getData()) < 0;
		boolean n2SmallerThanNode = n2.compareTo(node.getData()) < 0;
		while (n1SmallerThanNode == n2SmallerThanNode) {
			if (n1SmallerThanNode) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
			// nodes with value n1 and n2 do not exist
			if (node == null) {
				return null;
			}
			
			n1SmallerThanNode = n1.compareTo(node.getData()) < 0;
			n2SmallerThanNode = n2.compareTo(node.getData()) < 0;
		}
		
		// node with value n1 or n2 does not exist
		if (!nodeHasOffspring(node, n1) || !nodeHasOffspring(node, n2)) {
			return null;
		}
		
		return node.getData();
        
	}

	private void getNodesBetween(List<T> resultList, BinaryTreeNode<T> node, T n1, T n2) {
		if (node == null) {
			return;
		}
		
		T data = node.getData();
		boolean biggerThanN1 = data.compareTo(n1) > 0;
		boolean smallerThanN2 = data.compareTo(n2) < 0;
		
		if (biggerThanN1) {
			getNodesBetween(resultList, node.getLeft(), n1, n2);
		}
		
		if (biggerThanN1 && smallerThanN2) {
			resultList.add(data);
		}
		
		if (smallerThanN2) {
			getNodesBetween(resultList, node.getRight(), n1, n2);
		}
	}
	
	public List<T> getNodesBetween(T n1, T n2){
		List<T> resultList = new ArrayList<>();
		getNodesBetween(resultList, root, n1, n2);
		return resultList;
	}

}

