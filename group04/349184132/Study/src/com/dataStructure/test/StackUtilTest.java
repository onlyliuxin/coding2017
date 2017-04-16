package com.dataStructure.test;

import com.dataStructure.Stack;
import com.dataStructure.StackUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/** 
* StackUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 7, 2017</pre> 
* @version 1.0 
*/ 
public class StackUtilTest {
    Stack s = new Stack();

    @Before
    public void before() throws Exception {
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: reverse(Stack s)
     */
    @Test
    public void testReverse() throws Exception {

        StackUtil.reverse(s);
        int[] result = new int[s.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (Integer) s.pop();
        }
        int[] expect = new int[]{1, 2, 3, 4, 5};
        Assert.assertArrayEquals(expect, result);
    }

    /**
     * Method: remove(Stack s, Object o)
     */
    @Test
    public void testRemove() throws Exception {
        int[] result = new int[s.size() - 1];
        StackUtil.remove(s, 1);
        for (int i = 0; i < result.length; i++) {
            result[i] = (Integer) s.pop();
        }
        int[] expect = new int[]{5, 4, 3, 2};
        Assert.assertArrayEquals(expect, result);

    }

    /**
     * Method: getTop(Stack s, int len)
     */
    @Test
    public void testGetTop() throws Exception {
        // stack 5 4 3 2 1
        Object[] result = StackUtil.getTop(s, 2);
        // 5 4
        Object[] expect = {5, 4};
        Assert.assertArrayEquals(expect, result);
    }

    /**
     * Method: isValidPairs(String s)
     */
    @Test
    public void testIsValidPairs() throws Exception {
        String s1 = "([e{d}f])";
        boolean flag1 = StackUtil.isValidPairs(s1);
        Assert.assertEquals(true, flag1); // ArrayList.remove 边界 删除最后一个
        String s2 = "([b{x]y})";
        boolean flag2 = StackUtil.isValidPairs(s2);
        Assert.assertEquals(false, flag2);
    }


}