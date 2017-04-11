package assignment0405;

import assignment.Stack;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/9.
 */
public class StackUtilTest {
    @Test
    public void reverse() throws Exception {
        Stack stack = new Stack();
        pushAll(stack, 1, 2, 3, 4);
        Assert.assertEquals("4, 3, 2, 1", stack.toString());
        StackUtil.reverse(stack);
        Assert.assertEquals("1, 2, 3, 4", stack.toString());
        StackUtil.reverse(stack);
        Assert.assertEquals("4, 3, 2, 1", stack.toString());

    }


    @Test
    public void remove() throws Exception {
        Stack stack = new Stack();
        pushAll(stack, 0, 1, 2, 3);
        StackUtil.remove(stack, 1);
        Assert.assertEquals("3, 2, 0", stack.toString());
        pushAll(stack, 4, 5, 6, 5);
        Assert.assertEquals("5, 6, 5, 4, 3, 2, 0", stack.toString());
        StackUtil.remove(stack, 5);
        Assert.assertEquals("6, 4, 3, 2, 0", stack.toString());
        pushAll(stack, null, 7, null);
        Assert.assertEquals("null, 7, null, 6, 4, 3, 2, 0", stack.toString());
        StackUtil.remove(stack, null);
        Assert.assertEquals("7, 6, 4, 3, 2, 0", stack.toString());


    }

    @Test(expected = RuntimeException.class)
    public void getTop() throws Exception {
        Stack stack = new Stack();
        pushAll(stack, 1, 2, 3, 4, 5);
        Assert.assertArrayEquals(new Object[]{5, 4, 3}, StackUtil.getTop(stack, 3));
        Assert.assertArrayEquals(new Object[]{5}, StackUtil.getTop(stack, 1));
        Assert.assertArrayEquals(new Object[]{5, 4, 3, 2, 1}, StackUtil.getTop(stack, 5));
        //异常
        StackUtil.getTop(stack, 6);
    }

    @Test
    public void isValidPairs() throws Exception {
        Assert.assertTrue(StackUtil.isValidPairs("()[]{}([])"));
        Assert.assertTrue(StackUtil.isValidPairs("([e{d}f])"));
        Assert.assertFalse(StackUtil.isValidPairs("([b{x]y})"));
        Assert.assertFalse(StackUtil.isValidPairs("((a)[]{b(c)}"));
    }

    private void pushAll(Stack s, Object... objects) {
        for (Object object : objects) {
            s.push(object);
        }
    }

}