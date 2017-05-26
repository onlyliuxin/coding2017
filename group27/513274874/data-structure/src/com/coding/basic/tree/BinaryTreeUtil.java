package com.coding.basic.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
            result.addAll(preOrderVisit(root.getLeft()));
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
            result.addAll(inOrderVisit(root.getLeft()));
            result.add(root.getData());
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
            result.addAll(postOrderVisit(root.getLeft()));
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

        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        if(root == null ) return  null ;

        stack.push(root);

        while(!stack.isEmpty()){
            BinaryTreeNode node = stack.pop();

            result.add((T) node.getData());

            if(node.getRight()!= null){
                stack.push(node.getRight());
            }

            if(node.getRight() != null){
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

        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();

        if(root == null ) return  null ;

        BinaryTreeNode node = root;

        while(node != null || !stack.isEmpty()){

            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
            if (stack.size() > 0) {
                node = stack.pop();
                result.add((T) node.getData());
                node = node.getRight();
            }

        }

        return result;
    }

}
