package test.datastructure.basic;

import datastructure.basic.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.lang.reflect.Field;
import java.util.EmptyStackException;

/**
 * Stack Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 24, 2017</pre>
 */
public class StackTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    private Stack getStack() {
        Stack stack = new Stack();
        for (int i = 1; i <= 5; ++i) {
            stack.push(i);
        }
        return stack;
    }

    private void assertStack(Stack stack, Object[] actual) {
        Class<Stack> clazz = Stack.class;
        ArrayList elementData = null;
        try {
            Field field = clazz.getDeclaredField("elementData");
            field.setAccessible(true);
            elementData = (ArrayList) field.get(stack);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Object[] excepted = null;
        if (elementData != null) {
            int size = stack.size();
            excepted = new Object[size];
            for (int i = 0; i < size; ++i) {
                excepted[i] = elementData.get(i);
            }
        }
        Assert.assertArrayEquals(excepted, actual);
    }

    /**
     * Method: push(Object o)
     */
    @Test
    public void testPush() throws Exception {
//TODO: Test goes here...
        Stack stack = getStack();
        for (int i = 6; i <= 10; ++i) {
            stack.push(i);
        }
        assertStack(stack, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    /**
     * Method: pop()
     */
    @Test
    public void testPop() throws Exception {
//TODO: Test goes here...
        Stack stack = getStack();
        int count = stack.size() + 2;
        Object[] values = new Object[count];
        boolean[] exceptions = new boolean[count];
        for (int i = 0; i < count; ++i) {
            try {
                values[i] = stack.pop();
            } catch (EmptyStackException e) {
                exceptions[i] = true;
            }
        }
        Assert.assertArrayEquals(values, new Object[]{5, 4, 3, 2, 1, null, null});
        Assert.assertArrayEquals(exceptions, new boolean[]{false, false, false, false, false, true, true});
        assertStack(stack, new Object[0]);
    }
} 
