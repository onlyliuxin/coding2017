package dataStructure;

public class BinaryTree {

    private BinaryTreeNode root;

    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    public BinaryTreeNode insert(int data) {
        BinaryTreeNode node = new BinaryTreeNode(data);
        root = insert(root, node);
        return root;
    }

    private BinaryTreeNode insert(BinaryTreeNode root, BinaryTreeNode newNode) {
        if (root == null) {
            root = newNode;
        } else if (newNode.data > root.data) {
            root.right = insert(root.right, newNode);
        } else {
            root.left = insert(root.left, newNode);
        }
        return root;
    }

    /**
     * binary tree node
     */
    private class BinaryTreeNode {

        private int data;
        private BinaryTreeNode left;
        private BinaryTreeNode right;

        public BinaryTreeNode(int data) {
            this.left = null;
            this.right = null;
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
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

