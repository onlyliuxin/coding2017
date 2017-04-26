package stack;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Stack;

/**
 * @author nvarchar
 *         date 2017/4/25
 */
@RunWith(JUnit4.class)
public class StackUtilTest {

    @Test
    public void testReverse() {
        Stack s = new Stack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        Assert.assertEquals("[1, 2, 3, 4, 5]", s.toString());
        StackUtil.reverse(s);
        Assert.assertEquals("[5, 4, 3, 2, 1]", s.toString());
    }

    @Test
    public void testRemove() {
        Stack s = new Stack();
        s.push(1);
        s.push(2);
        s.push(3);
        StackUtil.remove(s, 2);
        Assert.assertEquals("[1, 3]", s.toString());
    }

    @Test
    public void testGetTop() {
        Stack s = new Stack();
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
