package week1.com.coding.Test;

import org.junit.Before;
import org.junit.Test;

import week1.com.coding.basic.Stack;

public class StackTest
{
    Stack stack = null;
    
    @Before
    public void testPush()
    {
        stack = new Stack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
    }
    
    @Test
    public void testPop()
    {
        while (stack.size()>1)
        {
            System.out.println(stack.pop());
        }
    }
}
