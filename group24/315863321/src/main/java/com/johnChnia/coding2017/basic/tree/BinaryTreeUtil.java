package com.johnChnia.coding2017.basic.tree;


import com.johnChnia.coding2017.basic.stack.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by john on 2017/5/18.
 * 前序遍历：<root><left><right>.
 * 中序遍历: <left><root><right>.
 * 后序遍历: <left><right><root>.
 */
public class BinaryTreeUtil {
    /**
     * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
        if (root == null) {
            return null;
        }
        List<T> result = new ArrayList<>();
        result.add(root.data);
        List<T> temp1 = preOrderVisit(root.left);
        if (temp1 != null) {
            result.addAll(temp1);
        }
        List<T> temp2 = preOrderVisit(root.right);
        if (temp2 != null) {
            result.addAll(temp2);
        }
        return result;
    }

    /**
     * 用递归的方式实现对二叉树的中遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
        if (root == null) {
            return null;
        }
        List<T> result = new ArrayList<>();
        List<T> temp1 = inOrderVisit(root.left);
        if (temp1 != null) {
            result.addAll(temp1);
        }
        result.add(root.data);
        List<T> temp2 = inOrderVisit(root.right);
        if (temp2 != null) {
            result.addAll(temp2);
        }
        return result;
    }

    /**
     * 用递归的方式实现对二叉树的后遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
        if (root == null) {
            return null;
        }
        List<T> result = new ArrayList<>();

        List<T> temp1 = postOrderVisit(root.left);
        if (temp1 != null) {
            result.addAll(temp1);
        }
        List<T> temp2 = postOrderVisit(root.right);
        if (temp2 != null) {
            result.addAll(temp2);
        }
        result.add(root.data);
        return result;
    }

    /**
     * 用非递归的方式实现对二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
        if (Objects.isNull(root)) {
            return null;
        }
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        List<T> result = new ArrayList<>();
        stack.push(root);
        while (!stack.empty()) {
            BinaryTreeNode<T> top = stack.pop();
            if (Objects.nonNull(top.getRight())) {
                stack.push(top.getRight());
            }
            if (Objects.nonNull(top.getLeft())) {
                stack.push(top.getLeft());
            }
            result.add(top.data);
        }
        return result;
    }

    /**
     * 用非递归的方式实现对二叉树的中序遍历
     * 参考：http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
     *
     * @param root
     * @return
     */
    public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
        if (Objects.isNull(root)) {
            return null;
        }
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        List<T> result = new ArrayList<>();
        BinaryTreeNode<T> node = root;
        while (Objects.nonNull(node)) {
            stack.push(node);
            node = node.getLeft();
        }
        while (!stack.empty()) {
            node = stack.pop();
            result.add(node.data);
            if (Objects.nonNull(node.getRight())) {
                node = node.right;
                while (Objects.nonNull(node)) {
                    stack.push(node);
                    node = node.getLeft();
                }
            }
        }
        return result;
    }

}