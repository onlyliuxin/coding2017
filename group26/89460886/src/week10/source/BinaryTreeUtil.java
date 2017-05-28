package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author jiaxun
 */
public class BinaryTreeUtil {

    public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
        if (root == null) return null;
        List<T> resultList = new ArrayList<T>();
        preOrderVisit(root, resultList);
        return resultList;
    }

    private static <T> void preOrderVisit(BinaryTreeNode<T> node, List<T> resultList) {
        if (node != null) {
            resultList.add(node.getData());
            preOrderVisit(node.getLeft(), resultList);
            preOrderVisit(node.getRight(), resultList);
        }
    }

    public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
        if (root == null) return null;
        List<T> resultList = new ArrayList<T>();
        inOrderVisit(root, resultList);
        return resultList;
    }

    private static <T> void inOrderVisit(BinaryTreeNode<T> node, List<T> resultList) {
        if (node != null) {
            inOrderVisit(node.getLeft(), resultList);
            resultList.add(node.getData());
            inOrderVisit(node.getRight(), resultList);
        }
    }

    public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
        if (root == null) return null;
        List<T> resultList = new ArrayList<T>();
        postOrderVisit(root, resultList);
        return resultList;
    }

    private static <T> void postOrderVisit(BinaryTreeNode<T> node, List<T> resultList) {
        if (node != null) {
            postOrderVisit(node.getLeft(), resultList);
            postOrderVisit(node.getRight(), resultList);
            resultList.add(node.getData());
        }
    }

    public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
        if (root == null) return null;
        List<T> resultList = new ArrayList<T>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> currNode = root;
        stack.push(currNode);
        while (!stack.isEmpty()) {
            currNode = stack.pop();
            resultList.add(currNode.getData());
            if (currNode.getRight() != null) {
                stack.push(currNode.getRight());
            }
            if (currNode.getLeft() != null) {
                stack.push(currNode.getLeft());
            }
        }
        return resultList;
    }

    public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
        if (root == null) return null;
        List<T> resultList = new ArrayList<T>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
            BinaryTreeNode<T> currNode = stack.pop();
            resultList.add(currNode.getData());
            node = currNode.getRight();
        }
        return resultList;
    }

}
