package com.coding2017.group7.homework.c0226;

public class MyBinaryTreeNode {

    private Comparable data;
    private MyBinaryTreeNode left;
    private MyBinaryTreeNode right;

    public Comparable getData() {
        return data;
    }

    public void setData(Comparable data) {
        this.data = data;
    }

    public MyBinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(MyBinaryTreeNode left) {
        this.left = left;
    }

    public MyBinaryTreeNode getRight() {
        return right;
    }

    public void setRight(MyBinaryTreeNode right) {
        this.right = right;
    }

    public MyBinaryTreeNode insert(Comparable o) {
        if (data == null) {
            data = o;
        }
        int compare = o.compareTo(data);
        if (compare < 0) {
            if (left == null) {
                left = new MyBinaryTreeNode();
            }
            left.insert(o);
        } else if (compare > 0) {
            if (right == null) {
                right = new MyBinaryTreeNode();
            }
            right.insert(o);
        }
        return this;
    }

}
