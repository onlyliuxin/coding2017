package com.coding.basic;

public class BinaryTree<E extends Comparable<? super E>> {
    BinaryTreeNode root = null;

    public BinaryTree() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(E e) {
        root = insert(root, e);
    }

    private BinaryTreeNode<E> insert(BinaryTreeNode<E> node, E e) {
        if (node == null) {
            node = new BinaryTreeNode(e, null, null);
        }
        int compareResult = ((Comparable) e).compareTo(node.data);
        if (compareResult < 0) {
            node.left = insert(node.left, e);
        } else if (compareResult > 0) {
            node.right = insert(node.right, e);
        }
        return node;
    }

    public void clear() {
        root = null;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(BinaryTreeNode<E> node, E e) {
        if (node == null) {
            return false;
        }
        int compareResult = ((Comparable) e).compareTo(node.data);
        if (compareResult < 0) {
            return contains(node.left, e);
        } else if (compareResult > 0) {
            return contains(node.right, e);
        }
        // matching
        return true;
    }

    private BinaryTreeNode<E> findMin(BinaryTreeNode<E> node) {
        if (node != null) {
            while (node.left != null) {
                node = node.right;
            }
        }
        return node;
    }

    private BinaryTreeNode<E> findMax(BinaryTreeNode<E> node) {
        if (node != null) {
            while (node.right != null) {
                node = node.right;
            }
        }
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private BinaryTreeNode<E> remove(BinaryTreeNode<E> node, E e) {
        if (node == null) {
            return node;
        }
        int compareResult = ((Comparable) e).compareTo(node.data);
        if (compareResult < 0) {
            node.left = remove(node.left, e);
        } else if (compareResult > 0) {
            node.right = remove(node.right, e);
        }
        // matching
        if (node.left != null && node.right != null) { // two children
            node.data = (E) findMax(node.right).data;
            node.right = remove(node.right, node.data);
        } else { // one child
            node = (node.left != null) ? node.left : node.right;
        }
        return node;
    }

    private class BinaryTreeNode<E> {
        E data;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(E e, BinaryTreeNode<E> l, BinaryTreeNode<E> r) {
            data = e;
            left = l;
            right = r;
        }
    }
}
