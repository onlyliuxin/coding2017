package com.coding.basic;

/**
 * 实现二叉树
 * left总比父节点小
 * right总比父节点大
 */
public class BinaryTreeNode {
    private Node root;
    private int size = 0;

    /**
     * 插入数据
     * @param data
     */
    public void insert(int data) {
        final Node newNode = new Node(data);
        if (root == null) {//根节点为空 直接插入数据到根节点
            root = newNode;
        } else {
            Node current = root;
            while (true) {//循环判断
                Node parent = current;
                if (data < current.data) {//比父节点小 就是left
                    current = current.left;
                    //直到left节点不存在
                    if (current == null) {
                        //插入数据
                        parent.left = newNode;
                        return;
                    }
                } else {//比父节点大 也就是right
                    current = current.right;
                    //直到right节点不存在
                    if (current == null) {
                        //插入数据
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
        size++;
    }


    /**
     * 返回数量
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 重写toString 方便打印
     *
     * @return
     */
    @Override
    public String toString() {
        return "["+midTraverse(root)+"]";
    }

    /**
     * 节点内部类 用于保存数据
     */
    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    //先序遍历
    private String preTraverse(Node node) {
        if (node == null)
            return "";
        else
            return node.data + preJointComma(preTraverse(node.left)) + preJointComma(preTraverse(node.right));
    }
    //中序遍历
    private String midTraverse(Node node) {
        if (node == null)
            return "";
        else
            return midTraverse(node.left)+" "+node.data+" " +midTraverse(node.right);
    }
    //后序遍历
    private String posTraverse(Node node) {
        if (node == null)
            return "";
        else
            return posTraverse(node.left)+" " +posTraverse(node.right)+" "+node.data;
    }

    private String preJointComma(String str) {
        return str == "" ? "" : "," + str;
    }
}
