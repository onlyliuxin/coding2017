package test.com.coding.basic;

import com.coding.basic.Stack;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  Stack Test
 */
public class StackTest {
    @Test
    public void push() throws Exception {
        Stack stack = new Stack();
        stack.push(1);
        assertEquals(1,stack.pop());
    }

    @Test
    public void pop() throws Exception {
        Stack stack = new Stack();
        stack.push(1);
        assertEquals(1,stack.pop());
    }

    @Test
    public void peek() throws Exception {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        assertEquals(5,stack.peek());
    }

    @Test
    public void isEmpty() throws Exception {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        assertEquals(false,stack.isEmpty());
    }

    @Test
    public void size() throws Exception {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        assertEquals(5,stack.size());
    }

}