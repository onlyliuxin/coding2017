package com.coding.basic.tree;

/**
 * Korben's BinaryTreeNode
 *
 * Created by Korben on 21/02/2017.
 */
public class BinaryTreeNode<T extends Comparable> {

    private T data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private int size;

    public T getData() {
        return data;
    }

    public void setData(T data) {
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

    @SuppressWarnings("unchecked")
    public BinaryTreeNode insert(T data) {
        if (this.data == null) {
            this.data = data;
            return this;
        }
        int compareResult = this.data.compareTo(data);
        if (compareResult > 0) {
            if (this.left == null) {
                this.left = new BinaryTreeNode();
                this.left.data = data;
                return this.left;
            } else {
                return this.left.insert(data);
            }
        } else if (compareResult < 0) {
            if (this.right == null) {
                this.right = new BinaryTreeNode();
                this.right.data = data;
                return this.right;
            } else {
                return this.right.insert(data);
            }
        } else {
            return this;
        }
    }

    @SuppressWarnings("unchecked")
    public BinaryTreeNode delete(T data) {
        BinaryTreeNode treeNode = search(data);
        if (treeNode == null) {
            return null;
        }
        int compareResult = this.data.compareTo(data);
        if (compareResult > 0) {
            return this.left.delete(data);
        } else if (compareResult < 0) {
            return this.right.delete(data);
        } else {
            if (treeNode.right == null) {
                if (this.left == null) {
                    this.data = null;
                } else {
                    this.left = this;
                }
            } else {
                this.data = (T) this.right.findMin().data;

                this.right.delete(this.data);
            }
        }

        return this;
    }

    private BinaryTreeNode findMin() {
        if (this.data == null) {
            return null;
        }
        if (this.left == null) {
            return this;
        }
        return this.left.findMin();
    }

    @SuppressWarnings("unchecked")
    public BinaryTreeNode search(T data) {
        if (this.data == null) {
            return null;
        }
        int compareResult = this.data.compareTo(data);
        if (compareResult > 0) {
            if (this.left == null) {
                return null;
            } else {
                return this.left.search(data);
            }
        } else if (compareResult < 0) {
            if (this.right == null) {
                return null;
            } else {
                return this.right.search(data);
            }
        } else {
            return this;
        }
    }
}