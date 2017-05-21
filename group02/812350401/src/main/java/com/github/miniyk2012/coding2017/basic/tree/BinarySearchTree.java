package com.github.miniyk2012.coding2017.basic.tree;

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
        remove(e, root);
	}

	private BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> root) {
        if (root == null) {
            throw new NoSuchElementException("不存在该元素，删除失败");
        }
        if (root.data.compareTo(e) == 0) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.right == null && root.left != null) {
                return root.left;
            } else if (root.right != null && root.left == null) {
                return root.right;
            } else {
                BinaryTreeNode<T> minParent = findMinParent(root.right);
                root.data = minParent.left.data;
                minParent.left = null;
                return root;
            }
        } else if (root.data.compareTo(e) < 0) {
            root.right = remove(e, root.right);
            return root;
        } else {
            root.left = remove(e, root.left);
            return root;
        }
    }

    private BinaryTreeNode<T> findMinParent(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> p = root;
        BinaryTreeNode<T> parent = null;
        while (p.left!=null) {
            parent = p;
            p = p.left;
        }
        return parent;
    }
	
}

