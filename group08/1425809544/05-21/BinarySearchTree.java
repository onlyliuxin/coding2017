package com.zhuoyue.scheduleplan.domain;

/**
 * @author xyy
 * @create 2017-05-26 15:50
 * @deprecated 二叉查找树是一个二叉树, 其中每个节点都含有一个Comparable的值,
 * 每个节点的值大于左子树的所有值,小于右子树的所有值
 **/
public class BinarySearchTree<T extends Comparable> {


    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        if (root == null) {
            return null;
        }
        return findMin(root).getData();
    }

    private BinaryTreeNode<T> findMin(BinaryTreeNode<T> root) {

        if (root == null) {
            return null;
        } else if (root.getLeft() == null) {
            return root;
        } else {
            return findMin(root.getLeft());
        }
    }

    public T findMax() {
        if (root == null) {
            return null;
        }
        return findMax(root).getData();
    }

    private BinaryTreeNode<T> findMax(BinaryTreeNode<T> root) {

        if (root == null) {
            return null;
        } else if (root.getRight() == null) {
            return root;
        } else {
            return findMax(root.getRight());
        }

    }

    public int height() {
        return height(root);
    }

    private int height(BinaryTreeNode<T> root) {
        if (root == null) {
            return 0;
        } else {
            int leftChildHeight = height(root.getLeft());
            System.out.println(leftChildHeight);
            int rigthChildHeight = height(root.getRight());
            System.out.println(rigthChildHeight);
            if (leftChildHeight > rigthChildHeight) {
                return leftChildHeight + 1;
            } else {
                return rigthChildHeight + 1;
            }
        }
    }

    public int size() {
        return size(root);
    }

    private int size(BinaryTreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        return size(root.getLeft()) + 1 + size(root.getRight());
    }

    public void remove(T e) {
        remove(e, root);
    }

    //暂时没看懂
    private BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> root) {
        if (root == null) {
            return root;
        }
        int compareResult = e.compareTo(root.data);

        if (compareResult < 0) {
            root.left = remove(e, root.getLeft());
        } else if (compareResult > 0) {
            root.right = remove(e, root.getRight());
        } else {
            if (root.getLeft() != null && root.getRight() != null) {
                root.data = findMin(root.getRight()).data;
                root.right = remove(root.data, root.right);
            } else {
                root = (root.left != null) ? root.left : root.right;
            }
        }


        return root;


    }
}
