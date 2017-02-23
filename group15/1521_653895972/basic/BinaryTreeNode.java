package com.oneces.tool.basic;

/**
 * 实现二叉树
 * left总比父节点小
 * right总比父节点大
 */
public class BinaryTreeNode {
    private Node root;
    private int size = 0;

    public void insert(int data) {
        final Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            while (true) {
                Node parent = current;
                if (data < current.data) {//left
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {//right
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
        size++;
    }


    //先序遍历
    private String preTraverse(Node node) {
        if (node == null)
            return "";
        else
            return node.data + preJointComma(preTraverse(node.left)) + preJointComma(preTraverse(node.right));
    }
    //中序遍历
    private String midTraverse(Node node) {
        if (node == null)
            return "";
        else
            return midTraverse(node.left)+" "+node.data+" " +midTraverse(node.right);
    }
    //后序遍历
    private String posTraverse(Node node) {
        if (node == null)
            return "";
        else
            return posTraverse(node.left)+" " +posTraverse(node.right)+" "+node.data;
    }

    private String preJointComma(String str) {
        return str == "" ? "" : "," + str;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "["+midTraverse(root)+"]";
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
