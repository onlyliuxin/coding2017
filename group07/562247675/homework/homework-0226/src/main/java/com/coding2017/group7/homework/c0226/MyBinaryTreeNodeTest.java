package com.coding2017.group7.homework.c0226;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyBinaryTreeNodeTest {


    @Test
    public void insert() throws Exception {
        MyBinaryTreeNode node = new MyBinaryTreeNode();
        node.insert(5)
                .insert(2)
                .insert(7)
                .insert(1)
                .insert(6)
                .insert(4)
                .insert(8)
                .insert(3);
        Comparable data1 = node.getLeft().getLeft().getData();
        Comparable data4 = node.getLeft().getRight().getData();
        Comparable data6 = node.getRight().getLeft().getData();
        Comparable data8 = node.getRight().getRight().getData();
        Comparable data3 = node.getLeft().getRight().getLeft().getData();
        Assert.assertEquals(1, data1);
        Assert.assertEquals(4, data4);
        Assert.assertEquals(6, data6);
        Assert.assertEquals(8, data8);
        Assert.assertEquals(3, data3);

    }


}