package com.github.wdn.coding2017.basic.tree;

/**
 * Created by wangxin on 2017/5/20.
 */
public class BinaryTreeNode<T extends Comparable> {

    public T data;
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public BinaryTreeNode() {
    }
    public BinaryTreeNode(T data) {
        this.data = data;
    }

    public BinaryTreeNode insert(T o){
        BinaryTreeNode result;
        if(o.compareTo(data)<0){
            if(this.left==null){
                BinaryTreeNode newNode = new BinaryTreeNode();
                newNode.setData(o);
                setLeft(newNode);
                result = newNode;
            }else{
                result = this.left.insert(o);
            }
        }else{
            if(this.right==null){
                BinaryTreeNode newNode = new BinaryTreeNode();
                newNode.setData(o);
                setRight(newNode);
                result = newNode;
            }else{
                result = this.right.insert(o);
            }
        }
        return  result;
    }
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

}
