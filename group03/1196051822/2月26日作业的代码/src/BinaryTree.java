package com.byhieg.coding2017;

/**
 * Created by byhieg on 17/2/22.
 * Mail to byhieg@gmail.com
 */

public class BinaryTree {

    private BinaryTreeNode root = new BinaryTreeNode();

    public BinaryTree(Object rootData){
        root = root.insert(rootData);
    }


    //左边的值小于等于父节点的值，右边的值大于父节点的值
    private void insertNode(BinaryTreeNode root, BinaryTreeNode node) {
        int value = (int)node.getData();
        int rootValue = (int)root.getData();
        if (value <= rootValue){
            insertLeft(root,node);
        }else {
            insertRight(root,node);
        }
    }


    public void insert(Object o) {
        BinaryTreeNode node = new BinaryTreeNode();
        node = node.insert(o);
        insertNode(root,node);
    }

    private void insertLeft(BinaryTreeNode father, BinaryTreeNode node) {
        if (father.getLeft() == null) {
            father.setLeft(node);
        }else{
            insertNode(father.getLeft(),node);
        }
    }

    private void insertRight(BinaryTreeNode father, BinaryTreeNode node) {
        if (father.getRight() == null) {
            father.setRight(node);
        } else {
            insertNode(father.getRight(),node);
        }
    }

    //前序遍历输出书
    private void preOrder(BinaryTreeNode node) {
        if (node != null) {
            System.out.println(node.getData());
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }


    //打印树
    public void printTree(){
        preOrder(root);
    }

}
