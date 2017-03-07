package com.coding.basic;

public class BinaryTreeNode {

    private Object data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private BinaryTreeNode root;

    public BinaryTreeNode(Object data, BinaryTreeNode left, BinaryTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
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

    public BinaryTreeNode insert(Object o) {
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode(o, null, null);
        if (root == null) {
            root = binaryTreeNode;
        } else {
            add(o, root);
        }
        return binaryTreeNode;
    }

    private BinaryTreeNode add(Object o, BinaryTreeNode target) {
        if (target == null) {
            target = new BinaryTreeNode(o, null, null);
        } else {
            if (compare(o, target.data) > 0) {
                target.right = add(o, target.right);
            } else if (compare(o, target.data) < 0) {
                target.left = add(o, target.left);
            }
        }
        return target;
    }

    private int compare(Object src, Object target) {
        return ((String) src).compareTo((String) target);
    }

}
