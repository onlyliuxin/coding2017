package tree;

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
		
		BinaryTreeNode<T> node = getRoot();
		while(node.getLeft() != null) {
			node = node.getLeft();
		}
		
		return node.getData();
	}
	
	public T findMax(){
		
		BinaryTreeNode<T> node = getRoot();
		while(node.getRight() != null) {
			node = node.getRight();
		}
		
		return node.getData();
	}
	
	public int height() {
		
	    return getHeight(getRoot());
	}
	
	private int getHeight(BinaryTreeNode<T> node) {
		
		if (node == null) {
			return 0;
		}
		
		return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
		
	}
	
	public int size() {
		
		BinaryTreeNode<T> node = getRoot();
		List<T> nodes = BinaryTreeUtil.inOrderVisit(node);
		
		return nodes.size();
	}
	
	private BinaryTreeNode<T> find(BinaryTreeNode<T> root, T e) {
		
		if (root == null) {
			return null;
		}

		int compare = root.data.compareTo(e);
		
		if (compare == 0) {
			System.out.println(root.data);
			return root;
		}
		
		if (compare > 0) {
			find (root.getLeft(),e);
		} else {
			find (root.getRight(),e);
		}
		
		return null;
	}
	
	
	
	public void remove(T e){
		
		BinaryTreeNode<T> root = getRoot();
		BinaryTreeNode<T> node = find(root,e);
		if (node == null) {
			return;
		}
		
		BinaryTreeNode<T> pNode = getRoot();
		while(node.getData() != e) {
			pNode = node;
			if ((int)node.getData() - (int)e > 0 ) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}
		
		boolean left = false;

		if ((int)pNode.getLeft().getData() == (int)e) {
			left = true;
		}
			
		if (getHeight(node) == 1) {
			if (left) {
				pNode.setLeft(null);
			} else {
				pNode.setRight(null);
			}
			return;
		}

		
		int leftH = getHeight(node.getLeft());
		int rightH = getHeight(node.getRight());
		
		if (leftH >= rightH) {
			if (left) {
				pNode.setLeft(node.getLeft());		
			} else {
				pNode.setRight(node.getLeft());
			}
			
			if (node.getRight() != null) {
				System.out.println("1 ok");
				BinaryTreeNode<T> r = node.getLeft();
				while(r.getRight() != null) {
					r = node.getRight();
				}
				r.setRight(node.getRight());
				
			}

		} else {
			if (left) {
				pNode.setLeft(node.getRight());
			} else {
				pNode.setRight(node.getRight());
			}
			
			if (node.getLeft() != null) {
				System.out.println("2 ok");
				BinaryTreeNode<T> r = node.getRight();
				while(r.getLeft() != null) {
					r = node.getLeft();
				}
				r.setLeft(node.getLeft());
				
			}
			
		}
		
		
		
		
	}
	
}

