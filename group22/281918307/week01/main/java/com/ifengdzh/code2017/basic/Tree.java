package com.ifengdzh.code2017.basic;

/**
 * Created by ajaxfeng on 2017/4/4.
 */
public class Tree {

    private Node root;


    /**
     * 输入数据
     *
     * @param data
     */
    public void insert(int data) {
        insert(root, data);
    }

    /**
     * 输入数据
     *
     * @param data
     */
    private void insert(Node currentNode, int data) {
        //如果不存在节点,就创建根节点.
        if (null == root) {
            currentNode = new Node(data);
            root = currentNode;
            return;
        }

        int currentData = currentNode.getData();
        //如果数据比根节点大.则往右找
        Node nextNode = null;
        boolean isLeft = false;
        if (currentData < data) {
            nextNode = currentNode.getRight();
        } else if (currentData > data) {
            nextNode = currentNode.getLeft();
            isLeft = true;
        } else {
            System.out.println(data + "已经存在了...");
        }
        if (nextNode != null) {
            insert(nextNode, data);
        } else {
            nextNode = new Node(data);
            if (isLeft) {
                currentNode.setLeft(nextNode);
            } else {
                currentNode.setRight(nextNode);
            }
        }
    }

    /**
     * 查找在那个节点上面
     *
     * @param data
     */
    public void trace(int data) {
        trace(null, data);
    }

    private void trace(Node currentNode, int data) {
        if (currentNode == null) {
            currentNode = root;
        }
        int currentNodeData = currentNode.getData();
        System.out.println(" " + currentNodeData);
        Node nextNode = null;
        if (currentNodeData > data) {
            nextNode = currentNode.getLeft();
        } else if (currentNodeData < data) {
            nextNode = currentNode.getRight();
        } else {
            System.out.println("命中了....");
        }
        if (null != nextNode) {
            trace(nextNode, data);
        }
    }

    public void traceAll() {
        traceAll(root);
    }

    private void traceAll(Node currentNode) {
        if (null == currentNode) {
            return;
        }
        System.out.println(" " + currentNode.getData());
        if (currentNode.getLeft() != null) {
            System.out.print(" left: ");
            traceAll(currentNode.getLeft());
        }

        if (currentNode.getRight() != null) {
            System.out.print(" right: ");
            traceAll(currentNode.getRight());
        }
    }


    /**
     * 精通内部类
     */
    public static class Node {

        public Node() {

        }

        public Node(int data) {
            this.data = data;
        }

        private Node left;
        private Node right;
        private int data;

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}
