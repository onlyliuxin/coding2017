package com.bruce.homework0409.stack;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Stack;

/**
 * Created by Bruce.Jiao on 2017/4/8.
 */
public class StackUtilTest {

    private Stack<Integer>  stack;

    @Before
    public void create() {
        stack = new Stack<>();
        stack.push(3);
        stack.push(5);
        stack.push(6);
        stack.push(9);
        stack.push(0);
    }

    @Test
    public void testReverse() {
        System.out.println("original:" + stack.toString());
        stack = StackUtil.reverse(stack);
        System.out.println("after reverse:" + stack.toString());
    }

    @Test
    public void testRemove() {
        System.out.println("original:" + stack.toString());
        StackUtil.remove(stack, 5);
        System.out.println("after remove 5 :" + stack.toString());
    }

    @Test
    public void testGetTop() {
        System.out.println("original:" + stack.toString());
        Object[] top = StackUtil.getTop(stack, 3);
        System.out.println("get top 3 :" + Arrays.toString(top));
        System.out.println("after get top :" + stack.toString());
    }

    @Test
    public void test() {
        System.out.println("({[e({d})f]}) : " + StackUtil.isValidPairs("({[e({d})f]})"));
        System.out.println("(d)" + StackUtil.isValidPairs("(d)"));
        System.out.println("([b{x]y})" + StackUtil.isValidPairs("([b{x]y})"));
    }
}
