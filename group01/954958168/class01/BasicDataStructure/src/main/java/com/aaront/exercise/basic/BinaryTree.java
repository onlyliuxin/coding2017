package com.aaront.exercise.basic;

public class BinaryTree {

    private BinaryTreeNode head = new BinaryTreeNode(null);
    private BinaryTreeNode root;
    private int size;
    private int index = 0;

    public static final int PREORDER = 0;
    public static final int INORDER = 1;
    public static final int POSTORDER = 2;
    public static final int HIERARCHICAL = 3;

    public static final int RECURSION = 10;
    public static final int ITERATION = 11;

    public void add(Integer o) {
        BinaryTreeNode node = new BinaryTreeNode(o);
        if (root == null) {
            root = node;
            head.setLeft(root);
        } else {
            insert(root, node);
        }
        size++;
    }

    private void insert(BinaryTreeNode node, BinaryTreeNode newNode) {
        // 要插入的节点插入当前节点的左子树
        if (node.getData() > newNode.getData()) {
            if (node.getLeft() == null) {
                node.setLeft(newNode);
            } else {
                insert(node.left, newNode);
            }
        } else { // 要插入的节点插入当前节点的右子树
            if (node.getRight() == null) {
                node.setRight(newNode);
            } else {
                insert(node.right, newNode);
            }
        }
    }

    public BinaryTreeNode search(int data) {
        return search(data, ITERATION);
    }

    public BinaryTreeNode search(int data, int method) {
        switch (method) {
            case RECURSION:
                return findNodeRecursion(root, data);
            case ITERATION:
                return findNodeIteration(data);
            default:
                throw new IllegalArgumentException("不支持的查找方法");
        }
    }

    private BinaryTreeNode findNodeRecursion(BinaryTreeNode node, int data) {
        if (node == null) return null;
        if (node.getData() == data) return node;
        if (node.getData() > data) return findNodeRecursion(node.getLeft(), data);
        return findNodeRecursion(node.getRight(), data);
    }

    private BinaryTreeNode findNodeIteration(int data) {
        BinaryTreeNode currentNode = root;
        while (currentNode != null) {
            if (currentNode.getData() == data) {
                return currentNode;
            }
            if (currentNode.getData() > data) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }
        return null;
    }

    public BinaryTreeNode min() {
        return findMin(root);
    }

    private BinaryTreeNode findMin(BinaryTreeNode node) {
        if (node == null) return null;
        if (node.getLeft() == null) return node;
        return findMin(node.getLeft());
    }

    public BinaryTreeNode max() {
        return findMax(root);
    }

    private BinaryTreeNode findMax(BinaryTreeNode node) {
        if (node == null) return null;
        if (node.getRight() == null) return node;
        return findMax(node.getRight());
    }

    public void delete(Integer data) {
        BinaryTreeNode node = search(data);
        if (node == null) return;
        BinaryTreeNode parentNode = searchParentNode(node);
        if (parentNode == null) return;
        // 删除叶子节点
        if (node.getLeft() == null && node.getRight() == null) {
            if (parentNode.getLeft() == node) parentNode.setLeft(null);
            else parentNode.setRight(null);
        } else if (node.getLeft() != null && node.getRight() == null) { // 删除只有左子树的节点
            if (parentNode.getLeft() == node) parentNode.setLeft(node.getLeft());
            else parentNode.setRight(node.getLeft());
        } else if (node.getRight() != null && node.getLeft() == null) { // 删除只有右子树的节点
            if (parentNode.getLeft() == node) parentNode.setLeft(node.getRight());
            else parentNode.setRight(node.getRight());
        } else { // 删除有两个子树的节点
            BinaryTreeNode replace = findMin(node.getRight());
            BinaryTreeNode replaceParentNode = searchParentNode(replace);
            replaceParentNode.setLeft(replace.getRight());
            node.setData(replace.getData());
            replace.setLeft(null);
            replace.setRight(null);
        }
        size--;
    }

    private BinaryTreeNode searchParentNode(BinaryTreeNode node) {
        if (node == null) return null;
        if (node == root) return head;
        BinaryTreeNode current = root;
        while (current != null) {
            if (current.getLeft() == node || current.getRight() == node) return current;
            if (current.getData().compareTo(node.getData()) > 0) current = current.getLeft();
            else current = current.getRight();
        }
        return null;
    }

    public int[] traversal() {
        return traversal(PREORDER);
    }

    public int[] traversal(int order) {
        int[] datas = new int[size];
        if (order == PREORDER) {
            preorderTraversal(root, datas);
        } else if (order == INORDER) {
            inorderTraversal(root, datas);
        } else if (order == POSTORDER) {
            postorderTraversal(root, datas);
        } else {
            hierarchicalTraversal(root, datas);
        }
        index = 0;
        return datas;
    }

    private void preorderTraversal(BinaryTreeNode node, int[] datas) {
        if (node == null) {
            return;
        }

        datas[index++] = node.getData();
        preorderTraversal(node.getLeft(), datas);
        preorderTraversal(node.getRight(), datas);
    }

    private void inorderTraversal(BinaryTreeNode node, int[] datas) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.getLeft(), datas);
        datas[index++] = node.getData();
        inorderTraversal(node.getRight(), datas);
    }

    private void postorderTraversal(BinaryTreeNode node, int[] datas) {
        if (node == null) {
            return;
        }

        postorderTraversal(node.getLeft(), datas);
        postorderTraversal(node.getRight(), datas);
        datas[index++] = node.getData();
    }

    private void hierarchicalTraversal(BinaryTreeNode node, int[] datas) {
        if (node == null) return;
        Queue queue = new Queue();
        queue.enQueue(node);
        while (!queue.isEmpty()) {
            BinaryTreeNode tmp = (BinaryTreeNode) queue.deQueue();
            datas[index++] = tmp.getData();
            if (tmp.getLeft() != null) queue.enQueue(tmp.getLeft());
            if (tmp.getRight() != null) queue.enQueue(tmp.getRight());
        }
    }

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
    }
}
