package com.dudy.learn01.base;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dudy on 2017/2/21.
 */
public class MyBinaryTreeTest {


    /**
     * 		5
     * 	 2     7
     * 1  4  6   8
     *
     */
    @Test
    public void insert() throws Exception {


        MyBinaryTree tree = new MyBinaryTree();
        tree.insert(5);
        tree.insert(2);
        tree.insert(1);
        tree.insert(7);
        tree.insert(8);
        tree.insert(4);
        tree.insert(6);


        tree.display(tree.getRoot());

    }

    @Test
    public void display() throws Exception {

    }

}