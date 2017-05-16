package me.lzb.basic.tree;

/**
 * @author LZB
 */
public class BinarySearchTree<T extends Comparable<T>> {
    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        if (this.root == null) {
            throw new RuntimeException("empty tree");
        }
        BinaryTreeNode<T> result = this.root;
        while (result.getLeft() != null) {
            result = result.getLeft();
        }
        return result.getData();
    }

    public T findMax() {
        if (this.root == null) {
            throw new RuntimeException("empty tree");
        }
        BinaryTreeNode<T> result = this.root;
        while (result.getRight() != null) {
            result = result.getRight();
        }
        return result.getData();
    }

    public int height() {


        return -1;
    }

    public int size() {
        return -1;
    }

    public void remove(T e) {

    }

}
