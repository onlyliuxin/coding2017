package basic.dataStructure.binaryTree;

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
        return findMin(root);
    }

    private T findMin(BinaryTreeNode<T> root){
        List<T> list = BinaryTreeUtil.preOrderVisit(root);
        T min = list.get(0);
        for (T t : list) {
            min = min.compareTo(t) == -1 ? min : t;
        }
        return min;
    }

    public T findMax() {
        return findMax(root);
    }

    private T findMax(BinaryTreeNode<T> root){
        List<T> list = BinaryTreeUtil.preOrderVisit(root);
        T max = list.get(0);
        for (T t : list) {
            max = max.compareTo(t) == 1 ? max : t;
        }
        return max;
    }

    public int height() {
        return calHeight(root);
    }

    private int calHeight(BinaryTreeNode<T> root) {
        if (root == null) return 0;

        int left = calHeight(root.left);
        int right = calHeight(root.right);

        return (left > right ? left : right) + 1;

    }

    public int size() {
        List<T> list = BinaryTreeUtil.preOrderVisit(root);
        return list.size();
    }

    public void remove(T e) {
        remove(root, (Integer) e);
    }

    private void remove(BinaryTreeNode<T> node, int value) {
        if (node == null) throw new RuntimeException("no such node has value = " + value);

        T data = node.getData();
        if (data.compareTo(value) == 0) {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left != null && node.right == null) {
                node.data = node.left.data;
                node.left = null;
            } else if (node.left == null && node.right != null) {
                node.data = node.right.data;
                node.right = null;
            } else {
                T replace = findMin(node.right);
                node.data = replace;
                remove(node.right, (Integer)replace);
            }
        } else if (data.compareTo(value) == -1) {
            remove(node.right, value);
        } else {
            remove(node.left, value);
        }
    }

}

