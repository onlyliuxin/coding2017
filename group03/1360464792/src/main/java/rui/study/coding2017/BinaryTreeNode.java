package rui.study.coding2017;

public class BinaryTreeNode {

    private Comparable data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public Comparable getData() {
        return data;
    }
    public void setData(Comparable data) {
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

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(Comparable data) {
        this.data = data;
    }

    public BinaryTreeNode(Comparable data, BinaryTreeNode left, BinaryTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
