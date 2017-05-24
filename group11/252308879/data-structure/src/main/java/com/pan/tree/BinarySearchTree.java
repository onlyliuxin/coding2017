package com.pan.tree;

import com.pan.queue.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉查找树
 *
 * @param <T>
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
        if (root == null) {
            return null;
        }
        return findMin(root);
    }

    private T findMin(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.data;
        }
        return findMin(node.left);
    }

    public T findMax() {
        if (root == null) {
            return null;
        }
        return findMax(root);
    }

    private T findMax(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.data;
        }
        return findMax(node.right);
    }


    public int height() {
        if (root == null) {
            return -1;
        }
        return height(root);
    }

    /**
     * 计算二叉树的高度
     *
     * @param node
     * @return
     */
    private int height(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftChildHeight = height(node.left);
        int rightChildHeight = height(node.right);

        // +1 因为包含node本身
        if (leftChildHeight > rightChildHeight) {
            return leftChildHeight + 1;
        } else {
            return rightChildHeight + 1;
        }
    }

    /**
     * 计算二叉树的大小
     *
     * @return
     */
    public int size() {
        if (root == null) {
            return -1;
        }
        return size(root);
    }

    private int size(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return size(node.left) + size(node.right) + 1;
    }

    /**
     * 移出节点
     *
     * @param x
     * @return
     */
    public void remove(T x) {
        remove(x, root);
    }

    private BinaryTreeNode<T> remove(T x, BinaryTreeNode<T> node) {
        if (node == null) {
            return node;
        }
        int compareResult = x.compareTo(node.data);
        if (compareResult < 0) {
            node.left = remove(x, node.left);
        } else if (compareResult > 0) {
            node.right = remove(x, node.right);
        } else {
            if (node.left != null && node.right != null) {
                node.data = findMin(node.right);
                node.right = remove(node.data, node.right);
            } else {
                node = (node.left != null) ? node.left : node.right;
            }
        }
        return node;
    }

    /**
     * 层级访问
     * @return
     */
    public List<T> levelVisit() {
        List<T> result = new ArrayList<T>();
        Queue<BinaryTreeNode<T>> queue = new Queue<>();
        BinaryTreeNode<T> node = root;
        if (node != null) {
            queue.enQueue(node);
            while (!queue.isEmpty()) {
                node = queue.deQueue();
                result.add(node.data);
                if (node.left != null) {
                    queue.enQueue(node.left);
                }
                if (node.right != null) {
                    queue.enQueue(node.right);
                }
            }
        }
        return result;
    }

    /**
     * 判断是否属于二叉查找树：
     *  使用中序排列后，判读元素是否为升序
     * @return
     */
    public boolean isValid() {
        if (root == null){
            return true;
        }
        List<T> orderVisit = BinaryTreeUtil.inOrderVisit(root);
        for (int i = 0; i < orderVisit.size(); i++) {
            // 出现了中序排序后，前面的元素比后面大
            if (orderVisit.get(i).compareTo(orderVisit.get(i+1)) > 0){
                return false;
            }
        }
        return true;
    }


    /**
     * 获取两个节点的最小公共祖先
     *
     * @param node1 BinaryNode
     * @param node2 BinaryNode
     * @return
     */
    public T getLowestCommonAncestor(T node1, T node2) {
        return getLowestCommonAncestor(root, node1, node2);
    }

    private T getLowestCommonAncestor(BinaryTreeNode<T> node,T node_1, T node_2){
        if (findNode(node.left, node_1)) {
            if (findNode(node.right, node_2)) {
                return node.data;
            } else {
                return getLowestCommonAncestor(node.left, node_1, node_2);
            }
        } else {
            if (findNode(node.left, node_2)) {
                return node.data;
            } else {
                return getLowestCommonAncestor(node.right, node_1, node_2);
            }
        }
    }

    /**
     * 判断节点是否在当前二叉树中
     *
     * @param root
     * @param node
     * @return
     */
    private boolean findNode(BinaryTreeNode<T> root, T node) {
        if (root == null || node == null) {
            return false;
        }
        if (root == node) {
            return true;
        }
        // 递归查找是否在左节点中
        boolean foundLeft = findNode(root.left, node);
        if (foundLeft) {
            return true;
        }
        // 不在继续查找右节点中
        boolean foundRight = findNode(root.right, node);
        return foundRight;
    }

    /**
     * 给定两个值， 获得处于这两个值中间的节点
     * @param n1
     * @param n2
     * @return
     */
    public List<T> getNodesBetween(T n1, T n2) {
        List<T> result = new ArrayList<>();
        getNodesBetween(root, n1, n2, result);
        return result;
    }

    private void getNodesBetween(BinaryTreeNode<T> node, T data_1, T data_2, List<T> result){
        if (node == null){
            return;
        }
        if (node.data.compareTo(data_1) > 0){
            getNodesBetween(node.left, data_1, data_2, result);
        }
        if (node.data.compareTo(data_1) >= 0 && node.data.compareTo(data_2) <= 0){
            result.add(node.getData());
        }
        if (node.data.compareTo(data_2) < 0){
            getNodesBetween(node.right, data_1, data_2, result);
        }
    }
}

