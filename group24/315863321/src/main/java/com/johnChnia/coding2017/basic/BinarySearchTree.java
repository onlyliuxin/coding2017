package com.johnChnia.coding2017.basic;

import com.johnChnia.coding2017.basic.queue.Queue;

/**
 * Created by john on 2017/3/13.
 */
public class BinarySearchTree {


    /**
     * The root node of tree.
     */
    public BstNode root;

    private Queue q = new Queue();


    private static class BstNode {
        private int data;
        private BstNode left;
        private BstNode right;

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    /**
     * create an BinarySearchTree.
     *
     * @param root root node of tree
     * @param data stored element
     * @return binarySearchTree
     */
    public BstNode insert(BstNode root, int data) {
        if (root == null) {
            root = getNewBstNode(data);
            if (this.root == null) {
                this.root = root;
            }
            return root;
        }
        if (data <= root.data) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }
        return root;
    }

    private BstNode getNewBstNode(int data) {
        BstNode node = new BstNode();
        node.data = data;
        node.left = null;
        node.right = null;
        return node;
    }

    /**
     * Returns the minimum value in the tree.
     *
     * @param root root node of the tree。
     * @return the minimum value in the tree
     * @throws IllegalArgumentException if root is null.
     */
    public int findMin(BstNode root) {
        int min;
        if (root == null) {
            throw new IllegalArgumentException("tree is empty");
        } else if (root.left == null) {
            min = root.data;
        } else {
            min = findMin(root.left);
        }
        return min;
    }

    /**
     * Returns the maximum value in the tree.
     *
     * @param root root node of the tree。
     * @return the maximum value in the tree
     * @throws IllegalArgumentException if root is null.
     */
    public int findMax(BstNode root) {
        int max;
        if (root == null) {
            throw new IllegalArgumentException("tree is empty");
        } else if (root.right == null) {
            max = root.data;
        } else {
            max = findMax(root.right);
        }
        return max;
    }


    /**
     * Traverse each node from left to right.
     *
     * @param root root node of the tree
     */
    public void LevelOrder(BstNode root) {
        if (root == null) {
            return;
        }
        q.add(root);
        while (!q.empty()) {
            BstNode current = (BstNode) q.peek();
            if (current.left != null) {
                q.add(current.left);
            }
            if (current.right != null) {
                q.add(current.right);
            }
            q.remove();
        }
    }

    public BstNode getRoot() {
        return root;
    }

}
