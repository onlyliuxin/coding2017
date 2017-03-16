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
        int[] preOrder = {1,2,3,4,5,6,7};
        int[] inOrder = {3,2,4,1,6,5,7};
        ReConstructBST.Node n = ReConstructBST.construct(preOrder,inOrder);
        ReConstructBST.traveralByLevel(n);
    }

}