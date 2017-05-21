package me.lzb.basic.tree;

/**
 * @author LZB
 */
public class BinaryTreeNode<T extends Comparable<T>> {
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


    /**
     * 左边比父节点小，右边比父节点大
     * 这层满了就下一层继续add,直到找到空位
     *
     * @param d data
     */
    public void insert(T d) {
        BinaryTreeNode<T> b = new BinaryTreeNode(d);
        if (isSmaller(d)) {
            //比父节点小，左边
            if (this.left == null) {
                this.left = b;
            } else {
                this.left.insert(d);
            }

        } else {//相等不考虑
            //比父节点大，右边
            if (this.right == null) {
                this.right = b;
            } else {
                this.right.insert(d);
            }

        }
    }

    /**
     * 是否比当前节点的data小
     *
     * @param d data
     * @return true false
     */
    private boolean isSmaller(T d) {
        return this.data.compareTo(d) > 0;
    }

    @Override
    public String toString() {
//        return getLeft() != null ? getLeft().getData().toString() : "" + ":" + getData().toString() + ":" + getRight() != null ? getRight().getData().toString() : "";
        return getData().toString();
    }


}
