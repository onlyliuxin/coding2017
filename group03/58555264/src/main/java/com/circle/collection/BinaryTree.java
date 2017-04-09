package com.circle.collection;

/**
 * Created by keweiyang on 2017/2/25.
 * 自定义二叉树
 */
public class BinaryTree {

    private Node root;

    /**
     * 查找某个节点
     *
     * @param key
     * @return
     */
    public Node get(int key) {
        Node currentNode = root;

        if (currentNode == null) {
            throw new RuntimeException("这棵二叉树为空二叉树");
        } else {
            while (currentNode.getId()!= key) {
                if (currentNode.getId() > key) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode.getRightChild();
                }

                if (currentNode == null) {
                    return null;
                }
            }
        }

        return currentNode;
    }

    /**
     * 插入一个节点
     *
     * @param id
     * @param data
     */
    public void insert(int id, Object data) {
        Node newNode = new Node(id, data);
        //1:先找到插入位置
        Node parentNode = null;
        Node currentNode = root;
        if (root == null) {
            root = newNode;
        } else {
            while (true) {
                parentNode = currentNode;
                if (currentNode.getId() > id) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        //如果没有左子节点，则插入
                        parentNode.setLeftChild(newNode);
                        return;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        //如果没有右子节点，则插入
                        parentNode.setRightChild(newNode);
                        return;
                    }

                }
            }
        }
    }

    /**
     * 删除节点
     * @param key
     */
    public boolean delete(int key) {
        //根据key找到对应的节点
        // 1、如果该节点不存在就抛出异常
        Node parentNode = null;
        Node currentNode = root;
        boolean isLeftNode = false;
        if (currentNode == null) {
            throw new RuntimeException("二叉树为空");
        }else{
            while (currentNode.getId() != key) {
                parentNode = currentNode;
                if (currentNode.getId() < key) {
                    currentNode = currentNode.getRightChild();
                    isLeftNode = false;
                }else{
                    currentNode = currentNode.getLeftChild();
                    isLeftNode = true;
                }

                if (currentNode == null) {
                    return false;
                }
            }
        }


        //2、下面讨论该节点存在
        // 2.1如果该节点的左右子节点都不存在，则直接删除该节点
        if (currentNode.getRightChild() == null && currentNode.getLeftChild() == null) {
            this.noChild(currentNode, parentNode, isLeftNode);
        }
        // 2.2如果该节点的只存在一个子节点
        //2.2.1 如果存在左节点
        if (currentNode.getRightChild() == null) {
            this.oneLeftChild(currentNode,parentNode,isLeftNode);
        }
        //2.2.2 如果存在右节点
        if (currentNode.getLeftChild() == null) {
            this.oneRightChild(currentNode, parentNode, isLeftNode);
        }
        //2.3 如果左右孩子都存在，则直接拿右孩子中最小的节点替换该节点
        if (currentNode.getLeftChild() != null && currentNode.getRightChild() != null) {
            this.bothChild(currentNode, parentNode, isLeftNode);
        }
        return false;

    }

    private void bothChild(Node currentNode, Node parentNode, boolean flag) {
        //找到中序后继节点
        if (flag) {
            Node node = this.successor(currentNode);
            node.setLeftChild(currentNode.getLeftChild());

            parentNode.setLeftChild(node);


        }else{
            Node node = this.successor(currentNode);
            node.setRightChild(currentNode.getRightChild());

            parentNode.setRightChild(node);

        }
    }

    private Node successor(Node currentNode) {
        //由于当前节点的左右子节点都存在，所以current一定存在
        Node parent = currentNode;
        Node current = currentNode.getRightChild();

        while (current != null) {
            parent = current;
            current = current.getLeftChild();
        }

        return parent;

    }

    private void oneRightChild(Node currentNode, Node parentNode, boolean flag) {
        if (flag) {
            parentNode.setRightChild(currentNode.getLeftChild());

        }else{
            parentNode.setRightChild(currentNode.getRightChild());

        }
    }

    private void oneLeftChild(Node currentNode, Node parentNode, boolean flag) {
        if (flag) {
            parentNode.setLeftChild(currentNode.getLeftChild());

        }else{
            parentNode.setLeftChild(currentNode.getRightChild());

        }
    }

    private void noChild(Node currentNode, Node parentNode, boolean flag) {
        if (flag) {
            parentNode.setLeftChild(null);

        }else{
            parentNode.setRightChild(null);

        }
    }

    /**
     * 前序
     *
     * @param node
     */
    public void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.getId() + "--- ");
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());

        }
    }

    /**
     * 中序
     *
     * @param node
     */
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.getLeftChild());
            System.out.println(node.getId() + "--");
            inOrder(node.getRightChild());

        }
    }

    /**
     * 后序
     *
     * @param node
     */
    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.getLeftChild());
            postOrder(node.getRightChild());
            System.out.println(node.getId() + "---");
        }
    }

    private class Node {
        private int id;
        private Object data;
        private Node leftChild;
        private Node rightChild;


        public Node(int id, Object data) {
            this.id = id;
            this.data = data;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {

            return "id= " + id + " , data= " + data;
        }
    }
}
