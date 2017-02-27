package com.pop.practice.homework.first.tree;

import java.io.Serializable;

import com.pop.practice.homework.first.collection.queue.LinkQueue;
import com.pop.practice.homework.first.collection.queue.Queue;

/**
 * 左大右小
 * 
 * @author haipop Date: 17-2-20 Time: 上午9:42
 */
public class BinaryTree<T extends Comparable> extends AbstractTree<T> implements Serializable {

    private static final long serialVersionUID = -1467347526158258388L;

    /**
     * 根节点
     */
    private BinaryNode root;

    @Override
    @SuppressWarnings("unchecked")
    protected void addNode(T elements) throws IllegalAccessException {
        if (root == null) {
            root = new BinaryNode<T>(elements, null, null);
        }
        BinaryNode<T> newNode = new BinaryNode<>(elements, null, null);
        Queue<BinaryNode> queue = new LinkQueue<>();
        queue.add(root);
        while (queue.isEmpty()) {
            BinaryNode node = queue.pull();
            if (((Comparable) node.getData()).compareTo(elements) > 0) {
                if (node.getRight() == null) {
                    node.setRight(newNode);
                    break;
                } else {
                    queue.add(node.getRight());
                }
            } else if (((Comparable) node.getData()).compareTo(elements) < 0) {
                if (node.getLeft() == null) {
                    node.setLeft(newNode);
                    break;
                } else {
                    queue.add(node.getLeft());
                }
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeNode(T elements) throws IllegalAccessException {
        if (root == null) {
            return;
        }
        root = removeNode(elements, root);
    }

    @SuppressWarnings("unchecked")
    private BinaryNode removeNode(T element, BinaryNode<T> node) {
        BinaryNode left = node.getLeft();
        BinaryNode right = node.getRight();
        if (node.getData().compareTo(element) > 0) {
            BinaryNode removeNode = removeNode(element, right);
            moveNode(element, node, left, right, removeNode);
            node.setRight(removeNode);
        } else if (node.getData().compareTo(element) < 0) {
            BinaryNode removeNode = removeNode(element, left);
            moveNode(element, node, left, right, removeNode);
            node.setLeft(removeNode);
        }
        if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private void moveNode(T element, BinaryNode<T> node, BinaryNode<T> left, BinaryNode<T> right,
            BinaryNode<T> removeNode) {
        if (removeNode.getData().compareTo(right) > 0) {
            BinaryNode tmp = removeNode.getRight();
            while (tmp != null) {
                tmp = tmp.getRight();
            }
            tmp.setRight(left.getRight());
        } else if (node.getData().compareTo(element) < 0) {
            BinaryNode tmp = removeNode.getLeft();
            while (tmp != null) {
                tmp = tmp.getLeft();
            }
            tmp.setLeft(right.getLeft());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contain(T data) throws IllegalAccessException {
        if (root == null) {
            return false;
        }
        Queue<BinaryNode> queue = new LinkQueue<>();
        queue.add(root);
        while (queue.isEmpty()) {
            BinaryNode node = queue.pull();
            if (((Comparable) node.getData()).compareTo(data) > 0) {
                if (node.getRight() != null) {
                    queue.add(node.getRight());
                }
            } else if (((Comparable) node.getData()).compareTo(data) < 0) {
                if (node.getLeft() == null) {
                    queue.add(node.getLeft());
                }
            } else {
                return true;
            }
        }
        return false;
    }

}