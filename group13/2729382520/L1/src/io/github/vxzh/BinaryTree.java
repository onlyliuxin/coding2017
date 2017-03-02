package io.github.vxzh;

/**
 * Created by vxzh on 26/02/2017.
 */
public class BinaryTree {

    private TreeNode root;

    public boolean isEmpty() {
        return root == null;
    }

    //节点个数
    public int size() {
        return size(root);
    }

    private int size(TreeNode subTree) {
        if (subTree == null) {
            return 0;
        } else {
            return 1 + size(subTree.leftChild)
                    + size(subTree.rightChild);
        }
    }

    public void insert(int o) {
        TreeNode newNode = new TreeNode(o, null, null);
        if (root == null)
            root = newNode;
        else {
            TreeNode current = root;
            while (true) {
                if (o < current.data) {
                    current = current.leftChild;
                    if (current == null) {
                        current.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        current.rightChild = newNode;
                        return;
                    }
                }
            }

        }

    }

    private class TreeNode {
        private int data;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode() {
        }

        public TreeNode(int o, TreeNode l, TreeNode r) {
            this.data = o;
            this.leftChild = l;
            this.rightChild = r;
        }
    }
}
