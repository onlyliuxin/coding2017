package zavier.week01.basic;

public class BinaryTreeNode {

    private Integer data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
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

    public BinaryTreeNode insert(Integer o) {

        if (o > this.data) {

            if (this.getRight() == null) {
                BinaryTreeNode node = new BinaryTreeNode(o);
                this.setRight(node);
                return node;
            } else {
                return this.getRight().insert(o);
            }

        } else {

            if (this.getLeft() == null) {
                BinaryTreeNode node = new BinaryTreeNode(o);
                this.setLeft(node);
                return node;
            } else {
                return this.getLeft().insert(o);
            }

        }

    }

}
