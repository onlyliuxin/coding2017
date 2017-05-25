package task12;

import task10.BinaryTreeNode;
import task10.BinaryTreeUtil;

import java.util.*;

@SuppressWarnings("unchecked")
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
        int compareResult = e.compareTo(root.data);
        if (compareResult == 0) return root;
        if (compareResult > 0) return findNode(e, root.right);
        if (compareResult < 0) return findNode(e, root.left);
        return null;
    }

    public List<T> levelVisit() {
        List<T> result = new ArrayList<>();
        Queue<BinaryTreeNode> temp = new ArrayDeque<>();
        levelVisit0(result, temp);
        return result;
    }

    private void levelVisit0(List<T> result, Queue<BinaryTreeNode> temp) {
        if (temp.isEmpty())
            return;
        BinaryTreeNode tempNode;
        Queue<BinaryTreeNode> nextLevelNodes = new ArrayDeque<>(); // 存放下一层节点
        while ((tempNode = temp.poll()) != null) {
            BinaryTreeNode left = tempNode.left;
            if (left != null) {
                result.add((T) left.data);
            }
            BinaryTreeNode right = temp.poll().right;
            if (right != null) {
                result.add((T) right.data);
            }
            nextLevelNodes.add(left);
            nextLevelNodes.add(right);
        }
        levelVisit0(result, nextLevelNodes);
    }

    public boolean isValid() {
        return isValid0(root);
    }

    private boolean isValid0(BinaryTreeNode<T> root) {
        if (root.left != null) {
            return root.left.data.compareTo(root.data) < 0 && isValid0(root.left);
        }
        return root.right == null || root.right.data.compareTo(root.data) > 0 && isValid0(root.right);
    }

    public T getLowestCommonAncestor(T n1, T n2) {
        List<T> nodesBetween = getNodesBetween(n1, n2);
        if (nodesBetween == null || nodesBetween.size() == 0) {
            return null;
        }
        return getNodesBetween(n1, n2).stream().sorted().findFirst().get();
    }

    public List<T> getNodesBetween(T n1, T n2) {
        if (n1.compareTo(n2) >= 0) {
            return null;
        }
        List<T> betweenNodes = new ArrayList<>();
        BinaryTreeNode<T> leftParentNode = findParentNode(findNode(n1, root));
        BinaryTreeNode<T> rightParentNode = findParentNode(findNode(n2, root));
        while (leftParentNode.data.compareTo(rightParentNode.data) != 0) {
            betweenNodes.add(leftParentNode.data);
            betweenNodes.add(rightParentNode.data);
            leftParentNode = findParentNode(leftParentNode);
            rightParentNode = findParentNode(rightParentNode);
        }
        betweenNodes.add(leftParentNode.data);
        return betweenNodes;
    }
}
