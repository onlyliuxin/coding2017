package task10;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeUtil {
    /**
     * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
     * <p>
     * 根左右
     */
    public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();

        T rootNode = root.getData();
        result.add(rootNode);
        preList(result, root);
        return result;
    }

    private static <T> void preList(List<T> result, BinaryTreeNode<T> root) {
        if (root.getLeft() != null) {
            result.add(root.getLeft().getData());
            preList(result, root.getLeft());
        }
        if (root.getRight() != null) {
            result.add(root.getRight().getData());
            preList(result, root.getRight());
        }
    }

    /**
     * 用递归的方式实现对二叉树的中遍历
     * <p>
     * 左根右
     */
    public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        inOrderList(result, root);
        return result;
    }

    private static <T> void inOrderList(List<T> result, BinaryTreeNode<T> root) {
        if (root == null) {
            return;
        }
        inOrderList(result, root.getLeft());
        result.add(root.getData());
        inOrderList(result, root.getRight());
    }

    /**
     * 用递归的方式实现对二叉树的后遍历
     * <p>
     * 左右根
     */
    public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        postOrderVisit(result, root);
        return result;
    }

    private static <T> void postOrderVisit(List<T> result, BinaryTreeNode<T> root) {
        if (root == null) {
            return;
        }
        postOrderVisit(result, root.getLeft());
        postOrderVisit(result, root.getRight());
        result.add(root.getData());
    }

    /**
     * 用非递归的方式实现对二叉树的前序遍历
     */
    public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();

        Stack<BinaryTreeNode<T>> s = new Stack<>();
        while (root != null || !s.empty()) {
            while (root != null) {
                result.add(root.getData());
                s.push(root);
                root = root.getLeft();
            }
            if (!s.empty()) {
                root = s.pop(); // 取上一级
                root = root.getRight();
            }
        }
        return result;
    }


    /**
     * 用非递归的方式实现对二叉树的中序遍历
     */
    public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        Stack<BinaryTreeNode<T>> s = new Stack<>();
        while (root != null || !s.empty()) {
            while (root != null) {
                s.push(root);
                root = root.getLeft();
            }
            if (!s.empty()) {
                root = s.pop();
                result.add(root.getData());
                root = root.getRight();
            }
        }
        return result;
    }

}