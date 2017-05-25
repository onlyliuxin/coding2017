package task11;

import task10.BinaryTreeNode;
import task10.BinaryTreeUtil;

import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        return findMin(root);
    }

    public T findMin(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> temp = root;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp.data;
    }

    public T findMax() {
        return findMax(root);
    }

    public T findMax(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp.data;
    }

    public int height() {
        return deep(root);
    }

    private int deep(BinaryTreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = deep(root.left);
        int rightHeight = deep(root.right);
        return leftHeight > rightHeight ? ++leftHeight : ++rightHeight;
    }

    public int size() {
        return countLeafNum(root);
    }

    private int countLeafNum(BinaryTreeNode<T> root) {
        if (root != null) {
            if (root.left == null && root.right == null)
                return 1;
            return countLeafNum(root.left) + countLeafNum(root.right) + 1; //加上根
        }
        return 0;
    }

    public void remove(T e) {
        BinaryTreeNode<T> nodeToRemove = findNode(e, root);
        if (nodeToRemove == null) {
            throw new RuntimeException("Are you kidding me?");
        }
        BinaryTreeNode<T> parentNode = findParentNode(nodeToRemove);
        if (nodeToRemove.left == null && nodeToRemove.right == null) { // 1.待删除的节点没有子节点
            if (parentNode.left.data.compareTo(e) == 0)
                parentNode.left = null;
            else parentNode.right = null;
        } else if (nodeToRemove.left != null && nodeToRemove.right == null) { // 2-1.待删除的节点只有左子节点
            if (parentNode.left.data.compareTo(e) == 0) {
                parentNode.left = nodeToRemove.left;
            } else parentNode.right = nodeToRemove.left;
        } else if (nodeToRemove.left == null) { // 2-2.待删除的节点只有右子节点
            if (parentNode.left.data.compareTo(e) == 0) {
                parentNode.left = nodeToRemove.right;
            } else parentNode.right = nodeToRemove.right;
        } else { // 3.待删除的节点有双子节点
            List<T> childrenNode = BinaryTreeUtil.inOrderVisit(nodeToRemove);
            BinaryTreeNode<T> inheritNode = findNode(childrenNode.get(childrenNode.indexOf(nodeToRemove.data) + 1), nodeToRemove);

            remove(inheritNode.data); // 删除替换节点

            // ~替换
            // ================================================
            inheritNode.left = nodeToRemove.left;
            inheritNode.right = nodeToRemove.right;
            if (parentNode.left.data.compareTo(e) == 0) {
                parentNode.left = inheritNode;
            } else parentNode.right = inheritNode;
        }

    }

    /**
     * 查找某个节点的父节点
     *
     * @param targetNode 要寻找父节点的节点
     */
    public BinaryTreeNode<T> findParentNode(BinaryTreeNode<T> targetNode) {
        T targetValue = targetNode.data;

        BinaryTreeNode<T> temp = root;
        while (targetValue.compareTo(temp.data) != 0) {
            if (temp.left.data.compareTo(targetValue) == 0 || temp.right.data.compareTo(targetValue) == 0)
                return temp;
            if (targetValue.compareTo(temp.data) < 0)
                temp = temp.left;
            else temp = temp.right;
        }
        return temp;
    }

    /**
     * 查找待删除的元素所在的节点
     *
     * @param e    待删除的元素
     * @param root 当前节点
     * @return 待删除的节点
     */
    public BinaryTreeNode<T> findNode(T e, BinaryTreeNode<T> root) {
        if (e.compareTo(root.data) == 0) return root;
        if (e.compareTo(root.data) > 0) return findNode(e, root.right);
        if (e.compareTo(root.data) < 0) return findNode(e, root.left);
        return null;
    }


}
