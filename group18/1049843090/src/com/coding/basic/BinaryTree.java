package com.coding.basic;


/**
 *
 */
public class BinaryTree {

    private Node root;

    public boolean insert(int data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return true;
        }

        return add(data);
        //return add(root,new Node(data));
    }

    private boolean add(Node currentNode,Node newNode) {
        if (currentNode.data == newNode.data) {
            return false;
        }
        if (currentNode.data < newNode.data) {
            if (currentNode.right == null) {
                currentNode.right = newNode;
                return true;
            } else {
                return add(currentNode.right, newNode);
            }
        }
        if (currentNode.left == null) {
            currentNode.left = newNode;
            return true;
        } else {
            return add(currentNode.left, newNode);
        }
    }

    private boolean add(int data) {
        Node newNode = new Node(data);
        boolean result = false;
        Node cursorNode = root;
        Node parentNode = null;
        while (true) {
            parentNode = cursorNode;
            if (cursorNode.data == data) {
                break;
            }
            if (cursorNode.data < data) {
                cursorNode = cursorNode.right;
                if (cursorNode == null) {
                    parentNode.right = newNode;
                    result = true;
                    break;
                }
            } else {
                cursorNode = cursorNode.left;
                if (cursorNode == null) {
                    parentNode.left = newNode;
                    result = true;
                    break;
                }
            }
        }
        return result;
    }


    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(5);
        binaryTree.insert(6);
        binaryTree.insert(4);
        binaryTree.insert(8);
        binaryTree.insert(7);
        binaryTree.insert(3);

        System.out.println("finsh");
    }

}
