package com.johnChnia.coding2017.basic.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.johnChnia.coding2017.basic.stack.StackUtil.*;


/**
 * Created by john on 2017/4/7.
 */
public class StackUtilTest {

    Stack<Integer> s1;
    Stack<Integer> s2;
    Stack<Integer> s3;

    @Before
    public void setUp() throws Exception {
        s1 = new Stack<>();
        s2 = new Stack<>();
        s3 = new Stack<>();

    }

    @Test
    public void testReverse() throws Exception {
        for (int i = 0; i < 4; i++) {
            s1.push(i);
        }
        reverse(s1);
        Assert.assertEquals("0→1→2→3", s1.toString());
    }

    @Test
    public void testRemove() throws Exception {
        for (int i = 0; i < 4; i++) {
            s2.push(i);
        }
        remove(s2, 1);
        Assert.assertEquals("3→2→0", s2.toString());
    }

    @Test
    public void testGetTop() throws Exception {
        for (int i = 0; i < 4; i++) {
            s3.push(i);
        }
        Object[] array = getTop(s3, 2);
        Assert.assertEquals(array.length, 2);
        Assert.assertEquals(array[0], 3);
        Assert.assertEquals(array[1], 2);
        Assert.assertEquals("3→2→1→0", s3.toString());

    }

    @Test
    public void testIsValidPairs() throws Exception {
        String s1 = "([e{d}f])";
        Assert.assertTrue(isValidPairs(s1));
        String s2 = "([b{x]y})";
        Assert.assertFalse(isValidPairs(s2));
    }

}