package com.pan.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树工具类
 * 思考:
 *      1、在使用递归的方式前序遍历、中序遍历、后续遍历中，如果需要返回遍历后的结果。
 *         不能直接在本方法内部直接使用递归，因为每次递归都会创建一个新的对象。所以可以写一个私有的方法，来递归
*       2、
 * @author Pan
 */
public class BinaryTreeUtil {

    /**
     * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试。
     *
     * @param root 传入的节点
     * @param <T>  泛型参数
     * @return List
     */
    public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root != null) {
            preOrderVisit(root, result);
        }
        return result;
    }


    private static <T> void preOrderVisit(BinaryTreeNode<T> root, List<T> result) {
        if (root != null) {
            result.add(root.getData());
            preOrderVisit(root.getLeft(), result);
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
        List<T> result = new ArrayList<T>();
        if (root != null) {
            inOrderVisit(root, result);
        }
        return result;
    }

    private static <T> void inOrderVisit(BinaryTreeNode<T> root, List<T> result) {
        if (root != null) {
            inOrderVisit(root.getLeft(), result);
            result.add(root.getData());
            inOrderVisit(root.getRight(), result);
        }
    }

    /**
     * 用递归的方式实现对二叉树的后遍历:
     * 左节点->右节点->中节点
     *
     * @param root
     * @return
     */
    public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root != null) {
            postOrderVisit(root, result);
        }
        return result;
    }

    private static <T> void postOrderVisit(BinaryTreeNode<T> root, List<T> result) {
        if (root != null) {
            postOrderVisit(root.getLeft(), result);
            postOrderVisit(root.getRight(), result);
            result.add(root.getData());
        }
    }

    /**
     * 用非递归的方式实现对二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {

        List<T> result = new ArrayList<T>();

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


        return result;
    }

}
