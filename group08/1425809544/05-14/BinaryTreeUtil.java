package com.zhuoyue.scheduleplan.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 递归和非递归实现对二叉树的遍历
 *
 * @author xyy
 * @create 2017-05-09 19:39
 **/
public class BinaryTreeUtil {

    /**
     * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        preOrderVisit(root, result);
        return result;
    }

    public static <T> void preOrderVisit(BinaryTreeNode<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        result.add(node.getData());
        preOrderVisit(node.getLeft(), result);
        preOrderVisit(node.getRight(), result);
    }

    /**
     * 用递归的方式实现对二叉树的中遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        inOrderVisit(root, result);

        return result;
    }

    public static <T> void inOrderVisit(BinaryTreeNode<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        inOrderVisit(node.getLeft(), result);
        result.add(node.getData());
        inOrderVisit(node.getRight(), result);
    }

    /**
     * 用递归的方式实现对二叉树的后遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        postOrderVisit(root, result);

        return result;
    }

    public static <T> void postOrderVisit(BinaryTreeNode<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        postOrderVisit(node.getLeft(), result);
        postOrderVisit(node.getRight(), result);
        result.add(node.getData());
    }

    /**
     * 用非递归的方式实现对二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {

        List<T> result = new ArrayList<T>();

        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> node = root;
        if (node != null) {
            stack.push(node);//先把root放入栈中
        }
        //如果栈不为空,
        while (!stack.isEmpty()) {
            node = stack.pop();
            result.add(node.getData());
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
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

        List<T> result = new ArrayList<T>();

        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> node = root;

        while (node != null || !stack.isEmpty()) {
            //寻找所有做孩纸
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
            //弹出最后一个左孩纸
            BinaryTreeNode<T> currentNode = stack.pop();
            result.add(currentNode.getData());//加入结果集
            node = currentNode.getRight();
        }
        return result;
    }

}


