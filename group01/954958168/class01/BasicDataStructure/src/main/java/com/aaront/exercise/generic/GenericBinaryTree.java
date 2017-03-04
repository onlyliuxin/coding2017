package com.aaront.exercise.generic;

import java.util.Arrays;

public class GenericBinaryTree<T extends Comparable<T>> {

    private BinaryTreeNode<T> head = new BinaryTreeNode<>(null);
    private BinaryTreeNode<T> root;
    private int size;
    private int index = 0;
    public static final int PREORDER = 0;
    public static final int INORDER = 1;
    public static final int POSTORDER = 2;
    public static final int HIERARCHICAL = 3;

    public static final int RECURSION = 10;
    public static final int ITERATION = 11;

    public void add(T o) {
        BinaryTreeNode<T> node = new BinaryTreeNode<>(o);
        if (root == null) {
            root = node;
            head.setLeft(root);
        } else {
            insert(root, node);
        }
        size++;
    }

    private void insert(BinaryTreeNode<T> node, BinaryTreeNode<T> newNode) {
        // 要插入的节点插入当前节点的左子树
        if (node.getData().compareTo(newNode.getData()) > 0) {
            if (node.getLeft() == null) {
                node.setLeft(newNode);
            } else {
                insert(node.left, newNode);
            }
        } else { // 要插入的节点插入当前节点的右子树
            if (node.getRight() == null) {
                node.setRight(newNode);
            } else {
                insert(node.right, newNode);
            }
        }
    }

    public BinaryTreeNode<T> search(T data) {
        return search(data, ITERATION);
    }

    public BinaryTreeNode<T> search(T data, int method) {
        switch (method) {
            case RECURSION:
                return findNodeRecursion(root, data);
            case ITERATION:
                return findNodeIteration(data);
            default:
                throw new IllegalArgumentException("不支持的查找方法");
        }
    }

    private BinaryTreeNode<T> findNodeRecursion(BinaryTreeNode<T> node, T data) {
        if (node == null) return null;
        if (node.getData().compareTo(data) == 0) return node;
        if (node.getData().compareTo(data) > 0) return findNodeRecursion(node.getLeft(), data);
        return findNodeRecursion(node.getRight(), data);
    }

    private BinaryTreeNode<T> findNodeIteration(T data) {
        BinaryTreeNode<T> currentNode = root;
        while (currentNode != null) {
            if (currentNode.getData().compareTo(data) == 0) {
                return currentNode;
            }
            if (currentNode.getData().compareTo(data) > 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }
        return null;
    }

    public BinaryTreeNode<T> min() {
        return findMin(root);
    }

    private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        if (node == null) return null;
        if (node.getLeft() == null) return node;
        return findMin(node.getLeft());
    }

    public BinaryTreeNode<T> max() {
        return findMax(root);
    }

    private BinaryTreeNode<T> findMax(BinaryTreeNode<T> node) {
        if (node == null) return null;
        if (node.getRight() == null) return node;
        return findMax(node.getRight());
    }

    public void delete(T data) {
        BinaryTreeNode<T> node = search(data);
        if (node == null) return;
        BinaryTreeNode<T> parentNode = searchParentNode(node);
        if (parentNode == null) return;
        // 删除叶子节点
        if (node.getLeft() == null && node.getRight() == null) {
            if (parentNode.getLeft() == node) parentNode.setLeft(null);
            else parentNode.setRight(null);
        } else if (node.getLeft() != null && node.getRight() == null) { // 删除只有左子树的节点
            if (parentNode.getLeft() == node) parentNode.setLeft(node.getLeft());
            else parentNode.setRight(node.getLeft());
        } else if (node.getRight() != null && node.getLeft() == null) { // 删除只有右子树的节点
            if (parentNode.getLeft() == node) parentNode.setLeft(node.getRight());
            else parentNode.setRight(node.getRight());
        } else { // 删除有两个子树的节点
            BinaryTreeNode<T> replace = findMin(node.getRight());
            BinaryTreeNode<T> replaceParentNode = searchParentNode(replace);
            replaceParentNode.setLeft(replace.getRight());
            node.setData(replace.getData());
            replace.setLeft(null);
            replace.setRight(null);
        }
        size--;
    }

    private BinaryTreeNode<T> searchParentNode(BinaryTreeNode<T> node) {
        if (node == null) return null;
        if (node == root) return head;
        BinaryTreeNode<T> current = root;
        while (current != null) {
            if (current.getLeft() == node || current.getRight() == node) return current;
            if (current.getData().compareTo(node.getData()) > 0) current = current.getLeft();
            else current = current.getRight();
        }
        return null;
    }

    public Object[] traversal() {
        return traversal(PREORDER);
    }

    public T[] traversal(T[] a) {
        Object[] elementData = traversal(PREORDER);
        return toArray(elementData, a);
    }

    public T[] traversal(int order, T[] a) {
        Object[] elementData = traversal(order);
        return toArray(elementData, a);
    }

    private T[] toArray(Object[] elementData, T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        return a;
    }

    public Object[] traversal(int order) {
        Object[] datas = new Object[size];
        if (order == PREORDER) {
            preorderTraversal(root, datas);
        } else if (order == INORDER) {
            inorderTraversal(root, datas);
        } else if (order == POSTORDER) {
            postorderTraversal(root, datas);
        } else {
            hierarchicalTraversal(root, datas);
        }
        index = 0;
        return datas;
    }

    private void preorderTraversal(BinaryTreeNode<T> node, Object[] datas) {
        if (node == null) {
            return;
        }

        datas[index++] = node.getData();
        preorderTraversal(node.getLeft(), datas);
        preorderTraversal(node.getRight(), datas);
    }

    private void inorderTraversal(BinaryTreeNode<T> node, Object[] datas) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.getLeft(), datas);
        datas[index++] = node.getData();
        inorderTraversal(node.getRight(), datas);
    }

    private void postorderTraversal(BinaryTreeNode<T> node, Object[] datas) {
        if (node == null) {
            return;
        }

        postorderTraversal(node.getLeft(), datas);
        postorderTraversal(node.getRight(), datas);
        datas[index++] = node.getData();
    }

    private void hierarchicalTraversal(BinaryTreeNode<T> node, Object[] datas) {
        if (node == null) return;
        GenericQueue<BinaryTreeNode<T>> queue = new GenericQueue<>();
        queue.enQueue(node);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> tmp =  queue.deQueue();
            datas[index++] = tmp.getData();
            if (tmp.getLeft() != null) queue.enQueue(tmp.getLeft());
            if (tmp.getRight() != null) queue.enQueue(tmp.getRight());
        }
    }


    class BinaryTreeNode<T extends Comparable<T>> {
        private T data;
        private BinaryTreeNode<T> left;
        private BinaryTreeNode<T> right;

        public BinaryTreeNode(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public BinaryTreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(BinaryTreeNode<T> left) {
            this.left = left;
        }

        public BinaryTreeNode<T> getRight() {
            return right;
        }

        public void setRight(BinaryTreeNode<T> right) {
            this.right = right;
        }
    }
}
