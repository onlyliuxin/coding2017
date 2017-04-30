package com.github.miniyk2012.coding2017.basic.stack;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertArrayEquals;

/** 
* StackUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>Apr 6, 2017</pre> 
* @version 1.0 
*/
public class StackUtilTest {

    private StackUtil sk;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReverse() {
        sk = new StackUtil();
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        sk.reverse(stack);
        assertEquals(1, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(3, stack.pop());
    }
    @Test
    public void testRemove() {
        sk = new StackUtil();
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(2);
        stack.push(5);
        sk.remove(stack, 2);
        assertEquals(5, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }
    @Test
    public void testGetTop() {
        sk = new StackUtil();
        Stack stack = new Stack();

        Object[] array = sk.getTop(stack, 3);
        assertArrayEquals(array, new Object[0]);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(2);
        stack.push(5);
        array = sk.getTop(stack, 3);
        assertArrayEquals(array, new Object[] {5, 2, 3});
        array = sk.getTop(stack, 6);
        assertArrayEquals(new Object[] {5, 2, 3, 2, 1}, array);
        array = sk.getTop(stack, -1);
        assertNull(array);
    }
    @Test
    public void testIsValidPairs() {
        sk = new StackUtil();
        String expr = "";
        assertEquals(true, sk.isValidPairs(expr));
        expr = "{xx[])yyza]}";
        assertEquals(false, sk.isValidPairs(expr));
        expr = "asd{[(asds)]sx}";
        assertEquals(true, sk.isValidPairs(expr));
    }

}
