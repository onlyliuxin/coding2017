package com.coding.basic.stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * r
 * Created by songbao.yang on 2017/4/11.
 */
public class StackUtilTest {

    StackUtil stackUtil;
    Stack stack;

    @Before
    public void setUp() throws Exception {
        stackUtil  = new StackUtil();
        stack = new Stack();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void reverse() throws Exception {

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        StackUtil.reverse(stack);

        String reverse = stack.toString();

        Assert.assertEquals("1,2,3,4,5", reverse);
    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void getTop() throws Exception {

    }

    @Test
    public void isValidPairs() throws Exception {

    }

}