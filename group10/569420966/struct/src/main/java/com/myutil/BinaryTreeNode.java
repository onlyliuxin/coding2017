package com.myutil;

import java.util.Random;

/**
 * 二叉树
 */
public class BinaryTreeNode<T extends Comparable<T>> {
    private T element;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
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

    /**
     * 将元素插入二叉树
     *
     * @param element 元素
     * @return 插入后的节点
     */
    public BinaryTreeNode<T> insert(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element must be not null.");
        }

        BinaryTreeNode<T> currentNode = null;
        if (this.element == null) {
            currentNode = this;
            currentNode.element = element;
        } else {
            currentNode = compareToElement(element, this);
        }

        return currentNode;
    }

    private BinaryTreeNode<T> compareToElement(T element, BinaryTreeNode<T> curr) {
        if (element.compareTo(curr.element) == -1) {
            if (curr.left == null) {
                BinaryTreeNode<T> node = new BinaryTreeNode<>();
                node.element = element;
                curr.left = node;
                return node;
            } else {
                return compareToElement(element, curr.left);
            }
        } else {
            if (curr.right == null) {
                BinaryTreeNode<T> node = new BinaryTreeNode<>();
                node.element = element;
                curr.right = node;
                return node;
            } else {
                return compareToElement(element, curr.right);
            }
        }
    }

    /**
     * 先序遍历
     *
     * @return 按先序遍历顺序展示节点值
     */
    public String preOrderTraversal() {
        return concatPreOrder(this);
    }

    private String concatPreOrder(BinaryTreeNode<T> node) {
        StringBuilder ret = new StringBuilder();
        if (node.left != null) {
            ret.append(concatPreOrder(node.left));
        }

        ret.append(node.element).append(" ");

        if (node.right != null) {
            ret.append(concatPreOrder(node.right));
        }

        return ret.toString();
    }

    /**
     * 中序遍历
     *
     * @return 按中序遍历顺序展示节点值
     */
    public String inOrderTraversal() {
        return concatInOrder(this);
    }

    private String concatInOrder(BinaryTreeNode<T> node) {
        StringBuilder ret = new StringBuilder();

        ret.append(node.element).append(" ");

        if (node.left != null) {
            ret.append(concatInOrder(node.left));
        }

        if (node.right != null) {
            ret.append(concatInOrder(node.right));
        }

        return ret.toString();
    }

    /**
     * 后序遍历
     *
     * @return 按后序遍历顺序展示节点值
     */
    public String postOrderTraversal() {
        return concatPostOrder(this);
    }

    private String concatPostOrder(BinaryTreeNode<T> node) {
        StringBuilder ret = new StringBuilder();

        if (node.right != null) {
            ret.append(concatPostOrder(node.right));
        }

        ret.append(node.element).append(" ");

        if (node.left != null) {
            ret.append(concatPostOrder(node.left));
        }

        return ret.toString();
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> binaryTree = new BinaryTreeNode<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            binaryTree.insert(random.nextInt(100));
        }


        System.out.println(binaryTree.preOrderTraversal());
        System.out.println(binaryTree.inOrderTraversal());
        System.out.println(binaryTree.postOrderTraversal());
    }
}
