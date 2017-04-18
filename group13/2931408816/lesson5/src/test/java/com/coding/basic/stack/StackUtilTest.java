package com.coding.basic.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

/**
 * Created by pikachu on 2017/4/7.
 */
public class StackUtilTest {
    // public static void reverse(Stack s)
    @Test
    public void testReverse(){
        Stack stack = new Stack();
        for (int i = 0; i < 5; i++) {
            stack.push(i+1);
        }
        Assert.assertEquals("[1, 2, 3, 4, 5]",stack.toString());
        StackUtil.reverse(stack);
        Assert.assertEquals("[5, 4, 3, 2, 1]",stack.toString());
    }
    // public static void remove(Stack s,Object o)
    @Test
    public void testRemove(){
        Stack stack = new Stack();
        for (int i = 0; i < 6; i++) {
            stack.push(i);
        }
        StackUtil.remove(stack,0);
        Assert.assertEquals("[1, 2, 3, 4, 5]", stack.toString());

        StackUtil.remove(stack,3);
        Assert.assertEquals("[1, 2, 4, 5]", stack.toString());

        StackUtil.remove(stack,5);
        Assert.assertEquals("[1, 2, 4]", stack.toString());
    }
    // public static Object[] getTop(Stack s,int len)
    @Test
    public void testGetTop(){
        Stack stack = new Stack();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        Object[] objects = StackUtil.getTop(stack,2);
        Assert.assertArrayEquals(new Object[]{9,8},objects);
    }
    // public static boolean isValidPairs(String s)

    /**
     *
     * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
     */
    @Test
    public void testIsValidPairs(){
       Assert.assertTrue( StackUtil.isValidPairs("([e{d}f])"));
       Assert.assertFalse(StackUtil.isValidPairs("([b{x]y})"));
    }
}
