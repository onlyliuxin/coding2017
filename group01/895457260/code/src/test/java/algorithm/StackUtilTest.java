package algorithm;

import datastructure.basic.Stack;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;

/**
 * StackUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>四月 9, 2017</pre>
 */
public class StackUtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    private Stack build(Object... args) {
        Stack stack = new Stack();
        Arrays.stream(args).forEach(stack::push);
        return stack;
    }

    private Object[] toArray(Stack stack) {
        Object[] array = new Object[stack.size()];
        for (int i = array.length - 1; i >= 0; --i) {
            array[i] = stack.pop();
        }
        Arrays.stream(array).forEach(stack::push);
        return array;
    }

    /**
     * Method: reverse(Stack s)
     */
    @Test
    public void testReverse() throws Exception {
//TODO: Test goes here...
        Stack stack = build(1, 3, 5, 7, 9);
        StackUtil.reverse(stack);
        Assert.assertArrayEquals(new Object[] {9, 7, 5, 3, 1}, toArray(stack));
    }

    /**
     * Method: remove(Stack s, Object o)
     */
    @Test
    public void testRemove() throws Exception {
//TODO: Test goes here...
        Stack stack = build(1, 3, 5, 7, 9, 11, 13);
        StackUtil.remove(stack, 0);
        Assert.assertArrayEquals(new Object[] {1, 3, 5, 7, 9, 11, 13}, toArray(stack));
        StackUtil.remove(stack, 1);
        Assert.assertArrayEquals(new Object[] {3, 5, 7, 9, 11, 13}, toArray(stack));
        StackUtil.remove(stack, 13);
        Assert.assertArrayEquals(new Object[] {3, 5, 7, 9, 11}, toArray(stack));
        StackUtil.remove(stack, 7);
        Assert.assertArrayEquals(new Object[] {3, 5, 9, 11}, toArray(stack));
    }

    /**
     * Method: getTop(Stack s, int len)
     */
    @Test
    public void testGetTop() throws Exception {
//TODO: Test goes here...
        Stack stack = build(1, 3, 5);
        Object[] array = toArray(stack);
        Assert.assertArrayEquals(new Object[] {}, StackUtil.getTop(stack, 0));
        Assert.assertArrayEquals(new Object[] {1, 3, 5}, array);
        Assert.assertArrayEquals(new Object[] {3, 5}, StackUtil.getTop(stack, 2));
        Assert.assertArrayEquals(new Object[] {1, 3, 5}, array);
        Assert.assertArrayEquals(new Object[] {1, 3, 5}, StackUtil.getTop(stack, 3));
        Assert.assertArrayEquals(new Object[] {1, 3, 5}, array);
        Assert.assertArrayEquals(new Object[] {1, 3, 5}, StackUtil.getTop(stack, 4));
        Assert.assertArrayEquals(new Object[] {1, 3, 5}, array);
    }

    /**
     * Method: isValidPairs(String s)
     */
    @Test
    public void testIsValidPairs() throws Exception {
//TODO: Test goes here...
        Assert.assertTrue(StackUtil.isValidPairs("{sqrt[p*(p-a)*(p-b)*(p-c)]}"));
        Assert.assertFalse(StackUtil.isValidPairs("{sqrt[p*[p-a)*(p-b]*(p-c)]}"));
    }

} 
