package list;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jiaxun
 */
public class TestStackUtil {

    private Stack stack = null;

    @Before
    public void setUp() {
        stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testReverse() {
        Stack expect = new Stack();
        expect.push(5);
        expect.push(4);
        expect.push(3);
        expect.push(2);
        expect.push(1);
        StackUtil.reverse(stack);
        while (!stack.isEmpty()) {
            Assert.assertEquals(expect.pop(), stack.pop());
        }
    }

    @Test
    public void testRemove() {
        StackUtil.remove(stack, 3);
        Stack expect = new Stack();
        expect.push(1);
        expect.push(2);
        expect.push(4);
        expect.push(5);
        while (!stack.isEmpty()) {
            Assert.assertEquals(expect.pop(), stack.pop());
        }
    }

    @Test
    public void testGetTop() {
        Object[] resultList = StackUtil.getTop(stack, 3);
        Object[] expectList = {5, 4, 3};
        for (int i = 0, len = expectList.length; i < len; i++) {
            Assert.assertEquals(resultList[i], expectList[i]);
        }
        Stack expectStack = new Stack();
        expectStack.push(1);
        expectStack.push(2);
        expectStack.push(3);
        expectStack.push(4);
        expectStack.push(5);
        while (!expectStack.isEmpty()) {
            Assert.assertEquals(stack.pop(), expectStack.pop());
        }
    }

    @Test
    public void testIsValidPairs() {
        Assert.assertTrue(StackUtil.isValidPairs("([e{d}f])"));
        Assert.assertFalse(StackUtil.isValidPairs("([b{x]y})"));
    }

}
