package com.johnChnia.coding2017.basic.tree;

import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang3.ObjectUtils.compare;
import static org.apache.commons.lang3.ObjectUtils.max;

/**
 * Created by john on 2017/5/27.
 * <p>
 * 二叉树：每个节点最多只有两个分支--https://zh.wikipedia.org/wiki/%E4%BA%8C%E5%8F%89%E6%A0%91
 * 二叉搜索树：https://zh.wikipedia.org/wiki/%E4%BA%8C%E5%85%83%E6%90%9C%E5%B0%8B%E6%A8%B9
 */
public class BinarySearchTree<T extends Comparable> {

    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        if (Objects.nonNull(root)) {
            BinaryTreeNode<T> node = root;
            while (Objects.nonNull(node.getLeft())) {
                node = node.getLeft();
            }
            return node.getData();
        }
        return null;
    }

    public T findMax() {
        if (Objects.nonNull(root)) {
            BinaryTreeNode<T> node = root;
            while (Objects.nonNull(node.getRight())) {
                node = node.getRight();
            }
            return node.getData();
        }
        return null;
    }


    public int height() {
        if (Objects.nonNull(root)) {
            return height(root);
        }
        return -1;
    }

    /**
     * Height of a node: Number of edges in longest path from the node to a leaf node
     * Height of tree: Height of root
     * Height of tree with 1 node: 0
     * Depth of a node: Number of edges in path from root to that node
     * 树的高度：max(leftTree，rightTree）+ 1
     *
     * @return 树的高度。
     */
    private int height(BinaryTreeNode<T> root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        int lheight = height(root.getLeft());
        int rheight = height(root.getRight());

        return max(lheight, rheight) + 1;
    }

    public int size() {
        if (Objects.nonNull(root)) {
            return size(root);
        }
        return -1;
    }

    /**
     * 参考：http://www.geeksforgeeks.org/write-a-c-program-to-calculate-size-of-a-tree/
     * Size of a tree is the number of elements present in the tree.
     * Size of a tree = Size of left subtree + 1 + Size of right subtree.
     *
     * @return 树的节点个数。
     */
    private int size(BinaryTreeNode<T> root) {
        if (Objects.isNull(root)) {
            return 0;
        }

        return 1 + size(root.getLeft()) + size(root.getRight());
    }


    public void remove(T e) {
        if (Objects.nonNull(e)) {
            remove(root, e);
        }
    }

    /**
     * http://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
     *
     * @param root 根节点
     * @param e    删除节点
     */
    private BinaryTreeNode<T> remove(BinaryTreeNode<T> root, T e) {
        int flag = compare(e, root.data);
        if (flag > 0) {
            root.right = remove(root.getRight(), e);
        } else if (flag < 0) {
            root.left = remove(root.getLeft(), e);
        } else {
            // 叶子节点
            if (Objects.isNull(root.getLeft()) && Objects.isNull(root.getRight())) {
                return null;
            } else if (Objects.isNull(root.getLeft())) {  // 只有一个子节点
                return root.getRight();
            } else if (Objects.isNull(root.getRight())) {
                return root.getLeft();
            }
            //两个子节点
            BinarySearchTree<T> temp = new BinarySearchTree<>(root.getRight());
            T data = temp.findMin();
            remove(root, data);
            root.data = data;
        }
        return root;
    }

    public List<T> levelVisit() {

        return null;
    }

    public boolean isValid() {
        return false;
    }

    public T getLowestCommonAncestor(T n1, T n2) {
        return null;

    }

    public List<T> getNodesBetween(T n1, T n2) {
        return null;
    }

}


