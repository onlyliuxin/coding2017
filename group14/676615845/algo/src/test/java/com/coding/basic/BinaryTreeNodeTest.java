package com.coding.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mark on 17/2/25.
 */
public class BinaryTreeNodeTest {

    private BinaryTreeNode tree;

    @Before
    public void setUp() throws Exception {
        tree = new BinaryTreeNode();
    }

    @After
    public void tearDown() throws Exception {
        tree = null;
    }

    @Test
    public void getData() throws Exception {

    }

    @Test
    public void setData() throws Exception {

    }

    @Test
    public void getLeft() throws Exception {

    }

    @Test
    public void setLeft() throws Exception {

    }

    @Test
    public void getRight() throws Exception {

    }

    @Test
    public void setRight() throws Exception {

    }

    @Test
    public void insert() throws Exception {
        tree.insert("8");
        tree.insert("1");
        tree.insert("2");
        tree.insert("10");
        tree.insert("4");
        tree.insert("34");
    }

}