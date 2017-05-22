package com.pan.tree;

import com.pan.queue.Queue;

import java.util.ArrayList;
import java.util.List;

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
        return findMin(root);
    }

    private T findMin(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.data;
        }
        return findMin(node.left);
    }

    public T findMax() {
        if (root == null) {
            return null;
        }
        return findMax(root);
    }

    private T findMax(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.data;
        }
        return findMax(node.right);
    }


    public int height() {
        if (root == null) {
            return -1;
        }
        return height(root);
    }

    /**
     * 计算二叉树的高度
     *
     * @param node
     * @return
     */
    private int height(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftChildHeight = height(node.left);
        int rightChildHeight = height(node.right);

        // +1 因为包含node本身
        if (leftChildHeight > rightChildHeight) {
            return leftChildHeight + 1;
        } else {
            return rightChildHeight + 1;
        }
    }

    /**
     * 计算二叉树的大小
     *
     * @return
     */
    public int size() {
        if (root == null) {
            return -1;
        }
        return size(root);
    }

    private int size(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return size(node.left) + size(node.right) + 1;
    }

    public void remove(T x) {
        remove(x, root);
    }

    /**
     * 移出节点
     * @param x
     * @param node
     * @return
     */
    private BinaryTreeNode<T> remove(T x, BinaryTreeNode<T> node) {
        if (node == null) {
            return node;
        }
        int compareResult = x.compareTo(node.data);
        if (compareResult < 0) {
            node.left = remove(x, node.left);
        } else if (compareResult > 0) {
            node.right = remove(x, node.right);
        } else {
            if (node.left != null && node.right != null) {
                node.data = findMin(node.right);
                node.right = remove(node.data, node.right);
            } else {
                node = (node.left != null) ? node.left : node.right;
            }
        }
        return node;
    }

    public List<T> levelVisit() {
        List<T> result = new ArrayList<T>();
        Queue<BinaryTreeNode<T>> queue = new Queue<>();
        BinaryTreeNode<T> node = root;
        if (node != null) {
            queue.enQueue(node);
            while (!queue.isEmpty()) {
                node = queue.deQueue();
                result.add(node.data);
                if (node.left != null) {
                    queue.enQueue(node.left);
                }
                if (node.right != null) {
                    queue.enQueue(node.right);
                }
            }
        }
        return result;
    }

    public boolean isValid() {
        return false;
    }

    /**
     *
    * @param n1
     * @param n2
     * @return
     */
    public T getLowestCommonAncestor(T n1, T n2) {
        return null;

    }

    public List<T> getNodesBetween(T n1, T n2) {
        return null;
    }

}

