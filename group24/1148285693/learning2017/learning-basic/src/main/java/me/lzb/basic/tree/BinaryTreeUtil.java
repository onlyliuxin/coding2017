package me.lzb.basic.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 前序遍历：根节点->左子树->右子树
 * 中序遍历：左子树->根节点->右子树
 * 后序遍历：左子树->右子树->根节点
 *
 * @author LZB
 */
public class BinaryTreeUtil {
    /**
     * 用递归的方式实现对二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        preOrderVisit(root, result);
        return result;
    }


    private static void preOrderVisit(BinaryTreeNode root, List result) {
        result.add(root.getData());

        if (root.getLeft() != null) {
            preOrderVisit(root.getLeft(), result);
        }
        if (root.getRight() != null) {
            preOrderVisit(root.getRight(), result);
        }
    }


    /**
     * 用递归的方式实现对二叉树的中遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        inOrderVisit(root, result);
        return result;
    }

    private static void inOrderVisit(BinaryTreeNode root, List result) {
        if (root.getLeft() != null) {
            inOrderVisit(root.getLeft(), result);
        }

        result.add(root.getData());

        if (root.getRight() != null) {
            inOrderVisit(root.getRight(), result);
        }
    }

    /**
     * 用递归的方式实现对二叉树的后遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        postOrderVisit(root, result);
        return result;
    }

    private static void postOrderVisit(BinaryTreeNode root, List result) {
        if (root.getLeft() != null) {
            postOrderVisit(root.getLeft(), result);
        }
        if (root.getRight() != null) {
            postOrderVisit(root.getRight(), result);
        }
        result.add(root.getData());
    }


    /**
     * 用非递归的方式实现对二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {

        List<T> result = new ArrayList<>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> node = root;
        //先把左侧节点全部入栈，然后一个个pop出来检查右侧节点
        while (node != null || stack.size() > 0) {
            while (node != null) {
                result.add(node.getData());
                stack.push(node);
                node = node.getLeft();
            }
            if (stack.size() > 0) {
                node = stack.pop();
                node = node.getRight();
            }
        }


        return result;
    }

    /**
     * 用非递归的方式实现对二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {

        List<T> result = new ArrayList<>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> node = root;
        while (node != null || stack.size() > 0) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
            if (stack.size() > 0) {
                node = stack.pop();
                result.add(node.getData());
                node = node.getRight();
            }
        }
        return result;
    }

    /**
     * 用非递归的方式实现对二叉树的后序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> postOrderWithoutRecursion(BinaryTreeNode<T> root) {

        List<T> result = new ArrayList<>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();

        Stack<BinaryTreeNode<T>> s2 = new Stack<>();

        BinaryTreeNode<T> node = root;

        BinaryTreeNode<T> r = root;
        while (node != null || stack.size() > 0) {
            //s2先入根节点，在入右边节点
            while (node != null) {
                stack.push(node);
                s2.push(node);
                node = node.getRight();
            }

            //最底层节点出栈，左节点继续循环
            if (stack.size() > 0) {
                node = stack.pop();
                node = node.getLeft();
            }
        }
        while (s2.size() > 0) {
            result.add(s2.pop().getData());
        }
        return result;
    }


}
