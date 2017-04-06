package com.coding.basic;

public class BinaryTreeNode {

    private Object data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private BinaryTreeNode parent;

    public BinaryTreeNode(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    public BinaryTreeNode getParent() {
        return parent;
    }

    public BinaryTreeNode insert(Object o) {
        BinaryTreeNode newNode = new BinaryTreeNode(o);
        BinaryTreeNode root = findRoot(this);
        if (root.data == null) {
            root.data = newNode;
        } else {
            int newVal = getNodeIntVal(newNode);
            insert(root, newNode, newVal);
        }
        return newNode;
    }

    private void insert(BinaryTreeNode node, BinaryTreeNode newNode, int newVal) {
        int nodeVal = getNodeIntVal(node);
        if (newVal < nodeVal) {
            if (node.left == null) {
                newNode.parent = node;
                node.left = newNode;
            } else {
                insert(node.left, newNode, newVal);
            }
        } else {
            if (node.right == null) {
                newNode.parent = node;
                node.right = newNode;
            } else {
                insert(node.right, newNode, newVal);
            }
        }
    }

    private BinaryTreeNode findRoot(BinaryTreeNode binaryTreeNode) {
        while (binaryTreeNode.parent != null) {
            binaryTreeNode = binaryTreeNode.parent;
        }
        return binaryTreeNode;
    }

    private int getNodeIntVal(BinaryTreeNode node) {
        if (node.data instanceof Integer) {
            return ((Integer) node.data).intValue();
        }
        return 0;
    }

    public int getDataIntVal() {
        if (data instanceof Integer) {
            return ((Integer) data).intValue();
        }
        return 0;
    }
}
