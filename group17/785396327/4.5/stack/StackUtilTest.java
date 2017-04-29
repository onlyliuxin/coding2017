package stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gongxun on 2017/4/12.
 */
public class StackUtilTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * [头元素,...,尾元素]
     */
    @Test
    public void testStack() {
        MyStack<Integer> stack = new MyStack<Integer>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack);
    }

    @Test
    public void testAddToBottom() {
        MyStack<Integer> s = new MyStack();
        s.push(1);
        s.push(2);
        s.push(3);

        StackUtil.addToBottom(s, 0);

        Assert.assertEquals("[3, 2, 1, 0]", s.toString());

    }

    @Test
    public void testReverse() {
        MyStack<Integer> s = new MyStack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        Assert.assertEquals("[5, 4, 3, 2, 1]", s.toString());
        StackUtil.reverse(s);
        Assert.assertEquals("[1, 2, 3, 4, 5]", s.toString());
    }

    @Test
    public void testReverse_247565311() {
        MyStack<Integer> s = new MyStack();
        s.push(1);
        s.push(2);
        s.push(3);

        Assert.assertEquals("[1, 2, 3]", s.toString());
        StackUtil.reverse(s);
        Assert.assertEquals("[3, 2, 1]", s.toString());
    }

    @Test
    public void testRemove() {
        MyStack<Integer> s = new MyStack();
        s.push(1);
        s.push(2);
        s.push(3);
        StackUtil.remove(s, 2);
        Assert.assertEquals("[3, 1]", s.toString());
    }

    @Test
    public void testGetTop() {
        MyStack<Integer> s = new MyStack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        {
            Object[] values = StackUtil.getTop(s, 3);
            Assert.assertEquals(5, values[0]);
            Assert.assertEquals(4, values[1]);
            Assert.assertEquals(3, values[2]);
        }
    }

    @Test
    public void testIsValidPairs() {
        Assert.assertTrue(StackUtil.isValidPairs("([e{d}f])"));
        Assert.assertFalse(StackUtil.isValidPairs("([b{x]y})"));
    }

}
