package tree;

import queue.Queue;
import stack.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william on 2017/5/8.
 */
public class BinaryTreeUtil {
    /**
     * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        if (root != null) {
            result.add(root.getData());
            if (root.getLeft() != null)
                result.addAll(preOrderVisit(root.getLeft()));
            if (root.getRight() != null)
                result.addAll(preOrderVisit(root.getRight()));
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
        List<T> result = new ArrayList<T>();
        if (root != null) {
            if (root.getLeft() != null)
                result.addAll(inOrderVisit(root.getLeft()));
            result.add(root.getData());
            if (root.getRight() != null)
                result.addAll(inOrderVisit(root.getRight()));
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
        List<T> result = new ArrayList<T>();
        if (root != null) {
            if (root.getLeft() != null)
                result.addAll(postOrderVisit(root.getLeft()));
            if (root.getRight() != null)
                result.addAll(postOrderVisit(root.getRight()));
            result.add(root.getData());
        }
        return result;
    }

    /**
     * 用非递归的方式实现对二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
        if (root != null) {

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
        Queue<BinaryTreeNode<T>> queue = new Queue<BinaryTreeNode<T>>();
        if (root != null) {
            queue.offer(root);
            while (!queue.isEmpty()) {
                BinaryTreeNode<T> node = queue.poll();
                if (node.getLeft() != null)
                    queue.offer(node);
                result.add(node.getData());
                if (node.getRight() != null)
                    queue.offer(node);
            }
        }
        return result;
    }

}
