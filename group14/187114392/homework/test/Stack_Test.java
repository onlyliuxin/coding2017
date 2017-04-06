
import org.junit.Test;
import com.coding.basic.*;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;

/**
 * Stack_Test Tester.
 *
 * @author <Authors name>
 * @since <pre>bshu 26, 2017</pre>
 * @version 1.0
 */
public class Stack_Test {
    @Test
    public void pop() {
        Stack stk = new Stack();
        stk.push("one");
        stk.push("two");
        assertEquals(stk.pop(),"two");
    }

    @Test
    public void pop_empty() {
        Stack stk = new Stack();
        try {
            stk.pop();
        }
        catch (EmptyStackException e) {
            assertEquals(e.toString(),"java.util.EmptyStackException");
        }
    }

    @Test
    public void push() {
        Stack stk = new Stack();
        stk.push("one");
        stk.push("two");
        assertEquals(stk.peek(),"two");
    }

    @Test
    public void peek() {
        Stack stk = new Stack();
        stk.push("one");
        stk.push("two");
        stk.push("three");
        assertEquals(stk.peek(),"three");
    }

    @Test
    public void peek_empty() {
        Stack stk = new Stack();
        try {
            stk.peek();
        }
        catch (EmptyStackException e) {
            assertEquals(e.toString(),"java.util.EmptyStackException");
        }
    }

    @Test
    public void isempty() {
        Stack stk = new Stack();
        stk.push("one");
        assertEquals(stk.isEmpty(),false);
        stk.pop();
        assertEquals(stk.isEmpty(),true);
    }
}
