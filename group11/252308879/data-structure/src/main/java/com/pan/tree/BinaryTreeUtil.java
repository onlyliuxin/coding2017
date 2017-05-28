package com.pan.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 代码被覆盖了，重新编写。
 * 思考:
 * 1、前序遍历： 根节点->左节点->右节点
 * 2、中序遍历:  左节点->根节点->右节点
 * 3、后序遍历:  左节点->右节点->根节点
 * 在使用迭代的方式实现三种遍历的时候，如果需要返回遍历后的值。那么需要重新写一个私有方法，传入用于收集的result来实现迭代。
 * 否则，被自己方法内迭代每次进入都会new一个新的对象
 */
public class BinaryTreeUtil {

    /**
     * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
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
        List<T> result = new ArrayList<>();
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

    public static <T> void postOrderVisit(BinaryTreeNode<T> node,List<T> result) {
        if (node == null){
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
        List<T> result = new ArrayList<>();
        Stack<BinaryTreeNode<T>> nodeStack = new Stack<>();
        BinaryTreeNode<T> node = root;
        if (node != null){
            nodeStack.push(node);
        }
        while (!nodeStack.isEmpty()){
            node = nodeStack.pop();
            result.add(node.getData());

            // 在此处要注意了：因为栈操作时先进后出，所以先判断右节点入栈
            if (node.getRight() != null){
                nodeStack.push(node.getRight());
            }
            if (node.getLeft() != null){
                nodeStack.push(node.getLeft());
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

        Stack<BinaryTreeNode<T>> nodeStack = new Stack<>();
        BinaryTreeNode<T> node = root;

        while (node != null || !nodeStack.isEmpty()){
            while (node != null){
                nodeStack.push(node);
                node = node.getLeft();
            }

            BinaryTreeNode<T> currentNode = nodeStack.pop();
            result.add(currentNode.getData());
            node = currentNode.getRight();
        }
        return result;
    }

}
