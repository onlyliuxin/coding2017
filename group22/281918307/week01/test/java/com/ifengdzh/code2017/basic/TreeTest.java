package com.ifengdzh.code2017.basic;

import static org.junit.Assert.*;

/**
 * 插入树节点.
 * Created by ajaxfeng on 2017/4/4.
 */
public class TreeTest {

    @org.junit.Test
    public void testInsert() throws Exception {
        Tree tree=new Tree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        tree.insert(7);
        tree.insert(2);
        System.out.println("out ... ");
        tree.traceAll();

    }

}