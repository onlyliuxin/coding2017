package com.aaront.exercise.generic;

import java.util.Arrays;
import java.util.List;

public class BinaryTreeUtil {
    /**
     * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
     *
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> preOrderVisit(GenericBinaryTree<T> root) {
        T[] result = root.traversal(GenericBinaryTree.PREORDER, (T[]) new Integer[0]);
        return Arrays.asList(result);
    }

    /**
     * 用递归的方式实现对二叉树的中遍历
     *
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> inOrderVisit(GenericBinaryTree<T> root) {
        T[] result = root.traversal(GenericBinaryTree.INORDER, (T[]) new Integer[0]);
        return Arrays.asList(result);
    }

    /**
     * 用递归的方式实现对二叉树的后遍历
     *
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> postOrderVisit(GenericBinaryTree<T> root) {
        T[] result = root.traversal(GenericBinaryTree.POSTORDER, (T[]) new Integer[0]);
        return Arrays.asList(result);
    }

    /**
     * 用非递归的方式实现对二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> preOrderWithoutRecursion(GenericBinaryTree<T> root) {

        List<T> result = root.traversalWithoutRecursion(GenericBinaryTree.PREORDER);

        return result;
    }

    /**
     * 用非递归的方式实现对二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> inOrderWithoutRecursion(GenericBinaryTree<T> root) {

        List<T> result = root.traversalWithoutRecursion(GenericBinaryTree.INORDER);

        return result;
    }

}