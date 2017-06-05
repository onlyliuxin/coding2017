package com.github.miniyk2012.coding2017.basic.tree;

import java.util.*;

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

    /**
     * 树的层序遍历
     * @return
     */
    public List<T> levelVisit(){
        return levelVisit(root);
    }

    private List<T> levelVisit(BinaryTreeNode<T> root) {
        ArrayDeque<BinaryTreeNode<T>> queue = new ArrayDeque<>();
        List<T> list = new LinkedList<>();
        if (root == null) return list;
        queue.add(root);
        BinaryTreeNode<T> head;
        while (!queue.isEmpty()) {
            head = queue.remove();
            if (head.left != null) {
                queue.add(head.left);
            }
            if (head.right != null) {
                queue.add(head.right);
            }
            list.add(head.getData());
        }
        return list;
    }

    /**
     * 判断该二叉树是否是查找二叉树
     * @return
     */
    public boolean isValid(){
        if (root == null) return true;
        BinarySearchTree<T> leftTree = new BinarySearchTree<>(root.left);
        BinarySearchTree<T> rightTree = new BinarySearchTree<>(root.right);
        boolean isValid = true;
        if (root.left != null) {
            if (!leftTree.isValid() || root.data.compareTo(leftTree.findMax())<0) {
                isValid = false;
            }
        }
        if (isValid && root.right != null) {
            if (!rightTree.isValid() || root.data.compareTo(rightTree.findMin())>0) {
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * 返回n1, n2的最低公共父节点(n1, n2分别为搜索二叉树中某节点的值，只考虑各节点值不同的情况, 且n1, n2是不同的节点值)
     * @param n1
     * @param n2
     * @return
     */
    public T getLowestCommonAncestor(T n1, T n2){
        return  getLowestCommonAncestor(root, n1, n2);
    }

    private T getLowestCommonAncestor(BinaryTreeNode<T> root, T n1, T n2) {
        if (root == null) {
            return null;
        }
        if (n1.compareTo(root.data)>0 && n2.compareTo(root.data)>0) {
            return getLowestCommonAncestor(root.right, n1, n2);
        }
        if (n1.compareTo(root.data)<0 && n2.compareTo(root.data)<0) {
            return getLowestCommonAncestor(root.left, n1, n2);
        }
        return root.data;
    }

    /**
     * 返回一个数组，每个元素e满足n1<e<n2（n1, n2为任意值，不一定是某节点的值）
     * @param n1
     * @param n2
     * @return
     */
    public List<T> getNodesBetween(T n1, T n2){
        List<T> elements = new ArrayList<>();
        getNodesBetween(elements, root, n1, n2);
        return elements;
    }

    private void getNodesBetween(List<T> elements, BinaryTreeNode<T> root, T n1, T n2) {
        if (root == null) {
            return;
        }
        // 若根节点在范围内，把根节点加入element
        if (n1.compareTo(root.data)<0 && n2.compareTo(root.data)>0) {
            elements.add(root.data);
        }
        // 找左子树的满足范围的节点
        if (n1.compareTo(root.data)<0) {
            getNodesBetween(elements, root.left, n1, n2);
        }
        // 找右子树的满足范围的节点
        if (n2.compareTo(root.data)>0) {
            getNodesBetween(elements, root.right, n1, n2);
        }
    }
}

