package main.coding_170409;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by peter on 2017/4/23.
 */
public class StackUtilTest extends TestCase {
    @Test
    public void testReverse() throws Exception {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        StackUtil.reverse(stack);
        Assert.assertArrayEquals(new Integer[]{3,2,1},new Integer[]{stack.pop(),stack.pop(),stack.pop()});
    }

    @Test
    public void testAddToBottom() throws Exception {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        StackUtil.addToBottom(stack,1);
        Assert.assertEquals(1,stack.peek().intValue());
    }

    @Test
    public void testRemove() throws Exception {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        StackUtil.remove(stack,20);
        Assert.assertEquals(2,stack.size());
    }

    @Test
    public void testGetTop() throws Exception {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(15);
        stack.push(21);
        stack.push(19);
        stack.push(89);
        StackUtil.getTop(stack,2);
        Assert.assertEquals(3,stack.size());
    }

    @Test
    public void testIsValidPairs() throws Exception {
        String s1 = "([e{d}f])";
        String s2 = "([b{x]y})";
        Assert.assertTrue(StackUtil.isValidPairs(s1));
        Assert.assertFalse(StackUtil.isValidPairs(s2));

    }

}