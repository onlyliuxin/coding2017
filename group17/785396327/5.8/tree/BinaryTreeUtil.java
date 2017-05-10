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
        //这里必须加上“或”的stack不为空，因为下面if判断的某个节点没有右子节点，这样root就为null，但是stack不为空，可以继续弹栈
        while (root != null || !stack.isEmpty()) {
            //将某个“根”节点的所有左子节点(包含左子孙节点)放入栈中
            while (root != null) {
                result.add(root.getData());
                stack.push(root);
                root = root.getLeft();
            }
            //将上面所有“根”节点的所有左子节点弹出一个
            //这里只能一个一个查询，因为必须从离叶子节点最近的“根”节点查询是否有右节点
            if (!stack.isEmpty()) {
                root = stack.pop();
                root = root.getRight();
            }
        }
        return result;
    }


    /**
     * 用非递归的方式实现对二叉树的中序遍历
     * 思路：1.首先一层层遍历节点的左子节点，并把遍历到节点存入栈中
     *      2.当遍历到叶子节点，开始循环弹栈
     *      3.如果弹出的节点存在右子节点，将右子节点设为新的根节点，并结束弹栈循环
     * @param root
     * @return
     */
    public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {

        List<T> result = new ArrayList<T>();
        Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }

            while (!stack.isEmpty()) {
                BinaryTreeNode<T> node = stack.pop();
                result.add(node.getData());
                BinaryTreeNode<T> right = node.getRight();
                if (right != null) {
                    root = right;
                    break;
                }
            }
        }
        return result;
    }

}
