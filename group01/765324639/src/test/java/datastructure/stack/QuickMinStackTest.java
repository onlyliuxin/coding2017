package datastructure.stack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QuickMinStackTest {

    @Test
    public void test() {
        QuickMinStack stack = new QuickMinStack();
        stack.push(5);
        stack.push(-5);
        stack.push(50);
        stack.push(23);
        stack.push(0);
        stack.push(100);
        
        assertEquals(-5, stack.findMin());
        assertEquals(6, stack.size());
        
        assertEquals(5, stack.pop());
        assertEquals(-5, stack.findMin());
        
        assertEquals(-5, stack.pop());
        assertEquals(0, stack.findMin());
        
        assertEquals(50, stack.pop());
        assertEquals(0, stack.findMin());
        
        assertEquals(23, stack.pop());
        assertEquals(0, stack.findMin());
        
        assertEquals(0, stack.pop());
        assertEquals(100, stack.findMin());
        
        assertEquals(100, stack.pop());
        
        assertEquals(0, stack.size());
        
    }
}
