package code10;

import code01.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by on 2017/5/9.
 */
public class BinaryTreeUtil {
    /**
     * 用递归的方式实现对二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        preOrder(root,result);
        return result;
    }

    public static <T> void preOrder(BinaryTreeNode<T> root, List<T> result){
        if(root == null){
            return;
        }
        result.add(root.getData());
        preOrder(root.getLeft(),result);
        preOrder(root.getRight(),result);
    }

    /**
     * 用递归的方式实现对二叉树的中遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        inOrder(root,result);
        return result;
    }

    public static <T> void inOrder(BinaryTreeNode<T> root, List<T> result){
        if(root == null){
            return;
        }
        inOrder(root.getLeft(),result);
        result.add(root.getData());
        inOrder(root.getRight(),result);
    }

    /**
     * 用递归的方式实现对二叉树的后遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<T>();
        postOrder(root,result);
        return result;
    }

    public static <T> void postOrder(BinaryTreeNode<T> root, List<T> result){
        if(root == null){
            return;
        }
        postOrder(root.getLeft(),result);
        postOrder(root.getRight(),result);
        result.add(root.getData());
    }

    /**
     * 用非递归的方式实现对二叉树的前序遍历（后序便历类似）
     * @param root
     * @return
     */
    public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {

        List<T> result = new ArrayList<T>();
        Stack visitStack = new Stack();

        visitStack.push(root);

        while(!visitStack.isEmpty()){
            BinaryTreeNode node = (BinaryTreeNode) visitStack.pop();
            result.add((T) node.getData());
            if(node.getRight() != null){
                visitStack.push(node.getRight());
            }
            if(node.getLeft() != null){
                visitStack.push(node.getLeft());
            }

        }

        return result;
    }
    /**
     * 用非递归的方式实现对二叉树的中序遍历
     */
    public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {

        List<T> result = new ArrayList<T>();

        Stack visitStack = new Stack();

        BinaryTreeNode node = root;

        while(node != null ||!visitStack.isEmpty()){
            while (node != null){
                visitStack.push(node);
                node = node.left;
            }
            BinaryTreeNode pNode = (BinaryTreeNode) visitStack.pop();
            result.add((T) pNode.getData());
            node = pNode.right;
        }
        return result;
    }

}