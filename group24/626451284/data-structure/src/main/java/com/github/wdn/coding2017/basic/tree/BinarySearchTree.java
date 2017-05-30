package com.github.wdn.coding2017.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by wangxin on 2017/5/26.
 */
public class BinarySearchTree<T extends Comparable> {
    BinaryTreeNode<T> root;
    public BinarySearchTree(BinaryTreeNode<T> root){
        this.root = root;
    }
    public BinaryTreeNode<T> getRoot(){
        return root;
    }
    public T findMin(){
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        BinaryTreeNode<T> local = root;
        while (local.getLeft()!=null){
            local = local.getLeft();
        }
        return local.data;
    }
    public T findMax(){
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        BinaryTreeNode<T> local = root;
        while (local.getRight()!=null){
            local = local.getRight();
        }
        return local.data;
    }
    public int height() {
        if(root==null){
            return 0;
        }
        int leftDeep = findChild(root.getLeft(),2);
        int rightDeep = findChild(root.getRight(),2);
        return Math.max(leftDeep,rightDeep);
    }
    private int findChild(BinaryTreeNode<T> node,int deepIndex){
        if(node.getLeft()!=null){
            findChild(node.getLeft(),deepIndex++);
        }
        if(node.getRight()!=null){
            findChild(node.getRight(),deepIndex++);
        }
        return deepIndex;
    }
    public int size() {
        if(root==null){
            return 0;
        }
        int count = sumChild(root)+1;
        return count;
    }
    private int sumChild(BinaryTreeNode<T> node){
        int count = 0;
        if(node.getLeft()!=null){
            count++;
            count+=sumChild(node.getLeft());
        }
        if(node.getRight()!=null){
            count++;
            count+=sumChild(node.getRight());
        }
        return count;
    }
    public void remove(T e){

    }
    public List<T> levelVisit(){
        List<T> values = new ArrayList<>();
        if(root == null){
            return values;
        }

        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();//层序遍历时保存结点的队列
        queue.offer(root);//初始化
        while(!queue.isEmpty()){
            BinaryTreeNode<T> node = queue.poll();
            //System.out.print(node.data + " ");//访问节点
            values.add(node.data);
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        return values;
    }
    public boolean isValid(){
        if (root == null) {
            return true;
        }
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private boolean dfs(BinaryTreeNode root, Comparable low, Comparable up) {
        if (root == null) {
            return true;
        }
        if (root.data.compareTo(up) >= 0 || root.data.compareTo(low) <= 0) {
            return false;
        }
        return dfs(root.left, low, root.data) && dfs(root.right, root.data, up);
    }
    public T getLowestCommonAncestor(BinaryTreeNode<T> nodeData, T node1, T node2){
        if(nodeData == null){
            return null;
        }
        if (nodeData.data == node1 || nodeData.data == node2) {
            return nodeData.data;
        }

        // Divide
        T left = getLowestCommonAncestor((BinaryTreeNode<T>)nodeData.left, node1, node2);
        T right = getLowestCommonAncestor((BinaryTreeNode<T>)nodeData.right, node1, node2);

        // Conquer
        if (left != null && right != null) {
            return nodeData.data;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }
    public List<T> getNodesBetween(T n1, T n2){
        return null;
    }
}
