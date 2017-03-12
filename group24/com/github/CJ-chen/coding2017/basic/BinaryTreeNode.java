/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass;

/**
 *
 * @author CJ
 */
public class BinaryTreeNode {

    private Object data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

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
        // 应该只需要实现这个就可以了
        int curValue = (Integer) this.getData();
        int insertValue = (Integer) o;

        BinaryTreeNode newNode = new BinaryTreeNode();
        newNode.setData(o);

        if (curValue > insertValue) {
            if (this.getLeft() != null) {
                return this.getLeft().insert(o);
            } else {
                this.setLeft(newNode);
                return this;
            }
        } else{
            if (this.getRight() != null) {
                return this.getRight().insert(o);
            } else {
                this.setRight(newNode);
                return this;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getData()).append("\n");
        sb.append(this.getLeft()).append("<--").append(this.getData()).append("\n");
        sb.append(this.getData()).append("-->").append(this.getRight()).append("\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        BinaryTreeNode btn = new BinaryTreeNode();
        btn.setData(5);
//        btn.insert(5);
        btn.insert(7);
        btn.insert(8);
        btn.insert(9);
        btn.insert(4);

        System.err.println(btn);
    }

}
