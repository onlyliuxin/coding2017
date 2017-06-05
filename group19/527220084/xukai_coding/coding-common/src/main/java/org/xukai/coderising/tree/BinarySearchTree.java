package org.xukai.coderising.tree;

import java.util.ArrayList;
import java.util.List;


public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	public T findMin(){
		return findMin(root).getData();
	}
	private BinaryTreeNode<T> findMin(BinaryTreeNode node) {
		if (node.getLeft() == null) {
			return node;
		}
		return findMin(node.getLeft());
	}
	public T findMax(){
		BinaryTreeNode<T> min = root.getRight();
		if (min == null) {
			return root.getData();
		}
		while (min.getRight() != null) {
			min = min.getRight();
		}
		return min.getData();
	}
	public int height() {
		return computerChildHeight(root);
	}

	private int computerChildHeight(BinaryTreeNode<T> node) {
		if (node == null) {
			return 0;
		}

		int leftHeight = computerChildHeight(node.getLeft());
		int rightHeight = computerChildHeight(node.getRight());
		return Math.max(leftHeight + 1, rightHeight + 1);
	}

	private int computerChildSize(BinaryTreeNode<T> node) {
		if (node == null) {
			return 0;
		}

		int leftHeight = computerChildSize(node.getLeft());
		int rightHeight = computerChildSize(node.getRight());
		return leftHeight + rightHeight + 1;
	}

	public int size() {
		return computerChildSize(root);
	}
	public void remove(T e){
		remove(e, root);

	}

	private BinaryTreeNode<T> remove( T t, BinaryTreeNode<T> node) {
		if(node == null)
			return node;//没有找到,doNothing
		int result = t.compareTo(node.data);
		if(result>0)
			node.right = remove(t,node.right);
		else if(result<0)
			node.left = remove(t,node.left);
		else if(node.left!=null&&node.right!=null)
		{
			node.data = findMin(node.right).data;
			remove(node.data,node.right);
		}
		else
			node = (node.left!=null)?node.left:node.right;
		return node;
	}

	public List<T> levelVisit(){
		List<T> nodes = new ArrayList<>();
		ArrayList<BinaryTreeNode<T>> currentLevelNodes = new ArrayList<>();
		if (root == null) {
			return nodes;
		}
		currentLevelNodes.add(root);
		return visit(nodes, currentLevelNodes);

	}

	private List<T> visit(List<T> nodes, ArrayList<BinaryTreeNode<T>> currentLevelNodes) {
		if (currentLevelNodes.size() == 0) {
			return nodes;
		}

		for (BinaryTreeNode<T> node : currentLevelNodes) {
			nodes.add(node.data);
		}
		for (BinaryTreeNode<T> node : currentLevelNodes) {
			ArrayList<BinaryTreeNode<T>> childLevelNodes = new ArrayList<>();
			if (node.getLeft() != null) {
				childLevelNodes.add(node.getLeft());
			}
			if (node.getRight() != null) {
				childLevelNodes.add(node.getRight());
			}
			visit(nodes, childLevelNodes);
		}
		return nodes;
	}

	public boolean isValid(){
		return validChild(root);
	}

	private boolean validChild(BinaryTreeNode<T> root) {
		if (root == null) {
			return true;
		}
		if (validChild(root.getLeft()) && validChild(root.getRight())) {
			return true;
		}
		return false;
	}

	public T getLowestCommonAncestor(T n1, T n2){
		isAncestor(root, n1, n2);
		return null;
        
	}

	private BinaryTreeNode<T> isAncestor(BinaryTreeNode<T> node, T n1, T n2) {
		int result1 = node.data.compareTo(n1);
		int result2 = node.data.compareTo(n2);
		if (result1 > 0 && result2 > 0) {
			return isAncestor(node.getLeft(), n1, n2);
		}
		if (result1 < 0 && result2 < 0) {
			return isAncestor(node.getRight(), n1, n2);
		}
		return node;
	}

	public List<T> getNodesBetween(T n1, T n2){
		ArrayList<T> list = new ArrayList<>();
		isNodeBetweenTwoParam(root, n1, n2, list);
		return list;
	}

	private void isNodeBetweenTwoParam(BinaryTreeNode<T> node, T n1, T n2, List<T> list) {
		if (node == null) {
			return;
		}
		int result1 = node.data.compareTo(n1);
		int result2 = node.data.compareTo(n2);
		if (result1 * result2 < 0) {
			list.add(node.data);
			isNodeBetweenTwoParam(node.getRight(), n1, n2, list);
			isNodeBetweenTwoParam(node.getLeft(), n1, n2, list);
		}
		if (result1 < 0 && result2 < 0) {
			isNodeBetweenTwoParam(node.getRight(), n1, n2, list);
		}
		if (result1 > 0 && result2 > 0) {
			isNodeBetweenTwoParam(node.getLeft(), n1, n2, list);
		}
		if (result1 == 0) {
			if (n2.compareTo(n1) > 0) {
				isNodeBetweenTwoParam(node.getRight(), n1, n2, list);
			} else {
				isNodeBetweenTwoParam(node.getLeft(), n1, n2, list);
			}
		}
		if (result2 == 0) {
			if (n1.compareTo(n2) > 0) {
				isNodeBetweenTwoParam(node.getRight(), n1, n2, list);
			} else {
				isNodeBetweenTwoParam(node.getLeft(), n1, n2, list);
			}
		}

	}

}

