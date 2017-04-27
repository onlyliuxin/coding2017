package com.basic.test;

import com.basic.Stack;
import com.basic.StackUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xudanxia on 2017/4/6.
 */
public class StackUtilTest {


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: reverse(Stack<Integer> s)
     *
     */
    @Test
    public void testReverse() throws Exception {

        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < 10; i++) {
            stack.push(i);
        }
        System.out.println("原stack内容: " + stack);
        StackUtil.reverse(stack);
        System.out.println("反转后stack的内容: " + stack);

    }

    /**
     *
     * Method: remove(Stack s, Object o)
     *
     */
    @Test
    public void testRemove() throws Exception {

        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < 10; i++) {
            stack.push(i);
        }
        System.out.println("原stack内容: " + stack);
        StackUtil.remove(stack, 5);
        System.out.println("删除元素5后stack的内容: " + stack);
    }

    /**
     *
     * Method: getTop(Stack s, int len)
     *
     */
    @Test
    public void testGetTop() throws Exception {

        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < 10; i++) {
            stack.push(i);
        }
        System.out.println("原stack内容: " + stack);
        System.out.println("取前5个元素: " + Arrays.toString(StackUtil.getTop(stack, 5)));
        System.out.println("取完后stack的内容: " + stack);
    }

    /**
     *
     * Method: isValidPairs(String s)
     *
     */
    @Test
    public void testIsValidPairs() throws Exception {

        boolean result = StackUtil.isValidPairs("{}[({})](){}(){[{[]({})}]}[][][[[{}]]]");
        Assert.assertTrue(result);
        result = StackUtil.isValidPairs("{}[({})(){}(){[{[]({})}]}[][][[[{}]]]");
        Assert.assertFalse(result);
    }


}
