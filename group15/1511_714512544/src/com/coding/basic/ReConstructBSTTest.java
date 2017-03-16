package com.coding.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  二叉树重建测试
 */
public class ReConstructBSTTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void construct() throws Exception {
        int[] preOrder = {1,2,4,7,3,5,6,8};
        int[] inOrder = {4,7,2,1,5,3,8,6};
        ReConstructBST.Node n = ReConstructBST.construct(preOrder,inOrder);
        ReConstructBST.printInPostOrder(n);
    }

}