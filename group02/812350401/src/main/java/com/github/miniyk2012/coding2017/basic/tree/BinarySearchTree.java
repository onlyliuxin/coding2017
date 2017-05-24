package com.github.miniyk2012.coding2017.basic.tree;

import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}

	public T findMin() {
	    if (root == null) {
	        throw new NoSuchElementException("根节点为空");
        }

        return findMin(root);
	}

	private T findMin(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> p = root;
        while (p.left!=null) {
            p = p.left;
        }
        return p.getData();
    }

	public T findMax(){
        if (root == null) {
            throw new NoSuchElementException("根节点为空");
        }
        return findMax(root);
	}

	private T findMax(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> p = root;
        while (p.right!=null) {
            p = p.right;
        }
        return p.getData();
    }

	public int height() {
        if (root == null) {
            return 0;
        }
        return getHeight(root);
	}

	private int getHeight(BinaryTreeNode<T> root) {
	    if (root == null) {
	        return 0;
        }
        int leftHeight = getHeight(root.left);
	    int rightHeight = getHeight(root.right);
	    return 1 + (leftHeight>rightHeight?leftHeight:rightHeight);
    }

	public int size() {
		if (root == null) {
		    return 0;
        }
        return getSize(root);
	}

	private int getSize(BinaryTreeNode<T> root) {
	    if (root == null) {
	        return 0;
        }
        int leftSize = getSize(root.left);
        int rightSize = getSize(root.right);
        return 1 + leftSize + rightSize;
    }
	public void remove(T e){
        if (root == null) {
            throw new NoSuchElementException("根节点为空");
        }
        root = remove(e, root);
	}

	private BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> root) {
        if (root == null) {
            throw new NoSuchElementException("不存在该元素，删除失败");
        }
        int cmp = root.data.compareTo(e);
        if (cmp == 0) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            T min = findMin(root.right);
            root.data = min;
            root.right = deleteMin(root.right);
        } else if (cmp < 0) {
            root.right = remove(e, root.right);
        } else {
            root.left = remove(e, root.left);
        }
        return root;
    }

    private BinaryTreeNode<T> deleteMin(BinaryTreeNode<T> root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = deleteMin(root.left);
        return root;
    }

    public List<T> levelVisit(){

        return null;
    }
    public boolean isValid(){
        return false;
    }
    public T getLowestCommonAncestor(T n1, T n2){
        return null;

    }
    public List<T> getNodesBetween(T n1, T n2){
        return null;
    }
}

