package datastructure.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StackUtilTest {

    private Stack stack = new Stack();
    
    @Before
    public void setUp() {
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
    }
    
    @Test
    public void testReverse() {
        StackUtil.reverse(stack);
        Assert.assertEquals(10, stack.size());
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(i, (int)stack.pop());
        }
    }

    @Test
    public void testRemove() {
        StackUtil.remove(stack, 5);
        Assert.assertEquals("0,1,2,3,4,6,7,8,9", stack.toString());
        
        for (int i = 3; i < 5; i++) {
            stack.push(i);
        }
        StackUtil.remove(stack, 3);
        Assert.assertEquals("0,1,2,4,6,7,8,9,4", stack.toString());
        
    }

    @Test
    public void testGetTop() {
        Object[] top = StackUtil.getTop(stack, 5);
        Assert.assertEquals("0,1,2,3,4,5,6,7,8,9", stack.toString());
        Assert.assertEquals(5, top.length);
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(9 - i, top[i]);
        }
    }

    @Test
    public void testIsValidPairs() {
        Assert.assertTrue(StackUtil.isValidPairs("([e{d}f])"));
        Assert.assertFalse(StackUtil.isValidPairs("([b{x]y})"));
        Assert.assertTrue(StackUtil.isValidPairs("ab([e{d}f(ef)])ghj"));
    }

}
