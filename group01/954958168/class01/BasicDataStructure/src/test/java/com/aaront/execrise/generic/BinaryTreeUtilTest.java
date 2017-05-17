package com.aaront.execrise.generic;

import com.aaront.exercise.generic.BinaryTreeUtil;
import com.aaront.exercise.generic.GenericBinaryTree;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class BinaryTreeUtilTest {

    GenericBinaryTree<Integer> root = null;

    @Before
    public void setUp() throws Exception {
        root = new GenericBinaryTree<>();
        root.add(1);
        root.add(2);
        root.add(5);
        root.add(3);
        root.add(4);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPreOrderVisit() {
        List<Integer> result = BinaryTreeUtil.preOrderVisit(root);
        Assert.assertEquals("[1, 2, 5, 3, 4]", result.toString());
    }

    @Test
    public void testInOrderVisit() {
        List<Integer> result = BinaryTreeUtil.inOrderVisit(root);
        Assert.assertEquals("[1, 2, 3, 4, 5]", result.toString());
    }

    @Test
    public void testPostOrderVisit() {
        List<Integer> result = BinaryTreeUtil.postOrderVisit(root);
        Assert.assertEquals("[4, 3, 5, 2, 1]", result.toString());
    }


    @Test
    public void testInOrderVisitWithoutRecursion() {
        root.add(6);
        root.add(7);
        List<Integer> result = BinaryTreeUtil.inOrderWithoutRecursion(root);
        Assert.assertEquals("[1, 2, 3, 4, 5, 6, 7]", result.toString());

    }

    @Test
    public void testPreOrderVisitWithoutRecursion() {
        root.add(6);
        root.add(7);

        List<Integer> result = BinaryTreeUtil.preOrderWithoutRecursion(root);
        Assert.assertEquals("[1, 2, 5, 3, 4, 6, 7]", result.toString());

    }
}