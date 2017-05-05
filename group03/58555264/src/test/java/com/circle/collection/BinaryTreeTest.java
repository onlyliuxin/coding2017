package com.circle.collection;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by keweiyang on 2017/2/25.
 */
public class BinaryTreeTest {

    private BinaryTree tree;

    @Test
    public void get() throws Exception {
        tree = new BinaryTree();
        tree.insert(5, 5);
        tree.insert(6, 6);
        System.out.println(tree.get(5));

    }

    @Test
    public void insert() throws Exception {
        tree = new BinaryTree();
        tree.insert(5, 5);
        tree.insert(6, 6);

    }

    @Test
    public void preOrder() throws Exception {
        tree = new BinaryTree();
        tree.insert(5, 5);
        tree.insert(6, 6);
        tree.preOrder(tree.get(5));

    }

    @Test
    public void inOrder() throws Exception {
        tree = new BinaryTree();
        tree.insert(5, 5);
        tree.insert(6, 6);
        tree.insert(4, 4);
        tree.inOrder(tree.get(5));
    }

    @Test
    public void postOrder() throws Exception {
        tree = new BinaryTree();
        tree.insert(5, 5);
        tree.insert(6, 6);
        tree.insert(4, 4);
        tree.postOrder(tree.get(5));
    }

    @Test
    public void delete() throws Exception {
        tree = new BinaryTree();
        tree.insert(7, 7);
        tree.insert(5, 5);
        tree.insert(10, 10);
        tree.insert(2, 2);
        tree.insert(6, 6);
        tree.insert(11, 11);
        tree.insert(13, 13);
        tree.inOrder(tree.get(7));

       tree.delete(10);
        tree.inOrder(tree.get(7));

    }
}