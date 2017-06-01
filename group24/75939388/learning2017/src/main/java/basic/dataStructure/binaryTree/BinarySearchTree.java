package basic.dataStructure.binaryTree;

import java.util.*;

public class BinarySearchTree<T extends Comparable> {

    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        return findMin(root);
    }

    private T findMin(BinaryTreeNode<T> root){
        List<T> list = BinaryTreeUtil.preOrderVisit(root);
        T min = list.get(0);
        for (T t : list) {
            min = min.compareTo(t) == -1 ? min : t;
        }
        return min;
    }

    public T findMax() {
        return findMax(root);
    }

    private T findMax(BinaryTreeNode<T> root){
        List<T> list = BinaryTreeUtil.preOrderVisit(root);
        T max = list.get(0);
        for (T t : list) {
            max = max.compareTo(t) == 1 ? max : t;
        }
        return max;
    }

    public int height() {
        return calHeight(root);
    }

    private int calHeight(BinaryTreeNode<T> root) {
        if (root == null) return 0;

        int left = calHeight(root.left);
        int right = calHeight(root.right);

        return (left > right ? left : right) + 1;

    }

    public int size() {
        List<T> list = BinaryTreeUtil.preOrderVisit(root);
        return list.size();
    }

    public void remove(T e) {
        remove(root, (Integer) e);
    }

    private void remove(BinaryTreeNode<T> node, int value) {
        if (node == null) throw new RuntimeException("no such node has value = " + value);

        T data = node.getData();
        if (data.compareTo(value) == 0) {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left != null && node.right == null) {
                node.data = node.left.data;
                node.left = null;
            } else if (node.left == null && node.right != null) {
                node.data = node.right.data;
                node.right = null;
            } else {
                T replace = findMin(node.right);
                node.data = replace;
                remove(node.right, (Integer)replace);
            }
        } else if (data.compareTo(value) == -1) {
            remove(node.right, value);
        } else {
            remove(node.left, value);
        }
    }

    /**
     * 逐层遍历
     */
    public List<T> levelVisit(){
        Queue<BinaryTreeNode> buffer = new LinkedList<BinaryTreeNode>();
        List<T> result = new ArrayList<T>();

        result = level(root, result, buffer);

        return result;
    }

    private List<T> level(BinaryTreeNode<T> node, List<T> result, Queue<BinaryTreeNode> buffer){
        result.add(node.getData());
        if(node.getLeft() != null){
            buffer.offer(node.left);
        }

        if(node.getRight() != null){
            buffer.offer(node.right);
        }

        while(!buffer.isEmpty()){
            result = level(buffer.poll(), result, buffer);
        }

        return result;
    }

    /**
     * 判断一个二叉树是不是二叉查找树
     */
    public boolean isValid(){
        if(root.left == null || root.right == null){
            return false;
        }

        return root.left.getData().compareTo(root.getData()) == -1 &&
                root.right.getData().compareTo(root.getData()) == 1;
    }

    /**
     * 获取两个节点的最小公共祖先
     */
    public T getLowestCommonAncestor(T n1, T n2){
        if(!isValid()){
            throw new RuntimeException("this tree is not binary search tree");
        }

        return getLowestCommonAncestor(root, n1, n2).data;
    }

    private BinaryTreeNode<T> getLowestCommonAncestor(BinaryTreeNode<T> node, T n1, T n2){
//        if(node == null || node.data.compareTo(n1) == 0 || node.data.compareTo(n2) == 0){
//            return node;
//        }
//
//        BinaryTreeNode<T> left = getLowestCommonAncestor(node.left, n1, n2);
//        BinaryTreeNode<T> right = getLowestCommonAncestor(node.right, n1, n2);
//
//        if (left != null && right != null) {
//            return node;
//        }
//        if (left != null) {
//            return left;
//        }
//        if (right != null) {
//            return right;
//        }
//        return null;
        if(node == null){
            return null;
        }

        int cmp1 = n1.compareTo(node.data);
        int cmp2 = n2.compareTo(node.data);

        if(cmp1 > 0 && cmp2 > 0){
            return getLowestCommonAncestor(node.right, n1, n2);
        }

        if(cmp1 < 0 && cmp2 < 0){
            return getLowestCommonAncestor(node.left, n1, n2);
        }

        return node;
    }


    /**
     * 给定两个值， 获得处于这两个值中间的节点
     */
    public List<T> getNodesBetween(T n1, T n2){
        if(!isValid()) throw new RuntimeException("this is not binary search tree");
        List<T> list = new ArrayList<T>();
        getNodesBetween(root, n1, n2, list);
        return list;
    }

    private void getNodesBetween(BinaryTreeNode<T> node, T n1, T n2, List<T> list){
        int cmp1 = n1.compareTo(node.data);
        int cmp2 = n2.compareTo(node.data);

        if(cmp1 == -1 && node.left != null){
            if(cmp2 != 0) list.add(node.data);
            getNodesBetween(node.left, n1, n2, list);
        }else if(cmp2 == 1 && node.right != null){
            if(cmp1 != 0) list.add(node.data);
            getNodesBetween(node.right, n1, n2, list);
        }
    }
}

