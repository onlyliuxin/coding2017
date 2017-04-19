package com.donaldy.test;

import com.donaldy.basic.Stack;
import com.donaldy.basic.StackUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DonaldY on 2017/4/6.
 */
public class StackUtilTest {

    @Test
    public void testReverse() {
        Stack stack = new Stack();
        Integer [] intArr = {5, 4, 3, 2, 1};
        for (int i = 4; i >= 0 ; --i)
            stack.push(intArr[i]);
        StackUtil.reverse(stack);
        for (int i = 4 ; i >= 0; --i) {
            Assert.assertEquals((int)stack.pop(), (int)intArr[i]);
        }
    }

    @Test
    public void testRemove() {
        Stack stack = new Stack();
        Integer [] intArr = {5, 4, 3, 2, 1};
        for (int i = 4; i >= 0 ; --i)
            stack.push(intArr[i]);

        StackUtil.remove(stack, 2);
        for (int i = 0; i < 5; ++i) {
            if (i == 3)
                continue;
            System.out.println("stack: " + stack.peek() + " i :　" + (int) intArr[i]);
            Assert.assertEquals((int)stack.pop(), (int)intArr[i]);
        }
    }

    @Test
    public void testGetTop() {
        Stack stack = new Stack();
        Integer [] intArr = {5, 4, 3, 2, 1};
        for (int i = 4; i >= 0 ; --i)
            stack.push(intArr[i]);

        int len = 3;
        Object [] arr = StackUtil.getTop(stack, len);

        for (int i = 0 ; i < arr.length ; ++i) {
            Assert.assertEquals((int)arr[i], (int)intArr[i]);
        }

        for (int i = 0; i < 5; ++i) {
            Assert.assertEquals((int)intArr[i], (int)stack.pop());
        }
    }

    @Test
    public void testIsValidPairs() {

        String str1 = "([e{d}f])";
        Assert.assertEquals(true, StackUtil.isValidPairs(str1));

        String str2 = "([b{x]y})";
        Assert.assertEquals(false, StackUtil.isValidPairs(str2));

    }
}
