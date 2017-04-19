package com.coding.week5.stack;

import com.coding.weak1.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
public class StackUtilTest {

    private Stack stack;

    @Before
    public void setup() {
        stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

    }
    @Test
    public void reverse() throws Exception {
        Assert.assertEquals("4,3,2,1",stack.toString());
        StackUtil.reverse(stack);
        Assert.assertEquals("1,2,3,4",stack.toString());
    }

    @Test
    public void remove() throws Exception {
        StackUtil.remove(stack, 3);
        Assert.assertEquals(stack.toString(), "4,2,1");
    }

    @Test
    public void getTop() throws Exception {
        Object[] objects = StackUtil.getTop(stack, 2);
        Assert.assertEquals(objects[0], 4);
        Assert.assertEquals(objects[1], 3);
    }

    @Test
    public void isValidPairs() throws Exception {
        String str = "[abdd]}";
        Assert.assertEquals(StackUtil.isValidPairs(str), false);
        str = "{add{ad[ddd]}}";
        Assert.assertEquals(StackUtil.isValidPairs(str), true);
        str = "{add{ad[d(dd]}}";
        Assert.assertEquals(StackUtil.isValidPairs(str), false);
        str = "{add{ad[d(d)d]}dfd}";
        Assert.assertEquals(StackUtil.isValidPairs(str), true);
    }

}