package com.github.wdn.coding2017.basic.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by wangxin on 2017/5/20.
 */
public class BinaryTreeUtil {
    /**
     * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
     *
     * @param root
     * @return
     */
    public static <T extends Comparable> List<T> preOrderVisit(BinaryTreeNode<T> root,List<T> result) {
        if(root!=null){
            result.add(root.getData());
            preOrderVisit(root.getLeft(),result);
            preOrderVisit(root.getRight(),result);
        }
        return result;
    }

    /**
     * 用递归的方式实现对二叉树的中遍历
     *
     * @param root
     * @return
     */
    public static <T extends Comparable> List<T> inOrderVisit(BinaryTreeNode<T> root,List<T> result) {
        if(root!=null){
            inOrderVisit(root.getLeft(),result);
            result.add(root.getData());
            inOrderVisit(root.getRight(),result);
        }
        return result;
    }

    /**
     * 用递归的方式实现对二叉树的后遍历
     *
     * @param root
     * @return
     */
    public static <T extends Comparable> List<T> postOrderVisit(BinaryTreeNode<T> root,List<T> result) {
        if(root!=null){
            postOrderVisit(root.getLeft(),result);
            postOrderVisit(root.getRight(),result);
            result.add(root.getData());
        }

        return result;
    }
    /**
     * 用非递归的方式实现对二叉树的前序遍历
     * @param root
     * @return
     */
    public static <T extends Comparable> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        List<T> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        stack.push(root);
        while (!stack.empty()){
            BinaryTreeNode<T> currentNode = stack.pop();
            result.add(currentNode.getData());

            if(currentNode.getRight()!=null){
                stack.push(currentNode.getRight());
            }
            if(currentNode.getLeft()!=null){
                stack.push(currentNode.getLeft());
            }
        }
        return result;
    }
    /**
     * 用非递归的方式实现对二叉树的中序遍历
     * @param root
     * @return
     */
    public static <T extends Comparable> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        ArrayList<T> result = new ArrayList<>();
        BinaryTreeNode<T> curt = root;
        while (curt != null || !stack.empty()) {
            while (curt != null) {
                stack.push(curt);
                curt = curt.getLeft();
            }
            curt = stack.pop();
            result.add(curt.getData());
            curt = curt.getRight();
        }
        return result;
    }
    public static <T extends Comparable> List<T> postOrderWithoutRecursion(BinaryTreeNode<T> root) {
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        ArrayList<T> result = new ArrayList<>();
        BinaryTreeNode<T> curt = root;
        BinaryTreeNode<T> prev = null;
        stack.push(curt);
        while (!stack.empty()) {
            curt = stack.peek();
            if ((curt.getLeft() == null && curt.getRight() == null) || (prev != null &&(prev == curt.getLeft() || prev == curt.getRight()))){
                result.add(curt.getData());
                stack.pop();
                prev = curt;
            }
            else{
                if(curt.getRight() != null){
                    stack.push(curt.getRight());
                }
                if(curt.getLeft() != null){
                    stack.push(curt.getLeft());
                }
            }
           
        }
        return result;
    }
}
