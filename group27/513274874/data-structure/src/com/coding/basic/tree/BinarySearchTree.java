package com.coding.basic.tree;

import java.util.Stack;

public class BinarySearchTree<T extends Comparable> {

    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin(BinaryTreeNode<T> node) {
        //取树最左侧的数据
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getData();
    }

    public T findMax(BinaryTreeNode<T> node) {
        //取树最右边的
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getData();
    }

    public int height() {
        //遍历压入栈，栈的元素最大数量就是树高
        int max = 0;
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();

        if (root == null) return 0;
        max++;
        BinaryTreeNode<T> node = root;
        while (node != null || !stack.isEmpty()) {
            int count = 0 ;
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
                count++;
            }

            if (stack.size() > 0) {
                node = stack.pop();
                node = node.getRight();
                count++;
            }

            if(count > max ) max = count;

        }

        return max;
    }

    public int size() {
        //遍历压入栈，栈的元素最大数量就是树高
        int size = 0;
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();

        if (root == null) return 0;
        BinaryTreeNode<T> node = root;
        while (node != null || !stack.isEmpty()) {

            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
            if (stack.size() > 0) {
                node = stack.pop();
                size++;
                node = node.getRight();
            }

        }
        return size;
    }

    public BinaryTreeNode<T> remove(T e ,BinaryTreeNode<T> t) {

        /*
        删除节点思路

        1.查找到被删除的节点

        节点存在以下情况：
        1)删除节点没有子节点
        2)删除节点有一个子节点
        3)删除节点有两个子节点
         */
        if(t == null ) return null;

        int comparable = e.compareTo(t.getData());

        if(comparable> 0){
            t.setRight(remove(e,t.getRight()));
        }else if(comparable < 0) {
            t.setLeft(remove(e,t.getLeft()));
        }else{
            if(t.getLeft() != null && t.getRight() != null){

                t.data = findMin(t.getRight());
                t.setRight(remove(t.data,t.getRight()));

            }else{
                t = (t.getLeft() == null ? t.getRight() : t.getLeft());
            }

        }


        return t;



    }

}

